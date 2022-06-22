package cz.kominekjan.extraordinarygifts.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Items {
    public static ItemStack giftMenuGrayGlassPane;
    public static ItemStack giftMenuGreenGlassPane;
    public static ItemStack giftMenuRedGlassPane;

    public static void init() {
        giftMenuGrayGlassPane = Item.create(Material.GRAY_STAINED_GLASS_PANE, " ", null, false);

        giftMenuGreenGlassPane = Item.create(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + "" + ChatColor.BOLD + "Create gift", null, true);

        giftMenuRedGlassPane = Item.create(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "" + ChatColor.BOLD + "Cancel", null, true);
    }
}
