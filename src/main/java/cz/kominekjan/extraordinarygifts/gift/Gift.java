package cz.kominekjan.extraordinarygifts.gift;


import cz.kominekjan.extraordinarygifts.economy.GiftEconomy;
import cz.kominekjan.extraordinarygifts.persistentdatatypes.PersistentDataItemStackArray;
import cz.kominekjan.extraordinarygifts.variables.Variables;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;

public class Gift {
    public static void create(Player p, ItemStack giftAppearance) {
        ArrayList<ItemStack> contents = Variables.GiftMap.temporary.get(p.getUniqueId());

        if (Variables.Economy.Gift.giveBalanceBack) {
            contents.add(GiftEconomy.Gift.whoPaidListItemStack.get(p.getUniqueId()));
        } else if (Variables.Economy.Gift.giveShulkerBoxBack) {
            Material ifShulker = GiftEconomy.Gift.whoPaidListItemStack.get(p.getUniqueId()).getType();

            for (Material shulker : Variables.GiftMenuEvent.shulkerBoxTags) {
                if (ifShulker == shulker) {
                    contents.add(GiftEconomy.Gift.whoPaidListItemStack.get(p.getUniqueId()));
                    break;
                }
            }
        }

        ItemStack gift = addGiftContents(contents, giftAppearance);

        Variables.GiftMap.temporary.remove(p.getUniqueId());

        if (p.getInventory().firstEmpty() == -1) {
            p.getWorld().dropItem(p.getLocation(), gift);
        } else {
            p.getInventory().addItem(gift);
        }
    }

    public static void open(Player p) {
        try {
            NamespacedKey namespacedKey = new NamespacedKey(plugin, "contents");

            List<ItemStack> items = Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(p.getInventory().getItemInMainHand()).getItemMeta()).getPersistentDataContainer().get(namespacedKey, PersistentDataItemStackArray.type));

            p.getInventory().remove(p.getInventory().getItemInMainHand());

            items.forEach(item -> {
                if (p.getInventory().firstEmpty() == -1) {
                    p.getWorld().dropItem(p.getLocation(), item);
                } else {
                    p.getInventory().addItem(item);
                }
            });
        } catch (NullPointerException ignored) {
        }
    }

    private static ItemStack addGiftContents(List<ItemStack> items, ItemStack gift) {
        ItemMeta meta = gift.getItemMeta();

        NamespacedKey namespacedKey = new NamespacedKey(plugin, "contents");

        assert meta != null;
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataItemStackArray.type, items);

        gift.setItemMeta(meta);

        return gift;
    }
}
