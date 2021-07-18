package searcharoo.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import searcharoo.search.SearchEntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class JSONDataLoader implements BaseDataLoader {

    private final ObjectMapper mapper;
    private final File folder;
    private final TypeReference<List<Map<String, Object>>> typeReference;
    private final SearchEntityUtils searchEntityUtils;

    public JSONDataLoader(File folder, ObjectMapper mapper, TypeReference<List<Map<String, Object>>> typeReference, SearchEntityUtils searchEntityUtils) {
        this.folder = folder;
        this.mapper = mapper;
        this.typeReference = typeReference;
        this.searchEntityUtils = searchEntityUtils;
    }

    public JSONDataLoader(File folder){
        this.folder = folder;
        this.typeReference = new TypeReference<List<Map<String, Object>>>() {};
        this.mapper = new ObjectMapper();
        this.searchEntityUtils = new SearchEntityUtils();
    }


    @Override
    public void loadFilesFromFolder() {
        File[] listOfFiles = this.folder.listFiles();

        if(listOfFiles == null || listOfFiles.length == 0) return;

        Stream<File> jsonFiles = Arrays.stream(listOfFiles).filter(
                (file) -> file.getName().endsWith(".json")
        );

        jsonFiles.forEach((currentFile) -> processFile(currentFile));
    }

    @Override
    public void processFile(File file) {

        if (file == null || !file.canRead()) return;

        List<Map<String, Object>> marshalledData = mapDataToObjects(file);
        String entityName = file.getName()
                .strip()
                .toLowerCase()
                .replace(".json", "");
        convertDataObjectsToSearchMap(entityName, marshalledData);
    }

    void convertDataObjectsToSearchMap(String entityName, List<Map<String, Object>> marshalledData) {
        this.searchEntityUtils.dataObjectsToSearchMap(entityName, marshalledData);
    }

    @Override
    public List<Map<String, Object>> mapDataToObjects(File jsonFile) {

        if (jsonFile == null || !jsonFile.canRead()) {
            return Collections.emptyList();
        }

        try {
            return this.mapper.readValue(
                    jsonFile,
                    this.typeReference
            );
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
