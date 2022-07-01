package cz.kominekjan.extraordinarygifts.commands.all;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.config;

public class MessageCommand {
    public static final String commandName = "message";

    private static final int maxMessageLength = config.getInt("giftMessage.maxMessageLength");
    private static final int maxWordLength = config.getInt("giftMessage.maxWordLength");
    private static final int maxLineLength = config.getInt("giftMessage.maxLineLength");
    private static final List<String> forbiddenWords = config.getStringList("giftMessage.forbiddenWords");
    private static final List<String> forbiddenLetters = config.getStringList("giftMessage.forbiddenLetters");

    public static void command(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(ChatColor.RED + "eGifts: No message specified!");
            return;
        }

        if (String.join(" ", Arrays.copyOfRange(args, 1, args.length)).length() > maxMessageLength) {
            p.sendMessage(ChatColor.RED + "eGifts: Message is too long!");
            return;
        }

        for (int i = 1; i < args.length; i++) {
            if (args[i].length() > maxWordLength) {
                p.sendMessage(ChatColor.RED + "eGifts: Word \"" + args[i] + "\" is too long!");
                return;
            }
        }


    }
}
