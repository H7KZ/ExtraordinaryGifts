package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.messages.PlayerMessage;
import cz.kominekjan.extraordinarygifts.variables.Variables.Economy;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static cz.kominekjan.extraordinarygifts.permissions.Permissions.CheckPermissionAllWithoutReload;
import static cz.kominekjan.extraordinarygifts.permissions.Permissions.economyCommandPermission;

public class EconomyCommand {
    public static final String commandName = "economy";

    public static final ArrayList<String> contents = new ArrayList<>();

    private static void giftEconomy() {
        if (!Economy.Gift.enabled) return;

        ArrayList<String> giftCostList = new ArrayList<>();

        for (LinkedHashMap<String, String> payment : Economy.Gift.possiblePaymentMethods) {
            giftCostList.add(payment.get("amount") + " " + payment.get("material"));
        }

        String giftCost = String.join(", ", giftCostList);

        contents.add("§6Gift cost: " + giftCost);

        if (Economy.Gift.useShulkerBox) {
            contents.add("§6Gift cost: Shulker boxes");
        }
    }

    public static void init() {
        giftEconomy();
    }

    public static void command(Player p) {
        if (!CheckPermissionAllWithoutReload(p, economyCommandPermission)) {
            return;
        }

        if (!Economy.enabled) {
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
