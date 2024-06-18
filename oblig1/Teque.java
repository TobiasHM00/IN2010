import java.util.ArrayList;

public class Teque {
    Node forste, siste, midten;
    ArrayList<Node> liste = new ArrayList<>();

    class Node{
        Node neste, forrige;
        int innhold;
        public Node(int x){
            innhold = x;
        }
    }

    public void push_back(int x){
        Node nyNode = new Node(x);
        if(siste == null && forste == null){
            forste = nyNode;
            siste = nyNode;
            liste.add(nyNode);
            return;
        }

        siste.neste = nyNode;
        nyNode.forrige = siste;
        siste = nyNode;
        liste.add(nyNode);
    }

    public void push_front(int x){
        Node nyNode = new Node(x);
        if(forste == null && siste == null){
            forste = nyNode;
            siste = nyNode;
            liste.add(nyNode);
            return;
        }
        
        forste.forrige = nyNode;
        nyNode.neste = forste;
        forste = nyNode;
        liste.add(0, nyNode);
    }

    public void push_middle(int x){
        Node nyNode = new Node(x);
        if(forste == null && siste == null){
            forste = nyNode;
            siste = nyNode;
            liste.add(nyNode);
            midten = nyNode;
            return;
        }
        if(midten == null){
            midten = liste.get((int) Math.floor((liste.size()+1)/2));
        }
        
        nyNode.neste = midten;
        nyNode.forrige = midten.forrige;
        midten.forrige = nyNode;
        midten.forrige.neste = nyNode;
        liste.add((int) Math.floor((liste.size()+1)/2), nyNode);
        midten = nyNode;
    }

    public int get(int x){
        return liste.get(x).innhold;
    }
}