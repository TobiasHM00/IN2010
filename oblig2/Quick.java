class Quick extends Sorter {
    public int[] quicksort(int[] liste, int minst, int hoyest){
        if(leq(liste.length, 1)){
            return null;
        }
        // her velger algoritmen partisjon
        int pivot = liste[minst + (hoyest-minst)/2]; // denne er litt rar for vi kan få double her
        int i = minst;
        int j = hoyest;
        while(leq(i, j)){
            while(lt(A[i], pivot)){
                i++;
            }
            while(gt(A[j], pivot)){
                j--;
            }
            if(leq(i,j)){
                swap(i, j);
                i++;
                j--;
            }
        }
        if(lt(minst,j)){
            quicksort(liste, minst, j);
        }
        if(lt(i,hoyest)){
            quicksort(liste, i, hoyest);
        }
        return liste;
    }

    //Mellom metode for å sende inn riktige verdier til sorterings algoritmen
    @Override
    public void sort(int[] liste, int n) {
        quicksort(liste, 0, n-1);
    }

    String algorithmName() {
        return "quick";
    }
}