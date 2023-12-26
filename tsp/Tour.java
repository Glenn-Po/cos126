public class Tour {


    // circularly linked list of nodes
    private class Node {
        private Point p;
        private Node  next;

        public Node(Point pt){
            this.p = pt;
            this.next = null;
        }

        // public Point getPoint(){
        //     return p;
        // }
        //
        // public Node getNext(){
        //     return next;
        // }
    }

    private Node start;
    private int size;

    // creates an empty tour
    public        Tour() {
        start = null;
        size  = 0;
    }

    // creates the 4-point tour a->b->c->d->a (for debugging)
    public        Tour(Point a, Point b, Point c, Point d)

    // returns the number of points in this tour
    public    int size() {
        return size;
    }

    // returns the length of this tour
    public double length() {
        Node current = start;
        while(current != current)
    }

    // returns a string representation of this tour
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = start;
        while(start != null){
            sb.append(current.p.toString() + "\n");
            current = current.next;
        }
        return sb.toString();
    }

    // draws this tour to standard drawing
    public   void draw()

    // inserts p using the nearest neighbor heuristic
    public   void insertNearest(Point p)

    // inserts p using the smallest increase heuristic
    public   void insertSmallest(Point p)

    // tests this class by directly calling all constructors and instance methods
    public static void main(String[] args)
}
