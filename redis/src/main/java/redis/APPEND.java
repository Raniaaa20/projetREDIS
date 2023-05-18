package redis;

import java.util.Map;

public class APPEND {
	
	public static int execute(Map<String, String> data, String key, String valueToAppend) {
        // Logique d'exécution de la commande APPEND
        String currentValue = data.get(key);
        if (currentValue != null) {
            data.put(key, currentValue + valueToAppend);
            return (currentValue + valueToAppend).length();
        } else {
            data.put(key, valueToAppend);
            return valueToAppend.length();
        }
    }

}
