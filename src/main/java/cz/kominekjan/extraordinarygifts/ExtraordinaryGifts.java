package cz.kominekjan.extraordinarygifts;

import cz.kominekjan.extraordinarygifts.config.InitConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExtraordinaryGifts extends JavaPlugin {

    @Override
    public void onEnable() {
        //STARTING UP
        System.out.println("-- ExtraordinaryGifts are starting --");

        //CONFIG FILE SETUP & SAVE
        System.out.println("-- ExtraordinaryGifts loading up config file... --");
        InitConfig.setup();
        InitConfig.save();
        System.out.println("-- ExtraordinaryGifts config file loaded --");

        //ENDING OF START
        System.out.println("-- ExtraordinaryGifts started with no signs of error until you do something in game ;) haha --");
    }

    @Override
    public void onDisable() {
        //SHUTTING DOWN
        System.out.println("-- ExtraordinaryGifts are being shutting down --");

        //CONFIG FILE SAVE
        System.out.println("-- ExtraordinaryGifts saving config file... --");
        InitConfig.save();
        System.out.println("-- ExtraordinaryGifts config file saved --");

        //ENDING OF SHUTDOWN
        System.out.println("-- ExtraordinaryGifts has been shutdown successfully --");
    }
}
