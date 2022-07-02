package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.databases.GiftMap;
import cz.kominekjan.extraordinarygifts.events.GiftAppearanceMenuEvent;
import cz.kominekjan.extraordinarygifts.events.GiftMenuEvent;
import cz.kominekjan.extraordinarygifts.guis.GiftAppearanceMenu;
import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import cz.kominekjan.extraordinarygifts.initialize.Deinitialize;
import cz.kominekjan.extraordinarygifts.messages.Disable;
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

        for (Player player : players) {
            if (player.getOpenInventory().getTopInventory().getHolder() instanceof GiftMenu) {
                Disable.givePlayersItemsBack(player);
            }

            if (player.getOpenInventory().getTopInventory().getHolder() instanceof GiftAppearanceMenu) {
                Disable.givePlayersItemsBack(GiftMap.temporary.get(player.getUniqueId()), player);
            }
        }

        for (Player player : players) player.closeInventory();

        System.out.println("Reloading config.");
        plugin.reloadConfig();
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveDefaultConfig();

        System.out.println("ReInitializing.");
        System.out.println("Items");
        Deinitialize.items();

        System.out.println("Events");
        Deinitialize.events();

        System.out.println("Commands");
        Deinitialize.commands();

        System.out.println("Clearing temporary gifts.");
        GiftMap.temporary.clear();
        GiftMenuEvent.receiveItems.clear();
        GiftAppearanceMenuEvent.receiveItems.clear();

        System.out.println("Reloaded!");
    }
}
