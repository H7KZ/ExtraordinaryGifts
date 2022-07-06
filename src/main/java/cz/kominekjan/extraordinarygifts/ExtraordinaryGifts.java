package cz.kominekjan.extraordinarygifts;

import cz.kominekjan.extraordinarygifts.guis.GiftAppearanceMenu;
import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import cz.kominekjan.extraordinarygifts.initialize.Initialize;
import cz.kominekjan.extraordinarygifts.messages.Disable;
import cz.kominekjan.extraordinarygifts.messages.Enable;
import cz.kominekjan.extraordinarygifts.variables.Variables;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;

import static cz.kominekjan.extraordinarygifts.variables.Variables.GiftMenuEvent.giftMenuRemoveItems;

public final class ExtraordinaryGifts extends JavaPlugin {

    public static ExtraordinaryGifts plugin;

    public static FileConfiguration config;

    public static void stopping() {
        System.out.println("Closing inventories.");
        ArrayList<Player> players = new ArrayList<>(plugin.getServer().getOnlinePlayers());

        for (Player player : players) {
            if (player.getOpenInventory().getTopInventory().getHolder() instanceof GiftMenu) {
                cancel(player);
            }

            if (player.getOpenInventory().getTopInventory().getHolder() instanceof GiftAppearanceMenu) {
                givePlayersItemsBack(Variables.GiftMap.temporary.get(player.getUniqueId()), player);
            }
        }

        for (Player player : players) player.closeInventory();
    }

    private static void cancel(Player p) {
        givePlayersItemsBack(p);
    }

    public static void givePlayersItemsBack(Player p) {
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
        //STARTING UP
        Enable.starting();

        plugin = this;

        //CONFIG FILE LOAD & COPY DEFAULTS
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();

        //ITEMS LOADING
        Initialize.items();

        //VARIABLES INITIALIZATION
        Initialize.variables();

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
        Disable.closing();
    }
}
