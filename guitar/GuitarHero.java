public class GuitarHero {

    // implements an interactive guitar player that plays guitar in real time
    public static void main(String[] args) {
        // keys
        final double BASE_FREQ = 440;
        String keyboardString = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        final int NOTE_COUNT = keyboardString.length();
        GuitarString[] strings = new GuitarString[NOTE_COUNT];
        // create guitar strings for each note
        for (int i = 0; i < NOTE_COUNT; i++) {
            strings[i] = new GuitarString(BASE_FREQ * Math.pow(2, (i - 24.0) / 12));
        }

        // the main input loop
        Keyboard keyboard = new Keyboard();
        while (true) {

            // check if the user has played a key; if so, process it
            if (keyboard.hasNextKeyPlayed()) {

                // the key the user played
                char key = keyboard.nextKeyPlayed();

                int index = keyboardString.indexOf(key);

                if (index >= 0)
                    strings[index].pluck();
            }

            // compute the superposition of the samples
            double sample = 0.0;
            for (int i = 0; i < NOTE_COUNT; i++) {
                sample += strings[i].sample();
            }
            StdAudio.play(sample);

            for (int i = 0; i < NOTE_COUNT; i++) {
                strings[i].tic();

            }
        }
    }
}
