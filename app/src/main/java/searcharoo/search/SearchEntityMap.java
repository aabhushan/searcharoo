package searcharoo.search;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchEntityMap {
    final static Map<String, SearchEntityFieldMap> searchEntityMap = new TreeMap<>();

    public static SearchEntityFieldMap getSearchEntityFieldMap(String searchEntityName) {
        return searchEntityMap.get(searchEntityName);
    }

    public static SearchEntityFieldMap putSearchEntityFieldMap(String searchEntityName, SearchEntityFieldMap searchEntityFieldMap) {
        return searchEntityMap.put(searchEntityName, searchEntityFieldMap);
    }

    public static Boolean searchEntityExists(String searchEntityName) {
        if (searchEntityName == null) return false;
        return searchEntityMap.containsKey(searchEntityName);
    }

    public static List<String> getAllEntities(){
        return searchEntityMap.keySet().stream().toList();
    }

    public static List<String> getAllEntityFields(String entityName){
        return searchEntityMap.get(entityName).fields();
    }

    public static String getEntityByIndex(int index) {
        return getAllEntities().get(index);
    }

    public static String getEntityFieldByIndex(String entityName, int index) {
        List<String> entityFields = getAllEntityFields(entityName);

        if(entityFields == null || entityFields.size() <= index) return null;

        return getAllEntityFields(entityName).get(index);
    }
}
