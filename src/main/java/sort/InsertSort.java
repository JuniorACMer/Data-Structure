    package sort;

    import java.util.Arrays;

    /**
     * @author Spark
     */
    public class InsertSort {
        public static void main(String[] args) {
            int[] data = new int[]{56, 78, 45, 65, 35, 11, 94, 12, 36, 78, 61, 43, 14};
            insertSort(data);
            System.out.println(Arrays.toString(data));
        }

        public static void insertSort(int[] data) {
            for (int i = 1; i < data.length; i++) {
                int temporary = data[i];
                int j = i - 1;
                for (; j >= 0 && temporary<data[j]; j--){
                    data[j+1] = data[j];
                }
                data[j+1] = temporary;
            }

        }
    }
