package cz.kominekjan.extraordinarygifts.commands;

import cz.kominekjan.extraordinarygifts.commands.all.*;
import cz.kominekjan.extraordinarygifts.messages.PlayerMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.messages.ErrorMessage.notEnoughArgs;
import static cz.kominekjan.extraordinarygifts.messages.ErrorMessage.youMustBeAPlayer;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Commands.commandNameList;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Permissions.*;

@SuppressWarnings("NullableProblems")
public class Commands implements CommandExecutor {
    private static void noPermission(Player p) {
        p.sendMessage(PlayerMessage.eGiftsCommandNoPermission);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!command.getName().equalsIgnoreCase("egifts")) {
            if (!sender.hasPermission(eGiftCommandPermission)) {
                return true;
            }
        }

        if (args.length == 0) {
            if (sender instanceof Player) {
                notEnoughArgs((Player) sender, commandNameList);
            } else {
                sender.sendMessage("eGifts: Not enough arguments!");
            }

            return true;
        }

        if (Objects.equals(args[0], ReloadCommand.commandName)) {
            if (sender.hasPermission(reloadCommandPermission) || sender.hasPermission(allCommandsWithReloadPermission)) {
                if (sender instanceof Player) sender.sendMessage(PlayerMessage.eGiftsReloading);

                ReloadCommand.command();

                if (sender instanceof Player) sender.sendMessage(PlayerMessage.eGiftsReloaded);

            } else {
                sender.sendMessage("eGifts: You don't have permission to use this command!");
            }

            return true;
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
                if (p.hasPermission(helpCommandPermission) || p.hasPermission(allCommandsWithoutReloadPermission) || p.hasPermission(allCommandsWithReloadPermission)) {
                    HelpCommand.command(p);
                } else {
                    noPermission(p);
                }
            }
            case CreateCommand.commandName -> {
                if (p.hasPermission(createCommandPermission) || p.hasPermission(allCommandsWithoutReloadPermission) || p.hasPermission(allCommandsWithReloadPermission)) {
                    CreateCommand.command(p);
                } else {
                    noPermission(p);
                }
            }
            case OpenCommand.commandName -> {
                if (p.hasPermission(openCommandPermission) || p.hasPermission(allCommandsWithoutReloadPermission) || p.hasPermission(allCommandsWithReloadPermission)) {
                    OpenCommand.command(p);
                } else {
                    noPermission(p);
                }
            }
            case MessageCommand.commandName -> {
                if (p.hasPermission(messageCommandPermission) || p.hasPermission(allCommandsWithoutReloadPermission) || p.hasPermission(allCommandsWithReloadPermission)) {
                    MessageCommand.command(p, args);
                } else {
                    noPermission(p);
                }
            }
            case TitleCommand.commandName -> {
                if (p.hasPermission(titleCommandPermission) || p.hasPermission(allCommandsWithoutReloadPermission) || p.hasPermission(allCommandsWithReloadPermission)) {
                    TitleCommand.command(p, args);
                } else {
                    noPermission(p);
                }
            }
            case EconomyCommand.commandName -> {
                if (p.hasPermission(economyCommandPermission) || p.hasPermission(allCommandsWithoutReloadPermission) || p.hasPermission(allCommandsWithReloadPermission)) {
                    EconomyCommand.command(p);
                } else {
                    noPermission(p);
                }
            }
            default -> p.sendMessage(PlayerMessage.eGiftsUnknownCommand);
        }

        return true;
    }
}
