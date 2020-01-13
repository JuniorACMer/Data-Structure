package sort;

import java.util.Arrays;

/**
 * @author Spark
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] data = new int[]{56, 78, 45, 65, 35, 11, 94, 12, 36, 78, 61, 43, 14};

        selectSort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void selectSort(int[] data) {
        int length = data.length;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                minIndex = data[minIndex] < data[j] ? minIndex : j;
            }
            data[i] = data[minIndex] ^ data[i];
            data[minIndex] = data[minIndex] ^ data[i];
            data[i] = data[minIndex] ^ data[i];
        }
    }
}
