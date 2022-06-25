package cz.kominekjan.extraordinarygifts.events;

import cz.kominekjan.extraordinarygifts.databases.GiftMap;
import cz.kominekjan.extraordinarygifts.guis.GiftAppearanceMenu;
import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import cz.kominekjan.extraordinarygifts.items.Items;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;

public class GiftMenuEvent implements Listener {

    private static final Map<UUID, Boolean> receiveItems = new HashMap<>();
    private static final ItemStack[] giftMenuNeutralItems = {
            Items.giftMenuNeutral,
    };

    private static final ItemStack[] giftMenuAcceptItem = {
            Items.giftMenuAccept,
    };

    private static final ItemStack[] giftMenuCancelItem = {
            Items.giftMenuCancel,
    };

    private static final ItemStack[] giftMenuRemoveItems = {
            Items.giftMenuNeutral,
            Items.giftMenuAccept,
            Items.giftMenuCancel
    };

    private static ArrayList<ItemStack> removeGiftMenuItems(ItemStack[] items) {
        ArrayList<ItemStack> result = new ArrayList<>();

        for (ItemStack item : items) {
            if (Arrays.asList(giftMenuRemoveItems).contains(item) || item == null) {
                continue;
            }

            result.add(item);
        }

        return result;
    }

    private static void cancel(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        for (ItemStack item : e.getInventory().getContents()) {
            if (Arrays.asList(giftMenuRemoveItems).contains(item) || item == null) {
                continue;
            }

            if (p.getInventory().firstEmpty() == -1) {
                p.getWorld().dropItem(p.getLocation(), item);
                continue;
            }

            p.getInventory().addItem(item);
        }
    }

    @EventHandler
    public void onGiftMenuClick(InventoryClickEvent e) {

        if (Objects.requireNonNull(e.getInventory()).getHolder() == null) {
            return;
        }

        if (!(e.getInventory().getHolder() instanceof GiftMenu)) {
            return;
        }

        ItemStack currentItem = e.getCurrentItem();

        if (e.getClickedInventory() == e.getWhoClicked().getInventory() && Objects.requireNonNull(e.getClickedInventory()).contains(currentItem)) {
            return;
        }

        if (Arrays.asList(giftMenuNeutralItems).contains(currentItem)) {
            NamespacedKey key = new NamespacedKey(plugin, "neutral");

            assert currentItem != null;
            if (Objects.requireNonNull(Objects.requireNonNull(currentItem.getItemMeta()).getPersistentDataContainer().get(key, PersistentDataType.STRING)).equals("neutral")) {
                e.setCancelled(true);
            }
        }

        if (Arrays.asList(giftMenuAcceptItem).contains(currentItem)) {
            NamespacedKey key = new NamespacedKey(plugin, "accept");

            assert currentItem != null;
            if (Objects.requireNonNull(Objects.requireNonNull(currentItem.getItemMeta()).getPersistentDataContainer().get(key, PersistentDataType.STRING)).equals("accept")) {
                GiftMap.temporary.put(e.getWhoClicked().getUniqueId(), removeGiftMenuItems(e.getInventory().getContents()));
                GiftAppearanceMenu giftAppearanceMenu = new GiftAppearanceMenu();
                e.getWhoClicked().openInventory(giftAppearanceMenu.getInventory());
                e.setCancelled(true);
            }
        }

        if (Arrays.asList(giftMenuCancelItem).contains(currentItem)) {
            NamespacedKey key = new NamespacedKey(plugin, "cancel");

            assert currentItem != null;
            if (Objects.requireNonNull(Objects.requireNonNull(currentItem.getItemMeta()).getPersistentDataContainer().get(key, PersistentDataType.STRING)).equals("cancel")) {
                receiveItems.put(e.getWhoClicked().getUniqueId(), true);
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
            }
        }
    }

    @EventHandler
    public void onGiftMenuClose(InventoryCloseEvent e) {
        if (Objects.requireNonNull(e.getInventory()).getHolder() == null) {
            return;
        }

        if (e.getInventory().getHolder() instanceof GiftMenu && receiveItems.get(e.getPlayer().getUniqueId()) != null) {
            receiveItems.remove(e.getPlayer().getUniqueId());
            cancel(e);
        }
    }
}
