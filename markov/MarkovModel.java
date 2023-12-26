public class MarkovModel {

    private static final int CHAR_SET = 128;

    // stores how many times a kgram shows up
    private ST<String, Integer> frequency;

    // stores how many times a kgram is followed by a certain character
    private ST<String, int[]> table;

    private int order; // length of kgrams


    // creates a Markov model of order k based on the specified text
    public MarkovModel(String text, int k) {

        // copy first k characters to simulate a circular string
        if (k > 0) text += text.substring(0, k);

        order = k;

        // Storing frequencies of appearance of krams in a Symbol Table
        frequency = new ST<String, Integer>();
        for (int i = 0; i + order < text.length(); i++) {
            String kgram = text.substring(i, i + order);
            if (!frequency.contains(kgram)) {
                frequency.put(kgram, 1); // first time, count is 1
                continue;
            }
            int count = frequency.get(kgram) + 1;
            frequency.put(kgram, count);
        }

        // frequency table for the number of times characters are
        // following a kgram
        table = new ST<String, int[]>();
        for (int i = 0; i + order < text.length(); i++) {
            String kgram = text.substring(i, i + order);
            if (!table.contains(kgram))
                table.put(kgram, new int[CHAR_SET]);
            char next = text.charAt(i + order);
            // update the number of times 'next' follows 'kgram'
            table.get(kgram)[next]++; //
        }

    }

    // returns the order of the model (also known as k)
    public int order() {
        return order;
    }

    // returns a String representation of the model (more info below)
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String key : table) {
            sb.append(key + ": ");

            for (int i = 0; i < CHAR_SET; i++) {
                if (table.get(key)[i] > 0)
                    sb.append((char) i + " " + table.get(key)[i] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // returns the # of times 'kgram' appeared in the input text
    public int freq(String kgram) {
        if (kgram.length() != order)
            throw new IllegalArgumentException("kgram must be of length "
                                                       + order);
        if (frequency.contains(kgram))
            return frequency.get(kgram);

        return 0;
    }

    // returns the # of times 'c' followed 'kgram' in the input text
    public int freq(String kgram, char c) {
        if (kgram.length() != order)
            throw new IllegalArgumentException("kgram must be of length "
                                                       + order);
        if (table.contains(kgram))
            return table.get(kgram)[c];

        return 0;
    }

    // returns a random character, chosen with weight proportional to the
    // number of times each character followed 'kgram' in the input text
    public char random(String kgram) {
        if (kgram.length() != order)
            throw new IllegalArgumentException("kgram must be of length " +
                                                       order);
        if (freq(kgram) == 0)
            throw new IllegalArgumentException(kgram + "does not feature in text");

        int[] frequencies = new int[CHAR_SET];
        for (int i = 0; i < CHAR_SET; i++)
            frequencies[i] = freq(kgram, (char) i);

        return (char) StdRandom.discrete(frequencies);

    }

    // tests all instance methods to make sure they're working as expected
    public static void main(String[] args) {
        String text1 = "banana";
        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println("Order = " + model1.order());
        StdOut.println("freq(\"an\", 'a')    = " + model1.freq("an", 'a'));
        StdOut.println("freq(\"na\", 'b')    = " + model1.freq("na", 'b'));
        StdOut.println("freq(\"na\", 'a')    = " + model1.freq("na", 'a'));
        StdOut.println("freq(\"na\")         = " + model1.freq("na"));
        StdOut.println();

        String text3 = "one fish two fish red fish blue fish";
        MarkovModel model3 = new MarkovModel(text3, 4);
        StdOut.println("freq(\"ish \", 'r') = " + model3.freq("ish ", 'r'));
        StdOut.println("freq(\"ish \", 'x') = " + model3.freq("ish ", 'x'));
        StdOut.println("freq(\"ish \")      = " + model3.freq("ish "));
        StdOut.println("freq(\"tuna\")      = " + model3.freq("tuna"));


        String text4 = "gagggagaggcgagaaa";
        MarkovModel model4 = new MarkovModel(text4, 2);
        StdOut.println("Random('gg') = " + model1.random("gg"));
        StdOut.println("freq(\"ish \", 'r') = " + model4.freq("", 'a'));
        StdOut.println("freq(\"ish \", 'x') = " + model4.freq("", 'a'));
        StdOut.println("freq(\"c\")      = " + model4.freq("c"));
        StdOut.println("freq(\"g\")      = " + model4.freq("g"));
        StdOut.println("freq(\"a\")      = " + model4.freq("a"));

    }
}
