public class RingBuffer {

    private double[] buffer; // container for buffer(queue) elements
    private int head; // keeps track of the head of the queue
    private int tail; // keeps track of the tail of the queue
    private int size; // stores the size of the queue
    private int capacity; // stores the capacity of the array

    // Creates an empty ring buffer with the specified capacity.
    public RingBuffer(int capacity) {
        buffer = new double[capacity];
        head = 0;
        tail = -1;
        size = 0;
        this.capacity = capacity;
    }

    // Returns the capacity of this ring buffer.
    public int capacity() {
        return capacity;
    }

    // Returns the number of items currently in this ring buffer.
    public int size() {
        return size;
    }

    // Is this ring buffer empty (size equals zero)?
    public boolean isEmpty() {
        return size == 0;
    }

    // Is this ring buffer full (size equals capacity)?
    public boolean isFull() {
        return size == capacity;
    }

    // Adds item x to the end of this ring buffer.
    public void enqueue(double x) {
        if (isFull())
            throw new RuntimeException("queue is full");
        tail = (tail + 1) % capacity;
        buffer[tail] = x;
        size++;
    }

    // Deletes and returns the item at the front of this ring buffer.
    public double dequeue() {
        if (isEmpty())
            throw new RuntimeException("queue is empty");

        double x = buffer[head];
        head = (head + 1) % capacity;
        size--;
        return x;
    }

    // Returns the item at the front of this ring buffer.
    public double peek() {
        if (isEmpty())
            throw new RuntimeException("queue is empty");
        return buffer[head];
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(n);

        StdOut.printf("Test #1 - check capacity - should be %d\n", n);
        StdOut.printf("**** Capacity is %d\n", buffer.capacity());

        StdOut.printf("Test #2 - check size - should be %d\n", 0);
        StdOut.printf("**** Size is %d\n", buffer.size());

        for (int i = 1; i <= n; i++) {
            buffer.enqueue(i);
            StdOut.printf("Test #3.%d - check size after %d enqueues- should be %d\n",
                          i, i, i);
            StdOut.printf("**** Size is %d\n", buffer.size());
        }

        double val1 = buffer.peek();
        StdOut.printf("Test #4 - check peek value == %.1f\n", 1.0);
        StdOut.printf("**** Value is %.1f\n", val1);

        double val2 = buffer.dequeue();
        StdOut.printf("Test #5 - dequeue a value, then check value == %.1f and "
                              + "size == %d after a dequeue\n", 1.0, n - 1);
        StdOut.printf("**** Value is %.1f\n", val2);
        StdOut.printf("**** Size is %d\n", buffer.size());

        buffer.enqueue(val2);
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        StdOut.printf("Test #6 - enqueues + dequeues, then check size == %d and"
                              + " peek == %.1f\n",
                      1, (double) (n + 1) * n / 2);
        StdOut.printf("**** Size is %d\n", buffer.size());
        StdOut.printf("**** Peek value is %.1f\n", buffer.peek());
        StdOut.println(buffer.isEmpty());
        StdOut.println(buffer.isFull());
    }
}

