package searcharoo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommandInterfaceTest {
    @Test void appHasWelcomeMessage() {
        CommandInterface classUnderTest = new CommandInterface(App.APP_INPUT_STREAM, App.APP_PRINT_STREAM);
        assertNotNull(classUnderTest.getWelcomeMessage(), "app should have a welcome message");
    }

    @Test void appHasAppName() {
        CommandInterface classUnderTest = new CommandInterface(App.APP_INPUT_STREAM, App.APP_PRINT_STREAM);
        assertNotNull(classUnderTest.getAppName(), "app should have a name");
    }

    @Test void appHasSearchOptions() {
        CommandInterface classUnderTest = new CommandInterface(App.APP_INPUT_STREAM, App.APP_PRINT_STREAM);
        assertNotNull(classUnderTest.getSearchOptions(), "app should have search options");
    }

    @Test void appHasSearchSubOptions() {
        CommandInterface classUnderTest = new CommandInterface(App.APP_INPUT_STREAM, App.APP_PRINT_STREAM);
        assertNotNull(classUnderTest.getSearchSubOptions(), "app should have search sub options");
    }
}
