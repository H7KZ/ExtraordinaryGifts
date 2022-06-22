package cz.kominekjan.extraordinarygifts.messages;

public class Enable {
    private static final String ANSI_CYAN = Colors.ANSI_CYAN;

    private static final String ANSI_RESET = Colors.ANSI_RESET;

    public static void starting() {
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts are starting --" + ANSI_RESET);
    }

    public static void closing() {
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts started with no signs of error --" + ANSI_RESET);
    }
}
