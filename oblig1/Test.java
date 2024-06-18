import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        BinaertSoketre tre = new BinaertSoketre();
        
        try {
            Scanner sc = new Scanner(new File(args[0]));
            while(sc.hasNextLine()){
                String linje = sc.nextLine();
                String[] biter = linje.strip().split(" ");
                

                if(biter[0].equals("insert")){
                    tre.insert(tre.root, Integer.parseInt(biter[1]), null);
                } else if(biter[0].equals("contains")){
                    System.out.println(tre.contains(tre.root, Integer.parseInt(biter[1])));
                } else if (biter[0].equals("remove")) {
                    tre.remove(tre.root, Integer.parseInt(biter[1]));
                } else if (biter[0].equals("size")){
                    System.out.println(tre.size());
                } 
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Feil filnavn [ERROR]");
        }
    }
}
