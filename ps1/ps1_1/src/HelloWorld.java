public class HelloWorld {
    static int MysteryFunction(int argA, int argB) {
        int c = 1;
        int d = argA;
        int e = argB;
        while (e > 0) {
            if (2 * (e / 2) != e) {
                c = c * d;
            }
            d = d * d;
            e = e / 2;
        }
        return c;
    }

    public static void main(String args[]) {
        int output = MysteryFunction(5, 5);
        System.out.printf("My name is JINHANG.\n");
        System.out.printf("My favourite algorithm is Knuth-Morris-Pratt algorithm.\n");
        System.out.printf("I come from China and used to study Medicine in China. It's so glad to meet you!\n");
        System.out.printf("The answer of Problem 1.a is: " + output + ".\n");
        System.out.printf("The answer of Problem 1.b is: MysteryFunction calculates integer n to the power of n.");
    }
}
