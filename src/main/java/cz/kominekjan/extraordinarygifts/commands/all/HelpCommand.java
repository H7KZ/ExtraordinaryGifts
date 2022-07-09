package cz.kominekjan.extraordinarygifts.commands.all;

import org.bukkit.entity.Player;

import static cz.kominekjan.extraordinarygifts.variables.Variables.Permissions.allCommandsWithReloadPermission;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Permissions.reloadCommandPermission;

public class HelpCommand {
    public static final String commandName = "help";

    private static final String[] contentsWithReload = {
            "§6/egifts help:§f shows this help",
            "§6/egifts reload:§f reloads the entire plugin",
            "§6/egifts create:§f creates a new gift",
            "§6/egifts open:§f opens a gift in your hand",
            "§6/egifts economy:§f If the gift economy is enabled, shows the cost of gifts",
            "§6/egifts message <text of the message>:§f adds a message to the gift",
            "§6/egifts title <title of the message>:§f adds a title to the gift",
            "§6for title and message you can use chat color codes with &<color char>"
    };

    private static final String[] contentsWithoutReload = {
            "§6/egifts help:§f shows this help",
            "§6/egifts create:§f creates a new gift",
            "§6/egifts open:§f opens a gift in your hand",
            "§6/egifts economy:§f If the gift economy is enabled, shows the cost of gifts",
            "§6/egifts message <text of the message>:§f adds a message to the gift",
            "§6/egifts title <title of the message>:§f adds a title to the gift",
            "§6for title and message you can use chat color codes with &<color char>"
    };

    public static void command(Player p) {
        p.sendMessage("§6§l-- ExtraordinaryGifts help: --");
        if (p.hasPermission(reloadCommandPermission) || p.hasPermission(allCommandsWithReloadPermission)) {
            for (String help : contentsWithReload) {
                p.sendMessage(help);
            }
        } else {
            for (String help : contentsWithoutReload) {
                p.sendMessage(help);
            }
        }
    }
}
