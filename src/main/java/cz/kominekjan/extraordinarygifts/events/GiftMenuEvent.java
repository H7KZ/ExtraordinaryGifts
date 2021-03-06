package cz.kominekjan.extraordinarygifts.events;

import cz.kominekjan.extraordinarygifts.guis.GiftAppearanceMenu;
import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import cz.kominekjan.extraordinarygifts.variables.Variables.GiftMap;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;
import static cz.kominekjan.extraordinarygifts.events.EventFunctions.*;
import static cz.kominekjan.extraordinarygifts.variables.Variables.GiftMenuEvent.*;

public class GiftMenuEvent implements Listener {
    public static final String[] bannedItems = {"gift", "accept", "neutral", "cancel"};

    @EventHandler
    public void onGiftMenuClick(InventoryClickEvent e) {

        if (Objects.requireNonNull(e.getInventory()).getHolder() == null) {
            return;
        }

        if (!(e.getInventory().getHolder() instanceof GiftMenu)) {
            return;
        }

        ItemStack currentItem = e.getCurrentItem();

        if (currentItem == null) {
            return;
        }

        if (giftMenuBanShulkerBoxes && shulkerBoxTags.contains(currentItem.getType())) {
            e.setCancelled(true);

            return;
        }

        if (giftMenuBannedMaterials != null && giftMenuBannedMaterials.contains(currentItem.getType())) {
            e.setCancelled(true);

            return;
        }

        // GIFT
        try {
            NamespacedKey namespacedKey = new NamespacedKey(plugin, "gift");

            if (Objects.requireNonNull(Objects.requireNonNull(currentItem.getItemMeta()).getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING)).equals("gift")) {
                e.setCancelled(true);

                return;
            }
        } catch (NullPointerException ignored) {
        }

        if (e.getClickedInventory() == e.getWhoClicked().getInventory() && Objects.requireNonNull(e.getClickedInventory()).contains(currentItem)) {
            return;
        }

        // NEUTRAL
        try {
            NamespacedKey namespacedKey = new NamespacedKey(plugin, "neutral");

            if (Objects.requireNonNull(Objects.requireNonNull(currentItem.getItemMeta()).getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING)).equals("neutral")) {
                e.setCancelled(true);

                return;
            }
        } catch (NullPointerException ignored) {
        }

        // ACCEPT
        try {
            NamespacedKey namespacedKey = new NamespacedKey(plugin, "accept");

            if (Objects.requireNonNull(Objects.requireNonNull(currentItem.getItemMeta()).getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING)).equals("accept")) {
                if (isGiftEmpty(e.getInventory())) {
                    e.setCancelled(true);

                    return;
                }

                e.setCancelled(true);

                doNotReceiveItems.put(e.getWhoClicked().getUniqueId(), true);

                GiftMap.temporary.put(e.getWhoClicked().getUniqueId(), removeGiftMenuItems(e.getInventory().getContents()));

                GiftAppearanceMenu giftAppearanceMenu = new GiftAppearanceMenu();

                e.getWhoClicked().openInventory(giftAppearanceMenu.getInventory());

                return;
            }
        } catch (NullPointerException ignored) {
        }

        // CANCEL
        try {
            NamespacedKey namespacedKey = new NamespacedKey(plugin, "cancel");

            if (Objects.requireNonNull(Objects.requireNonNull(currentItem.getItemMeta()).getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING)).equals("cancel")) {
                e.setCancelled(true);

                receiveItems.put(e.getWhoClicked().getUniqueId(), true);

                e.getWhoClicked().closeInventory();
            }
        } catch (NullPointerException ignored) {
        }
    }

    @EventHandler
    public void onGiftMenuClose(InventoryCloseEvent e) {
        if (Objects.requireNonNull(e.getInventory()).getHolder() == null) {
            return;
        }

        if (!(e.getInventory().getHolder() instanceof GiftMenu)) {
            return;
        }

        if (receiveItems.get(e.getPlayer().getUniqueId()) != null && doNotReceiveItems.get(e.getPlayer().getUniqueId()) == null) {
            receiveItems.remove(e.getPlayer().getUniqueId());

            cancel(e);

            return;
        }

        if (receiveItems.get(e.getPlayer().getUniqueId()) == null && doNotReceiveItems.get(e.getPlayer().getUniqueId()) != null) {
            doNotReceiveItems.remove(e.getPlayer().getUniqueId());

            return;
        }

        if (receiveItems.get(e.getPlayer().getUniqueId()) == null && doNotReceiveItems.get(e.getPlayer().getUniqueId()) == null) {
            cancel(e);
        }
    }
}
