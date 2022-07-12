package cz.kominekjan.extraordinarygifts.validateAndFormat;

import cz.kominekjan.extraordinarygifts.messages.PlayerMessage;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Message.maxMessageLength;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Title.*;

public class TitleMessageValidate {
    public static Boolean isPlayerHoldingGift(Player p) {
        try {
            NamespacedKey namespacedKey = new NamespacedKey(plugin, "gift");

            if (Objects.requireNonNull(Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING)).equals("gift")) {
                return true;
            }
        } catch (NullPointerException ignored) {
        }

        p.sendMessage(ChatColor.RED + "eGifts: You must be holding a gift.");

        return false;
    }

    public static Boolean checkForbidden(Player p, String[] args, List<String> forbiddenWords, int maxWordLength, List<Character> forbiddenLetters) {
        for (String forbiddenWord : forbiddenWords) {
            if (String.join(" ", Arrays.copyOfRange(args, 1, args.length)).toLowerCase().contains(forbiddenWord.toLowerCase())) {
                p.sendMessage(ChatColor.RED + "eGifts: Word \"" + forbiddenWord + "\" is forbidden!");

                return false;
            }
        }

        for (int i = 1; i < args.length; i++) {
            if (args[i].length() > maxWordLength) {
                p.sendMessage(ChatColor.RED + "eGifts: Word \"" + args[i] + "\" is too long! Max word length is " + maxWordLength + " characters.");

                return false;
            }

            for (int j = 0; j < args[i].length(); j++) {
                if (forbiddenLetters.contains(args[i].charAt(j))) {
                    p.sendMessage(ChatColor.RED + "eGifts: Letter \"" + args[i].charAt(j) + "\" is forbidden!");

                    return false;
                }
            }
        }

        return true;
    }

    public static Boolean validateTitle(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(ChatColor.RED + "eGifts: No title specified!");

            return false;
        }

        if (String.join(" ", Arrays.copyOfRange(args, 1, args.length)).length() > maxTitleLength) {
            p.sendMessage(ChatColor.RED + "eGifts: Title is too long! Max title length is " + maxTitleLength + " characters.");

            return false;
        }

        return checkForbidden(p, args, forbiddenWords, maxWordLength, forbiddenLetters);
    }

    public static Boolean validateMessage(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(PlayerMessage.messageCommandNoMessage);

            return false;
        }

        if (String.join(" ", Arrays.copyOfRange(args, 1, args.length)).length() > maxMessageLength) {
            p.sendMessage(PlayerMessage.messageCommandTooLong(maxMessageLength));

            return false;
        }

        return checkForbidden(p, args, forbiddenWords, maxWordLength, forbiddenLetters);
    }
}
