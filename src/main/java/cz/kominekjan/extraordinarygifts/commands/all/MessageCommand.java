package cz.kominekjan.extraordinarygifts.commands.all;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cz.kominekjan.extraordinarygifts.commands.all.titleCommand.checkForbidden;
import static cz.kominekjan.extraordinarygifts.commands.all.titleCommand.isPlayerHoldingGift;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Message.*;

public class MessageCommand {
    public static final String commandName = "message";

    public static void command(Player p, String[] args) {
        if (!isPlayerHoldingGift(p)) {
            return;
        }

        if (!validateMessage(p, args)) {
            return;
        }

        List<String> message = getFormattedMessage(args);

        ItemMeta giftMeta = p.getInventory().getItemInMainHand().getItemMeta();
        assert giftMeta != null;
        giftMeta.setLore(message);

        p.getInventory().getItemInMainHand().setItemMeta(giftMeta);
    }

    private static Boolean validateMessage(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(ChatColor.RED + "eGifts: No message specified!");
            return false;
        }

        if (String.join(" ", Arrays.copyOfRange(args, 1, args.length)).length() > maxMessageLength) {
            p.sendMessage(ChatColor.RED + "eGifts: Message is too long! Max message length is " + maxMessageLength + " characters.");
            return false;
        }

        return checkForbidden(p, args, forbiddenWords, maxWordLength, forbiddenLetters);
    }

    private static List<String> getFormattedMessage(String[] args) {
        List<String> message = new ArrayList<>();
        List<String> tempLineMessage = new ArrayList<>();
        int tempLineLength = 0;

        for (int i = 1; i < args.length; i++) {
            if (tempLineLength <= maxLineLength && tempLineLength + args[i].length() + 1 >= maxLineLength) {
                message.add(messageColor + ChatColor.translateAlternateColorCodes('&', String.join(" ", tempLineMessage)));
                tempLineMessage.clear();
                tempLineLength = 0;
            }

            if (i == args.length - 1) {
                if (tempLineLength <= maxLineLength && tempLineLength + args[i].length() + 1 <= maxLineLength) {
                    tempLineMessage.add(args[i]);
                    message.add(messageColor + ChatColor.translateAlternateColorCodes('&', String.join(" ", tempLineMessage)));
                    tempLineMessage.clear();
                }
            }

            tempLineMessage.add(args[i]);
            tempLineLength = String.join(" ", tempLineMessage).length();
        }

        return message;
    }
}
