public class Ordered {
    // Program to determine monotonicity of 3-item sequence
    public static void main(String[] args) {
        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);
        int num3 = Integer.parseInt(args[2]);

        boolean isAMotonicSequence = (num1 > num2 && num2 > num3);
        isAMotonicSequence = isAMotonicSequence || (num1 < num2 && num2 < num3);

        System.out.println(isAMotonicSequence);
    }
}
