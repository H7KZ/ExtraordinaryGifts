package cz.kominekjan.extraordinarygifts.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import cz.kominekjan.extraordinarygifts.messages.Errors;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.Field;
import java.util.UUID;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.plugin;

public class Head {
    public static ItemStack create(String name, String uuid, String key, String value) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();

        assert headMeta != null;
        headMeta.setDisplayName(name);

        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);

        headMeta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, value);

        return setCustomHead(uuid, head, headMeta);
    }

    private static ItemStack setCustomHead(String uuid, ItemStack head, SkullMeta headMeta) {
        if (uuid == null || uuid.trim().equals("")) {
            head.setItemMeta(headMeta);
            return head;
        }

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        String url = "https:///textures.minecraft.net/texture/" + uuid;

        profile.getProperties().put("textures", new Property("textures", url));

        Field profileField;

        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
            Errors.failedToSetProfileHead();
        }

        head.setItemMeta(headMeta);

        return head;
    }

    public static ItemStack create(String name, String uuid) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();

        assert headMeta != null;
        headMeta.setDisplayName(name);

        return setCustomHead(uuid, head, headMeta);
    }
}
