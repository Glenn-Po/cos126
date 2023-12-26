public class BeadTracker {

    // calculates and prints the distance moved by beads between frames
    private static void printDistances(Blob[] prevBeads, Blob[] nextBeads,
                                       double delta) {
        for (int i = 0; i < prevBeads.length; i++) {
            double minDistance = Double.POSITIVE_INFINITY;
            for (int j = 0; j < nextBeads.length; j++)
                minDistance = Math.min(minDistance,
                                       nextBeads[j].distanceTo(prevBeads[i]));

            if (minDistance <= delta)
                StdOut.printf("%.4f\n", minDistance);
        }
    }

    // calculates and prints distance moved by beads in a video =
    public static void main(String[] args) {

        // minimum 'acceptable' mass that can constitute a blob
        int min = Integer.parseInt(args[0]);

        // luminance threshold to delineate a blob
        double tau = Double.parseDouble(args[1]);

        // maximum 'feasible' distance blob can move between frames
        double delta = Double.parseDouble(args[2]);

        // starting indices of list of picture filenames
        int prev = 3, next = 4;

        Stopwatch timer1 = new Stopwatch();

        // comparing video frame by frame
        int n = 0;
        while (next < args.length) {
            Picture prevPicture = new Picture(args[prev]);
            Picture nextPicture = new Picture(args[next]);
            n = (prevPicture.width() * prevPicture.height()
                    + nextPicture.width() * nextPicture.height()) / 2;
            BeadFinder prevFinder = new BeadFinder(prevPicture, tau);
            BeadFinder nextFinder = new BeadFinder(nextPicture, tau);

            printDistances(nextFinder.getBeads(min),
                           prevFinder.getBeads(min), delta);

            prev = next;
            next++;
        }
        double elapsed1 = timer1.elapsedTime();
        StdOut.printf("%16d  %16d %16d %16.5f\n", n, next - 3, (next - 3) * n, elapsed1);

    }
}
