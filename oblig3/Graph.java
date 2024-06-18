import java.util.HashMap;
import java.util.PriorityQueue;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Graph {
    int antallNoder;
    HashMap<String, ArrayList<String>> filmer;
    HashMap<String, Actor> skuespillere;
    ArrayList<Edge> kanter = new ArrayList<>();

    public Graph(int antallNoder, HashMap<String, ArrayList<String>> filmer, HashMap<String, Actor> skuespillere) {
        this.antallNoder = antallNoder;
        this.filmer = filmer;
        this.skuespillere = skuespillere;
        
    }

    //Lager en kant med valgt info, og legger den til i kanter-listen hvis den er unik.
    public void leggTilKant(String skue1, String skue2, String film, String rating) {
        Edge nykant = new Edge(skuespillere.get(skue1), skuespillere.get(skue2), Float.parseFloat(rating), film);
        boolean kantfinnes = false;
        //Sjekker om den nye kanten er lik noen av kantene som allerede eksisterer for den gitte noden.
        for(Edge kant : nykant.skue1.kanter) {
            if(kant.sammenlign(nykant) && kant.film == nykant.film) {
                kantfinnes = true;
            }
        }
        //Hvis kanten ikke finnes allerede saa legges den til for begge skuespillerne og i kanter-lista.
        if(!kantfinnes) {
            nykant.skue1.kanter.add(nykant);
            nykant.skue2.kanter.add(nykant);
            kanter.add(nykant);
        }
    }

    //Gjoer et breddefoerst-soek for aa finne korteste vei til en node.
    public void breddefoerst(Actor startnode, Actor sluttnode) {
        ArrayList<Actor> koe = new ArrayList<>();
        ArrayList<Actor> besoekt = new ArrayList<>();
        HashMap<Actor, Edge> forelder = new HashMap<>();
        besoekt.add(startnode);
        koe.add(startnode);
        boolean funnet = false;

        //Fortsetter saa lenge det er flere noder i koen, og sluttnoden ikke er funnet.
        while(!koe.isEmpty() && funnet == false) {
            Actor valgt = koe.remove(0);
            if(valgt == sluttnode) {
                funnet = true;
            }
            if(valgt != null) {
                /*Gaar gjennom alle kantene til valgt node og legger til alle 
                skuespillerene disse kantene gaar til.*/
                for(Edge kant : valgt.kanter) {
                    if(!besoekt.contains(kant.andreSkue(valgt))) {
                        koe.add(kant.andreSkue(valgt));
                        besoekt.add(kant.andreSkue(valgt));
                        forelder.put(kant.andreSkue(valgt), kant);
                    }
                }
            }

        }
        //Printer ut resultatet av soekingen.
        if(funnet) {
            Actor valgtnode = sluttnode;
            String svar = "";
            while(valgtnode != startnode) {
                svar = "===[ " + filmer.get(forelder.get(valgtnode).film).get(0) + " (" + filmer.get(forelder.get(valgtnode).film).get(1) + ")" + " ] ===>  " + valgtnode.navn + "\n" + svar;
                valgtnode = forelder.get(valgtnode).andreSkue(valgtnode);
            }
            svar = valgtnode.navn + "\n" + svar;
            System.out.println(svar);
        } else {
            System.out.println("Ingen sti funnet");
        }
    }

    //Dijkstras algoritme brukes til aa soeke i graf med kant-vekter.
    public void dijkstra(Actor startnode, Actor sluttnode) {
        PriorityQueue<Infoholder> koe = new PriorityQueue<>(new Infoholdercomparator());
        ArrayList<Actor> besoekt = new ArrayList<>();
        HashMap<Actor, Float> distanse = new HashMap<>();
        HashMap<Actor, Edge> forelder = new HashMap<>();
        boolean truffet = false;
        distanse.put(startnode, Float.parseFloat("0"));
        koe.add(new Infoholder(0, startnode));

        while(!koe.isEmpty() && besoekt.size() < skuespillere.size() && !truffet) {
            Actor valgtNode = koe.remove().skue;
            if(valgtNode == sluttnode) {
                truffet = true;
            } else if(!besoekt.contains(valgtNode)) {
                //Legger noden som fjernes fra koen i besoekt hvis den ikke er der allerede.
                besoekt.add(valgtNode);
                for(Edge kant : valgtNode.kanter) {
                    float nyvekt = distanse.get(valgtNode) + kant.vekt;
                    Actor nestenode = kant.andreSkue(valgtNode);
                    //Hvis mappet med distanser ikke har noden enda, putt den inn med maks distanse.
                    if(!distanse.containsKey(nestenode)) {
                        distanse.put(nestenode, Float.MAX_VALUE);
                    }
                    /*Hvis den nye vekten kalkulert for en node er mindre enn den forrige vekten lagret
                    for den spesifikke noden.*/
                    if(nyvekt < distanse.get(nestenode)) {
                        distanse.put(nestenode, nyvekt);
                        forelder.put(nestenode, kant);
                        koe.add(new Infoholder(nyvekt, nestenode));
                    } 
                }
            }
        }
        //Skriver ut rekken med filmer.
        Actor kommetNode = sluttnode;
        String svarstring = "";
        final DecimalFormat df = new DecimalFormat("0.0");
        while(kommetNode != startnode) {
            svarstring = "===[ " + filmer.get(forelder.get(kommetNode).film).get(0) + " (" + filmer.get(forelder.get(kommetNode).film).get(1) + ")" + " ] ===>  " + kommetNode.navn + "\n" + svarstring;
            kommetNode = forelder.get(kommetNode).andreSkue(kommetNode);
        }
        svarstring = kommetNode.navn + "\n" + svarstring + "Total weight: " + df.format(distanse.get(sluttnode)) + "\n";
        System.out.println(svarstring);
    }

    //Soerger for at dfs blir kalt paa alle nodene.
    public void dfsFull() {
        HashMap<Actor, Boolean> besoekt = new HashMap<>();
        HashMap<String, Integer> komponent = new HashMap<>();
        int nyttTall = 0;

        //Fyller hashmap med alle noder
        for(Actor skue : skuespillere.values()){
            besoekt.put(skue, false);
        }

        for(String skue : skuespillere.keySet()) {
            if(!besoekt.get(skuespillere.get(skue))) {
                nyttTall = iterativDSF(skuespillere.get(skue), besoekt);
                if(komponent.containsKey("" + nyttTall)) {
                    komponent.put("" + nyttTall, komponent.get("" + nyttTall) + 1);
                } else {
                    komponent.put("" + nyttTall, 1);
                }
            }
        }

        /*Det blir ekstremt ghetto med telling for 1-størrelse-komponenter, 
        saa vi gjoer noen gnarly moves.*/
        int ant0 = komponent.get("0");
        komponent.remove("0");
        komponent.put("1", ant0); 
        
        for(String storrelse : komponent.keySet()) {
            System.out.println("There are " + komponent.get(storrelse) + " compontents of size " + storrelse);
        }
    }

    //Kalles for aa sjekke en hel komponent
    public int iterativDSF(Actor startnode, HashMap<Actor, Boolean> besoekt) {
        ArrayList<Actor> stack = new ArrayList<>();
        stack.add(startnode);
        int antallNoder = 0;
        while(stack.size() != 0) {
            Actor popped = stack.remove(0);
            for(Edge kant : popped.kanter) {
                if(!besoekt.get(kant.andreSkue(popped))) {
                    stack.add(kant.andreSkue(popped));
                    besoekt.put(kant.andreSkue(popped), true);
                    antallNoder++;
                }
            }
        }
        return antallNoder;
    }
}
