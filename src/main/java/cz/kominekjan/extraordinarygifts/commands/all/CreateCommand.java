package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.economy.GiftEconomy;
import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import cz.kominekjan.extraordinarygifts.variables.Variables;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CreateCommand {
    public static final String commandName = "create";

    public static void command(Player p) {

        if (Variables.Economy.Gift.enabled) {
            if (!GiftEconomy.Gift.checkBalance(p)) {
                p.sendMessage(ChatColor.RED + "You don't have enough balance to create a gift. To see all possible ways of paying, type /gift economy.");
                return;
            }
        }

        GiftMenu giftMenuInv = new GiftMenu();

        p.openInventory(giftMenuInv.getInventory());
    }
}
