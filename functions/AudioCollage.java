public class AudioCollage {
    // Returns a new array that rescales a[] by a factor of alpha.
    public static double[] amplify(double[] a, double alpha) {
        int n = a.length;
        double[] amplified = new double[n];
        for (int i = 0; i < n; i++) {
            amplified[i] = alpha * a[i];
        }
        return amplified;
    }

    // Returns a new array that is the reverse of a[].
    public static double[] reverse(double[] a) {
        int n = a.length;
        double[] reversed = new double[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = a[n - i - 1];
        }
        return reversed;
    }

    // Returns a new array that is the concatenation of a[] and b[].
    public static double[] merge(double[] a, double[] b) {
        double[] merged = new double[a.length + b.length];
        int index = 0;
        for (int i = 0; i < a.length; i++, index++)
            merged[index] = a[i];

        for (int i = 0; i < b.length; i++, index++)
            merged[index] = b[i];

        return merged;
    }

    // Returns a new array that is the sum of a[] and b[],
    // padding the shorter array with trailing 0s if necessary.
    public static double[] mix(double[] a, double[] b) {
        int len = Math.max(a.length, b.length);
        double[] shorter, mixed = new double[len];

        if (a.length > b.length) {
            // a is longer; copy a and add b later
            int n = a.length;
            shorter = b;
            for (int i = 0; i < n; i++)
                mixed[i] = a[i];

        }
        else {
            // b is longer; copy b and add a later
            int n = b.length;
            shorter = a;
            for (int i = 0; i < n; i++)
                mixed[i] = b[i];

        }

        // add the shorter array
        for (int i = 0; i < shorter.length; i++) {
            // check for over
            mixed[i] += shorter[i];
        }
        return mixed;
    }

    // Returns a new array that changes the speed of a[] by a factor of alpha.
    public static double[] changeSpeed(double[] a, double alpha) {
        int n = (int) Math.floor(a.length / alpha);
        double[] sampled = new double[n];
        for (int i = 0; i < n; i++) {
            int idx = (int) (i * alpha);
            sampled[i] = a[idx];
        }
        return sampled;
    }

    // clamp: replace all samples greater than +1.0 with 1.0;
    // and all samples less than -1.0 with -1.0.
    private static double[] clamp(double[] a) {
        int n = a.length;
        double[] clamped = new double[n];
        for (int i = 0; i < n; i++) {
            double clamedValue = a[i];
            if (clamedValue > 1.0) clamedValue = 1.0;
            if (clamedValue < -1.0) clamedValue = -1.0;

            clamped[i] = clamedValue;
        }
        return clamped;
    }

    // normalize: rescale a sound so that all values are between –1.0 and +1.0.
    private static double[] normalize(double[] a) {
        int n = a.length;
        double[] normalized = new double[n];
        double min = Double.POSITIVE_INFINITY, max = Double.NEGATIVE_INFINITY;

        // normalise, we have the get the max and min values
        // in order to rescale the input array
        for (int i = 0; i < n; i++) {
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }
        for (int i = 0; i < n; i++) {
            // translate from [min, min] to [-1, 1] using
            // y - yi = m(x - xi) -> y = m(x-xi) + yi,  m = (yf - yi)/(xf - xi)
            // , (yi, yf)=(-1, 1), (xi, xf) = (min, max)
            double slope = (1.0 - -1.0) / (max - min);

            double normalizedValue = slope * (a[i] - min) + -1.0;

            normalized[i] = normalizedValue;
        }
        return normalized;
    }

    // cut: extract a contiguous subarray from a given sound.
    private static double[] cut(double[] a, int start, int end) {
        // assert start >= 0 && end < a.length;
        int len = start - end + 1;
        double[] subSamples = new double[len];
        for (int i = 0; i < len && start <= end; i++, start++)
            subSamples[i] = a[start];

        return subSamples;
    }

    // trim: remove leading & trailing sequence of samples
    // that are 0.0 (or nearly 0.0).
    private static double[] trim(double[] a) {
        int n = a.length, trimedStart = 0, trimedEnd = n - 1;
        final double EPSILON = 0.001; // how small a sample can be
        for (int i = 0; i < n && a[i] < EPSILON; i++) trimedStart++;
        for (int i = n - 1; i >= 0 && a[i] < EPSILON; i--) trimedEnd--;

        if (trimedEnd <= trimedStart) return new double[0];

        int len = trimedEnd - trimedStart + 1;
        double[] trimed = new double[len];
        for (int i = 0; i < len && trimedStart <= trimedEnd; i++, trimedStart++)
            trimed[i] = a[trimedStart];

        return trimed;
    }

    // loop: repeat a given sound a specified number of times.
    private static double[] loop(double[] a, int loops) {
        int n = loops * a.length;
        double[] looped = new double[n];
        for (int i = 0; i < n; i++) {
            looped[i] = a[i % a.length];
        }
        return looped;
    }

    // mirror: concatenate a sound with its reverse.
    private static double[] mirror(double[] a) {
        return merge(a, reverse(a));
    }

    // hip–hop: increase speed of a sound; mirror it; then loop it.
    private static double[] hipHop(double[] a, double speed, int loops) {
        return loop(mirror(changeSpeed(a, speed)), loops);
    }

    // echo, delay, reverb
    private static double[] echo(double[] a, double delayFactor) {
        double[] echoed = mix(a, delay(a, delayFactor));
        return echoed;
    }

    // delay
    private static double[] delay(double[] a, double factor) {
        int n = a.length, len = (int) (n * factor);

        double[] delayed = new double[len];
        for (int i = 0; i < len; i++) {
            delayed[i] = a[(int) (i / factor)];
        }
        return delayed;
    }

    // reverb : add a time-delayed version of a sound to itself,
    // attenuated by a given factor.
    // private static double[] reverb(double[] a, double arg) {
    //     // raise not implemented
    //     double[] reverbed = new double[a.length];
    //
    //     return reverbed;
    // }

    // fade-in: gradually increase the volume at the
    // beginning of a sound.
    private static double[] fadeIn(double[] a, double percentage) {
        // assert percentage >= 0 && percentage < 1
        int n = a.length, fadeLength = (int) (percentage * n);
        // smoothly change amplitude from 0 to 1
        // copy old array while fading the portion required
        double[] fadedIn = new double[n];
        for (int i = 0; i < n; i++) {
            if (i < fadeLength) // postion to be faded
                fadedIn[i] = a[i] * (double) i / fadeLength;
            else fadedIn[i] = a[i];
        }
        return fadedIn;
    }

    // fade-out: gradually decrease the volume at the end of a sound.
    private static double[] fadeOut(double[] a, double percentage) {
        // assert percentage >= 0 && percentage < 1
        int n = a.length, fadeLength = (int) (percentage * n);
        // smoothly change amplitude from 1 to 0
        // copy old array while fading the portion required
        double[] fadedOut = new double[n];
        for (int i = n - 1, j = 0; i >= 0; i--, j++) {
            if (j < fadeLength) // postion to be faded
                fadedOut[i] = a[i] * (double) j / fadeLength;
            else fadedOut[i] = a[i];
        }
        return fadedOut;
    }

    // crossfade: fade-out first sound; fade-in second sound; overlap.
    private static double[] crossFade(double[] a, double[] b, double fadeInPercentage,
                                      double fadeOutPercentage) {
        return mix(fadeIn(a, fadeInPercentage), fadeOut(b, fadeOutPercentage));
    }

    // fade: fade in and out by the same %
    private static double[] fade(double[] a, double percentage) {
        // assert percentage < 0.5
        return mix(fadeIn(a, percentage), fadeOut(a, percentage));
    }

    // tremolo: create a trembling effect by modulating the amplitude up and down.
    private static double[] tremolo(double[] a, double minVolume, double maxVolume) {
        // A randomised effect made by amplifying each sample
        // by a random value
        int n = a.length;
        double[] tremoloSamples = new double[n];
        for (int i = 0; i < n; i++) {
            tremoloSamples[i] = StdRandom.uniformDouble(minVolume, maxVolume) * a[i];
        }
        return tremoloSamples;
    }


    // Creates an audio collage and plays it on standard audio.
    // See below for the requirements.
    public static void main(String[] args) {

        // piano codes and corresponing letter
        double[][] PIANO_SOLFEGE = {
                StdAudio.read("do.wav"), StdAudio.read("re.wav"),
                StdAudio.read("me.wav"), StdAudio.read("fa.wav"),
                StdAudio.read("so.wav"), StdAudio.read("la.wav"),
                StdAudio.read("ti.wav"), StdAudio.read("do2.wav"),
                };


        double[][] audioSamples = {
                StdAudio.read("drum.wav"),
                StdAudio.read("piano.wav")
        };

        double[] finalCollage;
        // merge all piano solfege sounds
        double[] solfege = merge(PIANO_SOLFEGE[0], PIANO_SOLFEGE[1]);

        for (int i = 2; i < PIANO_SOLFEGE.length; i++) {
            solfege = merge(solfege, PIANO_SOLFEGE[i]);
        }

        // then change the speed
        finalCollage = changeSpeed(solfege, 2);

        // mix it with loop of paino audio
        finalCollage = mix(finalCollage, loop(audioSamples[1], 2));

        // amplify
        finalCollage = amplify(finalCollage, 1.5);

        // reverse the final collage
        double[] reversed = reverse(finalCollage);

        // then merge with the final collage
        finalCollage = mix(finalCollage, reversed);

        // make into hiphop with 20% fade
        finalCollage = fade(hipHop(finalCollage, 1, 2), 0.2);

        // normalise
        finalCollage = clamp(finalCollage);


        // StdOut.print("Length: " + finalCollage.length);
        // double maxSample = Double.NEGATIVE_INFINITY,
        // minSample = Double.POSITIVE_INFINITY;
        // for (int i = 0; i < finalCollage.length; i++) {
        //     minSample = Math.min(minSample, finalCollage[i]);
        //     maxSample = Math.max(maxSample, finalCollage[i]);
        // }
        // StdOut.printf("   | Max: %g   |  Min: %g ", maxSample, minSample);

        // then play
        StdAudio.play(finalCollage);


        // Tests

        // StdAudio.play(StdAudio.read("afrobeat.wav"));
        // StdAudio.play(changeSpeed(StdAudio.read("afrobeat.wav"), 1.5));
        // StdAudio.play(fade(hipHop(StdAudio.read("piano.wav"), 1.2, 5), .3));

        // StdAudio.play(delay(hipHop(StdAudio.read("piano.wav"), 1.2, 5), 3));
        // StdAudio.play(echo(loop(StdAudio.read("piano.wav"), 5), 2));

        // StdAudio.play(fadeIn(StdAudio.read("exposure.wav"), 0.8));
        // StdAudio.play(fadeOut(hipHop(StdAudio.read("piano.wav"), 1.2, 1), 0.8));


        StdAudio.drain();
    }
}
