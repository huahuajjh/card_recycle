package com.tqmars.cardrecycle.infrastructure.StringTools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jjh on 1/21/17.
 */
public final class PropertiesFileTool {
    private static Properties p;

    static{
        p = new Properties();
        InputStream is = null;
        try {
            is = PropertiesFileTool.class.getResourceAsStream("/conf/app.properties");
            p.load(is);
        } catch (IOException e) {
        }
        finally{
            try {
                is.close();
            } catch (IOException e) {
            }
        }
    }

    public static String readByKey(String key) {
        return p.getProperty(key);
    }
}
