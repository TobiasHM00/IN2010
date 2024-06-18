import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Innleveringsoppgave3 {
    public static void main(String[] args) {
        File skuespillerfil = new File("actors.tsv");
        File filmfil = new File("movies.tsv");

        HashMap<String, Actor> skuespillere = new HashMap<>();
        HashMap<String, ArrayList<String>> filmer = new HashMap<>();
        HashMap<String, ArrayList<String>> kanter = new HashMap<>();        
        String[] linje;

        try {
            Scanner skueleser = new Scanner(skuespillerfil, "UTF-8");
            //Leser alle linjene i actor.tsv og lager skuespillerobjekter med infoen. Dette er nodene.
            while(skueleser.hasNextLine()) {
                linje = skueleser.nextLine().split("\t");
                skuespillere.put(linje[0], new Actor(linje[0],linje[1])); 
                for(int i = 2; i < linje.length; i++) {
                    /*Legger inn alle filmene i et map med alle skuespillerne som hoerer til filmene
                    som verdier, for aa holde styr paa kantene til senere.*/
                    if(kanter.containsKey(linje[i])) {
                        kanter.get(linje[i]).add(linje[0]);
                    } else {
                        kanter.put(linje[i], new ArrayList<String>());
                        kanter.get(linje[i]).add(linje[0]);
                    }
                }
            }
            skueleser.close();

            //Leser inn alle filmene og legger dem i et map.
            Scanner filmleser = new Scanner(filmfil, "UTF-8");
            while(filmleser.hasNextLine()) {
                linje = filmleser.nextLine().split("\t");
                filmer.put(linje[0], new ArrayList<>());
                filmer.get(linje[0]).add(linje[1]);
                filmer.get(linje[0]).add(linje[2]);
            }
            filmleser.close();
        } catch (FileNotFoundException e) {
            System.out.println("Feil i filinnlesning");
        }

        Graph graf = new Graph(skuespillere.size(), filmer, skuespillere);

        //Trippel noested for-loop. De kaller meg ikke kjoeretid Jonas av ingen grunn. O(n^3) goes brrr.
        for(String kantkode : kanter.keySet()) {
            if(filmer.containsKey(kantkode)) {
                for(int i = 0; i < kanter.get(kantkode).size(); i++) {
                    for(int j = i + 1; j < kanter.get(kantkode).size(); j++) {
                        graf.leggTilKant(kanter.get(kantkode).get(i), kanter.get(kantkode).get(j), kantkode, filmer.get(kantkode).get(1));
                    }
                }       
            }
        }
        System.out.println("Oppgave 1:\n");
        System.out.println("Noder: " + skuespillere.size());
        System.out.println("Kanter: " + graf.kanter.size());

        System.out.println("\nOppgave 2:\n");
        graf.breddefoerst(skuespillere.get("nm2255973"), skuespillere.get("nm0000460"));
        graf.breddefoerst(skuespillere.get("nm0424060"), skuespillere.get("nm0000243"));
        graf.breddefoerst(skuespillere.get("nm4689420"), skuespillere.get("nm0000365"));
        graf.breddefoerst(skuespillere.get("nm0000288"), skuespillere.get("nm0001401"));
        graf.breddefoerst(skuespillere.get("nm0031483"), skuespillere.get("nm0931324"));

        System.out.println("\nOppgave 3:\n");
        graf.dijkstra(skuespillere.get("nm2255973"), skuespillere.get("nm0000460"));
        graf.dijkstra(skuespillere.get("nm0424060"), skuespillere.get("nm0000243"));
        graf.dijkstra(skuespillere.get("nm4689420"), skuespillere.get("nm0000365"));
        graf.dijkstra(skuespillere.get("nm0000288"), skuespillere.get("nm0001401"));
        graf.dijkstra(skuespillere.get("nm0031483"), skuespillere.get("nm0931324"));

        System.out.println("Oppgave 4:\n");
        graf.dfsFull();
    }
}