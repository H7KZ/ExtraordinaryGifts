package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.initialize.Deinitialize;
import cz.kominekjan.extraordinarygifts.initialize.Initialize;
import cz.kominekjan.extraordinarygifts.variables.Variables;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.config;
import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;

public class ReloadCommand {
    public static final String commandName = "reload";

    public static void command() {
        System.out.println("Reloading...");

        System.out.println("Closing inventories.");
        ArrayList<Player> players = new ArrayList<>(plugin.getServer().getOnlinePlayers());

        for (Player player : players) player.closeInventory();

        System.out.println("Reloading config.");
        plugin.reloadConfig();
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveDefaultConfig();

        System.out.println("ReInitializing.");
        System.out.println("Items");
        Deinitialize.items();
        Initialize.items();

        System.out.println("Variables");
        Deinitialize.variables();
        Initialize.variables();

        System.out.println("Events");
        Deinitialize.events();
        Initialize.events();

        System.out.println("Commands");
        Deinitialize.commands();
        Initialize.commands();

        System.out.println("Clearing temporary gifts.");
        Variables.GiftMap.temporary.clear();
        Variables.GiftMenuEvent.receiveItems.clear();
        Variables.GiftAppearanceMenuEvent.receiveItems.clear();

        System.out.println("Reloaded!");
    }
}
