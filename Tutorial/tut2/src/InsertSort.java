public class InsertSort {
    public static void insertSort(int[] arr, int tap) {
        if (tap > 0) {
            insertSort(arr, tap - 1);
            int pivot = arr[tap];
            for (int i = 0; i < tap; i++) {
                if (pivot < arr[i]) {
                    for (int j = tap; j > i; j--) {
                        arr[j] = arr[j - 1];
                    }
                    arr[i] = pivot;
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] testArray = {0,2,1,5,3,8,6,9,10};
        insertSort(testArray, 8);
        for (int i = 0; i < testArray.length; i++) {
            System.out.println(testArray[i]);
        }
    }
}
