package cz.kominekjan.extraordinarygifts.commands;

import cz.kominekjan.extraordinarygifts.commands.all.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static cz.kominekjan.extraordinarygifts.messages.Errors.notEnoughArgs;
import static cz.kominekjan.extraordinarygifts.messages.Errors.youMustBeAPlayer;

@SuppressWarnings("NullableProblems")
public class Commands implements CommandExecutor {

    private final String[] commandNameList = {"help", "create", "send", "open", "mail"};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player p)) {
            youMustBeAPlayer(sender);
            return true;
        }

        if (!command.getName().equalsIgnoreCase("egifts")) {
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
