public class FindMissingElem {
    public static int find(int[] arr) {
        int high = arr.length;
        int low = 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] > mid + 1) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low + 1;
    }
    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4, 5, 6, 7, 8, 10};
        System.out.println(find(test));
    }
}
