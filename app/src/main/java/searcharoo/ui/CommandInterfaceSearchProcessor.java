package searcharoo.ui;

import searcharoo.search.SearchEntityFieldMap;
import searcharoo.search.SearchEntityFieldValueMap;
import searcharoo.search.SearchEntityMap;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class CommandInterfaceSearchProcessor {

    private static final String SEARCH_OPTION_BELOW = "Enter the Search option below and press enter:\n";
    private static final String FIELD_YOU_WANT_TO_SEARCH = "Please choose the field you want to search in %s and press enter:\n";
    private static final String SEARCH_RESULTS = "Search Results:\n";
    private static final String RESULT_ITEM_SEPARATOR = "\n==================================================\n";
    private static final String LINE_SEPARATOR = "\n--------------------------------------------------\n";
    private static final String NO_SEARCH_RESULTS = "No Search Results.";
    public static final String ENTITY_ID_FIELD = "_id";
    private final PrintStream printStream;

    public CommandInterfaceSearchProcessor(PrintStream printStream) {
        this.printStream = printStream;
    }

    String getSearchFieldValueFromUser(Scanner scanner, String formattedFieldValueStr) {
        String searchFieldValue = null;

        printStream.println(formattedFieldValueStr);
        if (scanner.hasNext()) searchFieldValue = scanner.nextLine().trim();
        return searchFieldValue;
    }

    String getSearchFieldFromUser(Scanner scanner, String searchEntity) {
        String searchField = null;

        printStream.println(FIELD_YOU_WANT_TO_SEARCH.formatted(searchEntity.toUpperCase()));
        printStream.println(getEntityFieldSearchOptions(searchEntity));
        if (scanner.hasNext()) searchField = SearchEntityMap.getEntityFieldByIndex(
                searchEntity,
                Integer.parseInt(scanner.nextLine().trim()) - 1
        );
        return searchField;
    }

    String getSearchOptionFromUser(Scanner scanner) {
        String searchEntity = null;
        printStream.println(SEARCH_OPTION_BELOW);
        printStream.println(getEntitySearchOptions());
        if (scanner.hasNext()) searchEntity = SearchEntityMap.getEntityByIndex(
                Integer.parseInt(scanner.nextLine().trim()) - 1
        );
        return searchEntity;
    }

    Set<Map<String, Object>> getSearchResults(String searchEntity, String searchField, String searchFieldValue) {
        if (searchFieldValue.equals("$empty")) searchFieldValue = "";

        Set<Map<String, Object>> result = SearchEntityMap
                .getSearchEntityFieldMap(searchEntity)
                .get(searchField)
                .getSearchEntityFieldValue(searchFieldValue);

        populateRelatedEntities(result);
        return result;
    }

    private void populateRelatedEntities(Set<Map<String, Object>> result) {
        result.forEach((resultItem) -> {
            getRelatedEntitiesWithValues(resultItem).forEach((joinField, joinFieldValue) -> {
                Set<Map<String, Object>> relatedEntityResult = getRelatedEntities(joinField, joinFieldValue.toString());
                result.addAll(relatedEntityResult);
            });
        });
    }

    private Set<Map<String, Object>> getRelatedEntities(String entityIdField, String entityIdFieldValue) {
        String entityName = getEntityNameFromIdField(entityIdField);

        SearchEntityFieldMap searchEntityFieldMap = SearchEntityMap.getSearchEntityFieldMap(entityName);
        if(searchEntityFieldMap == null) return Collections.emptySet();

        SearchEntityFieldValueMap searchEntityFieldValueMap = searchEntityFieldMap.get(ENTITY_ID_FIELD);
        if(searchEntityFieldValueMap == null) return Collections.emptySet();

        return searchEntityFieldValueMap.getSearchEntityFieldValue(entityIdFieldValue);
    }

    private String getEntityNameFromIdField(String joinField) {
        return joinField.replace(ENTITY_ID_FIELD, "s");
    }

    private Map<String, Object> getRelatedEntitiesWithValues(Map<String, Object> resultItem) {
        return resultItem
                .entrySet()
                .stream()
                .filter(
                        entry -> entry
                                .getKey()
                                .endsWith(ENTITY_ID_FIELD)
                ).collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue())
                );
    }

    String formatSearchResult(Set<Map<String, Object>> result) {

        if (result == null) return NO_SEARCH_RESULTS;

        StringBuilder resultStr = new StringBuilder();
        resultStr.append(SEARCH_RESULTS);
        result.forEach((entityObject) -> {
            resultStr.append(RESULT_ITEM_SEPARATOR);
            entityObject.forEach((key, value) -> {
                resultStr.append("\n%s = %s".formatted(key, value));
                resultStr.append(LINE_SEPARATOR);
            });
        });
        return resultStr.toString();
    }

    String getEntityFieldSearchOptions(String entityName) {
        StringBuilder entityFieldOptions = new StringBuilder();
        for (int i = 0; i < SearchEntityMap.getAllEntityFields(entityName).size(); i++) {
            entityFieldOptions.append("%s) %s \n".formatted(
                    i + 1, SearchEntityMap.getEntityFieldByIndex(entityName, i).toUpperCase())
            );
        }
        return entityFieldOptions.toString();
    }

    String getEntitySearchOptions() {
        StringBuilder entitySearchOptions = new StringBuilder();
        for (int i = 0; i < SearchEntityMap.getAllEntities().size(); i++) {
            entitySearchOptions.append("%s) %s ".formatted(
                    i + 1, SearchEntityMap.getEntityByIndex(i).toUpperCase())
            );
        }
        return entitySearchOptions.toString();
    }

}

