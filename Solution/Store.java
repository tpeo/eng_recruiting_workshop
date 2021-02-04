package Solution;
import java.util.*;

public class Store {
    private int version;
    private HashMap<Integer, TreeMap<Integer, Integer>> container;

    public Store() {
        version = 1;
        container = new HashMap<Integer, TreeMap<Integer, Integer>>();
    }

    public String put(String key, int value) {
        int keyNum = Integer.parseInt(key.substring(3));

        container.computeIfAbsent(keyNum, k -> new TreeMap<>()).put(version, value);
        return String.format("PUT(#%d) %s = %d", version++, key, value);
    }

    public String get(String key) {
        int keyNum = Integer.parseInt(key.substring(3));

        TreeMap<Integer, Integer> versionToValue = container.get(keyNum);
        return String.format("GET %s = %s", key, versionToValue == null ? "<NULL>" : versionToValue.lastEntry().getValue());
    }

    public String get(String key, int version) {
        int keyNum = Integer.parseInt(key.substring(3));

        TreeMap<Integer, Integer> versionToValue = container.get(keyNum);
        if (versionToValue == null) {
            return String.format("GET %s(#%d) = <NULL>", key, version);
        }

        Integer value = versionToValue.get(version);
        if (value != null) {
            return  String.format("GET %s(#%d) = %d", key, version, value);
        }
        
        Integer latestVersion = versionToValue.lowerKey(version);
        if (latestVersion != null) {
            return  String.format("GET %s(#%d) = %d", key, version, versionToValue.get(latestVersion));
        }

        return String.format("GET %s(#%d) = <NULL>", key, version); // there are no versions lower than the given version 
    }
}