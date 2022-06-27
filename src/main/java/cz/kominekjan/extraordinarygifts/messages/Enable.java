package cz.kominekjan.extraordinarygifts.messages;

public class Enable {
    private static final String ANSI_CYAN = Colors.ANSI_PURPLE;

    private static final String ANSI_RESET = Colors.ANSI_RESET;

    public static void starting() {
        System.out.println(ANSI_CYAN + "ExtraordinaryGifts: are starting" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "PLEASE FOR THE RELOAD USE AN INTERNAL RELOAD COMMAND /eGifts reload" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "OTHERWISE THERE IS A SMALL CHANCE OF BUG ITEMS GETTING INTO INVENTORIES!" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "THIS BUG IS NOT DONE BY ME, IT IS A BUG OF SPIGOT! KEEP THAT IN MIND! BE CAREFUL!" + ANSI_RESET);
    }

    public static void closing() {
        System.out.println(ANSI_CYAN + "ExtraordinaryGifts: started with no signs of error" + ANSI_RESET);
    }
}
