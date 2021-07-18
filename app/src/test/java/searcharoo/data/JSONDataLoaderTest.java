package searcharoo.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import searcharoo.search.SearchEntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@DisplayName("JSONDataLoaderTest")
class JSONDataLoaderTest {

    @DisplayName("loadFilesFromFolder should be able to load the JSON file and process the file")
    @Test
    void canLoadFilesFromFolder() {

        File mockParameterFolder = mock(File.class);
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);

        File mockFile1 = mock(File.class);
        when(mockFile1.getName()).thenReturn("mockFile1.json");
        File mockFile2 = mock(File.class);
        when(mockFile2.getName()).thenReturn("mockFile2.csv");
        when(mockParameterFolder.listFiles()).thenReturn(new File[]{mockFile1, mockFile2});

        JSONDataLoader jsonDataLoader = new JSONDataLoader(mockParameterFolder, mockObjectMapper, new TypeReference<>() {
        }, new SearchEntityUtils());
        JSONDataLoader classUnderTest = spy(jsonDataLoader);

        classUnderTest.loadFilesFromFolder();

        verify(classUnderTest).processFile(mockFile1);
    }

    @DisplayName("loadFilesFromFolder should be able to ignore paths with no file")
    @Test
    void canIgnorePathWithNoFiles() {

        File mockParameterFolder = mock(File.class);
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);

        when(mockParameterFolder.listFiles()).thenReturn(new File[0]);

        JSONDataLoader jsonDataLoader = new JSONDataLoader(
                mockParameterFolder,
                mockObjectMapper,
                new TypeReference<>() {
                }, new SearchEntityUtils());
        JSONDataLoader classUnderTest = spy(jsonDataLoader);

        classUnderTest.loadFilesFromFolder();

        verify(classUnderTest, never()).processFile(any());
    }

    @DisplayName("processFile should be able to map and convert the object to map")
    @Test
    void canProcessFile() throws IOException {

        String mockEntity = "mockEntity";
        File mockParameterFolder = mock(File.class);
        List<Map<String, Object>> testList = Collections.emptyList();

        File mockJSONFile = mock(File.class);
        when(mockJSONFile.canRead()).thenReturn(true);
        when(mockJSONFile.getName()).thenReturn("%s.json".formatted(mockEntity));

        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        TypeReference<List<Map<String, Object>>> testTypeReference = new TypeReference<>() {
        };
        when(mockObjectMapper.readValue(mockJSONFile, testTypeReference)).thenReturn(testList);

        JSONDataLoader jsonDataLoader = new JSONDataLoader(
                mockParameterFolder,
                mockObjectMapper,
                testTypeReference,
                new SearchEntityUtils()
        );
        JSONDataLoader classUnderTest = spy(jsonDataLoader);

        classUnderTest.processFile(mockJSONFile);

        verify(classUnderTest).mapDataToObjects(mockJSONFile);
        verify(classUnderTest).convertDataObjectsToSearchMap(mockEntity.toLowerCase(), testList);
    }

    @DisplayName("processFile should handle null file case")
    @Test
    void canProcessFileEmptyFile() throws IOException {

        String mockEntity = "mockEntity";
        File mockParameterFolder = mock(File.class);
        List<Map<String, Object>> testList = Collections.emptyList();

        File mockJSONFile = null;

        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        TypeReference<List<Map<String, Object>>> testTypeReference = new TypeReference<>() {
        };
        when(mockObjectMapper.readValue(mockJSONFile, testTypeReference)).thenReturn(testList);

        JSONDataLoader jsonDataLoader = new JSONDataLoader(
                mockParameterFolder,
                mockObjectMapper,
                testTypeReference,
                new SearchEntityUtils()
        );
        JSONDataLoader classUnderTest = spy(jsonDataLoader);

        classUnderTest.processFile(mockJSONFile);

        verify(classUnderTest, never()).mapDataToObjects(mockJSONFile);
        verify(classUnderTest, never()).convertDataObjectsToSearchMap(mockEntity.toLowerCase(), testList);
    }
}