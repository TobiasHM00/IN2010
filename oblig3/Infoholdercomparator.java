import java.util.Comparator;

//Sammenligner to infoholdere slik at priority queue kan brukes.
public class Infoholdercomparator implements Comparator<Infoholder>{
    @Override
    public int compare(Infoholder o1, Infoholder o2) {
        if(o1.distanse > o2.distanse) {
            return 1;
        } else if(o1.distanse < o2.distanse) {
            return -1;
        }
        return 0;
    }
}
