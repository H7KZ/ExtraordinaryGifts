package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.economy.GiftEconomy;
import cz.kominekjan.extraordinarygifts.initialize.Deinitialize;
import cz.kominekjan.extraordinarygifts.initialize.Initialize;
import cz.kominekjan.extraordinarygifts.variables.Variables;
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
        Deinitialize.variables();
        Initialize.variables();

        logger.info("ExtraordinaryGifts: ReInitializing.");
        logger.info("ExtraordinaryGifts: ReInitializing Items");
        Deinitialize.items();
        Initialize.items();

        logger.info("ExtraordinaryGifts: Clearing temporary gifts.");
        Variables.GiftMap.temporary.clear();
        Variables.GiftMenuEvent.receiveItems.clear();
        Variables.GiftAppearanceMenuEvent.receiveItems.clear();
        GiftEconomy.Gift.whoPaidListItemStack.clear();

        logger.info("ExtraordinaryGifts: Reloaded!");
    }
}
