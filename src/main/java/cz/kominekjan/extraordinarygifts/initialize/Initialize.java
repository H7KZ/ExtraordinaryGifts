package cz.kominekjan.extraordinarygifts.initialize;

import cz.kominekjan.extraordinarygifts.ExtraordinaryGifts;
import cz.kominekjan.extraordinarygifts.commands.Commands;
import cz.kominekjan.extraordinarygifts.events.GiftAppearanceMenuEvent;
import cz.kominekjan.extraordinarygifts.events.GiftMenuEvent;
import cz.kominekjan.extraordinarygifts.events.GiftPlaceEvent;
import cz.kominekjan.extraordinarygifts.items.Items;
import cz.kominekjan.extraordinarygifts.messages.Colors;

import java.util.Objects;

public class Initialize {
    private static final ExtraordinaryGifts plugin = ExtraordinaryGifts.plugin;

    private static final String ANSI_CYAN = Colors.ANSI_PURPLE;

    private static final String ANSI_RESET = Colors.ANSI_PURPLE;

    public static void commands() {
        Objects.requireNonNull(plugin.getCommand("egifts")).setExecutor(new Commands());
        System.out.println(ANSI_CYAN + "ExtraordinaryGifts: commands have been registered" + ANSI_RESET);
    }

    public static void items() {
        Items.init();
        System.out.println(ANSI_CYAN + "ExtraordinaryGifts: items have been loaded" + ANSI_RESET);
    }

    public static void events() {
        plugin.getServer().getPluginManager().registerEvents(new GiftMenuEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GiftAppearanceMenuEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GiftPlaceEvent(), plugin);
        System.out.println(ANSI_CYAN + "ExtraordinaryGifts: events have been registered" + ANSI_RESET);
    }
}
