import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestTeque {
    public static void main(String[] args) {
        Teque teque = new Teque();
        try {
            Scanner sc = new Scanner(new File(args[0]));
            
            while(sc.hasNextLine()){
                String linje = sc.nextLine();
                String[] biter = linje.strip().split(" ");
                
                if(biter[0].equals("push_back")){
                    teque.push_back(Integer.parseInt(biter[1]));
                } else if(biter[0].equals("push_front")){
                    teque.push_front(Integer.parseInt(biter[1]));
                } else if(biter[0].equals("push_middle")){
                    teque.push_middle(Integer.parseInt(biter[1]));
                } else if(biter[0].equals("get")){
                    System.out.println(teque.get(Integer.parseInt(biter[1])));
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Feil filnavn [ERROR]");
        }
    }
}