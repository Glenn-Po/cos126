public class Sierpinski {
    // Height of an equilateral triangle with the specified side length.
    public static double height(double length) {
        // double hypotenuse = length, tangent = length / 2;
        // double opposite = Math.sqrt(hypotenuse * hypotenuse - tangent * tangent);
        // return opposite;

        return length * Math.sqrt(3) / 2;
    }

    // Draws a filled equilateral triangle with the specified side length
    // whose bottom vertex is (x, y).
    public static void filledTriangle(double x, double y, double length) {
        double h = height(length);
        double[] verticesX = { x - (length / 2), x, x + (length / 2) };
        double[] verticesY = { y + h, y, y + h };
        StdDraw.filledPolygon(verticesX, verticesY);
    }

    // Draws a Sierpinski triangle of order n, such that the largest filled
    // triangle has the specified side length and bottom vertex (x, y).
    public static void sierpinski(int n, double x, double y, double length) {
        double h = height(length);
        if (n == 0)
            return;
        filledTriangle(x, y, length);

        sierpinski(n - 1, x, y + h, length / 2); // top
        sierpinski(n - 1, x - (length / 2), y, length / 2); // left
        sierpinski(n - 1, x + (length / 2), y, length / 2); // right

    }

    // Takes an integer command-line argument n;
    // draws the outline of an upwards equilateral triangle of length 1
    // whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0);
    // and draws a Sierpinski triangle of order n that fits inside the outline.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        // initial coords
        double x = 0.5, y = 0.0, length = 0.5;

        double h = height(length) * 2;

        // outline
        StdDraw.polygon(new double[] { x - length, x, x + length },
                        new double[] { y, y + h, y });


        sierpinski(n, x, y, length);
    }
}
