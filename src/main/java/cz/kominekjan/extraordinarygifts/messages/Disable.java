package cz.kominekjan.extraordinarygifts.messages;

import static cz.kominekjan.extraordinarygifts.variables.Variables.Colors.ANSI_PURPLE;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Colors.ANSI_RESET;

public class Disable {
    public static void closing() {
        System.out.println(ANSI_PURPLE + "ExtraordinaryGifts: has been shutdown successfully" + ANSI_RESET);
    }
}
