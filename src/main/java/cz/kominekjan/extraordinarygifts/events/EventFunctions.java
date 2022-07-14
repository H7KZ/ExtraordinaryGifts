package cz.kominekjan.extraordinarygifts.events;

import cz.kominekjan.extraordinarygifts.economy.GiftEconomy;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;
import static cz.kominekjan.extraordinarygifts.events.GiftMenuEvent.bannedItems;
import static cz.kominekjan.extraordinarygifts.items.Items.DropPlayerItems;

public class EventFunctions {
    static ArrayList<ItemStack> removeGiftMenuItems(ItemStack[] items) {
        ArrayList<ItemStack> result = new ArrayList<>();

        for (ItemStack item : items) {
            if (IsBannedItem(item)) {
                continue;
            }

            result.add(item);
        }

        return result;
    }

    static void cancel(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();

        GiftEconomy.Gift.returnBalance(p);

        for (ItemStack item : e.getInventory().getContents()) {
            if (IsBannedItem(item)) {
                continue;
            }

            if (p.getInventory().firstEmpty() == -1) {
                p.getWorld().dropItem(p.getLocation(), item);
                continue;
            }

            p.getInventory().addItem(item);
        }
    }

    static Boolean isGiftEmpty(Inventory inv) {
        ArrayList<ItemStack> items = new ArrayList<>(Arrays.asList(inv.getContents()));

        items.removeIf(item -> {
            IsBannedItem(item);
            return IsBannedItem(item);
        });

        return items.isEmpty();
    }

    private static Boolean IsBannedItem(ItemStack item) {
        for (String bannedItem : bannedItems) {
            try {
                NamespacedKey namespacedKey = new NamespacedKey(plugin, bannedItem);

                if (Objects.requireNonNull(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING)).equals(bannedItem)) {
                    return true;
                }
            } catch (NullPointerException ignored) {
            }
        }

        return false;
    }

    static void cancel(ArrayList<ItemStack> items, Player p) {
        GiftEconomy.Gift.returnBalance(p);

        DropPlayerItems(items, p);
    }
}
