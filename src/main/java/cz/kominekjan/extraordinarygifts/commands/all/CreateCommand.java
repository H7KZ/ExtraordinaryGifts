package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import org.bukkit.entity.Player;

public class CreateCommand {
    public static final String commandName = "create";

    private static final GiftMenu giftMenuInv = new GiftMenu();

    public static void command(Player p) {
        p.openInventory(giftMenuInv.getInventory());
    }
}
