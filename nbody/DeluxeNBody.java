public class DeluxeNBody {
    public static void main(String[] args) {
        double TOTAL_TIME = Double.parseDouble(args[0]);
        double DELTA_TIME = Double.parseDouble(args[1]);


        double G = 6.67E-11; // Nm^2kg^-2

        int BODY_COUNT = StdIn.readInt();
        double UNIVERSE_RADIUS = StdIn.readDouble();

        int FRAME_RATE = 20;

        double SCALE_BOUND = UNIVERSE_RADIUS;
        double SCALE_FACTOR = ((UNIVERSE_RADIUS * 7 / 40) - (UNIVERSE_RADIUS * 1 / 40)) / (2
                * SCALE_BOUND);
        double SCALE_SHIFT = UNIVERSE_RADIUS * 4 / 40;

        // positions of bodies
        double[] px = new double[BODY_COUNT];
        double[] py = new double[BODY_COUNT];
        double[] pz = new double[BODY_COUNT];
        // velocity of bodies
        double[] vx = new double[BODY_COUNT];
        double[] vy = new double[BODY_COUNT];
        double[] vz = new double[BODY_COUNT];
        // masses of bodies
        double[] mass = new double[BODY_COUNT];
        // images of bodies
        String[] imgPath = new String[BODY_COUNT];

        // force on each body at time t
        double[] fx = new double[BODY_COUNT];
        double[] fy = new double[BODY_COUNT];
        double[] fz = new double[BODY_COUNT];

        for (int i = 0; i < BODY_COUNT; i++) {
            // read x and y coordinates
            px[i] = StdIn.readDouble();
            py[i] = StdIn.readDouble();
            pz[i] = StdIn.readDouble();
            // read x and y components of velocity
            vx[i] = StdIn.readDouble();
            vy[i] = StdIn.readDouble();
            vz[i] = StdIn.readDouble();
            // read body mass
            mass[i] = StdIn.readDouble();
            // read image filenames
            imgPath[i] = StdIn.readString();

        }


        // initialize
        StdDraw.setXscale(-UNIVERSE_RADIUS, UNIVERSE_RADIUS);
        StdDraw.setYscale(-UNIVERSE_RADIUS, UNIVERSE_RADIUS);

        StdDraw.enableDoubleBuffering();
        // StdAudio.playInBackground("./2001.wav");

        double time = 0.0;

        while (time < TOTAL_TIME) {
            StdDraw.picture(0, 0, "./starfield.jpg");

            // calculate net forces
            for (int i = 0; i < BODY_COUNT; i++) {
                for (int j = 0; j < BODY_COUNT; j++) {
                    if (i == j) continue;
                    double dx = px[j] - px[i];
                    double dy = py[j] - py[i];
                    double dz = pz[j] - pz[i];
                    double r = Math.sqrt(dx * dx + dy * dy + dz * dz);
                    double f = (G * mass[i] * mass[j]) / (r * r);

                    fx[i] += f * (dx / r);
                    fy[i] += f * (dy / r);
                    fz[i] += f * (dz / r);
                }
            }

            // update positions and velocities
            for (int i = 0; i < BODY_COUNT; i++) {
                double ax = fx[i] / mass[i];
                double ay = fy[i] / mass[i];
                double az = fz[i] / mass[i];

                vx[i] += ax * DELTA_TIME;
                vy[i] += ay * DELTA_TIME;
                vz[i] += az * DELTA_TIME;


                px[i] += vx[i] * DELTA_TIME;
                py[i] += vy[i] * DELTA_TIME;
                pz[i] += vz[i] * DELTA_TIME;
                StdOut.printf("[%d]: x: %e, y: %e, z: %e  \n ", i, px[i], py[i], pz[i]);

            }

            // draw the universe to screen

            for (int i = 0; i < BODY_COUNT; i++) {
                double scale = SCALE_FACTOR * pz[i] + SCALE_SHIFT;
                // if (pz[i] < -UNIVERSE_RADIUS)
                //     scale = UNIVERSE_RADIUS * 1 / 40;
                // if (pz[i] > UNIVERSE_RADIUS)
                //     scale = UNIVERSE_RADIUS * 7 / 40;
                // scale = UNIVERSE_RADIUS * 4 / 40;
                StdDraw.picture(px[i], py[i], imgPath[i], scale, scale);
            }
            StdOut.println();

            for (int i = 0; i < BODY_COUNT; i++) {
                fx[i] = 0;
                fy[i] = 0;
                fz[i] = 0;
            }
            time += DELTA_TIME;
            // display and pause for 20 ms
            StdDraw.show();  // double buffer is enabled
            StdDraw.pause(FRAME_RATE);
        }

        // print universe
        StdOut.printf("%d\n", BODY_COUNT);
        StdOut.printf("%.2e\n", UNIVERSE_RADIUS);
        for (int i = 0; i < BODY_COUNT; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          px[i], py[i], vx[i], vy[i], mass[i], imgPath[i]);
        }
        StdOut.println();


    }
}
