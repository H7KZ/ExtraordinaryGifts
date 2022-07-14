package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.economy.GiftEconomy;
import cz.kominekjan.extraordinarygifts.initialize.Initialize;
import cz.kominekjan.extraordinarygifts.variables.Variables.GiftAppearanceMenuEvent;
import cz.kominekjan.extraordinarygifts.variables.Variables.GiftMap;
import cz.kominekjan.extraordinarygifts.variables.Variables.GiftMenuEvent;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.*;

public class ReloadCommand {
    public static final String commandName = "reload";

    public static void command() {
        logger.info("ExtraordinaryGifts: Reloading...");

        logger.info("ExtraordinaryGifts: Closing inventories.");
        ArrayList<Player> players = new ArrayList<>(plugin.getServer().getOnlinePlayers());

        for (Player player : players) player.closeInventory();

        logger.info("ExtraordinaryGifts: Reloading config.");
        plugin.reloadConfig();
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveDefaultConfig();

        logger.info("ExtraordinaryGifts: ReInitializing Variables");
        Initialize.variables();

        logger.info("ExtraordinaryGifts: ReInitializing.");
        logger.info("ExtraordinaryGifts: ReInitializing Items");
        Initialize.items();

        logger.info("ExtraordinaryGifts: Clearing temporary gifts.");
        GiftMap.temporary.clear();
        GiftMenuEvent.receiveItems.clear();
        GiftAppearanceMenuEvent.receiveItems.clear();
        GiftEconomy.Gift.whoPaidListItemStack.clear();

        logger.info("ExtraordinaryGifts: Reloaded!");
    }
}
