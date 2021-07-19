package searcharoo.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CommandInterface {


    public static final String ENTER_THE_VALUE_FOR_FIELD = "Please enter the value for %s and press enter. Enter $empty to search for empty.:\n";
    private static final String APP_NAME = "SEARCHAROO";
    private static final String WELCOME_MESSAGE = "[ WELCOME to %s ]\n".formatted(APP_NAME);

    private final InputStream inputStream;
    private final PrintStream printStream;
    private final CommandInterfaceSearchProcessor searchProcessor;


    public CommandInterface(InputStream inputStream, PrintStream printStream, CommandInterfaceSearchProcessor searchProcessor) {
        this.printStream = printStream;
        this.inputStream = inputStream;
        this.searchProcessor = searchProcessor;
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

        searchEntity = this.searchProcessor.getSearchOptionFromUser(scanner);

        if (searchEntity == null) exitCommandInterface();

        searchField = this.searchProcessor.getSearchFieldFromUser(scanner, searchEntity);

        if (searchField == null) exitCommandInterface();

        searchFieldValue = this.searchProcessor.getSearchFieldValueFromUser(
                scanner, ENTER_THE_VALUE_FOR_FIELD.formatted(searchField.toUpperCase()));

        Set<Map<String, Object>> result = this.searchProcessor.getSearchResults(searchEntity, searchField, searchFieldValue);

        printStream.println(this.searchProcessor.formatSearchResult(result));
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
}
