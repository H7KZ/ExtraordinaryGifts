package cz.kominekjan.extraordinarygifts.variables;

import cz.kominekjan.extraordinarygifts.items.Items;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.config;

public class Variables {
    public static class Message {
        public static final int maxMessageLength = config.getInt("giftMessage.maxMessageLength");
        public static final int maxWordLength = config.getInt("giftMessage.maxWordLength");
        public static final int maxLineLength = config.getInt("giftMessage.maxLineLength");
        public static final List<String> forbiddenWords = config.getStringList("giftMessage.forbiddenWords");
        public static final List<Character> forbiddenLetters = config.getCharacterList("giftMessage.forbiddenLetters");
        public static final String messageColor = config.getString("giftMessage.messageColor");
    }

    public static class Title {
        public static final int maxTitleLength = config.getInt("giftTitle.maxTitleLength");
        public static final int maxWordLength = config.getInt("giftTitle.maxWordLength");
        public static final List<String> forbiddenWords = config.getStringList("giftTitle.forbiddenWords");
        public static final List<Character> forbiddenLetters = config.getCharacterList("giftTitle.forbiddenLetters");
        public static final String titleColor = config.getString("giftTitle.titleColor");
    }

    public static class Permissions {
        public static final String reloadCommandPermission = config.getString("giftPermissions.reloadCommand");
        public static final String createCommandPermission = config.getString("giftPermissions.createCommand");
        public static final String openCommandPermission = config.getString("giftPermissions.openCommand");
        public static final String titleCommandPermission = config.getString("giftPermissions.titleCommand");
        public static final String messageCommandPermission = config.getString("giftPermissions.messageCommand");
        public static final String helpCommandPermission = config.getString("giftPermissions.helpCommand");
        public static final String eGiftCommandPermission = config.getString("giftPermissions.egiftsCommand");
        public static final String allCommandsWithoutReloadPermission = config.getString("giftPermissions.allCommandsWithoutReload");
        public static final String allCommandsWithReloadPermission = config.getString("giftPermissions.allCommandsWithReload");
        public static final String[] commandNameList = {"help", "reload", "create", "open", "title", "message"};
    }

    public static class GiftMenuEvent {
        public static final Map<UUID, Boolean> receiveItems = new HashMap<>();
        public static final Map<UUID, Boolean> doNotReceiveItems = new HashMap<>();
        public static final ItemStack[] giftMenuRemoveItems = {
                Items.giftMenuNeutral,
                Items.giftMenuAccept,
                Items.giftMenuCancel
        };
        public static final ArrayList<Material> giftMenuBannedMaterials = Items.giftMenuBannedMaterials;
        public static final Boolean giftMenuBanShulkerBoxes = config.getBoolean("giftInventory.banShulkerBoxes");
        public static final Set<Material> shulkerBoxTags = Tag.SHULKER_BOXES.getValues();
        public static final ItemStack[] giftMenuNeutralItems = {
                Items.giftMenuNeutral,
        };
        public static final ItemStack[] giftMenuAcceptItem = {
                Items.giftMenuAccept,
        };
        public static final ItemStack[] giftMenuCancelItem = {
                Items.giftMenuCancel,
        };
    }

    public static class GiftAppearanceMenuEvent {
        public static final Map<UUID, Boolean> receiveItems = new HashMap<>();
        public static final Map<UUID, Boolean> doNotReceiveItems = new HashMap<>();
        public static final ItemStack[] giftAppearanceMenuCancelItems = {
                Items.giftAppearanceMenuCancel,
        };
    }

    public static class GiftMap {
        public static final Map<UUID, ArrayList<ItemStack>> temporary = new HashMap<>();
    }

    public static class Colors {
        public static final String ANSI_RED = "\u001B[31m";

        public static final String ANSI_PURPLE = "\033[0;35m";

        public static final String ANSI_RESET = "\u001B[0m";
    }
}
