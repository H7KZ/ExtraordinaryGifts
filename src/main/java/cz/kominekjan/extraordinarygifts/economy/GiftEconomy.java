package cz.kominekjan.extraordinarygifts.economy;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class GiftEconomy {
    public static class Gift {
        private static final ArrayList<UUID> whoPaidList = new ArrayList<>();

        public static Boolean checkBalance(Player p) {

            return true;
        }

        public static void payBalance(Player p) {

        }

        public static void returnBalance(Player p) {

        }
    }
}
