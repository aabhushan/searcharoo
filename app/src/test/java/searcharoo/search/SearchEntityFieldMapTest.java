package searcharoo.search;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SearchEntityFieldMapTest {

    @Test
    void putShouldStoreTheField() {
        Map<String, SearchEntityFieldValueMap> mockSearchEntityFieldMap = new TreeMap<>();
        SearchEntityFieldValueMap searchEntityFieldValueMap = new SearchEntityFieldValueMap();
        String mockString = "I love testing";
        SearchEntityFieldMap classUnderTest = new SearchEntityFieldMap(mockSearchEntityFieldMap);

        classUnderTest.put(mockString, searchEntityFieldValueMap);

        assertEquals(mockSearchEntityFieldMap.get(mockString), searchEntityFieldValueMap);
    }

    @Test
    void getShouldStoreAndReturnFieldValueMap() {
        Map<String, SearchEntityFieldValueMap> mockSearchEntityFieldMap = new TreeMap<>();
        String mockEntityName = "I love testing";
        SearchEntityFieldValueMap mockSearchEntityFieldValueMap = new SearchEntityFieldValueMap();
        mockSearchEntityFieldMap.put(mockEntityName, mockSearchEntityFieldValueMap);

        SearchEntityFieldMap classUnderTest = new SearchEntityFieldMap(mockSearchEntityFieldMap);
        SearchEntityFieldValueMap result = classUnderTest.get(mockEntityName);

        assertEquals(mockSearchEntityFieldValueMap, result);
    }

    @Test
    void existsShouldReturnTrueWhenFieldValueMapExists() {
        Map<String, SearchEntityFieldValueMap> mockSearchEntityFieldMap = new TreeMap<>();
        String mockEntityName = "I love testing";
        SearchEntityFieldValueMap mockSearchEntityFieldValueMap = new SearchEntityFieldValueMap();
        mockSearchEntityFieldMap.put(mockEntityName, mockSearchEntityFieldValueMap);

        SearchEntityFieldMap classUnderTest = new SearchEntityFieldMap(mockSearchEntityFieldMap);
        Boolean result = classUnderTest.exists(mockEntityName);

        assertEquals(true, result);
    }

    @Test
    void existsShouldReturnFalseWhenFieldValueMapDoesntExist() {
        Map<String, SearchEntityFieldValueMap> mockSearchEntityFieldMap = new TreeMap<>();
        String mockEntityName = "I love testing";

        SearchEntityFieldMap classUnderTest = new SearchEntityFieldMap(mockSearchEntityFieldMap);
        Boolean result = classUnderTest.exists(mockEntityName);

        assertEquals(false, result);
    }

    @Test
    void fieldsShouldReturnTheFieldsForEntity() {

        Map<String, SearchEntityFieldValueMap> mockSearchEntityFieldMap = new TreeMap<>();
        String mockField = "I love testing";
        SearchEntityFieldValueMap mockSearchEntityFieldValueMap = new SearchEntityFieldValueMap();
        mockSearchEntityFieldMap.put(mockField, mockSearchEntityFieldValueMap);

        SearchEntityFieldMap classUnderTest = new SearchEntityFieldMap(mockSearchEntityFieldMap);
        List<String> result = classUnderTest.fields();

        assertEquals(List.of(mockField), result);
    }
}