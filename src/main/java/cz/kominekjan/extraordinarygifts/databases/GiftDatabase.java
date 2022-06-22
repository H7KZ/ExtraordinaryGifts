package cz.kominekjan.extraordinarygifts.databases;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.config;

public class GiftDatabase {
    private static File file;
    private static FileConfiguration giftDatabaseFile;

    public static void setup() {

        String filePath = Objects.requireNonNull(config.getString("database.path"));

        file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("ExtraordinaryGifts")).getDataFolder(), filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        giftDatabaseFile = YamlConfiguration.loadConfiguration(file);

    }

    public static FileConfiguration get() {
        return giftDatabaseFile;
    }

    public static void save() {
        try {
            giftDatabaseFile.save(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void reload() {
        giftDatabaseFile = YamlConfiguration.loadConfiguration(file);
    }
}
