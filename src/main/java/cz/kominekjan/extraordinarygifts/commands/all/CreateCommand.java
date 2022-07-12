package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.economy.GiftEconomy;
import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import cz.kominekjan.extraordinarygifts.messages.PlayerMessage;
import cz.kominekjan.extraordinarygifts.variables.Variables;
import org.bukkit.entity.Player;

public class CreateCommand {
    public static final String commandName = "create";

    public static void command(Player p) {

        if (Variables.Economy.enabled && Variables.Economy.Gift.enabled) {
            if (!GiftEconomy.Gift.checkBalance(p.getInventory().getItemInMainHand())) {
                p.sendMessage(PlayerMessage.createCommandNotEnoughBalance);

                return;
            }

            GiftEconomy.Gift.payBalance(p, p.getInventory().getItemInMainHand());
        }

        GiftMenu giftMenuInv = new GiftMenu();

        p.openInventory(giftMenuInv.getInventory());
    }
}
