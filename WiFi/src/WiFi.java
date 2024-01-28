import java.util.Arrays;

class WiFi {

    /**
     * Implement your solution here
     */
    public static double computeDistance(int[] houses, int numOfAccessPoints) {
        Arrays.sort(houses);
        int len = houses.length;
        double high = houses[len - 1] - houses[0];
        double low = 0.0;
        double mid = (low + high) / 2;

        while (Math.abs(high - low) > 0.1) {
            if (coverable(houses, numOfAccessPoints, mid)) {
                high = mid;
                mid = (low + high) / 2;
            } else {
                low = mid;
                mid = (low + high) / 2;
            }
        }
        return mid;
    }

    /**
     * Implement your solution here
     */
    public static boolean coverable(int[] houses, int numOfAccessPoints, double distance) {
        Arrays.sort(houses);
        int len = houses.length;
        int count = 1;
        int farPoint = 0;
        for (int i = 0; i < len; i++) {
            if (houses[i] - houses[farPoint] <= distance * 2) {
                continue;
            } else {
                farPoint = i;
                count++;
            }
        }
        return count <= numOfAccessPoints;
    }
}
