import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Oppgave1{
    public static void main(String[] args) {
        ArrayList<Integer> liste = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File("input/" + args[0]));
            
            while(sc.hasNext()){
                String linje = sc.nextLine();
                String[] biter = linje.strip().split(" ");
                if(biter[0].equals("insert")){
                    insert(liste, Integer.parseInt(biter[1]));
                } else if(biter[0].equals("size")){
                    System.out.println(size(liste));
                } else if(biter[0].equals("contains")){
                    System.out.println(contains(liste, Integer.parseInt(biter[1])));
                } else if(biter[0].equals("remove")){
                    remove(liste, Integer.parseInt(biter[1]));
                }
            }
            
            sc.close();
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }

    public static void insert(ArrayList<Integer> liste, int tall){
        for(int i = 0; i < size(liste); i++){
            if(liste.get(i) == tall){
                return;
            }
        }
        liste.add(size(liste), tall);
    }

    public static int size(ArrayList<Integer> liste){
        int storrelse = 0;
        for(int x : liste){
            storrelse++;
        }
        return storrelse;
    }

    public static boolean contains(ArrayList<Integer> liste, int tall){
        for(int x : liste){
            if(x == tall){
                return true;
            }
        }
        return false;
    }

    public static void remove(ArrayList<Integer> liste, int tall){
        for(int i = 0; i < size(liste); i++){
            if(liste.get(i) == tall){
                liste.remove(i);
            }
        }
    }
}