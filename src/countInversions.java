/*
Create two algorithms which count the number of inversions in an input sequence
Input: An array A of n integers from 1 to n
Output: An integer corresponding to number of inversions in A
Array A can be reordered/sorted if needed

First algorithm must be O(n*log(n))
Second algorithm must be O(n+k) and will be better when k <= n (i.e. O(n+n) = O(n))

An inversion is a pair of elements in sequence S = [s1,s2...s_n] pair of elements (s_i,s_j) where
i < j and s_i > s_j
ex. S = [2,1,5,3,4] there are 3 inversion pairs, (2,1), (5,3), (5,4)

An array n elements may have as many as n(n-1)/2 inversions

When number of inversions, k, may be any value between 0 n(n-1)/2

Best running time alg. is O(n*log(n)) and a second algorithm O(n+k) exists
which is O(n^2) when k is is O(n^2)
 */


void main() {

    int algo = 2;

    int[] A = {2, 1, 5, 3, 4}; // 3 pairs (2,1), (5,3), (5,4)
    int[] B = {3,2,1,5,7,6,4}; // 7 pairs (3,2) (3,1) (2,1) (5,4) (7,6) (7,4) (6,4)
    int[] C = {1,2,3,4,5,6,7,8,9,10}; // 0 pairs
    int[] D = {10,9,8,7,6,5,4,3,2,1}; // 45 pairs

    if(algo==1) {
        System.out.printf("Algo 1 returns k = %d (Expected = 3) on input array A\n", algo1(A, A.length));
        System.out.printf("Algo 1 returns k = %d (Expected = 7) on input array A\n", algo1(B, B.length));
        System.out.printf("Algo 1 returns k = %d (Expected = 0) on input array A\n", algo1(C, C.length));
        System.out.printf("Algo 1 returns k = %d (Expected = 45) on input array A\n", algo1(D, D.length));
    }
    else {
        System.out.printf("Algo 2 returns k = %d (Expected = 3) on input array A\n", algo2(A, A.length));
        System.out.printf("Algo 2 returns k = %d (Expected = 7) on input array A\n", algo2(B, B.length));
        System.out.printf("Algo 2 returns k = %d (Expected = 0) on input array A\n", algo2(C, C.length));
        System.out.printf("Algo 2 returns k = %d (Expected = 45) on input array A\n", algo2(D, D.length));
    }

}
    int merge(int[] A, int[] L, int[] R, int l, int r) {
    int i = 0;
    int j = 0;
    int k = 0;
    int count = 0;
    while(i < l && j < r) {
        if(L[i] <= R[j]) {
            A[k++] = L[i++];
        }
        else {
            A[k++] = R[j++];
            count +=  (l-i);
        }
    }
    while(i < l) {
        A[k++] = L[i++];
    }
    while(j < r) {
        A[k++] = R[j++];
    }
    return count;
    }


    /*
    Do normal recursive mergeSort algorithm on Array A
    Time complexity is O(nlogn)
    Simply add a counter for the ivnersions
     */
    int algo1 ( int[] A, int n){
        int k = 0;

        if(A.length < 2) {
            return 0;
        }

        int mid = A.length/2;
        int[] L = new int[mid];
        int[] R = new int[n-mid];

        for(int i = 0; i < mid; i++) {
            L[i] = A[i];
        }

        for(int i = mid; i < n; i++) {
            R[i - mid] = A[i];
        }
        k+=algo1(L,mid);
        k+=algo1(R, (n-mid));
        k+=merge(A, L, R, mid, (n-mid));

        return k;
    }


    /*
    Basically insertion sort but count the swaps
    For each insertion or swap of element x leftwards means that the element
    to the left must be greater than x, which means the element left of x and x
    is an inversion pair. As we keep moving x leftwards, we will encounter every inversion pair
    until eventually we meet an element that is not greater than x, we can stop swapping/inserting
    and thus found all pairs


    O(n+k)
     */
    int algo2 ( int[] A, int n){
        int k = 0;

        for(int i = 1; i < n; i++) {
            int q = A[i];
            int j = i - 1;

            while (j >= 0 && A[j] > q) {
                A[j + 1] = A[j];
                j--;
                k++;
            }
            A[j+1] = q;
        }
        return k;
    }
