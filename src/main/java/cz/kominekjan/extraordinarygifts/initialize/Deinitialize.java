package cz.kominekjan.extraordinarygifts.initialize;

import cz.kominekjan.extraordinarygifts.items.Items;
import cz.kominekjan.extraordinarygifts.variables.Variables;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.logger;

public class Deinitialize {
    public static void items() {
        Items.init();
        logger.info("ExtraordinaryGifts: items have been reloaded");
    }

    public static void variables() {
        Variables.init();
        logger.info("ExtraordinaryGifts: variables have been reloaded");
    }
}
