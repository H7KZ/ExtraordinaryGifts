package cz.kominekjan.extraordinarygifts.commands.all;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Title.*;

public class titleCommand {
    public static final String commandName = "title";

    public static void command(Player p, String[] args) {
        if (!isPlayerHoldingGift(p)) {
            return;
        }

        if (!validateTitle(p, args)) {
            return;
        }

        String title = getFormattedTitle(args);

        ItemMeta giftMeta = p.getInventory().getItemInMainHand().getItemMeta();
        assert giftMeta != null;
        giftMeta.setDisplayName(title);

        p.getInventory().getItemInMainHand().setItemMeta(giftMeta);
    }

    private static Boolean validateTitle(Player p, String[] args) {
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

    private static String getFormattedTitle(String[] args) {
        return titleColor + ChatColor.translateAlternateColorCodes('&', String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
    }

    static Boolean isPlayerHoldingGift(Player p) {
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

    static Boolean checkForbidden(Player p, String[] args, List<String> forbiddenWords, int maxWordLength, List<Character> forbiddenLetters) {
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
}
