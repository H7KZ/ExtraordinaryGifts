package cz.kominekjan.extraordinarygifts.economy;

import cz.kominekjan.extraordinarygifts.variables.Variables;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

public class GiftEconomy {
    public static class Gift {
        public static final HashMap<UUID, ItemStack> whoPaidListItemStack = new HashMap<>();

        public static Boolean checkBalance(ItemStack itemInMainHand) {
            if (Variables.Economy.Gift.useShulkerBox) {
                for (Material shulker : Variables.GiftMenuEvent.shulkerBoxTags) {
                    if (itemInMainHand.getType() == shulker) {
                        return true;
                    }
                }
            }

            for (LinkedHashMap<String, String> method : Variables.Economy.Gift.possiblePaymentMethods) {
                if (checkIfHasEnoughBalance(itemInMainHand, method)) {
                    return true;
                }
            }
            return false;
        }

        public static void payBalance(Player p, ItemStack itemInMainHand) {
            if (Variables.Economy.Gift.useShulkerBox) {
                for (Material shulker : Variables.GiftMenuEvent.shulkerBoxTags) {
                    if (itemInMainHand.getType() == shulker) {
                        whoPaidListItemStack.put(p.getUniqueId(), itemInMainHand);

                        p.getInventory().removeItem(itemInMainHand);

                        return;
                    }
                }
            }

            for (LinkedHashMap<String, String> method : Variables.Economy.Gift.possiblePaymentMethods) {
                if (checkIfHasEnoughBalance(itemInMainHand, method)) {
                    itemInMainHand.setAmount(itemInMainHand.getAmount() - Integer.parseInt(method.get("amount")));

                    ItemStack balance = new ItemStack(Material.valueOf(method.get("material")), Integer.parseInt(method.get("amount")));

                    whoPaidListItemStack.put(p.getUniqueId(), balance);

                    return;
                }
            }
        }

        public static void returnBalance(Player p) {
            ItemStack balance = whoPaidListItemStack.get(p.getUniqueId());
            if (balance == null) {
                return;
            }
            whoPaidListItemStack.remove(p.getUniqueId());

            if (p.getInventory().firstEmpty() == -1) {
                p.getWorld().dropItem(p.getLocation(), balance);

                return;
            }
            p.getInventory().addItem(balance);
        }

        private static Boolean checkIfHasEnoughBalance(ItemStack itemInMainHand, LinkedHashMap<String, String> method) {
            return itemInMainHand.getType() == Material.valueOf(method.get("material")) && itemInMainHand.getAmount() >= Integer.parseInt(method.get("amount"));
        }
    }
}
