package cz.kominekjan.extraordinarygifts.persistentdatatypes;

import com.manya.pdc.DataTypes;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class PersistentDataItemStackArray {
    public static PersistentDataType<?, List<ItemStack>> type;

    public static void init() {
        type = DataTypes.list(DataTypes.ITEM_STACK);
    }
}
