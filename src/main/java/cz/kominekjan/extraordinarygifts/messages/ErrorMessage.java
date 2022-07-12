package cz.kominekjan.extraordinarygifts.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.logger;

public class ErrorMessage {
    public static void notEnoughArgs(Player p, String[] commandNameList) {
        p.sendMessage(ChatColor.RED + "eGifts: You must specify arguments! Available are: /eGifts + " + ChatColor.ITALIC + String.join(", ", commandNameList));
    }

    public static void youMustBeAPlayer(CommandSender s) {
        s.sendMessage(" eGifts: You must be a player! ");
    }

    public static void invalidGiftInvSize() {
        logger.warning(" ExtraordinaryGifts: Invalid inventory size! Defaulting to 27!");
    }

    public static void failedToSetProfileHead() {
        logger.warning("ExtraordinaryGifts: Failed to set profile for head.");
    }
}
