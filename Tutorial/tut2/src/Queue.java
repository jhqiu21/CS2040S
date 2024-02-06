public class Queue {
    private int[] queue;
    private int head;
    private int tail;
    private int size;

    public Queue(int size) {
        this.size = size;
        this.head = 0;
        this.tail = 0;
        this.queue = new int[size];
    }

    /**
     * All operation is O(1) time complexity
     * */

    public void enqueue(int elem) {
        this.queue[this.tail] = elem;
        this.tail = (this.tail + 1) % this.size;
    }

    public void dequeue() {
        this.head = (this.head + 1) % this.size;
    }

    public int peak() {
        return this.queue[this.head];
    }
}
