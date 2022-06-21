package cz.kominekjan.extraordinarygifts.initialize;

import cz.kominekjan.extraordinarygifts.ExtraordinaryGifts;
import cz.kominekjan.extraordinarygifts.commands.Commands;
import cz.kominekjan.extraordinarygifts.databases.GiftStorage;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

public class Initialize {
    private static final ExtraordinaryGifts plugin = ExtraordinaryGifts.plugin;

    public static FileConfiguration config;
    private static final String ANSI_CYAN = ExtraordinaryGifts.ANSI_CYAN;

    private static final String ANSI_RESET = ExtraordinaryGifts.ANSI_CYAN;

    public static void loadConfig() {
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts loading up config file ... --" + ANSI_RESET);
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveConfig();
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts config file loaded --" + ANSI_RESET);
    }

    public static void loadStorage() {
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts Storage loading up ... --" + ANSI_RESET);
        GiftStorage.setup();
        GiftStorage.save();
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts Storage loaded --" + ANSI_RESET);
    }

    public static void registerCommands() {
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts registering the new command \"/eGifts ...\" --" + ANSI_RESET);
        Objects.requireNonNull(plugin.getCommand("egifts")).setExecutor(new Commands());
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts command \"/eGifts\" has been registered --" + ANSI_RESET);
    }

    public static void saveConfig() {
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts saving config file ... --" + ANSI_RESET);
        saveConfig();
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts config file saved --" + ANSI_RESET);
    }

    public static void saveStorage() {
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts saving Storage ... --" + ANSI_RESET);
        GiftStorage.save();
        System.out.println(ANSI_CYAN + "-- ExtraordinaryGifts Storage saved --" + ANSI_RESET);
    }
}
