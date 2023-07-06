import java.util.HashSet;
import java.util.Set;

public class KeyManager {
    private Set<Integer> allocatedKeys;

    public KeyManager() {
        allocatedKeys = new HashSet<>(200);
    }

    public int getKey() {
        int key = 0;
        while (allocatedKeys.contains(key)) {
            key++;
        }
        allocatedKeys.add(key);
        return key;
    }

    public boolean releaseKey(int key) {
        if (allocatedKeys.contains(key)) {
            allocatedKeys.remove(key);
            return true;
        }
        return false;
    }




}
