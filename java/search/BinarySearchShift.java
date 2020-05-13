package search;

public class BinarySearchShift {

    public static void main(String[] args) {
        int n = args.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(args[i]);
        }
        //\exists k: \forall i<k, j>=k:  a[i] > a[j]
        // && \forall i<j<k: a[i] < a[j]
        // && \forall  k <= i < j: a[i] < a[j]
        System.out.println(recFindShift(a, -1, a.length));
        //\forall i<R, j>=R: a[i]>a[j] && \forall i<j<R: a[i]<a[j] && \forall k<=i<j: a[i] < a[j]
    }


    // pre:
    //∃ k: ∀ i<k, j>=k:  a[i] > a[j]
    // ∧ ∀ i<j<k: a[i] < a[j]
    // ∧ ∀  k <= i < j: a[i] < a[j]
    // post: ∀ i<R, j>=R: a[i]>a[j] && ∀ i<j<R: a[i]<a[j] && ∀ k<=i<j: a[i] < a[j]
    static int findShift(int[] a) {
        int l = -1, r = a.length;
        //pre:
        //inv: -1 <= l < r <= |a|
        //     (l==-1 || a[l]>a[|a|-1]) && (r==|a| || a[r]<=a[|a|-1])
        while (r - l > 1) {

            //pre: I
            int m = (l + r) / 2;
            //post: I && l < m < r

            // immutable
            if (a[m] > a[a.length - 1]) {
                // a[m] > a[|a|-1]
                l = m;
                // l' = m && r' == r -> r' == r && l' > l -> r'-l' < r-l && a[l']>a[|a|-1]
            } else {
                // a[m] <= a[|a|-1]
                r = m;
                //l' = l && r' = m -> l' = l && r' < r -> r'-l' < r-l && a[r']<=a[|a|-1]
            }
            // I && r' - l' < r-l
        }
        //post:
        // r-l == 1 && I
        //  |
        //  V
        // \forall i<R, j>=R: a[i]>a[j] && \forall i<j<R: a[i]<a[j] && \forall R<=i<j: a[i] < a[j]
        return r;
    }


    // pre:
    // ∃ k: ∀ l <= i < k, r >= j >= k:  a[i] > a[j]
    // ∧ ∀ l <= i < j < k: a[i] < a[j]
    // ∧ ∀  k <= i < j <= r: a[i] < a[j]

    // post: ∀ l <= i < R, r >= j >= R: a[i]>a[j]
    // ∧ ∀ l <= i < j < R: a[i] < a[j]
    // ∧ ∀ R <= i <  j <= r: a[i] < a[j]

    //inv: -1 <= l < r <= |a|
    //     (l==-1 ∨ a[l] > a[|a|-1]) ∧ (r==|a| ∨ a[r] <= a[|a|-1])
    private static int recFindShift(int[] a, int l, int r) {
        if (r - l > 1) {
            // r - l > 1
            //pre: I
            int m = (l + r) / 2;
            //post: I && l < m < r

            // immutable
            if (a[m] > a[a.length - 1]) {
                // a[m] > a[|a|-1]
                l = m;
                // l' = m ∧ r' == r -> r' == r ∧ l' > l -> r' - l' < r - l ∧ a[l'] > a[|a|-1]
            } else {
                // a[m] <= a[|a|-1]
                r = m;
                //l' = l ∧ r' = m -> l' = l ∧ r' < r -> r'-l' < r-l ∧ a[r']<=a[|a|-1]
            }
            // I ∧ r' - l' < r-l
            return recFindShift(a, l, r);
        } else {
            // r-l == 1 ∧ I
            //
            // ∀ l <= i < R, r >= j >= R: a[i]>a[j]
            // ∧ ∀ l <= i < j < R: a[i] < a[j]
            // ∧ ∀ R <= i <  j <= r: a[i] < a[j]
            return r;
        }
    }
}
