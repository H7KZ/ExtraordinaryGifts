package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import org.bukkit.entity.Player;

public class CreateCommand {
    public static final String commandName = "create";

    public static void command(Player p) {

        GiftMenu giftMenuInv = new GiftMenu();

        p.openInventory(giftMenuInv.getInventory());
    }
}
