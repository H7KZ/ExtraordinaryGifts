package cz.kominekjan.extraordinarygifts.items;

import cz.kominekjan.extraordinarygifts.economy.GiftEconomy;
import cz.kominekjan.extraordinarygifts.variables.Variables.GiftMap;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.config;
import static cz.kominekjan.extraordinarygifts.variables.Variables.GiftMenuEvent.giftMenuRemoveItems;

@SuppressWarnings("unchecked")
public class Items {
    public static ItemStack giftMenuNeutral;
    public static ItemStack giftMenuAccept;
    public static ItemStack giftMenuCancel;
    public static ArrayList<Material> giftMenuBannedMaterials;
    public static ItemStack giftAppearanceMenuNeutral;
    public static ItemStack giftAppearanceMenuCancel;
    public static ArrayList<ItemStack> giftsArray;

    public static void init() {
        initGiftMenuNeutral();
        initGiftMenuAccept();
        initGiftMenuCancel();

        initGiftMenuBannedMaterials();

        initGiftAppearanceMenuNeutral();
        initGiftAppearanceMenuCancel();

        initGiftsArray();
    }

    public static void givePlayersItemsBack(Player p) {
        GiftEconomy.Gift.returnBalance(p);

        for (ItemStack item : p.getOpenInventory().getTopInventory().getContents()) {
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

    public static void givePlayersItemsBack(ArrayList<ItemStack> items, Player p) {
        GiftEconomy.Gift.returnBalance(p);

        DropPlayerItems(items, p);
    }

    public static void DropPlayerItems(ArrayList<ItemStack> items, Player p) {
        for (ItemStack item : items) {
            if (item == null) {
                continue;
            }

            if (p.getInventory().firstEmpty() == -1) {
                p.getWorld().dropItem(p.getLocation(), item);
                continue;
            }

            p.getInventory().addItem(item);
        }

        GiftMap.temporary.remove(p.getUniqueId());
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

    private static void initGiftMenuBannedMaterials() {
        ArrayList<Material> materials = new ArrayList<>();

        List<String> configBannedMaterials = config.getStringList("giftInventory.bannedMaterials");

        for (String configBannedMaterial : configBannedMaterials) {
            Material mat = Material.valueOf(configBannedMaterial);

            materials.add(mat);
        }

        giftMenuBannedMaterials = materials;
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

            gift.put("name", "??c??lRed gift");
            gift.put("uuid", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTcyNmQ5ZDA2MzJlNDBiZGE1YmNmNjU4MzliYTJjYzk4YTg3YmQ2MTljNTNhZGYwMDMxMGQ2ZmM3MWYwNDJiNSJ9fX0");

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
