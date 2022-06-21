package cz.kominekjan.extraordinarygifts.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class errors {
    public static void notEnoughArgs(Player p, String[] commandNameList) {
        p.sendMessage(ChatColor.RED + "EGIFTS: You must specify arguments! Available are: /eGifts + " + ChatColor.ITALIC + String.join(", ", commandNameList));
    }

    public static void youMustBeAPlayer(CommandSender s) {
        s.sendMessage(ChatColor.RED + "EGIFTS: You must be a player!");
    }
}
