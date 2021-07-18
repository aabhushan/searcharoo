package searcharoo.ui;

import searcharoo.search.SearchEntityMap;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CommandInterface {

    public static final String SEARCH_OPTION_BELOW = "Enter the Search option below and press enter:\n";
    public static final String FIELD_YOU_WANT_TO_SEARCH = "Please choose the field you want to search in %s and press enter:\n";
    public static final String ENTER_THE_VALUE_FOR_FIELD = "Please enter the value for %s and press enter:\n";
    private static final String APP_NAME = "SEARCHAROO";
    private static final String WELCOME_MESSAGE = "[ WELCOME to %s ]\n".formatted(APP_NAME);
    public static final String SEARCH_RESULTS = "Search Results:\n";
    public static final String RESULT_ITEM_SEPARATOR = "\n==================================================\n";
    public static final String LINE_SEPARATOR = "\n--------------------------------------------------\n";
    public static final String NO_SEARCH_RESULTS = "No Search Results.";

    private final InputStream inputStream;
    private final PrintStream printStream;


    public CommandInterface(InputStream inputStream, PrintStream printStream) {
        this.printStream = printStream;
        this.inputStream = inputStream;
    }


    String getAppName() {
        return APP_NAME;
    }

    public void startCommandLineInterface() {
        String commandOption = "";
        try (Scanner scanner = new Scanner(inputStream)) {
            printStream.println(getWelcomeMessage());
            printStream.println(getCommandOptions());
            if (scanner.hasNext()) {
                commandOption = scanner.nextLine().trim();
            }
            executeCommandOptions(commandOption, scanner);
        }
    }

    public void executeCommandOptions(String commandOption, Scanner scanner) {
        switch (commandOption) {
            case "1":
                executeSearchOptions(scanner);
                break;
            case "2":
                exitCommandInterface();
            default:
                exitCommandInterface();

        }
    }

    private void executeSearchOptions(Scanner scanner) {

        String searchEntity = null;
        String searchField = null;
        String searchFieldValue = null;

        printStream.println(SEARCH_OPTION_BELOW);
        printStream.println(getEntitySearchOptions());
        if (scanner.hasNext()) searchEntity = SearchEntityMap.getEntityByIndex(
                Integer.parseInt(scanner.nextLine().trim()) - 1
        );

        printStream.println(FIELD_YOU_WANT_TO_SEARCH.formatted(searchEntity.toUpperCase()));
        printStream.println(getEntityFieldSearchOptions(searchEntity));
        if (scanner.hasNext()) searchField = SearchEntityMap.getEntityFieldByIndex(
                searchEntity,
                Integer.parseInt(scanner.nextLine().trim()) - 1
        );

        printStream.println(ENTER_THE_VALUE_FOR_FIELD.formatted(searchField.toUpperCase()));
        if (scanner.hasNext()) searchFieldValue = scanner.nextLine().trim();

        Set<Map<String, Object>> result = SearchEntityMap.getSearchEntityFieldMap(searchEntity).get(searchField).getSearchEntityFieldValue(searchFieldValue);
        printStream.println(formatSearchResult(result));
        printStream.println();
    }

    String formatSearchResult(Set<Map<String, Object>> result) {

        if (result == null) return NO_SEARCH_RESULTS;

        StringBuilder resultStr = new StringBuilder();
        resultStr.append(SEARCH_RESULTS);
        result.forEach((entityObject) -> {
            resultStr.append(RESULT_ITEM_SEPARATOR);
            entityObject.entrySet().forEach((entry) -> {
                resultStr.append("\n%s = %s".formatted(entry.getKey(), entry.getValue()));
                resultStr.append(LINE_SEPARATOR);
            });
        });
        return resultStr.toString();
    }

    public void exitCommandInterface() {
        System.exit(0);
    }

    String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    String getCommandOptions() {
        return "Enter the Command option below and press enter\n".formatted(getAppName()) +
                "1) %s\n".formatted(CommandSearchOptions.OPTION1) +
                "2) %s\n".formatted(CommandSearchOptions.OPTION2);
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

    String getEntityFieldSearchOptions(String entityName) {
        StringBuilder entityFieldOptions = new StringBuilder();
        for (int i = 0; i < SearchEntityMap.getAllEntityFields(entityName).size(); i++) {
            entityFieldOptions.append("%s) %s \n".formatted(
                    i + 1, SearchEntityMap.getEntityFieldByIndex(entityName, i).toUpperCase())
            );
        }
        return entityFieldOptions.toString();
    }
}
