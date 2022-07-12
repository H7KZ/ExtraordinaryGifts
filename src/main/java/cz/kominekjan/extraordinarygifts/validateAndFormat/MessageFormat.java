package cz.kominekjan.extraordinarygifts.validateAndFormat;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

import static cz.kominekjan.extraordinarygifts.variables.Variables.Message.maxLineLength;
import static cz.kominekjan.extraordinarygifts.variables.Variables.Message.messageColor;

public class MessageFormat {
    public static List<String> getFormattedMessage(String[] args) {
        List<String> message = new ArrayList<>();

        List<String> tempLineMessage = new ArrayList<>();

        int tempLineLength = 0;

        for (int i = 1; i < args.length; i++) {
            if (tempLineLength <= maxLineLength && tempLineLength + args[i].length() + 1 >= maxLineLength) {
                message.add(messageColor + ChatColor.translateAlternateColorCodes('&', String.join(" ", tempLineMessage)));

                tempLineMessage.clear();

                tempLineLength = 0;
            }

            if (i == args.length - 1) {
                if (tempLineLength <= maxLineLength && tempLineLength + args[i].length() + 1 <= maxLineLength) {
                    tempLineMessage.add(args[i]);

                    message.add(messageColor + ChatColor.translateAlternateColorCodes('&', String.join(" ", tempLineMessage)));

                    tempLineMessage.clear();
                }
            }

            tempLineMessage.add(args[i]);

            tempLineLength = String.join(" ", tempLineMessage).length();
        }

        return message;
    }
}
