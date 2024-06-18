import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Kattunge {
    public static void main(String[] args) {
        Scanner leser = null;
        try {
            leser = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] - Fil ikke funnet");
        }
        String katt = leser.nextLine();
        
        //"hvis du står fast, kast et hashmap på problemet" -Nicholas Tao
        HashMap<String,String> tre = new HashMap<>();

        String linje = leser.nextLine().strip();
        
        while(!linje.equals("-1")) {
            String[] loev = linje.strip().split(" ");
            for(int i = 1; i < loev.length; i++) {
                tre.put(loev[i], loev[0]);
            }
            linje = leser.nextLine().strip();
        }
        String vei = "";
        vei += katt; 

        while(tre.containsKey(katt)) {
            katt = tre.get(katt);
            vei += " " + katt;
        }
        System.out.println(vei);
        leser.close();
    }
}