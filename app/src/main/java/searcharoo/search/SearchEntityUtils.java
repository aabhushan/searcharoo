package searcharoo.search;

import java.util.*;

public class SearchEntityUtils {

    public void dataObjectsToSearchMap(String entityName, List<Map<String, Object>> mappedDataObjectList) {
        mappedDataObjectList.stream().forEach((entityMap) -> {
            updateSearchEntityFieldMap(
                    entityName,
                    entityMap
            );
        });
    }

    void updateSearchEntityFieldMap(
            String entityName,
            Map<String, Object> entityMap
    ) {

        SearchEntityFieldMap searchEntityFieldMap;

        if (SearchEntityMap.searchEntityExists(entityName)) {
            searchEntityFieldMap = SearchEntityMap.getSearchEntityFieldMap(entityName);
        } else {
            searchEntityFieldMap = new SearchEntityFieldMap();
            SearchEntityMap.putSearchEntityFieldMap(entityName, searchEntityFieldMap);
        }

        entityMap.entrySet().stream().forEach((entry) -> {
            updateSearchEntityFieldValueMap(entry, entityMap, searchEntityFieldMap);
        });
    }

    void updateSearchEntityFieldValueMap(Map.Entry<String, Object> entry, Map<String, Object> entityMap, SearchEntityFieldMap searchEntityFieldMap) {

        if(entry == null || entityMap == null || searchEntityFieldMap == null) return;

        SearchEntityFieldValueMap searchEntityFieldValueMap;
        if (searchEntityFieldMap.exists(entry.getKey())) {
            searchEntityFieldValueMap = searchEntityFieldMap.get(entry.getKey());
        } else {
            searchEntityFieldValueMap = new SearchEntityFieldValueMap();
            searchEntityFieldMap.put(entry.getKey(), searchEntityFieldValueMap);
        }

        entityMap.entrySet().stream().forEach(entityEntry -> {
            String fieldValue = entry.getValue().toString();
            Set<Map<String, Object>> entityMapList;

            if (searchEntityFieldValueMap.entityFieldValueExists(fieldValue)) {
                entityMapList = searchEntityFieldValueMap.getSearchEntityFieldValue(fieldValue);
            } else entityMapList = new HashSet<>();

            entityMapList.add(entityMap);
            searchEntityFieldValueMap.putSearchEntityFieldValue(entry.getValue().toString(), entityMapList);
        });
    }
}
