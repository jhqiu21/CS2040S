import java.util.Random;

public class SortingTester {
    public static boolean checkSort(ISort sorter, int size) {
        // TODO: implement this
        if (size <= 0) {
            System.out.println("Invalid array");
            return false;
        } else if (size == 1) {
            System.out.println("Please input a larger size!");
            return true;
        }

        KeyValuePair[] testArray = new KeyValuePair[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            int randKey = r.nextInt(size);
            testArray[i] = new KeyValuePair(randKey, 20);
        }
        sorter.sort(testArray);
        for (int i = 0; i < size - 1; i++) {
            if (testArray[i].compareTo(testArray[i + 1]) == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isStable(ISort sorter, int size) {
        // TODO: implement this
        if (size <= 0) {
            System.out.println("Invalid array");
            return false;
        } else if (size == 1) {
            System.out.println("Please input a larger size!");
            return true;
        }

        KeyValuePair[] testArray = new KeyValuePair[size];
        int mid = size / 2;
        Random rand = new Random();
        // set two element which have same key but different value
        testArray[0] = new KeyValuePair(mid, 1);
        testArray[1] = new KeyValuePair(mid, 2);

        for (int i = 2; i < size; i++) {
            int randKey = rand.nextInt(size);
            // small filter which prevent randKey == mid
            while (randKey == mid) {
                randKey = rand.nextInt(size);
            }
            // make sure other testKey have a different value
            testArray[i] = new KeyValuePair(randKey, 9);
        }
        // sort the array
        sorter.sort(testArray);

        // set up a flag to memorize if we have encountered the first tap == mid
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (flag && testArray[i].getKey() == mid) {
                // we should encounter another tap after we encounter the tap.value == 1
                return true;
            }
            if (testArray[i].getValue() == 1) {
                flag = true;
            }
        }
        return false;
    }

    public static KeyValuePair[] randomArray(int size) {
        // construct a random array
        KeyValuePair[] testArray = new KeyValuePair[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            int randKey = r.nextInt(size);
            testArray[i] = new KeyValuePair(randKey, 20);
        }
        return testArray;
    }

    public static KeyValuePair[] ascendArray(int size) {
        // construct an ascend array
        KeyValuePair[] testArray = new KeyValuePair[size];
        for (int i = 0; i < size; i++) {
            testArray[i] = new KeyValuePair(i, i);
        }
        return testArray;
    }
    
    public static KeyValuePair[] descendArray(int size) {
        // Construct a descend array
        KeyValuePair[] testArray = new KeyValuePair[size];
        for (int i = 0; i < size; i++) {
            testArray[i] = new KeyValuePair(size - i - 1, i);
        }
        return testArray;
    }
    
    public static KeyValuePair[] almostSortArray(int size) {
        // construct an almost sorted array to Distinct Bubble Sort and Insert sort
        KeyValuePair[] testArray = new KeyValuePair[size];
        if (size == 1) {
            testArray[0] = new KeyValuePair(1, 0);
            return testArray;
        }

        for (int i = 0; i < size - 1; i++) {
            testArray[i] = new KeyValuePair(i + 1, i);
        }
        testArray[size - 1] = new KeyValuePair(0, size - 1);
        return testArray;
    }

    public static KeyValuePair[] repeatArray(int size) {
        // construct an array containing exactly an unique key
        KeyValuePair[] testArray = new KeyValuePair[size];
        for (int i = 0; i < size; i++) {
            testArray[i] = new KeyValuePair(size, i);
        }
        return testArray;
    }
    
    public static void testCase(ISort sorter, int size) {
        // test sorter using different testArray and print the result
        if (size <= 0) {
            System.out.println("checkSort: " + checkSort(sorter, size));
        } else {
            KeyValuePair[] randomArray = randomArray(size);
            KeyValuePair[] ascendArray = ascendArray(size);
            KeyValuePair[] descendArray = descendArray(size);
            KeyValuePair[] almostSortArray = almostSortArray(size);
            KeyValuePair[] repeatArray = repeatArray(size);
            System.out.println("checkSort: " + checkSort(sorter, size));
            System.out.println("checkStable: " + isStable(sorter, size));
            System.out.println("Best case: " + sorter.sort(ascendArray));
            System.out.println("Average case: " + sorter.sort(randomArray));
            System.out.println("Worst case: " + sorter.sort(descendArray));
            System.out.println("Almost Sorted Array: " + sorter.sort(almostSortArray));
            System.out.println("Repeat Array: " + sorter.sort(repeatArray));
        }
    }

    public static void main(String[] args) {
        // TODO: implement this
        //Create a Sorter
        ISort sortingObject = new SorterD();
        // Test the Sorter using different size test cases
        testCase(sortingObject, 10);
//        testCase(sortingObject, 50);
//        testCase(sortingObject, 100);
//        testCase(sortingObject, 500);
//        testCase(sortingObject, 1000);
//        testCase(sortingObject, 10000);
//        testCase(sortingObject, 30000);
    }


}
