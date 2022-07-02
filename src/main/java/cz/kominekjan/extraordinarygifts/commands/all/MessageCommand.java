package cz.kominekjan.extraordinarygifts.commands.all;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.config;
import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;

public class MessageCommand {
    public static final String commandName = "message";
    private static final int maxMessageLength = config.getInt("giftMessage.maxMessageLength");
    private static final int maxWordLength = config.getInt("giftMessage.maxWordLength");
    private static final int maxLineLength = config.getInt("giftMessage.maxLineLength");
    private static final List<String> forbiddenWords = config.getStringList("giftMessage.forbiddenWords");
    private static final List<Character> forbiddenLetters = config.getCharacterList("giftMessage.forbiddenLetters");
    private static final String messageColor = config.getString("giftMessage.messageColor");

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

    private static Boolean isPlayerHoldingGift(Player p) {
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

    private static Boolean validateMessage(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(ChatColor.RED + "eGifts: No message specified!");
            return false;
        }

        if (String.join(" ", Arrays.copyOfRange(args, 1, args.length)).length() > maxMessageLength) {
            p.sendMessage(ChatColor.RED + "eGifts: Message is too long!");
            return false;
        }

        for (String forbiddenWord : forbiddenWords) {
            if (String.join(" ", Arrays.copyOfRange(args, 1, args.length)).toLowerCase().contains(forbiddenWord.toLowerCase())) {
                p.sendMessage(ChatColor.RED + "eGifts: Word \"" + forbiddenWord + "\" is forbidden!");
                return false;
            }
        }

        for (int i = 1; i < args.length; i++) {
            if (args[i].length() > maxWordLength) {
                p.sendMessage(ChatColor.RED + "eGifts: Word \"" + args[i] + "\" is too long!");
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

    private static List<String> getFormattedMessage(String[] args) {
        List<String> message = new ArrayList<>();

        List<String> tempLineMessage = new ArrayList<>();
        int tempLineLength = 0;

        for (int i = 1; i < args.length; i++) {
            if (tempLineLength <= maxLineLength && tempLineLength + args[i].length() + 1 >= maxLineLength) {
                message.add(messageColor + String.join(" ", tempLineMessage));
                tempLineMessage.clear();
                tempLineLength = 0;
            }

            if (i == args.length - 1) {
                if (tempLineLength <= maxLineLength && tempLineLength + args[i].length() + 1 <= maxLineLength) {
                    tempLineMessage.add(args[i]);
                    message.add(messageColor + String.join(" ", tempLineMessage));
                    tempLineMessage.clear();
                }
            }

            tempLineMessage.add(args[i]);
            tempLineLength = String.join(" ", tempLineMessage).length();
        }

        return message;
    }
}
