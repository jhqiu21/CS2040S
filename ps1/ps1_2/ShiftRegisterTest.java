import static org.junit.Assert.*;

import org.junit.Test;

/**
 * ShiftRegisterTest
 * @author dcsslg
 * Description: set of tests for a shift register implementation
 */
public class ShiftRegisterTest {
    /**
     * Returns a shift register to test.
     * @param size
     * @param tap
     * @return a new shift register
     */
    ILFShiftRegister getRegister(int size, int tap) {
        return new ShiftRegister(size, tap);
    }

    /**
     * Tests shift with simple example.
     */
    @Test
    public void testShift1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.shift());
        }
    }

    /**
     * Tests generate with simple example.
     */
    @Test
    public void testGenerate1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 6, 1, 7, 2, 2, 1, 6, 6, 2, 3 };
        for (int i = 0; i < 10; i++) {
            assertEquals("GenerateTest", expected[i], r.generate(3));
        }
    }

    /**
     * Tests register of length 1.
     */
    @Test
    public void testOneLength() {
        ILFShiftRegister r = getRegister(1, 0);
        int[] seed = { 1 };
        r.setSeed(seed);
        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.generate(3));
        }
    }

    /**
     * Tests with erroneous seed.
     */
    @Test
    public void testError() {
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = { 1, 0, 0, 0, 1, 1, 0 };
        r.setSeed(seed);
        r.shift();
        r.generate(4);
    }

    @Test
    public void test_folder_1() {
        int[] array = new int[] {0, 1, 0, 1, 1, 1, 1, 0, 1};
        ShiftRegister shifter = new ShiftRegister(9, 7);
        shifter.setSeed(array);
        for (int i = 0; i < 10; i++) {
            int j = shifter.shift();
            System.out.print(j);
        }
    }

    @Test
    public void test_folder_2() {
        int[] array = new int[] {0, 1, 0, 1, 1, 1, 1, 0, 1};
        ShiftRegister shifter = new ShiftRegister(9, 7);
        shifter.setSeed(array);
        for (int i = 0; i < 10; i++) {
            int j = shifter.generate(3);
            System.out.println(j);
        }
    }

    @Test
    public void test_folder_3() {
        int[] array = new int[] {1};
        ShiftRegister shifter = new ShiftRegister(1, 0);
        shifter.setSeed(array);
        for (int i = 0; i < 10; i++) {
            int j = shifter.shift();
            System.out.print(j);
        }
    }

    @Test
    public void test_folder_4() {
        int[] array = new int[] {0, 1};
        ShiftRegister shifter = new ShiftRegister(2, 1);
        shifter.setSeed(array);
        for (int i = 0; i < 10; i++) {
            int j = shifter.generate(2);
            System.out.println(j);
        }
    }

    @Test
    public void test_folder_5() {
        int[] array = new int[] {0, 1, 0, 1, 1, 1, 1, 0, 1};
        ShiftRegister shifter = new ShiftRegister(9, 9);
        shifter.setSeed(array);
        for (int i = 0; i < 10; i++) {
            int j = shifter.generate(5);
            System.out.println(j);
        }
    }

    /**
     * Problem 2.b
     * Test under erroneous situation
     * Solution:
     * Using an erroneous seed to test. Because the program will return an error
     * if the seed size is greater than size in the constructor.
     * */

    @Test
    public void test_folder_6() {
        // test with erroneous seeds
        ILFShiftRegister seeds = new ShiftRegister(10, 7);
        int[] testcase = {1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0};
        seeds.setSeed(testcase);
    }

    @Test
    public void test_folder_7() {
        // test tap
        ILFShiftRegister seeds = new ShiftRegister(13, 14);
        int[] testcase = {1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0};
        seeds.setSeed(testcase);
    }


}
