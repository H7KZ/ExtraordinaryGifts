package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.databases.GiftMap;
import cz.kominekjan.extraordinarygifts.events.GiftAppearanceMenuEvent;
import cz.kominekjan.extraordinarygifts.events.GiftMenuEvent;
import cz.kominekjan.extraordinarygifts.guis.GiftAppearanceMenu;
import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import cz.kominekjan.extraordinarygifts.initialize.Initialize;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;
import static cz.kominekjan.extraordinarygifts.events.GiftMenuEvent.giftMenuRemoveItems;

public class ReloadCommand {
    public static final String commandName = "reload";

    public static void command() {
        System.out.println("Reloading...");

        System.out.println("Closing inventories.");
        ArrayList<Player> players = new ArrayList<>(plugin.getServer().getOnlinePlayers());

        for (Player player : players) {
            if (player.getOpenInventory().getTopInventory().getHolder() instanceof GiftMenu) {
                cancel(player);
            }

            if (player.getOpenInventory().getTopInventory().getHolder() instanceof GiftAppearanceMenu) {
                cancel(GiftMap.temporary.get(player.getUniqueId()), player);
            }
        }

        for (Player player : players) player.closeInventory();

        System.out.println("Reloading config.");
        plugin.reloadConfig();

        System.out.println("ReInitializing.");
        System.out.println("Items");
        Initialize.items();

        System.out.println("Events");
        Initialize.events();

        System.out.println("Commands");
        Initialize.commands();

        System.out.println("Clearing temporary gifts.");
        GiftMap.temporary.clear();
        GiftMenuEvent.receiveItems.clear();
        GiftAppearanceMenuEvent.receiveItems.clear();

        System.out.println("Reloaded!");
    }

    private static void cancel(Player p) {
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

    private static void cancel(ArrayList<ItemStack> items, Player p) {
        for (ItemStack item : items) {
            if (p.getInventory().firstEmpty() == -1) {
                p.getWorld().dropItem(p.getLocation(), item);
                continue;
            }

            p.getInventory().addItem(item);
        }

        GiftMap.temporary.remove(p.getUniqueId());
    }
}
