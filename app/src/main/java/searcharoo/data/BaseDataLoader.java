package searcharoo.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface BaseDataLoader {

    void loadFilesFromFolder();

    void processFile(File file);

    List<Map<String, Object>> mapDataToObjects(File jsonFile);

}
