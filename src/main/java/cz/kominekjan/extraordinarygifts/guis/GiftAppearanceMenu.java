package cz.kominekjan.extraordinarygifts.guis;

import cz.kominekjan.extraordinarygifts.items.Items;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class GiftAppearanceMenu implements InventoryHolder {
    public static final String title = "Gift Appearance Menu";
    private final Inventory giftAppearanceMenuInv;
    private final ItemStack neutralGlassPanel = Items.giftAppearanceMenuNeutral;
    private final ItemStack cancelGlassPanel = Items.giftAppearanceMenuCancel;

    public GiftAppearanceMenu() {
        ArrayList<ItemStack> gifts = Items.giftsArray;

        int navbarSize;

        if (gifts.size() < 9) {
            navbarSize = gifts.size() + (9 - gifts.size());
        } else {
            navbarSize = gifts.size() % 9 == 0 ? gifts.size() : gifts.size() + (9 - (gifts.size() % 9));
        }

        int fullInvSize = navbarSize + 9;

        giftAppearanceMenuInv = Bukkit.createInventory(this, fullInvSize, title);
        initialize(navbarSize, fullInvSize, gifts);
    }

    private void initialize(int navbarSize, int fullInvSize, ArrayList<ItemStack> gifts) {
        giftAppearanceMenuInv.setItem(navbarSize, cancelGlassPanel);

        for (int i = navbarSize + 1; i < fullInvSize; i++) {
            giftAppearanceMenuInv.setItem(i, neutralGlassPanel);
        }

        for (ItemStack gift : gifts) {
            giftAppearanceMenuInv.addItem(gift);
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Inventory getInventory() {
        return giftAppearanceMenuInv;
    }
}
