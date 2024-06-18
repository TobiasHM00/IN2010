import java.util.Arrays;

class Merge extends Sorter{

    @Override
    void sort(int[] array, int n) {
        if(leq(n,1)){
            return;
        }
        int indeks = (int) Math.floor(n/2);
        int[] A1 = Arrays.copyOfRange(array, 0, indeks);
        int[] A2 = Arrays.copyOfRange(array, indeks, n);
        sort(A1, A1.length);
        sort(A2, A2.length);
        merge(A1, A2, array);
    }

    int[] merge(int[] A1, int[] A2, int[] array){
        int i = 0;
        int j = 0;
        while(lt(i, A1.length) && lt(j, A2.length)){
            if(leq(A1[i], A2[j])){
                array[i+j] = A1[i];
                i++;
            } else{
                array[i+j] = A2[j];
                j++;
            }
        }
        while(lt(i, A1.length)){
            array[i+j] = A1[i];
            i++;
        }
        while(lt(j, A2.length)){
            array[i+j] = A2[j];
            j++;
        }
        return array;
    }

    @Override
    String algorithmName() {
        return "merge";
    }
} 