import java.util.Arrays;
class InversionCounter {
    public static long countSwaps(int[] arr) {
        int len = arr.length;
        int[] brr = new int[len];
        return swapCounter(arr, brr, 0, len - 1);
    }
    public static long swapCounter(int[] arr, int[] brr, int start, int end) {
        if (start >= end) {
            return 0;
        } else {
            long count = 0;
            int middle = start + (end - start) / 2;
            count += swapCounter(arr, brr, start, middle);
            count += swapCounter(arr, brr, middle + 1, end);
            count += mergeAndCount(arr, brr, start, middle, middle + 1, end);
            return count;
        }
    }


    /**
     * Given an input array so that arr[left1] to arr[right1] is sorted and arr[left2] to arr[right2] is sorted
     * (also left2 = right1 + 1), merges the two so that arr[left1] to arr[right2] is sorted, and returns the
     * minimum amount of adjacent swaps needed to do so.
     */
    public static long mergeAndCount(int[] arr, int left1, int right1, int left2, int right2) {
        if (arr == null) {
            return 0;
        }
        if (arr.length == 1) {
            return 0;
        }

        if (arr.length == 2) {
            if (arr[0] < arr[1]) {
                return 0;
            } else {
                return 1;
            }
        }

        int[] left = Arrays.copyOfRange(arr, left1, right1 + 1);

        int[] right = Arrays.copyOfRange(arr, left2, right2 + 1);

        int i = 0;
        int j = 0;
        int k = left1;
        long swapCount = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
                swapCount += right1 + 1 - left1 - i;
            }
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        return swapCount;
    }
}
