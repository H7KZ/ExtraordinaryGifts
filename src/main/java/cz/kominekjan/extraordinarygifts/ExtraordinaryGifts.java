package cz.kominekjan.extraordinarygifts;

import cz.kominekjan.extraordinarygifts.commands.Commands;
import cz.kominekjan.extraordinarygifts.databases.GiftStorage;
import cz.kominekjan.extraordinarygifts.initialize.Initialize;
import cz.kominekjan.extraordinarygifts.messages.Disable;
import cz.kominekjan.extraordinarygifts.messages.Enable;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ExtraordinaryGifts extends JavaPlugin {

    public static ExtraordinaryGifts plugin;

    public static FileConfiguration config;

    public static final String ANSI_CYAN = "\u001B[36m";

    public static final String ANSI_RESET = "\u001B[0m";

    @Override
    public void onEnable() {
        //STARTING UP
        Enable.starting();

        plugin = this;

        //CONFIG FILE LOAD & COPY DEFAULTS
        Initialize.loadConfig();
        config = Initialize.config;

        //STORAGE SETUP & LOAD
        Initialize.loadStorage();

        //COMMAND REGISTERING
        Initialize.registerCommands();

        //ENDING OF START
        Enable.closing();
    }

    @Override
    public void onDisable() {
        //SHUTTING DOWN
        Disable.starting();

        //CONFIG FILE SAVE
        Initialize.saveConfig();

        //STORAGE SAVE
        Initialize.saveStorage();

        //ENDING OF SHUTDOWN
        Disable.closing();
    }
}
