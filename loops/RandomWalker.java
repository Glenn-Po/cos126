public class RandomWalker {
    // Program to simulate a singular random walk
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        // initial position
        int x = 0, y = 0;
        System.out.println("(" + x + ", " + y + ")");
        for (int i = 0; i < n; i++) {
            double random = Math.random();
            if (random < 0.25) // Going North
                y += 1;
            else if (random < 0.50) // Going East
                x += 1;
            else if (random < 0.75) // Going South
                y -= 1;
            else
                x -= 1;
            System.out.println("(" + x + ", " + y + ")");
        }

        int distanceSquared = x * x + y * y;

        System.out.println("squared distance = " + distanceSquared);

    }
}
