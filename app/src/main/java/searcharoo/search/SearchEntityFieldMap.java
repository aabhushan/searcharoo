package searcharoo.search;

import java.util.*;

public class SearchEntityFieldMap {
    private Map<String, SearchEntityFieldValueMap> searchEntityFieldMap;

    SearchEntityFieldMap(){
        this.searchEntityFieldMap = new TreeMap<>();
    }

    SearchEntityFieldMap(Map<String, SearchEntityFieldValueMap> searchEntityFieldMap){
        this.searchEntityFieldMap = searchEntityFieldMap;
    }

    public void put(String searchEntityField, SearchEntityFieldValueMap searchEntityFieldValueMap) {
        searchEntityFieldMap.put(searchEntityField, searchEntityFieldValueMap);
    }

    public SearchEntityFieldValueMap get(String searchEntityField) {
        return searchEntityFieldMap.get(searchEntityField);
    }

    public Boolean exists(String searchEntityField) {
        if (searchEntityField == null) return false;

        return searchEntityFieldMap.containsKey(searchEntityField);
    }

    public List<String> fields(){
        return searchEntityFieldMap.keySet().stream().toList();
    }

}
