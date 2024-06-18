import java.util.ArrayList;
import java.util.Scanner;

public class Balanserttre {
    
    public static void main(String[] args) {
        ArrayList<Integer> sortertListe = new ArrayList<>();
        String linje = null;
        Scanner leser = new Scanner(System.in);
        System.out.println("Skriv tall i sortert rekkefølge, skriv -1 for aa avslutte.");
        linje = leser.nextLine();

        //Leser inn alt
        while(!linje.equals("-1")) {
            sortertListe.add(Integer.parseInt(linje));
            linje = leser.nextLine();
        }
        leser.close();

        //Kaller balanser
        System.out.println("Balansert tre:");
        balanser(sortertListe);        
    }

    public static void balanser (ArrayList<Integer> liste) {
        int midt = liste.size() / 2;
        ArrayList<Integer> hoyreListe = new ArrayList<>();
        ArrayList<Integer> venstreListe = new ArrayList<>();

        if(liste.size() != 0) {
            System.out.println(liste.get(midt));

            //Legger til alle elementene som er storre enn midten i egen liste.
            for(int i = midt + 1; i < liste.size(); i++) {
                hoyreListe.add(liste.get(i));
            }
            
            //Legger til alle elementer som er mindre enn midten i egen liste
            for(int i = 0; i < midt; i++) {
                venstreListe.add(liste.get(i));
            }

            //Rekursivt kaller balanser paa lista til hoyre og venstre for midten.
            balanser(hoyreListe);
            balanser(venstreListe);
        }
    }
}