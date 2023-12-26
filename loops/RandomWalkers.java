public class RandomWalkers {
    // Program to simulate several random walks
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        int totalSquaredDistance = 0;

        // initial position
        for (int i = 0; i < trials; i++) {
            int x = 0, y = 0;
            for (int j = 0; j < n; j++) {
                double random = Math.random();
                if (random < 0.25) // Going North
                    y += 1;
                else if (random < 0.50) // Going East
                    x += 1;
                else if (random < 0.75) // Going South
                    y -= 1;
                else
                    x -= 1; // Going West
            }
            totalSquaredDistance += (x * x + y * y);
        }
        double meanSquaredDistance = 0;
        if (trials > 0)
            meanSquaredDistance = (double) totalSquaredDistance / trials;
        System.out.println("mean squared distance = " + meanSquaredDistance);

    }
}
