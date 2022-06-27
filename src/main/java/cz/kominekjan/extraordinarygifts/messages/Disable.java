package cz.kominekjan.extraordinarygifts.messages;

import cz.kominekjan.extraordinarygifts.commands.all.ReloadCommand;

public class Disable {
    private static final String ANSI_CYAN = Colors.ANSI_PURPLE;

    private static final String ANSI_RESET = Colors.ANSI_RESET;

    public static void closing() {
        System.out.println(ANSI_CYAN + "ExtraordinaryGifts: has been shutdown successfully" + ANSI_RESET);
    }

    public static void reloading() {
        ReloadCommand.command();
    }
}
