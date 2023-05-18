package redis;

import java.util.Map;

public class STRLEN {
	
	public static int execute(Map<String, String> data, String key) {
        // Logique d'exécution de la commande STRLEN
        String value = data.get(key);
        if (value != null) {
            return value.length();
        } else {
            return 0;
        }
    }

}
