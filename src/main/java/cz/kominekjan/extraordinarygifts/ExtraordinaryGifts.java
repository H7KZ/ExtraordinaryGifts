package cz.kominekjan.extraordinarygifts;

import cz.kominekjan.extraordinarygifts.bstats.Metrics;
import cz.kominekjan.extraordinarygifts.guis.GiftAppearanceMenu;
import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import cz.kominekjan.extraordinarygifts.initialize.Initialize;
import cz.kominekjan.extraordinarygifts.messages.EnableMessage;
import cz.kominekjan.extraordinarygifts.variables.Variables;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Logger;

import static cz.kominekjan.extraordinarygifts.items.Items.givePlayersItemsBack;

public final class ExtraordinaryGifts extends JavaPlugin {

    public static ExtraordinaryGifts plugin;

    public static FileConfiguration config;

    public static Logger logger;

    public static void stopping() {
        logger.info("ExtraordinaryGifts: Closing inventories.");
        ArrayList<Player> players = new ArrayList<>(plugin.getServer().getOnlinePlayers());

        for (Player player : players) {
            if (player.getOpenInventory().getTopInventory().getHolder() instanceof GiftMenu) {
                givePlayersItemsBack(player);
            }

            if (player.getOpenInventory().getTopInventory().getHolder() instanceof GiftAppearanceMenu) {
                givePlayersItemsBack(Variables.GiftMap.temporary.get(player.getUniqueId()), player);
            }
        }

        for (Player player : players) player.closeInventory();

        logger.info("ExtraordinaryGifts: All inventories closed!.");
    }

    @Override
    public void onEnable() {
        plugin = this;

        logger = plugin.getLogger();

        new Metrics(this, 15712);

        //STARTING UP
        EnableMessage.starting();

        //CONFIG FILE LOAD & COPY DEFAULTS
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();

        //VARIABLES INITIALIZATION
        Initialize.variables();

        //ITEMS LOADING
        Initialize.items();

        //COMMAND REGISTERING
        Initialize.commands();

        //EVENT REGISTERING
        Initialize.events();

        //ENDING OF START
        EnableMessage.closing();
    }

    @Override
    public void onDisable() {
        //MAKING SURE THAT PLAYERS INV STAYS CLEAR WHEN DISABLING THIS PLUGIN
        stopping();

        //ENDING OF SHUTDOWN
        logger.info("ExtraordinaryGifts: Shut down successfully");
    }
}
