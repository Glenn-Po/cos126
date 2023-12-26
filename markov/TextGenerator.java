public class TextGenerator {

    // Generate text based on the Markov model for a 'kgram''s
    public static void main(String[] args) {

        int order = Integer.parseInt(args[0]);
        int textLength = Integer.parseInt(args[1]);

        String text = StdIn.readAll();

        MarkovModel model = new MarkovModel(text, order);

        String state = "";
        if (order > 0) state = text.substring(0, order);
        StdOut.print(state);
        for (int i = 0; i < textLength - order; i++) {

            char next = model.random(state);
            StdOut.print(next);

            // update the next state in the model considering when
            // k = 0
            if (order > 0) state = state.substring(1) + next;
        }
    }
}
