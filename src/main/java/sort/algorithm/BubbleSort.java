package sort.algorithm;

/**
 * @author Spark
 */
public class BubbleSort {
    public static void sort(Comparable<Object> a) {

    }

    private static boolean less(Comparable<Object> v, Comparable<Object> w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable<Object>[] a, int i, int j) {
        Comparable<Object> tmp;
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void show(Comparable<Object>[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + " ");
        }
    }

    public static boolean isSorted(Comparable<Object>[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i + 1])) {
                return false;
            }
        }
        return true;
    }
}
