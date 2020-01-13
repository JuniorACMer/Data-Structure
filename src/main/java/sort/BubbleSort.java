package sort;

import java.util.Arrays;

/**
 * @author Spark
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] data = new int[]{56, 78, 45, 65, 35, 11, 94, 12, 36, 78, 61, 43, 14};
        bubbleSort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void bubbleSort(int[] data) {
        boolean flag = true;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    data[j] = data[j] ^ data[j + 1];
                    data[j + 1] = data[j] ^ data[j + 1];
                    data[j] = data[j] ^ data[j + 1];
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }
}
