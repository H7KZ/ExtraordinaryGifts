package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.gift.Gift;
import org.bukkit.entity.Player;

import static cz.kominekjan.extraordinarygifts.permissions.Permissions.CheckPermissionAllWithoutReload;
import static cz.kominekjan.extraordinarygifts.permissions.Permissions.openCommandPermission;

public class OpenCommand {
    public static final String commandName = "open";

    public static void command(Player p) {
        if (!CheckPermissionAllWithoutReload(p, openCommandPermission)) {
            return;
        }

        Gift.open(p);
    }
}
