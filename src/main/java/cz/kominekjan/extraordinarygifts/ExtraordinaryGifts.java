package cz.kominekjan.extraordinarygifts;

import cz.kominekjan.extraordinarygifts.economy.GiftEconomy;
import cz.kominekjan.extraordinarygifts.guis.GiftAppearanceMenu;
import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import cz.kominekjan.extraordinarygifts.initialize.Initialize;
import cz.kominekjan.extraordinarygifts.messages.Enable;
import cz.kominekjan.extraordinarygifts.variables.Variables;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import static cz.kominekjan.extraordinarygifts.variables.Variables.GiftMenuEvent.giftMenuRemoveItems;

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

    public static void givePlayersItemsBack(Player p) {
        GiftEconomy.Gift.returnBalance(p);

        for (ItemStack item : p.getOpenInventory().getTopInventory().getContents()) {
            //noinspection DuplicatedCode
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
            if (p.getInventory().firstEmpty() == -1) {
                p.getWorld().dropItem(p.getLocation(), item);
                continue;
            }

            p.getInventory().addItem(item);
        }

        Variables.GiftMap.temporary.remove(p.getUniqueId());
    }

    @Override
    public void onEnable() {
        plugin = this;

        logger = plugin.getLogger();

        //STARTING UP
        Enable.starting();

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
        Enable.closing();
    }

    @Override
    public void onDisable() {
        //MAKING SURE THAT PLAYERS INV STAYS CLEAR WHEN DISABLING THIS PLUGIN
        stopping();

        //ENDING OF SHUTDOWN
        logger.info("ExtraordinaryGifts: Shut down successfully");
    }
}
