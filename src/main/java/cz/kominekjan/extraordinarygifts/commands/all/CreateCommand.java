package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.economy.GiftCreateEconomy;
import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import org.bukkit.entity.Player;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.config;

public class CreateCommand {
    public static final String commandName = "create";

    public static void command(Player p) {
        if (config.getBoolean("giftEconomy.enabled")) {
            if (!GiftCreateEconomy.checkBalance(p)) {
                return;
            }
        }

        GiftMenu giftMenuInv = new GiftMenu();

        p.openInventory(giftMenuInv.getInventory());
    }
}
