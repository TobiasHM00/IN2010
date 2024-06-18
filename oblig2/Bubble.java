public class Bubble extends Sorter {
    
    @Override
    void sort(int[] array, int n) {
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - i - 1; j++) {
                if(gt(array[j], array[j+1])) {
                    /* int pivot = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = pivot; */
                    swap(j, j+1);
                }
            }
        }
    }

    @Override
    String algorithmName() {
        return "bubble";
    }
}