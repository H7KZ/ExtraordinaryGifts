package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.messages.PlayerMessage;
import cz.kominekjan.extraordinarygifts.variables.Variables;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class EconomyCommand {
    public static final String commandName = "economy";

    public static final ArrayList<String> contents = new ArrayList<>();

    private static void giftEconomy() {
        if (!Variables.Economy.Gift.enabled) return;

        ArrayList<String> giftCostList = new ArrayList<>();

        for (LinkedHashMap<String, String> payment : Variables.Economy.Gift.possiblePaymentMethods) {
            giftCostList.add(payment.get("amount") + " " + payment.get("material"));
        }

        String giftCost = String.join(", ", giftCostList);

        contents.add("§6Gift cost: " + giftCost);

        if (Variables.Economy.Gift.useShulkerBox) {
            contents.add("§6Gift cost: Shulker boxes");
        }
    }

    public static void init() {
        giftEconomy();
    }

    public static void command(Player p) {
        if (!Variables.Economy.enabled) {
            p.sendMessage(PlayerMessage.economyNotEnabled);

            return;
        }

        if (contents.isEmpty()) {
            return;
        }

        p.sendMessage(PlayerMessage.economyDefaultWelcome);

        for (String econ : contents) {
            p.sendMessage(econ);
        }
    }
}
