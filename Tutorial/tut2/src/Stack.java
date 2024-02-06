public class Stack {
    private int[] stack;
    private int index;

    public Stack(int size) {
        this.stack = new int[size];
        this.index = 0;
    }

    /**
     * All operation is O(1) time complexity
     * */
    public void push(int elem) {
        this.stack[this.index] = elem;
        this.index++;
    }

    public void pop() {
        this.stack[this.index] = 0;
        this.index--;
    }

    public int peak() {
        return this.stack[this.index];
    }
}
