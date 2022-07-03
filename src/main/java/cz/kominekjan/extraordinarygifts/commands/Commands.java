package cz.kominekjan.extraordinarygifts.commands;

import cz.kominekjan.extraordinarygifts.commands.all.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.messages.Errors.notEnoughArgs;
import static cz.kominekjan.extraordinarygifts.messages.Errors.youMustBeAPlayer;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Permissions.*;

@SuppressWarnings("NullableProblems")
public class Commands implements CommandExecutor {
    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!command.getName().equalsIgnoreCase("egifts")) {
            if (!sender.hasPermission(eGiftCommandPermission)) {
                return true;
            }
        }

        if (args.length == 0) {
            sender.sendMessage("not enough arguments!");
            return true;
        }

        if (Objects.equals(args[0], ReloadCommand.commandName)) {
            if (sender.hasPermission(reloadCommandPermission) || sender.hasPermission(allCommandsWithReloadPermission)) {
                if (sender instanceof Player)
                    sender.sendMessage(ChatColor.YELLOW + "eGifts: Reloading ...");
                ReloadCommand.command();
                if (sender instanceof Player)
                    sender.sendMessage(ChatColor.GREEN + "eGifts: Successfully reloaded");
                return true;
            }
        }


        if (!(sender instanceof Player p)) {
            youMustBeAPlayer(sender);
            return true;
        }

        if (args.length == 0) {
            notEnoughArgs(p, commandNameList);
            return true;
        }

        switch (args[0]) {
            case HelpCommand.commandName -> {
                if (p.hasPermission(helpCommandPermission) || p.hasPermission(allCommandsWithoutReloadPermission) || p.hasPermission(allCommandsWithReloadPermission))
                    HelpCommand.command(p);
            }
            case CreateCommand.commandName -> {
                if (p.hasPermission(createCommandPermission) || p.hasPermission(allCommandsWithoutReloadPermission) || p.hasPermission(allCommandsWithReloadPermission))
                    CreateCommand.command(p);
            }
            case OpenCommand.commandName -> {
                if (p.hasPermission(openCommandPermission) || p.hasPermission(allCommandsWithoutReloadPermission) || p.hasPermission(allCommandsWithReloadPermission))
                    OpenCommand.command(p);
            }
            case MessageCommand.commandName -> {
                if (p.hasPermission(messageCommandPermission) || p.hasPermission(allCommandsWithoutReloadPermission) || p.hasPermission(allCommandsWithReloadPermission))
                    MessageCommand.command(p, args);
            }
            case titleCommand.commandName -> {
                if (p.hasPermission(titleCommandPermission) || p.hasPermission(allCommandsWithoutReloadPermission) || p.hasPermission(allCommandsWithReloadPermission))
                    titleCommand.command(p, args);
            }
            default -> p.sendMessage(ChatColor.RED + "eGifts: Unknown arguments! Please use /egifts help");
        }

        return true;
    }
}
