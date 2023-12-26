public class AutoGuitar {

    // implements an interactive guitar player that plays guitar in real time
    public static void main(String[] args) {
        // keys
        final double BASE_FREQ = 440;
        String filename = "./notes.txt";  // file containing notes

        In notesIn = new In(filename);

        String keyboardString = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        final int NOTE_COUNT = keyboardString.length();
        GuitarString[] strings = new GuitarString[NOTE_COUNT];

        // create guitar strings for each note
        for (int i = 0; i < NOTE_COUNT; i++) {
            strings[i] = new GuitarString(BASE_FREQ * Math.pow(2, (i - 24.0) / 12.0));
        }

        // main playing loop
        while (!notesIn.isEmpty()) {
            // contains the actual key including spaces
            String firstLine = notesIn.readLine();
            // contains the time to wait
            String secondLine = notesIn.readLine();

            // read first char which is the actual key
            char key = firstLine.charAt(0);
            long milliDelta = Long.parseLong(secondLine.trim());

            int index = keyboardString.indexOf(key);
            if (index >= 0)
                strings[index].pluck();


            // compute the superposition of the samples
            // as long as the time to play the next note has not
            // reached

            double seconds = milliDelta / 1000.0, TIME_FACTOR = 44_100;
            for (double t = 0.0; t < seconds * TIME_FACTOR; t++) {
                double sample = 0.0;
                for (int i = 0; i < NOTE_COUNT; i++)
                    sample += strings[i].sample();

                StdAudio.play(sample);

                for (int i = 0; i < NOTE_COUNT; i++)
                    strings[i].tic();
            }


        }

    }
}
