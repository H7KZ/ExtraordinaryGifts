package cz.kominekjan.extraordinarygifts.messages;

import cz.kominekjan.extraordinarygifts.ExtraordinaryGifts;

public class Disable {
    private static final String ANSI_CYAN = ExtraordinaryGifts.ANSI_CYAN;

    private static final String ANSI_RESET = ExtraordinaryGifts.ANSI_RESET;

    public static void starting() {
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts are being shutting down --" + ANSI_RESET);
    }

    public static void closing() {
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts has been shutdown successfully --" + ANSI_RESET);
    }
}
