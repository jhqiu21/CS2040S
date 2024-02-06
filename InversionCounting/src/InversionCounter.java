class InversionCounter {

    public static long countSwaps(int[] arr) {
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

    /**
     * Given an input array so that arr[left1] to arr[right1] is sorted and arr[left2] to arr[right2] is sorted
     * (also left2 = right1 + 1), merges the two so that arr[left1] to arr[right2] is sorted, and returns the
     * minimum amount of adjacent swaps needed to do so.
     */
    public static long mergeAndCount(int[] arr, int left1, int right1, int left2, int right2) {
        int[] merge = new int[arr.length];
        int countSwap = 0;
        int tap = 0;

        while (left1 <= right1 && left2 <= right2) {
            if (arr[left1] <=  arr[left2]) {
                merge[tap] = arr[left1];
                left1++;
            } else {
                merge[tap] = arr[left2];
                countSwap = countSwap + right1 - left1 + 1;
                left2++;
            }
            tap++;
        }

        while (left2 <= right2) {
            merge[tap] = arr[left2];
            left2++;
            tap++;
        }

        while (left1 <= right1) {
            merge[tap] = arr[left1];
            left1++;
            tap++;
        }

        return countSwap;
    }
}
