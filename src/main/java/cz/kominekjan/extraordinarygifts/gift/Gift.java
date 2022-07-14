package cz.kominekjan.extraordinarygifts.gift;


import cz.kominekjan.extraordinarygifts.economy.GiftEconomy;
import cz.kominekjan.extraordinarygifts.variables.Variables.Economy;
import cz.kominekjan.extraordinarygifts.variables.Variables.GiftMap;
import cz.kominekjan.extraordinarygifts.variables.Variables.GiftMenuEvent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;
import static cz.kominekjan.extraordinarygifts.validateAndFormat.TitleMessageValidate.isPlayerHoldingGift;

public class Gift {
    public static void create(Player p, ItemStack giftAppearance) {
        ArrayList<ItemStack> contents = GiftMap.temporary.get(p.getUniqueId());

        if (Economy.Gift.giveBalanceBack) {
            contents.add(GiftEconomy.Gift.whoPaidListItemStack.get(p.getUniqueId()));
        } else if (Economy.Gift.giveShulkerBoxBack) {
            Material ifShulker = GiftEconomy.Gift.whoPaidListItemStack.get(p.getUniqueId()).getType();

            for (Material shulker : GiftMenuEvent.shulkerBoxTags) {
                if (ifShulker == shulker) {
                    contents.add(GiftEconomy.Gift.whoPaidListItemStack.get(p.getUniqueId()));

                    break;
                }
            }
        }

        ItemStack gift = addGiftContents(contents, giftAppearance);

        if (p.getInventory().firstEmpty() == -1) {
            p.getWorld().dropItem(p.getLocation(), gift);
        } else {
            p.getInventory().addItem(gift);
        }

        GiftMap.temporary.remove(p.getUniqueId());

        GiftEconomy.Gift.whoPaidListItemStack.remove(p.getUniqueId());
    }

    public static void open(Player p) {
        if (!isPlayerHoldingGift(p)) {
            return;
        }

        List<ItemStack> items = new ArrayList<>();

        try {
            NamespacedKey namespacedKey = new NamespacedKey(plugin, "contents");

            String contents = Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(p.getInventory().getItemInMainHand()).getItemMeta()).getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING));

            YamlConfiguration restoreConfig = new YamlConfiguration();
            restoreConfig.loadFromString(contents);

            //noinspection unchecked
            items = (List<ItemStack>) restoreConfig.get("contents");
        } catch (NullPointerException ignored) {
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

        p.getInventory().remove(p.getInventory().getItemInMainHand());

        assert items != null;

        for (ItemStack item : items) {
            if (item == null) {
                continue;
            }
            if (p.getInventory().firstEmpty() == -1) {
                p.getWorld().dropItem(p.getLocation(), item);
            } else {
                p.getInventory().addItem(item);
            }
        }
    }

    private static ItemStack addGiftContents(List<ItemStack> items, ItemStack gift) {
        ItemMeta meta = gift.getItemMeta();

        YamlConfiguration itemConfig = new YamlConfiguration();
        itemConfig.set("contents", items);

        String contents = itemConfig.saveToString();

        assert meta != null;
        try {
            NamespacedKey namespacedKey = new NamespacedKey(plugin, "contents");

            meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, contents);
        } catch (NullPointerException ignored) {
        }

        gift.setItemMeta(meta);

        return gift;
    }
}
