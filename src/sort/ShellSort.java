package sort;

import java.util.Arrays;

/**
 * @author Spark
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] data = new int[]{56, 78, 45, 65, 35, 11, 94, 12, 36, 78, 61, 43, 14};
        shellSort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void shellSort(int[] data) {
        int span = data.length;
        while (span > 1) {
            span = span / 2;
            for (int i = 0; i < span; i++) {
                for (int j = i + span; j < data.length; j = j + span) {
                    int temp = data[j];
                    int index;
                    for (index = j - span; index >= 0 && data[index] > temp; index = index - span) {
                        data[index + span] = data[index];
                    }
                    data[index + span] = temp;
                }
            }
        }
    }
}
