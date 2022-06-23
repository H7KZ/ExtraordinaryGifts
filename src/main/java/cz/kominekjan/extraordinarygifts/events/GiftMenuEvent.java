package cz.kominekjan.extraordinarygifts.events;

import cz.kominekjan.extraordinarygifts.gift.Gift;
import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import cz.kominekjan.extraordinarygifts.items.Items;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;

public class GiftMenuEvent implements Listener {

    private static final ItemStack[] giftMenuNeutralItems = {
            Items.giftMenuGrayGlassPane,
    };

    private static final ItemStack[] giftMenuAcceptItem = {
            Items.giftMenuGreenGlassPane,
    };

    private static final ItemStack[] giftMenuCancelItem = {
            Items.giftMenuRedGlassPane,
    };

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
                Gift.create(e.getInventory());
                e.setCancelled(true);
            }
        }

        if (Arrays.asList(giftMenuCancelItem).contains(currentItem)) {
            NamespacedKey key = new NamespacedKey(plugin, "cancel");

            assert currentItem != null;
            if (Objects.requireNonNull(Objects.requireNonNull(currentItem.getItemMeta()).getPersistentDataContainer().get(key, PersistentDataType.STRING)).equals("cancel")) {
                cancel();
                e.setCancelled(true);
            }
        }
    }

    private static void cancel() {

    }
}
