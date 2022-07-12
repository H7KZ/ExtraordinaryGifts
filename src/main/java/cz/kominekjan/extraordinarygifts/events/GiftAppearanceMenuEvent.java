package cz.kominekjan.extraordinarygifts.events;

import cz.kominekjan.extraordinarygifts.economy.GiftEconomy;
import cz.kominekjan.extraordinarygifts.gift.Gift;
import cz.kominekjan.extraordinarygifts.guis.GiftAppearanceMenu;
import cz.kominekjan.extraordinarygifts.variables.Variables;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;
import static cz.kominekjan.extraordinarygifts.items.Items.DropPlayerItems;
import static cz.kominekjan.extraordinarygifts.variables.Variables.GiftAppearanceMenuEvent.doNotReceiveItems;
import static cz.kominekjan.extraordinarygifts.variables.Variables.GiftAppearanceMenuEvent.receiveItems;

public class GiftAppearanceMenuEvent implements Listener {
    private static void cancel(ArrayList<ItemStack> items, Player p) {
        GiftEconomy.Gift.returnBalance(p);

        DropPlayerItems(items, p);
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

        if (currentItem == null) {
            return;
        }

        // GIFT
        try {
            NamespacedKey namespacedKey = new NamespacedKey(plugin, "gift");

            if (Objects.requireNonNull(Objects.requireNonNull(currentItem.getItemMeta()).getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING)).equals("gift")) {

                doNotReceiveItems.put(e.getWhoClicked().getUniqueId(), true);

                Gift.create((Player) e.getWhoClicked(), currentItem);

                e.getWhoClicked().closeInventory();

                return;
            }
        } catch (NullPointerException ignored) {
        }

        // CANCEL
        try {
            NamespacedKey namespacedKey = new NamespacedKey(plugin, "cancel");

            if (Objects.requireNonNull(Objects.requireNonNull(currentItem.getItemMeta()).getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING)).equals("cancel")) {

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

        if (!(e.getInventory().getHolder() instanceof GiftAppearanceMenu)) {
            return;
        }

        if (receiveItems.get(e.getPlayer().getUniqueId()) != null && doNotReceiveItems.get(e.getPlayer().getUniqueId()) == null) {
            receiveItems.remove(e.getPlayer().getUniqueId());
            cancel(Variables.GiftMap.temporary.get(e.getPlayer().getUniqueId()), (Player) e.getPlayer());
            return;
        }

        if (receiveItems.get(e.getPlayer().getUniqueId()) == null && doNotReceiveItems.get(e.getPlayer().getUniqueId()) != null) {
            doNotReceiveItems.remove(e.getPlayer().getUniqueId());

            return;
        }

        if (receiveItems.get(e.getPlayer().getUniqueId()) == null && doNotReceiveItems.get(e.getPlayer().getUniqueId()) == null) {
            cancel(Variables.GiftMap.temporary.get(e.getPlayer().getUniqueId()), (Player) e.getPlayer());
        }
    }
}
