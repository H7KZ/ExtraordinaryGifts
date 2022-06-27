package cz.kominekjan.extraordinarygifts.commands;

import cz.kominekjan.extraordinarygifts.commands.all.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static cz.kominekjan.extraordinarygifts.messages.Errors.notEnoughArgs;
import static cz.kominekjan.extraordinarygifts.messages.Errors.youMustBeAPlayer;

@SuppressWarnings("NullableProblems")
public class Commands implements CommandExecutor {

    private final String[] commandNameList = {"help", "reload", "create", "send", "open", "mail"};

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!command.getName().equalsIgnoreCase("egifts")) {
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("not enough arguments!");
            return true;
        }

        if (Objects.equals(args[0], ReloadCommand.commandName)) ReloadCommand.command();

        if (!(sender instanceof Player p)) {
            youMustBeAPlayer(sender);
            return true;
        }

        if (args.length == 0) {
            notEnoughArgs(p, commandNameList);
            return true;
        }

        switch (args[0]) {
            case HelpCommand.commandName -> HelpCommand.command(p);
            case CreateCommand.commandName -> CreateCommand.command(p);
            case MailCommand.commandName -> MailCommand.command(p);
            case OpenCommand.commandName -> OpenCommand.command(p);
            case SendCommand.commandName -> SendCommand.command(p);
        }

        return true;
    }
}
