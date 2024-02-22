import java.lang.StringBuilder;
public class test {
    public static int find(String str, char c) {
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                index = i;
                break;
            }
            index = -1;
        }
        return index;
    }
    // Sunny 00 Rainy 01 Cloudy 10 Snowy 11

    public static String select (String str) {
        if (find(str,'u') == 1) {
            return "00";
        }
        if (find(str,'a') == 1) {
            return "01";
        }
        if (find(str,'l') == 1) {
            return "10";
        }
        if (find(str,'n') == 1) {
            return "11";
        }
        return "";
    }
    public static String binaryencoder(String str) {
        StringBuilder res = new StringBuilder();
        if (str == null) {
            return null;
        }
        while (find(str,',') != -1) {
            res.append(select(str));
            str = str.substring(find(str,',') + 2);
        }
        res.append(select(str));
        return res.toString();
    }
    public static void main(String[] args) {
        String str = "hello_world";
        String test = "Sunny, Rainy, Cloudy, Snowy";
        // System.out.println(test.substring(find(test, ',') + 2));
        System.out.println(binaryencoder(test));
    }
}
