public class Transform2D {
    // Returns a new array object that is an exact copy of the given array.
    // The given array is not mutated.
    public static double[] copy(double[] array) {
        int n = array.length;
        double[] copy = new double[n];
        for (int i = 0; i < n; i++) copy[i] = array[i];
        return copy;
    }

    // Scales the polygon by the factor alpha.
    public static void scale(double[] x, double[] y, double alpha) {
        // assert x.length == y.length;
        int n = x.length;
        for (int i = 0; i < n; i++) {
            x[i] = alpha * x[i];
            y[i] = alpha * y[i];
        }
    }

    // Translates the polygon by (dx, dy).
    public static void translate(double[] x, double[] y, double dx, double dy) {
        int n = x.length;
        for (int i = 0; i < n; i++) {
            x[i] += dx;
            y[i] += dy;
        }
    }

    // Rotates the polygon theta degrees counterclockwise, about the origin.
    public static void rotate(double[] x, double[] y, double theta) {
        int n = x.length;
        double cosTheta = Math.cos(Math.toRadians(theta)),
                sineTheta = Math.sin(Math.toRadians(theta));
        for (int i = 0; i < n; i++) {
            double xVal = x[i] * cosTheta - y[i] * sineTheta,
                    yVal = y[i] * cosTheta + x[i] * sineTheta;
            x[i] = xVal;
            y[i] = yVal;
        }
    }

    // Rotates the polygon theta degrees counterclockwise, about the (px,py).
    private static void rotateAround(double px, double py, double[] x,
                                     double[] y, double theta) {
        int n = x.length;
        double cosTheta = Math.cos(Math.toRadians(theta)),
                sineTheta = Math.sin(Math.toRadians(theta));
        for (int i = 0; i < n; i++) {
            double xVal = px + (x[i] - px) * cosTheta - (y[i] - py) * sineTheta,
                    yVal = py + (y[i] - py) * cosTheta + (x[i] - px) * sineTheta;
            x[i] = xVal;
            y[i] = yVal;
        }
    }


    // shear
    private static void shear(double[] x, double[] y, double xShear, double yShear) {
        int n = x.length;
        for (int i = 0; i < n; i++) {
            double xVal = x[i] + yShear * y[i], // Y-shear
                    yVal = y[i] + xShear * x[i]; // X-Shear
            x[i] = xVal;
            y[i] = yVal;
        }
    }

    // Tests each of the API methods by directly calling them.
    public static void main(String[] args) {

        int xd = 0;
        int[] ko = new int[10];
        xd = ko;
        StdOut.print(xd);
        return;
        // Set the x- and y-scale
        StdDraw.setScale(-5.0, +5.0);

        // Create polygon
        double[] x = { 0, 1, 1, 0 };
        double[] y = { 0, 0, 2, 1 };

        // Draw original polygon in red
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.polygon(x, y);

        double[] xc = copy(x);
        double[] yc = copy(y);
        // rotate polygon 90
        rotate(xc, yc, 90);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(xc, yc);


        double[] xc2 = copy(x);
        double[] yc2 = copy(y);
        // scale polygon 3
        scale(xc2, yc2, 2);
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.polygon(xc2, yc2);

        double[] xc3 = copy(x);
        double[] yc3 = copy(y);
        // translate polygon by -2 in x and 2 in y
        translate(xc3, yc3, -2, 2);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.polygon(xc3, yc3);


        double[] xc4 = copy(x);
        double[] yc4 = copy(y);
        // shear the polygon by factors 2 in x 1 in y
        shear(xc4, yc4, 2, 1);
        StdDraw.setPenColor(StdDraw.CYAN);
        StdDraw.polygon(xc4, yc4);


        double[] xc5 = copy(x);
        double[] yc5 = copy(y);
        // rotate around 60deg around 1,1
        rotateAround(1, 1, xc5, yc5, 60);
        StdDraw.setPenColor(StdDraw.CYAN);
        StdDraw.polygon(xc5, yc5);

    }
}
