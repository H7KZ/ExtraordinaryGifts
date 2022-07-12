package cz.kominekjan.extraordinarygifts.guis;

import cz.kominekjan.extraordinarygifts.items.Items;
import cz.kominekjan.extraordinarygifts.messages.ErrorMessage;
import cz.kominekjan.extraordinarygifts.variables.Variables;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

@SuppressWarnings("NullableProblems")
public class GiftMenu implements InventoryHolder {
    private final Inventory giftMenuInv;

    public GiftMenu() {
        int invSize = Variables.GiftMenu.size;

        int correctInvSize = 27;

        if (invSize <= 9 && invSize > 0) {
            correctInvSize = 9;
        } else if (invSize <= 18 && invSize > 0) {
            correctInvSize = 18;
        } else if (invSize < 1 || invSize > 27) {
            ErrorMessage.invalidGiftInvSize();
            invSize = 27;
        }

        int fullInvSize = correctInvSize + 9;

        giftMenuInv = Bukkit.createInventory(this, fullInvSize, Variables.GiftMenu.title);

        initialize(invSize, correctInvSize, fullInvSize);
    }

    private void initialize(int invSize, int correctInvSize, int fullInvSize) {
        for (int i = invSize; i < correctInvSize; i++) {
            giftMenuInv.setItem(i, Items.giftMenuNeutral);
        }

        giftMenuInv.setItem(correctInvSize, Items.giftMenuCancel);

        for (int i = correctInvSize + 1; i < fullInvSize - 1; i++) {
            giftMenuInv.setItem(i, Items.giftMenuNeutral);
        }

        giftMenuInv.setItem(fullInvSize - 1, Items.giftMenuAccept);
    }

    @Override
    public Inventory getInventory() {
        return giftMenuInv;
    }
}
