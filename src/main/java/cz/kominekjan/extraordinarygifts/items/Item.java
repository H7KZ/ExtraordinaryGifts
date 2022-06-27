package cz.kominekjan.extraordinarygifts.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;

public class Item extends ItemStack {
    public static ItemStack create(Material material, String name, List<String> lore, Boolean enchant, String key, String value) {
        ItemStack item = createItemStack(material, name, lore, enchant);

        ItemMeta meta = item.getItemMeta();

        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);

        assert meta != null;
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, value);

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack createItemStack(Material material, String name, List<String> lore, Boolean enchant) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;

        meta.setDisplayName(name);

        if (lore != null) {
            List<String> loreList = new ArrayList<>();

            loreList.add(String.valueOf(lore));

            meta.setLore(loreList);
        }

        if (enchant) {
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}
