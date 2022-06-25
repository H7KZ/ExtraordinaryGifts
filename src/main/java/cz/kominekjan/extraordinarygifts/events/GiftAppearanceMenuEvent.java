package cz.kominekjan.extraordinarygifts.events;

import cz.kominekjan.extraordinarygifts.databases.GiftMap;
import cz.kominekjan.extraordinarygifts.guis.GiftAppearanceMenu;
import cz.kominekjan.extraordinarygifts.items.Items;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;

public class GiftAppearanceMenuEvent implements Listener {

    private static final ItemStack[] giftAppearanceMenuCancelItems = {
            Items.giftAppearanceMenuCancel,
    };

    private static void cancel(ArrayList<ItemStack> items, Player p) {
        for (ItemStack item : items) {
            if (p.getInventory().firstEmpty() == -1) {
                p.getWorld().dropItem(p.getLocation(), item);
                continue;
            }

            p.getInventory().addItem(item);
        }

        GiftMap.temporary.remove(p.getUniqueId());
    }

    @EventHandler
    public void onGiftMenuClick(InventoryClickEvent e) {

        if (Objects.requireNonNull(e.getInventory()).getHolder() == null) {
            return;
        }

        if (!(e.getInventory().getHolder() instanceof GiftAppearanceMenu)) {
            return;
        }

        ItemStack currentItem = e.getCurrentItem();

        e.setCancelled(true);

        if (Arrays.asList(giftAppearanceMenuCancelItems).contains(currentItem)) {
            NamespacedKey key = new NamespacedKey(plugin, "cancel");

            assert currentItem != null;
            if (Objects.requireNonNull(Objects.requireNonNull(currentItem.getItemMeta()).getPersistentDataContainer().get(key, PersistentDataType.STRING)).equals("cancel")) {
                e.getWhoClicked().closeInventory();
            }
        }
    }

    @EventHandler
    public void onGiftMenuClose(InventoryCloseEvent e) {
        if (Objects.requireNonNull(e.getInventory()).getHolder() == null) {
            return;
        }

        if (e.getInventory().getHolder() instanceof GiftAppearanceMenu) {
            cancel(GiftMap.temporary.get(e.getPlayer().getUniqueId()), (Player) e.getPlayer());
        }
    }
}
