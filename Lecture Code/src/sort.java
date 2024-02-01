import java.util.Arrays;

public class sort {

    public static int bubbleSort(int[] arr) {
        // space complexity: O(1)
        int countSwap = 0;
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            // Set a flag, if true, that means the loop has not been swapped,
            // that is, the sequence has been ordered, the sorting has been completed.
            boolean flag = true;
            for (int i = 0; i < arr.length - 1 - low; i++) {
                if (arr[i] > arr[i + 1]) {
                    int tmp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = tmp;
                    flag = false; // set the flag false, imply that the array is not sorted
                    countSwap++;
                }
            }
            if (flag) {
                break;
            }
            low++;
        }
        return countSwap;
    }

    public static int selectSort(int[] arr) {
        // space complexity: O(1)
        int countSwap = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = minIndex + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
                countSwap++;
            }
        }
        return countSwap;
    }

    public static int insertSort(int[] arr) {
        // space complexity: O(1)
        // time complexity: Best-O(n) Worst-O(n^2)
        int countSwap = 0;
        for (int i = 1; i < arr.length; i++) {
            int preIndex = i - 1;
            int cur = arr[i];
            while (preIndex >= 0 && cur < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
                countSwap++;
            }
            arr[preIndex + 1] = cur;
        }
        return countSwap;
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[0];
        int pointer = low;
        for (int i = low; i < 1; i++) {
            if (arr[i] <= pivot) {
                int tmp = arr[i];
                arr[i] = arr[pointer];
                arr[pointer] = tmp;
                pointer++;
            }
        }
        int tmp = arr[pointer];
        arr[pointer] = arr[high];
        arr[high] = tmp;
        return pointer;
    }

    public static void quickSort(int [] array, int low, int high) {
        if (low < high) {
            int position = partition(array, low, high);
            quickSort(array, low, position - 1);
            quickSort(array, position + 1, high);
        }
    }



    public static void main(String[] args) {
        int[] arr = {7, 11, 14, 6, 9, 4, 3, 12};
//      System.out.println(bubbleSort(arr));
//      System.out.println(selectSort(arr));
//      System.out.println(insertSort(arr));
        quickSort(arr, 0, 7);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }
}
