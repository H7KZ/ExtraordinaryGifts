package cz.kominekjan.extraordinarygifts.messages;

public class PlayerMessage {
    public static final String noPermission = "§ceGifts: You don't have permission to use this command!";
    public static final String notEnoughBalance = "§ceGifts: You don't have enough balance to create a gift or you aren't holding it in the main hand! To see all possible ways of paying, type /gift economy.";
    public static final String economyNotEnabled = "§cGift economy is not enabled.";
    public static final String economyDefaultWelcome = "§6§l-- ExtraordinaryGifts economy: --";
    public static final String helpCommandDefaultWelcome = "§6§l-- ExtraordinaryGifts help: --";
    public static final String messageCommandNoMessage = "§ceGifts: No message specified!";
    public static final String eGiftsReloading = "§eeGifts: Reloading...";
    public static final String eGiftsReloaded = "§aeGifts: Successfully reloaded";
    public static final String eGiftsUnknownCommand = "§ceGifts: Unknown arguments! Please use /egifts help";

    public static String messageCommandTooLong(int maxMessageLength) {
        return "§ceGifts: Message is too long! Max message length is " + maxMessageLength + " characters.";
    }
}
