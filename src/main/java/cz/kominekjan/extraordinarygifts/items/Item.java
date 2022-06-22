package cz.kominekjan.extraordinarygifts.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Item extends ItemStack {
    public static ItemStack create(Material material, String name, List<String> lore, Boolean enchant) {
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
