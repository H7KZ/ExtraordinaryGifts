package cz.kominekjan.extraordinarygifts.messages;

public class Disable {
    private static final String ANSI_CYAN = Colors.ANSI_CYAN;

    private static final String ANSI_RESET = Colors.ANSI_RESET;

    public static void starting() {
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts are being shutting down --" + ANSI_RESET);
    }

    public static void closing() {
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts has been shutdown successfully --" + ANSI_RESET);
    }
}
