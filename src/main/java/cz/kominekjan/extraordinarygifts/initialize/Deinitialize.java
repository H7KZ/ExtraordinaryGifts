package cz.kominekjan.extraordinarygifts.initialize;

import cz.kominekjan.extraordinarygifts.ExtraordinaryGifts;
import cz.kominekjan.extraordinarygifts.commands.Commands;
import cz.kominekjan.extraordinarygifts.events.GiftAppearanceMenuEvent;
import cz.kominekjan.extraordinarygifts.events.GiftMenuEvent;
import cz.kominekjan.extraordinarygifts.events.GiftPlaceEvent;
import cz.kominekjan.extraordinarygifts.items.Items;
import org.bukkit.event.HandlerList;

import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.variables.Variables.Colors.ANSI_PURPLE;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Colors.ANSI_RESET;

public class Deinitialize {
    private static final ExtraordinaryGifts plugin = ExtraordinaryGifts.plugin;

    public static void commands() {
        Objects.requireNonNull(plugin.getCommand("egifts")).setExecutor(new Commands());
        System.out.println(ANSI_PURPLE + "ExtraordinaryGifts: commands have been reloaded" + ANSI_RESET);
    }

    public static void items() {
        Items.init();
        System.out.println(ANSI_PURPLE + "ExtraordinaryGifts: items have been reloaded" + ANSI_RESET);
    }

    public static void events() {
        HandlerList.unregisterAll(plugin);
        plugin.getServer().getPluginManager().registerEvents(new GiftMenuEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GiftAppearanceMenuEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GiftPlaceEvent(), plugin);
        System.out.println(ANSI_PURPLE + "ExtraordinaryGifts: events have been reloaded" + ANSI_RESET);
    }
}
