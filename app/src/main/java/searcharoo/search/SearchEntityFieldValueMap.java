package searcharoo.search;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SearchEntityFieldValueMap {

    private Map<String, Set<Map<String, Object>>> searchEntityFieldValueMap;

    SearchEntityFieldValueMap() {
        this.searchEntityFieldValueMap = new TreeMap<>();
    }

    SearchEntityFieldValueMap(Map<String, Set<Map<String, Object>>> searchEntityFieldValueMap) {
        this.searchEntityFieldValueMap = searchEntityFieldValueMap;
    }

    public void putSearchEntityFieldValue(String searchEntityFieldValue, Set<Map<String, Object>> searchEntityObject) {
        searchEntityFieldValueMap.put(searchEntityFieldValue, searchEntityObject);
    }

    public Set<Map<String, Object>> getSearchEntityFieldValue(String searchEntityFieldValue) {
        return searchEntityFieldValueMap.get(searchEntityFieldValue);
    }

    public Boolean entityFieldValueExists(String searchEntityFieldValue) {
        if (searchEntityFieldValue == null) return false;
        return searchEntityFieldValueMap.containsKey(searchEntityFieldValue);
    }
}
