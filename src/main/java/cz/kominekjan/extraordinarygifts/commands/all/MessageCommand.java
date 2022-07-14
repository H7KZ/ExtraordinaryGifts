package cz.kominekjan.extraordinarygifts.commands.all;

import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static cz.kominekjan.extraordinarygifts.permissions.Permissions.CheckPermissionAllWithoutReload;
import static cz.kominekjan.extraordinarygifts.permissions.Permissions.messageCommandPermission;
import static cz.kominekjan.extraordinarygifts.validateAndFormat.MessageFormat.getFormattedMessage;
import static cz.kominekjan.extraordinarygifts.validateAndFormat.TitleMessageValidate.isPlayerHoldingGift;
import static cz.kominekjan.extraordinarygifts.validateAndFormat.TitleMessageValidate.validateMessage;

public class MessageCommand {
    public static final String commandName = "message";

    public static void command(Player p, String[] args) {
        if (!CheckPermissionAllWithoutReload(p, messageCommandPermission)) {
            return;
        }

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
}
