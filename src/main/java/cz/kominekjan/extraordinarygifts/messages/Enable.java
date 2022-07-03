package cz.kominekjan.extraordinarygifts.messages;

import static cz.kominekjan.extraordinarygifts.variables.Variables.Colors.ANSI_PURPLE;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Colors.ANSI_RESET;

public class Enable {
    public static void starting() {
        System.out.println(ANSI_PURPLE + "ExtraordinaryGifts: are starting" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "PLEASE FOR THE RELOAD USE AN INTERNAL RELOAD COMMAND /eGifts reload" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "OTHERWISE THERE IS A SMALL CHANCE OF BUG ITEMS GETTING INTO INVENTORIES!" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "THIS BUG IS NOT DONE BY ME, IT IS A BUG OF SPIGOT! KEEP THAT IN MIND! BE CAREFUL!" + ANSI_RESET);
    }

    public static void closing() {
        System.out.println(ANSI_PURPLE + "ExtraordinaryGifts: started with no signs of error" + ANSI_RESET);
    }
}
