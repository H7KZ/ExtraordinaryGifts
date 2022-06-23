package cz.kominekjan.extraordinarygifts.commands.all;

import cz.kominekjan.extraordinarygifts.gift.Gift;
import org.bukkit.entity.Player;

public class OpenCommand {
    public static final String commandName = "open";

    public static void command(Player p) {
        Gift.open(p);
    }
}
