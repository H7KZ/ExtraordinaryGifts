package cz.kominekjan.extraordinarygifts.commands.all;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HelpCommand {
    public static final String commandName = "help";

    private static final String[] contents = {
            "/egifts help: shows this help",
            "/egifts create: creates a new gift",
            "/egifts send <message>: sends a gift to a player with/out a message",
            "/egifts open: opens a gift in your hand",
            "/egifts mail: opens mail of gifts"
    };

    public static void command(Player p) {
        p.sendMessage(ChatColor.GOLD + "-- ExtraordinaryGifts help: --");
        for (String c : contents) {
            String[] command = c.split(":");
            p.sendMessage(ChatColor.GOLD + command[0] + ":" + ChatColor.WHITE + command[1]);
        }
    }
}
