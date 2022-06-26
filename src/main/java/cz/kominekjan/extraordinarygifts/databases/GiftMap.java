package cz.kominekjan.extraordinarygifts.databases;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GiftMap {
    public static final Map<UUID, ArrayList<ItemStack>> temporary = new HashMap<>();
}
