package redis;

import java.util.Map;

public class GET {
	
	public static String execute(Map<String, String> data, String key) {
        // Logique d'exécution de la commande GET
        return data.get(key);
    }

}
