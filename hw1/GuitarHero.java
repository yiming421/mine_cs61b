import synthesizer.GuitarString;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    /* all the different frequency **/
    private static double[] concert = new double[40];
    /* guitar strings **/
    private static synthesizer.GuitarString[] strings = new synthesizer.GuitarString[40];
    /* keyboard representation of guitar string **/
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        /* compute frequency and initialize strings */
        for (int i = 0; i < 37; ++i) {
            concert[i] = CONCERT_A * Math.pow(2, (i - 24) / 12.0);
        }
        for (int i = 0; i < 37; ++i) {
            strings[i] = new synthesizer.GuitarString(concert[i]);
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                for (int i = 0; i < 37; ++i) {
                    if (key == keyboard.charAt(i)) {
                        strings[i].pluck();
                    }
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (int i = 0; i < 37; ++i) {
                sample += strings[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < 37; ++i) {
                strings[i].tic();
            }
        }
    }
}
