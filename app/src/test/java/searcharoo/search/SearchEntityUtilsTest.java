package searcharoo.search;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.*;

class SearchEntityUtilsTest {

    @Test
    void dataObjectsToSearchMapShouldUpdateSearchMapForEachItem() {

        String mockEntityName = "I am entity";
        List<Map<String, Object>> mockMappedDataObjectList = new ArrayList<>();
        Map<String, Object> mockObjectMap = new HashMap<>();
        mockObjectMap.put("field", "field_value");
        Map<String, Object> mockObjectMap2 = new HashMap<>();
        mockObjectMap.put("field2", "field_value2");
        mockMappedDataObjectList.add(mockObjectMap);
        mockMappedDataObjectList.add(mockObjectMap2);
        SearchEntityUtils searchEntityUtils = new SearchEntityUtils();
        SearchEntityUtils underTest = spy(searchEntityUtils);
        spy(underTest).updateSearchEntityFieldMap(anyString(), anyMap());

        underTest.dataObjectsToSearchMap(mockEntityName, mockMappedDataObjectList);

        verify(underTest, times(1)).updateSearchEntityFieldMap(mockEntityName, mockObjectMap);
        verify(underTest, times(1)).updateSearchEntityFieldMap(mockEntityName, mockObjectMap2);

        //Cleanup
        SearchEntityMap.searchEntityMap.remove(mockEntityName);
    }

    @Test
    void updateSearchEntityFieldMapShouldUpdateFieldValueMapForEachEntry() {
        String mockEntityName = "I am entity";
        Map<String, Object> mockEntityMap = new HashMap<>();
        mockEntityMap.put("field", "field_value");
        mockEntityMap.put("field2", "field_value2");
        SearchEntityFieldMap mockSearchEntityFieldMap = new SearchEntityFieldMap();
        SearchEntityMap.putSearchEntityFieldMap(mockEntityName, mockSearchEntityFieldMap);
        SearchEntityUtils searchEntityUtils = new SearchEntityUtils();
        SearchEntityUtils underTest = spy(searchEntityUtils);
        spy(underTest).updateSearchEntityFieldValueMap(any(Map.Entry.class), anyMap(), any(SearchEntityFieldMap.class));

        underTest.updateSearchEntityFieldMap(mockEntityName, mockEntityMap);

        verify(underTest,
                times(1)
        ).updateSearchEntityFieldValueMap(
                mockEntityMap.entrySet().iterator().next(),
                mockEntityMap,
                mockSearchEntityFieldMap
        );

        //Cleanup
        SearchEntityMap.searchEntityMap.remove(mockEntityName);
    }

    @Test
    void updateSearchEntityFieldValueMapShouldStoreFieldMap() {
        //TODO
    }
}