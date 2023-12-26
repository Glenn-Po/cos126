public class Avogadro {

    // calculates avogadro's number
    public static void main(String[] args) {

        // number of meters per pixel
        final double PIXEL_TO_METER = 0.000000175;

        // room temperature in Kelvin
        final int ABS_ROOM_TEMP = 297;

        final double VISCOSITY_OF_WATER = 9.135e-4;

        // radius of the bead in meters
        final double RADIUS_OF_BEAD = 0.5e-6;

        // universal gas constant, r
        final double UNIVERSAL_GAS_CONSTANT = 8.31446;

        // total radial displacement
        double totalRadialDisp = 0;

        // number of radial displacements
        int n = 0;

        // read all values for the radial displacements of the bead
        // and apply the Einstein-Smoluchowski equation
        
        while (!StdIn.isEmpty()) {
            double radialDisplacement = StdIn.readDouble() *
                    PIXEL_TO_METER;

            totalRadialDisp += Math.pow(radialDisplacement, 2);
            n++;
        }


        double variance = totalRadialDisp / (2 * n);

        // Estimate of the Boltzmann constant using the
        // Stokes-Einstein relation
        double boltzmann = (6 * variance * VISCOSITY_OF_WATER * RADIUS_OF_BEAD *
                Math.PI) / ABS_ROOM_TEMP;

        // Estimate of the Avogadro Number
        double avogadro = UNIVERSAL_GAS_CONSTANT / boltzmann;

        StdOut.printf("Boltzmann = %.4e\n", boltzmann);
        StdOut.printf("Avogadro = %.4e\n", avogadro);
    }
}
