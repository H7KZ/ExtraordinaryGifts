package cz.kominekjan.extraordinarygifts.commands;

import cz.kominekjan.extraordinarygifts.commands.all.CommandHelp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player p)) { sender.sendMessage("You must be a player!"); return true; }

        switch (command.getName()) {
            case CommandHelp.commandName:
                CommandHelp.command(p);
                break;
        }

        return true;
    }
}
