package cz.kominekjan.extraordinarygifts.economy;

import org.bukkit.entity.Player;

import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.config;

public class GiftCreateEconomy {
    public static Boolean checkBalance(Player p) {
        switch (Objects.requireNonNull(config.getString("giftEconomy.plugin")).toLowerCase()) {
            case GiftCreateVaultEconomy.pluginName:
                if (!GiftCreateVaultEconomy.checkBalance(p)) {
                    return false;
                }
                break;
            case GiftCreateTreasuryEconomy.pluginName:
                if (!GiftCreateTreasuryEconomy.checkBalance(p)) {
                    return false;
                }
        }

        return true;
    }
}
