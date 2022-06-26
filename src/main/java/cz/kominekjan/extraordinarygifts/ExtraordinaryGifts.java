package cz.kominekjan.extraordinarygifts;

import cz.kominekjan.extraordinarygifts.databases.GiftDatabase;
import cz.kominekjan.extraordinarygifts.initialize.Initialize;
import cz.kominekjan.extraordinarygifts.messages.Disable;
import cz.kominekjan.extraordinarygifts.messages.Enable;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExtraordinaryGifts extends JavaPlugin {

    public static ExtraordinaryGifts plugin;

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        //STARTING UP
        Enable.starting();

        plugin = this;

        //CONFIG FILE LOAD & COPY DEFAULTS
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();

        //GIFT DATABASE SETUP & LOAD
        Initialize.database();

        //COMMAND REGISTERING
        Initialize.commands();

        //ITEMS LOADING
        Initialize.items();

        //EVENT REGISTERING
        Initialize.events();

        //TYPES INIT
        Initialize.types();

        //ENDING OF START
        Enable.closing();
    }

    @Override
    public void onDisable() {
        //SHUTTING DOWN
        Disable.starting();

        //GIFT DATABASE FILE SAVE
        GiftDatabase.save();

        //ENDING OF SHUTDOWN
        Disable.closing();
    }
}
