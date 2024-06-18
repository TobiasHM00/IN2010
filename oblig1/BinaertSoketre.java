class BinaertSoketre {
    int storrelse;
    Node root;
    boolean lik = false;

    class Node{
        Node hBarn, vBarn, forelder;
        int innhold, hoyde;
        public Node(int i){
            innhold = i;
        }
    }

    public boolean contains(Node node, int tall){
        if(node == null){
            return false;
        } else if(tall < node.innhold) {
            return contains(node.vBarn, tall);
        } else if(tall > node.innhold) {
            return contains(node.hBarn, tall);
        } else {
            return node.innhold == tall;
        }
    }

    public Node insert(Node node, int tall, Node forrige) { 
        if(node == null && root == null) {
            node = new Node(tall);
            storrelse++;
            node.forelder = forrige;
            root = node;
        } else if(node == null && !lik) {   
            node = new Node(tall);
            storrelse++;
            node.forelder = forrige;
        } else if(tall < node.innhold) { 
            node.vBarn = insert(node.vBarn, tall, node);
        } else if(tall > node.innhold){
            node.hBarn = insert(node.hBarn, tall, node);
        } else {
            lik = true;
        }
        //TODO return balanse eller whatever
        lik = false;
        return node;
    }

    public Node remove(Node node, int tall) {
        Node fjernet = node;
        if (fjernet == null) {
            return null;
        } else if (tall < fjernet.innhold) {
            fjernet.vBarn = remove(fjernet.vBarn, tall);
        } else if (tall > fjernet.innhold) {
            fjernet.hBarn = remove(fjernet.hBarn, tall);
        } else if (fjernet.vBarn == null && fjernet.hBarn != null) {
            fjernet.hBarn.forelder = fjernet.forelder;
            if(fjernet.hBarn.forelder == null) {
                root = fjernet.hBarn;
            }
            storrelse--;
            return fjernet.hBarn;
        } else if (fjernet.hBarn == null && fjernet.vBarn != null) {
            fjernet.vBarn.forelder = fjernet.forelder;
            if(fjernet.vBarn.forelder == null) {
                root = fjernet.vBarn;
            }
            storrelse--;
            return fjernet.vBarn;
        } else if(fjernet.vBarn == null && fjernet.hBarn == null) {
            if(fjernet.innhold < fjernet.forelder.innhold){
                fjernet.forelder.vBarn = null;
            } else if(fjernet.innhold > fjernet.forelder.innhold){
                fjernet.forelder.hBarn = null;
            }
            storrelse--;
            return fjernet;
        } else {
            Node minst = FinnMinste(fjernet.hBarn);
            fjernet.innhold = minst.innhold;
            fjernet.hBarn = remove(fjernet.hBarn, minst.innhold);
        }
        return fjernet; 
        //TODO bare lagt til for å slippe error(fjern)
        //TODO: Balansere
    }

    public Node FinnMinste(Node v){
        Node minsth = v;
        while(minsth.vBarn != null) {
            minsth = minsth.vBarn;
        }
        return minsth;      
    }
    
    public int size() {
        return storrelse;
    }
}