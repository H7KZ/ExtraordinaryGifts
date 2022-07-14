package cz.kominekjan.extraordinarygifts.commands;

import cz.kominekjan.extraordinarygifts.commands.all.*;
import cz.kominekjan.extraordinarygifts.messages.PlayerMessage;
import cz.kominekjan.extraordinarygifts.messages.SenderMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.messages.ErrorMessage.notEnoughArgs;
import static cz.kominekjan.extraordinarygifts.permissions.Permissions.*;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Commands.commandNameList;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!command.getName().equalsIgnoreCase("egifts")) {
            return true;
        }

        if (!CheckPermissionAllWithReload(sender, eGiftCommandPermission)) {
            return true;
        }

        if (args.length == 0) {
            if (sender instanceof Player) {
                notEnoughArgs((Player) sender, commandNameList);
            } else {
                sender.sendMessage(SenderMessage.noArguments);
            }

            return true;
        }

        if (Objects.equals(args[0], ReloadCommand.commandName)) {
            if (!CheckPermissionAllWithReload(sender, reloadCommandPermission)) {
                return true;
            }

            if (sender instanceof Player) sender.sendMessage(PlayerMessage.eGiftsReloading);

            ReloadCommand.command();

            if (sender instanceof Player) sender.sendMessage(PlayerMessage.eGiftsReloaded);

            return true;
        }

        if (!(sender instanceof Player p)) {
            sender.sendMessage(SenderMessage.youNeedToBeAPlayer);

            return true;
        }

        switch (args[0]) {
            case HelpCommand.commandName -> HelpCommand.command(p);
            case CreateCommand.commandName -> CreateCommand.command(p);
            case OpenCommand.commandName -> OpenCommand.command(p);
            case MessageCommand.commandName -> MessageCommand.command(p, args);
            case TitleCommand.commandName -> TitleCommand.command(p, args);
            case EconomyCommand.commandName -> EconomyCommand.command(p);
            default -> p.sendMessage(PlayerMessage.eGiftsUnknownCommand);
        }

        return true;
    }
}
