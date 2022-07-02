package cz.kominekjan.extraordinarygifts.commands.all;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HelpCommand {
    public static final String commandName = "help";

    private static final String[] contents = {
            "/egifts help: shows this help",
            "/egifts reload: reloads the entire plugin",
            "/egifts create: creates a new gift",
            "/egifts open: opens a gift in your hand",
            "/egifts message <text of the message>: adds a message to the gift",
    };

    public static void command(Player p) {
        p.sendMessage(ChatColor.GOLD + "-- ExtraordinaryGifts help: --");
        for (String c : contents) {
            String[] command = c.split(":");
            p.sendMessage(ChatColor.GOLD + command[0] + ":" + ChatColor.WHITE + command[1]);
        }
    }
}
