package search;

public class BinarySearch {
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int n = args.length - 1;
        int[] a = new int[n];
        int i = 1;
        while (i < args.length) {
            a[i - 1] = Integer.parseInt(args[i]);
            i++;
        }
        int l = -1, r = a.length;
        System.out.println(iterSearch(a, x));
        //System.out.println(search(a, l, r, x));
    }

    // l >= -1 && r <= |a|
    // (l == -1 || a[l] > x) && (x >= a[r] r == |a|)
    static int recSearch(int[] a, int l, int r, int x) {
        if (r - l == 1) {
            // r == |a| || r == min: a[r]<=x
            return r;
        }
        int m = (l + r) / 2;
        if (a[m] > x) {
            return recSearch(a, m, r, x);
        } else {
            return recSearch(a, l, m, x);
        }
    }

    // pre: \forall i < j : a[i] >= a[j]
    static int iterSearch(int[] a, int x) {
        int l = -1, r = a.length;

        //pre:
        // -1 <= l < r <= |a|
        // (l == -1 || a[l] > x) && (x >= a[r] || r == |a|)
        while (r - l > 1) {

            //pre: I
            int m = (l + r) / 2;
            //post: I && l < m < r

            // immutable
            if (a[m] > x) {
                // a[m] > x
                l = m;
                // l' = m && r' == r -> r' == r && l' > l -> r'-l' < r-l
            } else {
                // a[m] <= x
                r = m;
                //l' = l && r' = m -> l' = l && r' < r -> r'-l' < r-l
            }
            // I && r'-l' < r-l
        }
        //post:
        // r-l == 1 && I
        //  |
        //  V
        // \forall i < r: a[i] > x && \forall j >= r a[j] <= x
        return r;
    }
    // post: \forall i < R: a[i] > x && \forall j >= R a[j] <= x
}
