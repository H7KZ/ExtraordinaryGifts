package cz.kominekjan.extraordinarygifts.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class InitConfig {
    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {

        file = new File(Bukkit.getServer().getPluginManager().getPlugin("ExtraordinaryGifts").getDataFolder(), "ExtraordinaryGifts.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        customFile = YamlConfiguration.loadConfiguration(file);

    }

    public static FileConfiguration get() {
        return customFile;
    }

    public static void save() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
