public class MergeSort {
    public static void mergeSort(int arr[], int n) {
        for (int size = 1; size < n; size *= 2) {
            // Pick starting point of different subarrays of current size
            for (int start = 0; start < n - 1; start += 2 * size) {
                // Find ending point of left subarray. mid+1 is starting point of right
                int mid = Math.min(start + size - 1, n - 1);
                int end = Math.min(start + 2 * size - 1, n - 1);
                // Merge Subarrays arr[left_start...mid] & arr[mid+1...right_end]
                merge(arr, start, mid, end);
            }
        }
    }

    /* Function to merge the two haves arr[l..m] and
    arr[m+1..r] of array arr[] */
    public static void merge(int arr[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        /* create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /* Copy data to temp arrays L[]
        and R[] */
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }


        /* Merge the temp arrays back into
        arr[l..r]*/
        int i = 0;
        int j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy the remaining elements of
        L[], if there are any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy the remaining elements of
        R[], if there are any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    /* Driver program to test above functions */
    public static void main(String[] args) {
        int arr[] = {12, 11, 13, 5, 6, 7};
        int n = arr.length;
        mergeSort(arr, n);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
