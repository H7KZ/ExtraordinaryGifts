package cz.kominekjan.extraordinarygifts.messages;

import cz.kominekjan.extraordinarygifts.guis.GiftAppearanceMenu;
import cz.kominekjan.extraordinarygifts.guis.GiftMenu;
import cz.kominekjan.extraordinarygifts.variables.Variables;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Colors.ANSI_PURPLE;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Colors.ANSI_RESET;
import static cz.kominekjan.extraordinarygifts.variables.Variables.GiftMenuEvent.giftMenuRemoveItems;

public class Disable {
    public static void closing() {
        System.out.println(ANSI_PURPLE + "ExtraordinaryGifts: has been shutdown successfully" + ANSI_RESET);
    }

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
}
