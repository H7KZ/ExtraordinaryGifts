package cz.kominekjan.extraordinarygifts.permissions;

import cz.kominekjan.extraordinarygifts.messages.PlayerMessage;
import cz.kominekjan.extraordinarygifts.messages.SenderMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Permissions {
    public static final String createCommandPermission = "extraordinarygifts.gift.create";
    public static final String openCommandPermission = "extraordinarygifts.gift.open";
    public static final String titleCommandPermission = "extraordinarygifts.gift.title";
    public static final String messageCommandPermission = "extraordinarygifts.gift.message";
    public static final String reloadCommandPermission = "extraordinarygifts.plugin.reload";
    public static final String helpCommandPermission = "extraordinarygifts.command.help";
    public static final String economyCommandPermission = "extraordinarygifts.command.economy";
    public static final String eGiftCommandPermission = "extraordinarygifts.command.egift";
    public static final String allCommandsWithoutReloadPermission = "extraordinarygifts.command.allCommandsWithoutReload";
    public static final String allCommandsWithReloadPermission = "extraordinarygifts.command.allCommandsWithReload";

    public static Boolean CheckPermissionAllWithoutReload(Player p, String permission) {
        if (p.hasPermission(permission) || p.hasPermission(allCommandsWithoutReloadPermission) || p.hasPermission(allCommandsWithReloadPermission)) {
            return true;
        }

        p.sendMessage(PlayerMessage.noPermission);
        return false;
    }

    public static Boolean CheckPermissionAllWithReload(CommandSender s, String permission) {
        if (s.hasPermission(permission) || s.hasPermission(allCommandsWithReloadPermission)) {
            return true;
        }

        s.sendMessage(SenderMessage.noPermission);
        return false;
    }
}
