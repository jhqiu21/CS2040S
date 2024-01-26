/**
 * Contains static routines for solving the problem of balancing m jobs on p processors
 * with the constraint that each processor can only perform consecutive jobs.
 */
public class LoadBalancing {

    /**
     * Checks if it is possible to assign the specified jobs to the specified number of processors such that no
     * processor's load is higher than the specified query load.
     *
     * @param jobSizes the sizes of the jobs to be performed
     * @param queryLoad the maximum load allowed for any processor
     * @param p the number of processors
     * @return true iff it is possible to assign the jobs to p processors so that no processor has more than queryLoad load.
     */
    public static boolean isFeasibleLoad(int[] jobSizes, int queryLoad, int p) {
        // TODO: Implement this
        // the running time is O(n)
        int len = jobSizes.length;
        // sum up the workload
        int sum = 0;
        // count the number of processor we need to use
        int count = 1;

        if (len == 0) {
            // return false if the array is invalid
            return false;
        }

        for (int i = 0; i < len; i++) {
            if (jobSizes[i] > queryLoad) {
                return false;
            } else if (sum + jobSizes[i] <= queryLoad) {
                sum = sum + jobSizes[i];
            } else {
                sum = jobSizes[i];
                count++;
            }
        }
        // check if the count is greater than p and check if the "final sum" is valid
        if (sum <= queryLoad && count <= p) {
            return true;
        }
        return false;
    }

    /**
     * Returns the minimum achievable load given the specified jobs and number of processors.
     *
     * @param jobSizes the sizes of the jobs to be performed
     * @param p the number of processors
     * @return the maximum load for a job assignment that minimizes the maximum load
     */
    public static int findLoad(int[] jobSizes, int p) {
        // TODO: Implement this
        // TODO: Invalid input not handled
        // use binary search to detect the minimal maximum load and its running time is O(logn).
        // find the max job size and sum of all job size in the job sequence
        if (jobSizes.length <= 0 || p < 0) {
            // check if array is invalid
            return -1;
        }
        int maxSize = 0;
        int total = 0;
        for (int i = 0; i < jobSizes.length; i++) {
            maxSize = Math.max(maxSize, jobSizes[i]);
            total = total + jobSizes[i];
        }
        // determine the scope of binary search
        int low = maxSize;
        int high = total;
        int mid = (low + high) / 2;
        while (low < high) {
            if (isFeasibleLoad(jobSizes, mid, p)) {
                // if mid satisfy the feasible load
                high = mid;
                mid = (low + high) / 2;
            } else {
                low = mid + 1;
                mid = (low + high) / 2;
            }
        }
        return mid;
    }

    // These are some arbitrary testcases.
    public static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, 100, 80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83},
            {7}
    };

    /**
     * Some simple tests for the findLoad routine.
     */
    public static void main(String[] args) {
        for (int p = 1; p < 30; p++) {
            System.out.println("Processors: " + p);
            for (int[] testCase : testCases) {
                System.out.println(findLoad(testCase, p));
            }
        }
    }
}
