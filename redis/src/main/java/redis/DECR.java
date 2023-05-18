package redis;

import java.util.Map;

public class DECR {
	
	public static int execute(Map<String, String> data, String key) {
        // Logique d'exécution de la commande DECR
        String value = data.get(key);
        int newValue;
        if (value != null) {
            try {
                int intValue = Integer.parseInt(value);
                newValue = intValue - 1;
            } catch (NumberFormatException e) {
                throw new RuntimeException("ERR value is not an integer or out of range");
            }
        } else {
            newValue = -1;
        }
        data.put(key, String.valueOf(newValue));
        return newValue;
    }

}
