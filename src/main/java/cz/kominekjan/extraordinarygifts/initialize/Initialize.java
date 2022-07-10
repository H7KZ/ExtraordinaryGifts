package cz.kominekjan.extraordinarygifts.initialize;

import cz.kominekjan.extraordinarygifts.commands.Commands;
import cz.kominekjan.extraordinarygifts.commands.all.EconomyCommand;
import cz.kominekjan.extraordinarygifts.events.GiftAppearanceMenuEvent;
import cz.kominekjan.extraordinarygifts.events.GiftMenuEvent;
import cz.kominekjan.extraordinarygifts.events.GiftPlaceEvent;
import cz.kominekjan.extraordinarygifts.items.Items;
import cz.kominekjan.extraordinarygifts.tabcompleter.TabCompleter;
import cz.kominekjan.extraordinarygifts.variables.Variables;

import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.logger;
import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;

public class Initialize {
    public static void commands() {
        Objects.requireNonNull(plugin.getCommand("egifts")).setExecutor(new Commands());
        Objects.requireNonNull(plugin.getCommand("egifts")).setTabCompleter(new TabCompleter());
        EconomyCommand.init();
        logger.info("ExtraordinaryGifts: commands have been registered");
    }

    public static void items() {
        Items.init();
        logger.info("ExtraordinaryGifts: items have been loaded");
    }

    public static void events() {
        plugin.getServer().getPluginManager().registerEvents(new GiftMenuEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GiftAppearanceMenuEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GiftPlaceEvent(), plugin);
        logger.info("ExtraordinaryGifts: events have been registered");
    }

    public static void variables() {
        Variables.init();
        logger.info("ExtraordinaryGifts: variables have been registered");
    }
}
