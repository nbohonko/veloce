package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class BaseStep {

    private static final ThreadLocal<Map<String, Object>> testsData = ThreadLocal.withInitial(HashMap::new);

    public static void saveData(String key, Object value) {
        testsData.get().put(key, value);
    }

    public static Object getData(String key) {
        List<Object> emptyList = new ArrayList<>();
        return testsData.get().getOrDefault(key, emptyList);
    }

    public static String getStringData(String key) {
        return testsData.get().getOrDefault(key, "no data").toString();
    }
}
