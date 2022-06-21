package cz.kominekjan.extraordinarygifts.messages;

import cz.kominekjan.extraordinarygifts.ExtraordinaryGifts;

public class Enable {
    private static final String ANSI_CYAN = ExtraordinaryGifts.ANSI_CYAN;

    private static final String ANSI_RESET = ExtraordinaryGifts.ANSI_RESET;

    public static void starting() {
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts are starting --" + ANSI_RESET);
    }

    public static void closing() {
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts started with no signs of error (until you do something in game) ;) haha --" + ANSI_RESET);
    }
}
