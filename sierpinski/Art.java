import java.awt.Color;

public class Art {


    // takes a 2 components on one axis and calculates new component along
    // of some proportion from the startß
    private static double getComp(double p, double p2,
                                  int proportion, int size) {
        return p + proportion * (p2 - p) / size;
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

    // Recursively draws a branch with n branches
    private static void drawBranch(double x1, double y1, double x2, double y2,
                                   int nBranches,
                                   int nSubBranches, double fruitSize) {
        double rotationAngle = 30;
        if (nSubBranches == 0) return;

        StdDraw.setPenRadius(nSubBranches * 0.002);

        StdDraw.setPenColor(new Color(159, 129, 112));

        // Give tip branches green color
        if (nSubBranches < 2)
            StdDraw.setPenColor(StdDraw.GREEN);

        // Draw fruits
        StdDraw.line(x1, y1, x2, y2);
        if (StdRandom.uniformDouble() > 0.8) { // 20% probability
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x2, y2, fruitSize);
        }

        for (int i = 1; i <= nBranches; i++) {

            double[] xr = { getComp(x1, x2, i, nBranches), x2 };
            double[] yr = { getComp(y1, y2, i, nBranches), y2 };
            double[] xl = Transform2D.copy(xr);
            double[] yl = Transform2D.copy(yr);


            // right branch
            rotateAround(xr[0], yr[0], xr, yr, rotationAngle);
            drawBranch(xr[0], yr[0], xr[1], yr[1], nBranches,
                       nSubBranches - 1, fruitSize / 2);

            // left branch
            rotateAround(xl[0], yl[0], xl, yl, -rotationAngle);
            drawBranch(xl[0], yl[0], xl[1], yl[1], nBranches,
                       nSubBranches - 1, fruitSize / 2);

        }

    }

    // draws cantor set
    private static void cantorset(double x, double y, double len, int depth) {
        if (depth == 0)
            return;
        // StdDraw.setPenRadius(depth * 4 * 0.002);
        StdDraw.line(x, y, x + len, y);
        cantorset(x, y - 0.05, (len / 3), depth - 1);
        cantorset(x + (2 * len / 3), y - 0.05, len / 3, depth - 1);


    }


    // Draw a tree on a lawn with fruits
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);

        final double BASE_FRUIT_SIZE = 0.05;

        // Draw lawn
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledPolygon(new double[] { 0, 0.2, 0.97, 0.87 },
                              new double[] { 0.05, 0.3, 0.3, 0.05 });

        // Draw fallen fruit
        StdDraw.setPenColor(StdDraw.RED);
        for (int i = 0; i < n; i++) {
            double x = StdRandom.uniformDouble(0.25, 0.8);
            double y = StdRandom.uniformDouble(0.1, 0.25);
            double r = BASE_FRUIT_SIZE;
            if (n > 1) r = StdRandom.uniformDouble(BASE_FRUIT_SIZE / n,
                                                   BASE_FRUIT_SIZE);

            if (StdRandom.uniformDouble() > 0.8)
                StdDraw.filledCircle(x, y, r);
        }

        // Draw tree
        StdDraw.setPenColor(new Color(159, 129, 112));
        drawBranch(0.5, 0.2, 0.5, 0.9, 3, n, BASE_FRUIT_SIZE);

    }
}
