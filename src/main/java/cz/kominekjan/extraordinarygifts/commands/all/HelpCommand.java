package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.messages.PlayerMessage;
import org.bukkit.entity.Player;

import static cz.kominekjan.extraordinarygifts.messages.HelpMessage.contentsWithReload;
import static cz.kominekjan.extraordinarygifts.messages.HelpMessage.contentsWithoutReload;
import static cz.kominekjan.extraordinarygifts.permissions.Permissions.*;

public class HelpCommand {
    public static final String commandName = "help";

    private static void sendPlayerHelp(Player p) {
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

    public static void command(Player p) {
        if (!CheckPermissionAllWithoutReload(p, helpCommandPermission)) {
            return;
        }

        p.sendMessage(PlayerMessage.helpCommandDefaultWelcome);

        sendPlayerHelp(p);
    }
}
