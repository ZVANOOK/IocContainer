package zva;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class IocContainer {
    private final Map<String, Function<Object[], ?>> registry = new HashMap<>();

    public <T> T resolve(String key, Object... args) {
        Function<Object[], ?> function = registry.get(key);
        if (function != null) {
            return (T) function.apply(args);
        }
        throw new IllegalArgumentException("!!! No registration for key: " + key);
    }

    public <T> void register(String key, Function<Object[], T> function) {
        registry.put(key, function);
    }
}
