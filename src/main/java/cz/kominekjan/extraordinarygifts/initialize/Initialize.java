package cz.kominekjan.extraordinarygifts.initialize;

import cz.kominekjan.extraordinarygifts.ExtraordinaryGifts;
import cz.kominekjan.extraordinarygifts.commands.Commands;
import cz.kominekjan.extraordinarygifts.databases.GiftDatabase;
import cz.kominekjan.extraordinarygifts.items.Items;
import cz.kominekjan.extraordinarygifts.messages.Colors;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

public class Initialize {
    private static final ExtraordinaryGifts plugin = ExtraordinaryGifts.plugin;

    public static FileConfiguration config;
    private static final String ANSI_CYAN = Colors.ANSI_CYAN;

    private static final String ANSI_RESET = Colors.ANSI_CYAN;

    public static void database() {
        GiftDatabase.setup();
        GiftDatabase.save();
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts Gift Database loaded --" + ANSI_RESET);
    }

    public static void commands() {
        Objects.requireNonNull(plugin.getCommand("egifts")).setExecutor(new Commands());
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts commands have been registered --" + ANSI_RESET);
    }

    public static void items() {
        Items.init();
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts items have been loaded --" + ANSI_RESET);
    }
}
