import java.util.PriorityQueue;
import java.util.Scanner;

public class Balanserttreheap {
    public static void main(String[] args) {
        PriorityQueue<Integer> sortertHeap = new PriorityQueue<>();
        String linje = null;

        Scanner leser = new Scanner(System.in);
        System.out.println("Skriv tall i sortert rekkefølge, skriv -1 for aa avslutte.");
        linje = leser.nextLine();

        //Leser inn alt
        while(!linje.equals("-1")) {
            sortertHeap.offer(Integer.parseInt(linje));
            linje = leser.nextLine();
        }
        leser.close();

        System.out.println("Balansert tre:");
        balanser(sortertHeap);

    }

    public static void balanser(PriorityQueue<Integer> heap) {
        PriorityQueue<Integer> balanseringsHeap = new PriorityQueue<>();
        int midt = heap.size() / 2;
        PriorityQueue<Integer> hoyreHeap = new PriorityQueue<>();
        PriorityQueue<Integer> venstreHeap = new PriorityQueue<>();

        if(heap.size() != 0) {
            int teller = 0;
            while(heap.size() > 0) {
                if (teller < midt) {
                    venstreHeap.offer(heap.poll());
                } else if (teller == midt) {
                    int tall = heap.poll();
                    System.out.println(tall);
                    balanseringsHeap.offer(tall);
                } else {
                    hoyreHeap.offer(heap.poll());
                }
                teller++;
            }

            balanser(hoyreHeap);
            balanser(venstreHeap);
        }
    }
}
