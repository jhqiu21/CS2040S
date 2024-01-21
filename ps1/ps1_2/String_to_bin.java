public class String_to_bin {
    // write a modified version that takes a text string (such as, “TheCowJumpedOverTheMoon”)
    // and converts it to a (long) binary string to use as a seed for a shift register.
    public static String str_to_binstr(String str) {
        // convert a text string to a (long) binary string
        char[] charArray = str.toCharArray(); // convert string to char array
        String result = "";
        for (int i = 0; i < charArray.length; i++) {
            // convert char to binary string and store it in strArray
            result = result + Integer.toBinaryString(charArray[i]);
        }
        return result;
    }
    public static int[] str_to_arr(String str) {
        // convert a (long) binary string to 1-d binary array
        char[] charArray = str.toCharArray();
        int len = charArray.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = charArray[i] - '0';
        }
        return res;
    }
    public static int[][] str_to_binarr(String str) {
        // convert a text string to 2-d binary array (as it may be helpful)
        char[] charArray = str.toCharArray();
        int len = charArray.length;
        String[] strArray = new String[len];
        int[][] int_array = new int[len][];
        for (int i = 0; i < len; i++) {
            strArray[i] = Integer.toBinaryString(charArray[i]);
            int_array[i] = str_to_arr(strArray[i]);
        }
        return int_array;
    }
    public static void main(String[] args) {
        // test_fold
        String str = "TheCowJumpedOverTheMoon";
        int[] test1 = str_to_arr(str_to_binstr(str));
        int[][] test2 = str_to_binarr(str);
        // test str_to_binstr
        System.out.println(str_to_binstr(str));
        // test str_to_arr
        for (int i = 0; i < test1.length; i++) {
            System.out.println(test1[i] + " ");
        }
        // test str_to_binarr
        for (int i = 0; i < test2.length; i++) {
            for(int j = 0; j < test2[i].length; j++) {
                System.out.print(test2[i][j]+" ");
            }
            System.out.println();
        }
    }
}