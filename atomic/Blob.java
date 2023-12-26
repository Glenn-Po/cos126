public class Blob {
    private int pixelCount; // number of pixels in blob
    private double sumXCooordinates; // sum of x coordinates of pixels
    private double sumYCooordinates; // sum of y coordinates of pixels

    //  creates an empty blob
    public Blob() {
        pixelCount = 0;
        sumXCooordinates = Double.NaN;
        sumYCooordinates = Double.NaN;
    }

    //  adds pixel (x, y) to this blob
    public void add(int x, int y) {
        if (Double.isNaN(sumXCooordinates)) sumXCooordinates = 0;
        if (Double.isNaN(sumYCooordinates)) sumYCooordinates = 0;
        sumXCooordinates += x;
        sumYCooordinates += y;
        pixelCount++;
    }

    //  number of pixels added to this blob
    public int mass() {
        return pixelCount;
    }

    //  Euclidean distance between the center of masses of the two blobs
    public double distanceTo(Blob that) {
        if (that.mass() <= 0 || this.mass() <= 0)
            throw new IllegalArgumentException("cannot calculate distance for" +
                                                       " blob of 0 mass");
        // or return NAN
        double diffXCoordinates = this.sumXCooordinates / this.pixelCount -
                that.sumXCooordinates / that.pixelCount;
        double diffYCoordinates = this.sumYCooordinates / this.pixelCount -
                that.sumYCooordinates / that.pixelCount;

        return Math.hypot(diffXCoordinates, diffYCoordinates);
    }

    //  string representation of this blob
    public String toString() {
        double xCM = this.sumXCooordinates / this.pixelCount;
        double yCM = this.sumYCooordinates / this.pixelCount;
        return String.format("%2d (%8.4f, %8.4f)", this.mass(), xCM, yCM);
    }

    //  tests this class by directly calling all instance methods
    public static void main(String[] args) {
        Blob blob = new Blob();
        blob.add(0, 0);
        blob.add(1, 0);
        StdOut.println(blob.mass());
        Blob blob2 = new Blob();
        blob.add(2, 1);
        blob.add(2, 2);
        blob.add(1, 1);
        StdOut.println(blob.distanceTo(blob2));
        StdOut.println(blob2);
    }
}
