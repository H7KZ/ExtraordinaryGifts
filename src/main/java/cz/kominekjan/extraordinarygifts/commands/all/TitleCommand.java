package cz.kominekjan.extraordinarygifts.commands.all;

import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import static cz.kominekjan.extraordinarygifts.permissions.Permissions.CheckPermissionAllWithoutReload;
import static cz.kominekjan.extraordinarygifts.permissions.Permissions.titleCommandPermission;
import static cz.kominekjan.extraordinarygifts.validateAndFormat.TitleFormat.getFormattedTitle;
import static cz.kominekjan.extraordinarygifts.validateAndFormat.TitleMessageValidate.isPlayerHoldingGift;
import static cz.kominekjan.extraordinarygifts.validateAndFormat.TitleMessageValidate.validateTitle;

public class TitleCommand {
    public static final String commandName = "title";

    public static void command(Player p, String[] args) {
        if (!CheckPermissionAllWithoutReload(p, titleCommandPermission)) {
            return;
        }

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
}
