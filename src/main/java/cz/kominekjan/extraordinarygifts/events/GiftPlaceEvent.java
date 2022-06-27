package cz.kominekjan.extraordinarygifts.events;

import cz.kominekjan.extraordinarygifts.items.Items;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;

public class GiftPlaceEvent implements Listener {
    @EventHandler
    public void onGiftPlace(PlayerInteractEvent e) {
        if (e.getItem() == null) {
            return;
        }

        if (Objects.requireNonNull(e.getItem()).getType() != Material.PLAYER_HEAD) {
            return;
        }

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        for (ItemStack gift : Items.giftsArray) {
            if (gift == e.getItem()) {
                NamespacedKey namespacedKey = new NamespacedKey(plugin, "gift");

                if (!Objects.requireNonNull(Objects.requireNonNull(e.getItem().getItemMeta()).getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING)).equals("gift")) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
