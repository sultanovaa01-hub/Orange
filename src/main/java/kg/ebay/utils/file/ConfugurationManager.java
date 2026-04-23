package kg.ebay.utils.file;

import org.aeonbits.owner.ConfigCache;

public class ConfugurationManager {

    public static AppConfig getBaseConfig (){
        return ConfigCache.getOrCreate(AppConfig.class);
    }
}
