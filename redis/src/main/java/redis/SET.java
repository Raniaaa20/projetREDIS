package redis;

import java.util.Map;

public class SET {
	
	public static String execute(Map<String, String> data, String key, String value) {
        //FONCTIONNEMENT 
        data.put(key, value);
        return "OK";
    }

}
