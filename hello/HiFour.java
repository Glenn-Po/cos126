public class HiFour {
    // This program takes 4 strings (names) as command
    // line arguments and displays a greetings with the names in reverse
    public static void main(String[] args) {
        String concatenatedNames = args[3] + ", " + args[2] + ", ";
        concatenatedNames += args[1] + ", and " + args[0] + ".";
        System.out.println("Hi " + concatenatedNames);
    }
}
