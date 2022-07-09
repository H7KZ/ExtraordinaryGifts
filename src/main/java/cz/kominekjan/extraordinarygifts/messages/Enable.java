package cz.kominekjan.extraordinarygifts.messages;

import static cz.kominekjan.extraordinarygifts.ExtraordinaryGifts.logger;

public class Enable {
    public static void starting() {
        logger.info("ExtraordinaryGifts: are starting");
    }

    public static void closing() {
        logger.info("ExtraordinaryGifts: started with no signs of error");
    }
}
