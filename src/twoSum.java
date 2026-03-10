void main() {

    int n = 50000;
    int[] A = new int[n];
    Random rand = new Random();
    for (int i = 0; i < n; i++) {
        A[i] = rand.nextInt(100000);
    }
    // int[] A = {1,2,50,3,60,24,1000,12,13,226,220,400,199,11,100,67,0,79,5,555,123,4}; //

    long start = System.nanoTime();
    boolean bool = bruteForce(A, A.length);
    long stop = System.nanoTime();
    System.out.printf("Bruteforce algorithm returns %b and took %d ns\n", bool, stop - start);

    start = System.nanoTime();
    bool = sortScan(A, A.length);
    stop = System.nanoTime();
    System.out.printf("Sort and scan algorithm returns %b and took %d ns\n", bool, stop - start);


    start = System.nanoTime();
    bool = hashAlgo(A, A.length);
    stop = System.nanoTime();
    System.out.printf("Hashmap algorithm returns %b and took %d ns\n", bool, stop - start);


}

void mergeSort(int[] A, int n) {

    if (n < 2) {
        return;
    }

    int mid = n / 2;
    int[] l = new int[mid];
    int[] r = new int[n - mid];

    for (int i = 0; i < mid; i++) {
        l[i] = A[i];
    }

    for (int i = mid; i < n; i++) {
        r[i - mid] = A[i];
    }

    mergeSort(l, mid);
    mergeSort(r, n - mid);
    merge(A, l, r, mid, n - mid);
}

void merge(int[] A, int[] l, int[] r, int leftIndex, int rightIndex) {
    int i = 0;
    int j = 0;
    int k = 0;

    while (i < leftIndex && j < rightIndex) {
        if (l[i] <= r[j]) {
            A[k++] = l[i++];
        } else {
            A[k++] = r[j++];
        }
    }

    while (i < leftIndex) {
        A[k++] = l[i++];
    }

    while (j < rightIndex) {
        A[k++] = r[j++];
    }
}

boolean bruteForce(int[] A, int n) {

    for (int i = 0; i < n; i++) {

        for (int j = 0; j < n; j++) {

            if ((A[i] + A[j]) == 225) {
                return true;
            }
        }
    }
    return false;
}


boolean sortScan(int[] A, int n) {

    mergeSort(A, n);
    int i = 0;
    int j = n - 1;

    while (i < j) {
        if ((A[i] + A[j]) == 225) {
            return true;
        } else if ((A[i] + A[j]) > 225) {
            j--;
        } else if ((A[i] + A[j]) < 225) {
            i++;
        }
    }
    return false;
}


boolean hashAlgo(int[] A, int n) {

    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < n; i++) {
        map.put(A[i], i);
    }

    for (int i = 0; i < n; i++) {
        int complement = 225 - A[i];
        if (map.containsKey(complement) && map.get(complement) != i) {
            return true;
        }
    }
    return false;

}
