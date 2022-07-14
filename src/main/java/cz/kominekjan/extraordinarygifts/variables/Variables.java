package cz.kominekjan.extraordinarygifts.variables;

import cz.kominekjan.extraordinarygifts.items.Items;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.config;

public class Variables {
    @SuppressWarnings("unchecked")
    public static void init() {
        // ECONOMY
        Economy.enabled = config.getBoolean("economy.enabled");
        Economy.Gift.enabled = config.getBoolean("economy.gift.enabled");
        Economy.Gift.useShulkerBox = config.getBoolean("economy.gift.shulkerBox");
        Economy.Gift.possiblePaymentMethods = (ArrayList<LinkedHashMap<String, String>>) config.get("economy.gift.options");
        Economy.Gift.giveBalanceBack = config.getBoolean("economy.gift.giveBalanceBack");
        Economy.Gift.giveShulkerBoxBack = config.getBoolean("economy.gift.giveShulkerBoxBack");

        // MESSAGE
        Message.maxMessageLength = config.getInt("giftMessage.maxMessageLength");
        Message.maxWordLength = config.getInt("giftMessage.maxWordLength");
        Message.maxLineLength = config.getInt("giftMessage.maxLineLength");
        Message.forbiddenWords = config.getStringList("giftMessage.forbiddenWords");
        Message.forbiddenLetters = config.getCharacterList("giftMessage.forbiddenLetters");
        Message.messageColor = config.getString("giftMessage.messageColor");

        // TITLE
        Title.maxTitleLength = config.getInt("giftTitle.maxTitleLength");
        Title.maxWordLength = config.getInt("giftTitle.maxWordLength");
        Title.forbiddenWords = config.getStringList("giftTitle.forbiddenWords");
        Title.forbiddenLetters = config.getCharacterList("giftTitle.forbiddenLetters");
        Title.titleColor = config.getString("giftTitle.titleColor");

        // GIFT MENU & GIFT APPEARANCE MENU
        GiftMenu.title = config.getString("giftInventory.title");
        GiftMenu.size = config.getInt("giftInventory.size");
        GiftAppearanceMenu.title = config.getString("giftAppearance.title");

        // GIFT MENU EVENT
        GiftMenuEvent.giftMenuRemoveItems = new ItemStack[]{Items.giftMenuNeutral, Items.giftMenuAccept, Items.giftMenuCancel};
        GiftMenuEvent.giftMenuBannedMaterials = Items.giftMenuBannedMaterials;
        GiftMenuEvent.giftMenuBanShulkerBoxes = config.getBoolean("giftInventory.banShulkerBoxes");
        GiftMenuEvent.giftMenuNeutralItems = new ItemStack[]{Items.giftMenuNeutral,};
        GiftMenuEvent.giftMenuAcceptItem = new ItemStack[]{Items.giftMenuAccept,};
        GiftMenuEvent.giftMenuCancelItem = new ItemStack[]{Items.giftMenuCancel,};

        // GIFT APPEARANCE MENU EVENT
        GiftAppearanceMenuEvent.giftAppearanceMenuCancelItems = new ItemStack[]{Items.giftAppearanceMenuCancel,};
    }

    public static class Economy {
        public static Boolean enabled;

        public static class Gift {
            public static Boolean enabled;

            public static Boolean useShulkerBox;

            public static ArrayList<LinkedHashMap<String, String>> possiblePaymentMethods;

            public static Boolean giveBalanceBack;

            public static Boolean giveShulkerBoxBack;
        }
    }

    public static class Message {
        public static int maxMessageLength;
        @SuppressWarnings("unused")
        public static int maxWordLength;
        public static int maxLineLength;
        @SuppressWarnings("unused")
        public static List<String> forbiddenWords;
        @SuppressWarnings("unused")
        public static List<Character> forbiddenLetters;
        public static String messageColor;
    }

    public static class Title {
        public static int maxTitleLength;
        public static int maxWordLength;
        public static List<String> forbiddenWords;
        public static List<Character> forbiddenLetters;
        public static String titleColor;
    }

    public static class Commands {
        public static final String[] commandNameList = {"help", "create", "open", "economy", "title", "message"};
    }

    public static class GiftMenu {
        public static String title;
        public static int size;
    }

    public static class GiftAppearanceMenu {
        public static String title;
    }

    public static class GiftMenuEvent {
        public static final Map<UUID, Boolean> receiveItems = new HashMap<>();
        public static final Map<UUID, Boolean> doNotReceiveItems = new HashMap<>();
        public static final Set<Material> shulkerBoxTags = Tag.SHULKER_BOXES.getValues();
        public static ItemStack[] giftMenuRemoveItems;
        public static ArrayList<Material> giftMenuBannedMaterials;
        public static Boolean giftMenuBanShulkerBoxes;
        @SuppressWarnings("unused")
        public static ItemStack[] giftMenuNeutralItems;
        @SuppressWarnings("unused")
        public static ItemStack[] giftMenuAcceptItem;
        @SuppressWarnings("unused")
        public static ItemStack[] giftMenuCancelItem;
    }

    public static class GiftAppearanceMenuEvent {
        public static final Map<UUID, Boolean> receiveItems = new HashMap<>();
        public static final Map<UUID, Boolean> doNotReceiveItems = new HashMap<>();
        @SuppressWarnings("unused")
        public static ItemStack[] giftAppearanceMenuCancelItems;
    }

    public static class GiftMap {
        public static final Map<UUID, ArrayList<ItemStack>> temporary = new HashMap<>();
    }
}
