public class LFSRtest {
    public static void shift(int[] arr, int pos) {
        int len = arr.length;
        int most_significant_bit = arr[0];
        int target_tap_pos = len - pos - 1;
        int target_tap_bit = arr[target_tap_pos];
        int feedback_bit = target_tap_bit ^ most_significant_bit; // XOR operation in java (^)
        // Shift left
        for (int i = 0; i < len - 1; i++) {
            arr[i] = arr[i + 1];
        }
        // assign the feedback bit to the least significant bit
        arr[len - 1] = feedback_bit;
    }
    public static boolean arrayequal(int[] exp, int[] test) {
        if (exp.length != test.length) {
            return false;
        } else {
            for (int i = 0; i < exp.length; i++) {
                if(exp[i] != test[i]) {
                    return false;
                }
            }
        }
        return true;
    }
    public static int findcycle() {
        int[] seed = {1, 1, 0, 1, 0};
        int[] expected = {1, 1, 0, 1, 0};
        int cycle = 0;
        for (int i = 0; i < 100; i++) {
            if (arrayequal(expected, seed) && cycle != 0) {
                return cycle;
            } else {
                cycle++ ;
                shift(seed, 2);
            }
        }
        return cycle;
    }

    public static int statistic(int[] arr) {
        int odd = 0;
        int even = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 1) {
                odd++;
            } else {
                even++;
            }
        }
        return (odd - even);
    }

    public static int count() {
        int[] test = {1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1};
        int num = 0;
        for (int i = 0; i < 1000; i++) {
            if (statistic(test) <= 2 && statistic(test) >= -2) {
                num++;
                shift(test, 9);
            } else {
                shift(test, 9);
            }
        }
        return num;
    }
    public static void main(String[] args) {
        System.out.println(findcycle());
        System.out.println(count());
    }
}
