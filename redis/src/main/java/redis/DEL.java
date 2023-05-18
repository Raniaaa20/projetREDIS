package redis;

import java.util.Map;

public class DEL {
	
	public static boolean execute(Map<String, String> data, String key) {
        // Logique d'exécution de la commande DEL
        return data.remove(key) != null;
    }

}
