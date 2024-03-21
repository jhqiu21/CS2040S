class Sorter {
    public static void sortStrings(String[] arr) {
        // TODO: implement your sorting function here
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            while (index >= 1 && isGreaterThan(arr[index - 1], arr[index])) {
                String tmp = arr[index - 1];
                arr[index - 1] = arr[index];
                arr[index] = tmp;
                index--;
            }
        }
    }
    public static boolean isGreaterThan(String str1, String str2) {
        char firstCh1 = str1.charAt(0);
        char firstCh2 = str2.charAt(0);
        if (firstCh1 == firstCh2) {
            char secondCh1 = str1.charAt(1);
            char secondCh2 = str2.charAt(1);
            return secondCh1 > secondCh2;
        } else {
            return firstCh1 > firstCh2;
        }
    }

}
