/**
 * The Optimization class contains a static routine to find the maximum in an array that changes direction at most once.
 */
public class Optimization {
    /**
     * A set of test cases.
     */
    static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, -100, -80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83}
    };
    /**
     * Returns the maximum item in the specified array of integers which changes direction at most once.
     *
     * @param dataArray an array of integers which changes direction at most once.
     * @return the maximum item in data Array
     */
    public static int searchMax(int[] dataArray) {
        // TODO: Implement this
        // Time Complexity: O(logn)
        int len = dataArray.length;
        int low = 0;
        int high = len - 1;
        int mid = (low + high) / 2;

        // basic case
        if (dataArray == null || len <= 0) {
            // given an invalid input
            return 0;
        } else if (len == 1) {
            return dataArray[0];
        } else if (len == 2) {
            return Math.max(dataArray[0], dataArray[1]);
        }

        // general case
        if (dataArray[0] > dataArray[1]) {
            // if the array decreases first and then increases, the maximal is one of its side value
            return Math.max(dataArray[low], dataArray[high]);
        } else {
            // if the array increases first and then decreases
            // we use binary search to eliminate the scope
            while (low < high) {
                mid = (low + high) / 2;
                if (low == high - 1) {
                    // edge case
                    return Math.max(dataArray[low], dataArray[high]);
                } else if (dataArray[mid] > dataArray[mid - 1] && dataArray[mid] > dataArray[mid + 1]) {
                    return dataArray[mid];
                } else if (dataArray[mid] > dataArray[mid + 1]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return dataArray[low];
        }
    }
    /**
     * A routine to test the searchMax routine.
     */
    public static void main(String[] args) {
        for (int[] testCase : testCases) {
            System.out.println(searchMax(testCase));
        }
    }
}
