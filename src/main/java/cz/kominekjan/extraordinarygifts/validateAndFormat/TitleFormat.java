package cz.kominekjan.extraordinarygifts.validateAndFormat;

import org.bukkit.ChatColor;

import java.util.Arrays;

import static cz.kominekjan.extraordinarygifts.variables.Variables.Title.titleColor;

public class TitleFormat {
    public static String getFormattedTitle(String[] args) {
        return titleColor + ChatColor.translateAlternateColorCodes('&', String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
    }
}
