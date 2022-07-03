package cz.kominekjan.extraordinarygifts.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static cz.kominekjan.extraordinarygifts.variables.Variables.Colors.ANSI_RED;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Colors.ANSI_RESET;

public class Errors {
    public static void notEnoughArgs(Player p, String[] commandNameList) {
        p.sendMessage(ChatColor.RED + "eGifts: You must specify arguments! Available are: /eGifts + " + ChatColor.ITALIC + String.join(", ", commandNameList));
    }

    public static void youMustBeAPlayer(CommandSender s) {
        s.sendMessage(ChatColor.RED + ANSI_RED + " eGifts: You must be a player! " + ANSI_RESET);
    }

    public static void invalidGiftInvSize() {
        System.out.println(ANSI_RED + " ExtraordinaryGifts: Invalid inventory size! Defaulting to 27!" + ANSI_RESET);
    }

    public static void failedToSetProfileHead() {
        System.out.println(ANSI_RED + " Failed to set profile for head." + ANSI_RESET);
    }
}
