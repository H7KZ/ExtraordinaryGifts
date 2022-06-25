package cz.kominekjan.extraordinarygifts.guis;

import cz.kominekjan.extraordinarygifts.items.Items;
import cz.kominekjan.extraordinarygifts.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.config;

public class GiftMenu implements InventoryHolder {
    private final Inventory giftMenuInv;
    private final ItemStack neutralGlassPanel = Items.giftMenuNeutral;
    private final ItemStack acceptGlassPanel = Items.giftMenuAccept;
    private final ItemStack cancelGlassPanel = Items.giftMenuCancel;

    public GiftMenu() {
        int invSize = config.getInt("giftInventory.size");

        int correctInvSize = 27;

        if (invSize <= 9 && invSize > 0) {
            correctInvSize = 9;
        } else if (invSize <= 18 && invSize > 0) {
            correctInvSize = 18;
        } else if (invSize < 1 || invSize > 27) {
            Errors.invalidGiftInvSize();
            invSize = 27;
        }

        int fullInvSize = correctInvSize + 9;

        giftMenuInv = Bukkit.createInventory(this, fullInvSize, "Gift Menu");
        initialize(invSize, correctInvSize, fullInvSize);
    }

    private void initialize(int invSize, int correctInvSize, int fullInvSize) {
        for (int i = invSize; i < correctInvSize; i++) {
            giftMenuInv.setItem(i, neutralGlassPanel);
        }

        giftMenuInv.setItem(correctInvSize, cancelGlassPanel);

        for (int i = correctInvSize + 1; i < fullInvSize - 1; i++) {
            giftMenuInv.setItem(i, neutralGlassPanel);
        }

        giftMenuInv.setItem(fullInvSize - 1, acceptGlassPanel);
    }

    @Override
    public Inventory getInventory() {
        return giftMenuInv;
    }
}
