package searcharoo.search;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class SearchEntityMapTest {

    @Test
    void getSearchEntityFieldMapShouldReturnCorrectMap() {
        String mockEntityString = "I love testing";
        SearchEntityFieldMap mockSearchEntityFieldMap = new SearchEntityFieldMap();
        SearchEntityMap.searchEntityMap.put(mockEntityString, mockSearchEntityFieldMap);

        assertEquals(SearchEntityMap.getSearchEntityFieldMap(mockEntityString), mockSearchEntityFieldMap);

        //Cleanup
        SearchEntityMap.searchEntityMap.remove(mockEntityString);
    }

    @Test
    void putSearchEntityFieldMapShouldStoreMapCorrectly() {
        String mockEntityString = "I love testing";
        SearchEntityFieldMap mockSearchEntityFieldMap = new SearchEntityFieldMap();

        SearchEntityMap.putSearchEntityFieldMap(mockEntityString, mockSearchEntityFieldMap);

        assertEquals(SearchEntityMap.getSearchEntityFieldMap(mockEntityString), mockSearchEntityFieldMap);

        //Cleanup
        SearchEntityMap.searchEntityMap.remove(mockEntityString);
    }

    @Test
    void searchEntityExistsShouldReturnTrueWhenEntityExists() {
        String mockEntityString = "I love testing";
        SearchEntityFieldMap mockSearchEntityFieldMap = new SearchEntityFieldMap();

        SearchEntityMap.putSearchEntityFieldMap(mockEntityString, mockSearchEntityFieldMap);

        Boolean result = SearchEntityMap.searchEntityExists(mockEntityString);

        assertEquals(result, true);

        //Cleanup
        SearchEntityMap.searchEntityMap.remove(mockEntityString);

    }

    @Test
    void searchEntityExistsShouldReturnFalseWhenEntityDoesntExist() {
        String mockEntityString = "I love testing";

        Boolean result = SearchEntityMap.searchEntityExists(mockEntityString);

        assertEquals(result, false);
    }

    @Test
    void getAllEntitiesShouldReturnAllEntities() {
        String mockEntityString = "I love testing";
        String mockEntityString2 = "I love testing more";

        SearchEntityFieldMap mockSearchEntityFieldMap = new SearchEntityFieldMap();
        SearchEntityFieldMap mockSearchEntityFieldMap2 = new SearchEntityFieldMap();

        SearchEntityMap.putSearchEntityFieldMap(mockEntityString, mockSearchEntityFieldMap);
        SearchEntityMap.putSearchEntityFieldMap(mockEntityString2, mockSearchEntityFieldMap2);

        List<String> result = SearchEntityMap.getAllEntities();

        assertEquals(result, List.of(mockEntityString, mockEntityString2));

        //Cleanup
        SearchEntityMap.searchEntityMap.remove(mockEntityString);
        SearchEntityMap.searchEntityMap.remove(mockEntityString2);
    }

    @Test
    void getAllEntityFieldsShouldReturnAllEntityFields(){
        String mockEntityString = "I love testing";

        SearchEntityFieldMap mockSearchEntityFieldMap = new SearchEntityFieldMap();
        String mockEntityField1 = "mockId";
        String mockEntityField2 = "mockValue";
        mockSearchEntityFieldMap.put(mockEntityField1, new SearchEntityFieldValueMap());
        mockSearchEntityFieldMap.put(mockEntityField2, new SearchEntityFieldValueMap());
        SearchEntityMap.putSearchEntityFieldMap(mockEntityString, mockSearchEntityFieldMap);

        List<String> result = SearchEntityMap.getAllEntityFields(mockEntityString);

        assertEquals(result, List.of(mockEntityField1, mockEntityField2));

        //Cleanup
        SearchEntityMap.searchEntityMap.remove(mockEntityString);

    }

    @Test
    void getEntityByIndexShouldReturnCorrectEntity() {
        String mockEntityString = "I love testing";
        String mockEntityString2 = "I love testing too";


        SearchEntityMap.putSearchEntityFieldMap(mockEntityString, new SearchEntityFieldMap());
        SearchEntityMap.putSearchEntityFieldMap(mockEntityString2, new SearchEntityFieldMap());

        String result = SearchEntityMap.getEntityByIndex(0);
        String result2 = SearchEntityMap.getEntityByIndex(1);

        assertEquals(result, mockEntityString);
        assertEquals(result2, mockEntityString2);

        //Cleanup
        SearchEntityMap.searchEntityMap.remove(mockEntityString);
        SearchEntityMap.searchEntityMap.remove(mockEntityString2);
    }

    @Test
    void getEntityFieldByIndexShouldReturnCorrectEntityField() {
        String mockEntityString = "I love testing";

        SearchEntityFieldMap mockSearchEntityFieldMap = new SearchEntityFieldMap();
        String mockEntityField1 = "mockId";
        String mockEntityField2 = "mockValue";
        mockSearchEntityFieldMap.put(mockEntityField1, new SearchEntityFieldValueMap());
        mockSearchEntityFieldMap.put(mockEntityField2, new SearchEntityFieldValueMap());
        SearchEntityMap.putSearchEntityFieldMap(mockEntityString, mockSearchEntityFieldMap);

        String result = SearchEntityMap.getEntityFieldByIndex(mockEntityString, 0);
        String result2 = SearchEntityMap.getEntityFieldByIndex(mockEntityString, 1);

        assertEquals(result, mockEntityField1);
        assertEquals(result2, mockEntityField2);

        //Cleanup
        SearchEntityMap.searchEntityMap.remove(mockEntityString);
    }
}