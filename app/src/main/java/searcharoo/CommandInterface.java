package searcharoo;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CommandInterface {

    private final String APP_NAME = "SEARCHAROO";
    private final String WELCOME_MESSAGE = "[ WELCOME to %s ]\n".formatted(getAppName());

    private final InputStream inputStream;
    private final PrintStream printStream;


    CommandInterface(InputStream inputStream, PrintStream printStream) {
        this.printStream = printStream;
        this.inputStream = inputStream;
    }


    String getAppName() {
        return APP_NAME;
    }

    public void startCommandLineInterface() {
        Scanner scanner = new Scanner(inputStream);
        printStream.println(getWelcomeMessage());
        printStream.println(getSearchOptions());
        String searchOption = scanner.nextLine();
        printStream.println("Option %s was chosen".formatted(searchOption));
    }

    public void exitCommandLineInterface() {
        System.exit(0);
    }

    String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    String getSearchOptions() {
        return "Select from the following options\n".formatted(getAppName()) +
                "-> Press 1 and enter to %s\n".formatted(SearchOptions.OPTION1) +
                "-> Press 2 and enter to %s\n".formatted(SearchOptions.OPTION2) +
                "-> Press 3 and enter to %s\n".formatted(SearchOptions.OPTION3);
    }

    String getSearchSubOptions() {
        return "Select: 1) %s 2) %s 3) %s\n"
                .formatted(
                        SearchOptions.SUBOPTION1,
                        SearchOptions.SUBOPTION2,
                        SearchOptions.SUBOPTION3
                );
    }
}
