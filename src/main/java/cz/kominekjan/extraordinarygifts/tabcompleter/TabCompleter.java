package cz.kominekjan.extraordinarygifts.tabcompleter;

import cz.kominekjan.extraordinarygifts.variables.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> result = new ArrayList<>();

        if (strings.length == 1) {
            for (String cmd : Variables.Commands.commandNameList) {
                if (cmd.toLowerCase().startsWith(strings[0].toLowerCase())) {
                    result.add(cmd);
                }
            }

            return result;
        }

        return null;
    }
}
