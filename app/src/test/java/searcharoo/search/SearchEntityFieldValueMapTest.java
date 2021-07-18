package searcharoo.search;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SearchEntityFieldValueMapTest {

    @Test
    void putShouldStoreTheEntityMap() {
        String mockSearchEntityFieldValue = "I love testing";
        Map<String, Set<Map<String, Object>>> mockSearchEntityFieldMap = new TreeMap<>();
        Set<Map<String, Object>> searchEntityObject = new HashSet<>();
        SearchEntityFieldValueMap classUnderTest = new SearchEntityFieldValueMap(mockSearchEntityFieldMap);

        classUnderTest.putSearchEntityFieldValue(mockSearchEntityFieldValue, searchEntityObject);

        assertEquals(mockSearchEntityFieldMap.get(mockSearchEntityFieldValue), searchEntityObject);
    }

    @Test
    void getShouldReturnEntityMap() {
        String mockSearchEntityFieldValue = "I love testing";

        Map<String, Set<Map<String, Object>>> mockSearchEntityFieldMap = new TreeMap<>();
        Set<Map<String, Object>> searchEntityObject = new HashSet<>();
        mockSearchEntityFieldMap.put(mockSearchEntityFieldValue, searchEntityObject);
        SearchEntityFieldValueMap classUnderTest = new SearchEntityFieldValueMap(mockSearchEntityFieldMap);

        Set<Map<String, Object>> result = classUnderTest.getSearchEntityFieldValue(mockSearchEntityFieldValue);

        assertEquals(searchEntityObject, result);
    }

    @Test
    void existsShouldReturnTrueWhenEntityMapExists() {
        String mockSearchEntityFieldValue = "I love testing";

        Map<String, Set<Map<String, Object>>> mockSearchEntityFieldMap = new TreeMap<>();
        Set<Map<String, Object>> searchEntityObject = new HashSet<>();
        mockSearchEntityFieldMap.put(mockSearchEntityFieldValue, searchEntityObject);
        SearchEntityFieldValueMap classUnderTest = new SearchEntityFieldValueMap(mockSearchEntityFieldMap);

        Boolean result = classUnderTest.entityFieldValueExists(mockSearchEntityFieldValue);

        assertEquals(true, result);
    }

    @Test
    void existsShouldReturnTrueWhenEntityMapDoesntExist() {
        String mockSearchEntityFieldValue = "I love testing";
        String mockSearchEntityFieldValue2 = "I am not going to get mapped";

        Map<String, Set<Map<String, Object>>> mockSearchEntityFieldMap = new TreeMap<>();
        Set<Map<String, Object>> searchEntityObject = new HashSet<>();
        mockSearchEntityFieldMap.put(mockSearchEntityFieldValue, searchEntityObject);
        SearchEntityFieldValueMap classUnderTest = new SearchEntityFieldValueMap(mockSearchEntityFieldMap);

        Boolean result = classUnderTest.entityFieldValueExists(mockSearchEntityFieldValue2);

        assertEquals(false, result);
    }
}