package cz.kominekjan.extraordinarygifts.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.config;

public class Items {
    public static ItemStack giftMenuNeutral;
    public static ItemStack giftMenuAccept;
    public static ItemStack giftMenuCancel;
    public static ItemStack giftAppearanceMenuNeutral;
    public static ItemStack giftAppearanceMenuCancel;
    public static ArrayList<ItemStack> giftsArray;

    public static void init() {
        initGiftMenuNeutral();
        initGiftMenuAccept();
        initGiftMenuCancel();

        initGiftAppearanceMenuNeutral();
        initGiftAppearanceMenuCancel();

        initGiftsArray();
    }

    private static void initGiftMenuNeutral() {
        Material mat = Material.valueOf(config.getString("giftInventory.gui.neutral.material"));
        String text = config.getString("giftInventory.gui.neutral.text");
        Boolean enchanted = config.getBoolean("giftInventory.gui.neutral.enchanted");
        giftMenuNeutral = Item.create(mat, text, null, enchanted, "neutral", "neutral");
    }

    private static void initGiftMenuAccept() {
        Material mat = Material.valueOf(config.getString("giftInventory.gui.acceptButton.material"));
        String text = config.getString("giftInventory.gui.acceptButton.text");
        Boolean enchanted = config.getBoolean("giftInventory.gui.acceptButton.enchanted");
        giftMenuAccept = Item.create(mat, text, null, enchanted, "accept", "accept");
    }

    private static void initGiftMenuCancel() {
        Material mat = Material.valueOf(config.getString("giftInventory.gui.cancelButton.material"));
        String text = config.getString("giftInventory.gui.cancelButton.text");
        Boolean enchanted = config.getBoolean("giftInventory.gui.cancelButton.enchanted");
        giftMenuCancel = Item.create(mat, text, null, enchanted, "cancel", "cancel");
    }

    private static void initGiftAppearanceMenuNeutral() {
        Material mat = Material.valueOf(config.getString("giftAppearance.gui.neutral.material"));
        String text = config.getString("giftAppearance.gui.neutral.text");
        Boolean enchanted = config.getBoolean("giftAppearance.gui.neutral.enchanted");
        giftAppearanceMenuNeutral = Item.create(mat, text, null, enchanted, "neutral", "neutral");
    }

    private static void initGiftAppearanceMenuCancel() {
        Material mat = Material.valueOf(config.getString("giftAppearance.gui.cancelButton.material"));
        String text = config.getString("giftAppearance.gui.cancelButton.text");
        Boolean enchanted = config.getBoolean("giftAppearance.gui.cancelButton.enchanted");
        giftAppearanceMenuCancel = Item.create(mat, text, null, enchanted, "cancel", "cancel");
    }

    private static void initGiftsArray() {
        ArrayList<LinkedHashMap<String, String>> gifts = (ArrayList<LinkedHashMap<String, String>>) config.get("giftAppearance.items");

        assert gifts != null;
        if (gifts.isEmpty()) {
            LinkedHashMap<String, String> gift = new LinkedHashMap<>();
            gift.put("name", "§c§lRed gift");
            gift.put("uuid", "5726d9d0632e40bda5bcf65839ba2cc98a87bd619c53adf00310d6fc71f042b5");
            gifts.add(gift);
        }

        ArrayList<ItemStack> giftsArrayRaw = new ArrayList<>();

        for (LinkedHashMap<String, String> gift : gifts) {
            String name = gift.get("name");
            String uuid = gift.get("texture");
            ItemStack head = Head.create(name, uuid, "gift", "gift");
            giftsArrayRaw.add(head);
        }

        giftsArray = giftsArrayRaw;
    }
}
