class Insertion extends Sorter {
    @Override
    void sort(int[] array, int n) {
        for(int i = 1; i < n; i++){
            int flyttes = array[i];
            int j = i-1;
            while(geq(j, 0) && gt(array[j],flyttes)){
                swap(j,j+1);
                j = j-1;
            }
            array[j+1] = flyttes;
        }
    }

    @Override
    String algorithmName() {
        return "insertion";
    }
}