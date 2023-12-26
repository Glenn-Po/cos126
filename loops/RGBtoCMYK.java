public class RGBtoCMYK {
    // Program to convert RGB color to CMYK
    public static void main(String[] args) {
        int red = Integer.parseInt(args[0]);
        int green = Integer.parseInt(args[1]);
        int blue = Integer.parseInt(args[2]);

        double white = Math.max(red / 255.0, Math.max(green / 255.0, blue / 255.0));

        double cyan = 0.0, magenta = 0.0, yellow = 0.0, black;

        black = 1.0 - white;

        if (white > 0.0) {
            cyan = (white - red / 255.0) / white;
            magenta = (white - green / 255.0) / white;
            yellow = (white - blue / 255.0) / white;
        }

        System.out.println("red     = " + red);
        System.out.println("green   = " + green);
        System.out.println("blue    = " + blue);
        System.out.println("cyan    = " + cyan);
        System.out.println("magenta = " + magenta);
        System.out.println("yellow  = " + yellow);
        System.out.println("black   = " + black);
    }
}
