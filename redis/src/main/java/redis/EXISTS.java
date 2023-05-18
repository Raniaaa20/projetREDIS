package redis;

import java.util.Map;

public class EXISTS {
	
	public static boolean execute(Map<String, String> data, String key) {
        // Logique d'exécution de la commande EXISTS
        return data.containsKey(key);
    }

}
