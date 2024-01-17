///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////

/**
 * class ShiftRegister
 * @author
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    ///////////////////////////////////
    // Create your class variables here
    ///////////////////////////////////
    // TODO:
    int[] arr;
    // create array size
    int len;
    // create tap_bit position
    int pos;

    ///////////////////////////////////
    // Create your constructor here:
    ///////////////////////////////////
    ShiftRegister(int size, int tap) {
        // TODO:
        arr = new int[size];
        // initialize length
        len = size;
        // ensure that tap position should be between 0 and size - 1
        if (tap < 0 || tap > size -1) {
            System.out.println("Error! There is an invalid tap!");
        } else {
            // initialize the tap position
            pos = tap;
        }
    }

    ///////////////////////////////////
    // Create your class methods here:
    ///////////////////////////////////
    /**
     * setSeed
     * @param seed
     * Description:
     */
    @Override
    public void setSeed(int[] seed) {
        // TODO:
        // Ensure seed array is valid
        if (len != seed.length) {
            System.out.println("Error! Seed length is invalid!");
        } else {
            // reverse and copy the seed
            for (int i = 0; i < len; i++) {
                if (seed[i] != 0 && seed[i] != 1) {
                    //Ensure that every bit in arr is valid!
                    System.out.println("Error! There is an invalid bit!");
                    return;
                } else {
                    arr[i] = seed[len - 1 - i];
                }
            }
        }
    }

    /**
     * shift
     * @return
     * Description:
     */
    @Override
    public int shift() {
        // TODO:
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
        return feedback_bit;
    }

    /**
     * generate
     * @param k
     * @return
     * Description:
     */
    @Override
    public int generate(int k) {
        // TODO:
        // create an array to store the result
        int[] result = new int[k];

        for(int i = k - 1; i >= 0; i--) {
            result[i] = shift();
        }

        return toDecimal(result);
    }

    /**
     * Returns the integer representation for a binary int array.
     * @param array
     * @return
     */
    private int toDecimal(int[] array) {
        // TODO:
        // convert binary to decimal
        int ans = 0;
        int base = 2;

        for (int i = 0; i < array.length; i++) {
            // calculate power n first
            int factor = 1;
            for (int j = 0; j < i; j++) {
                factor = factor * base;
            }
            ans = ans + array[i] * factor;
        }
        return ans;
    }
}