package cz.kominekjan.extraordinarygifts.gift;


import cz.kominekjan.extraordinarygifts.persistentdatatypes.PersistentDataItemStackArray;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;

public class Gift {
    public static void create(Player p, ItemStack giftType) {

    }

    public static void open(Player p) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, "contents");

        List<ItemStack> items = Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(p.getItemInUse()).getItemMeta()).getPersistentDataContainer().get(namespacedKey, PersistentDataItemStackArray.type));

        p.sendMessage(items.toString());
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
