package searcharoo.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import searcharoo.App;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("CommandInterfaceTest")
class CommandInterfaceTest {
    @Test void interfaceHasWelcomeMessage() {
        CommandInterface classUnderTest = new CommandInterface(App.APP_INPUT_STREAM, App.APP_PRINT_STREAM, null);
        assertNotNull(classUnderTest.getWelcomeMessage(), "interface should have a welcome message");
    }

    @Test void interfaceHasAppName() {
        CommandInterface classUnderTest = new CommandInterface(App.APP_INPUT_STREAM, App.APP_PRINT_STREAM, null);
        assertNotNull(classUnderTest.getAppName(), "interface should have a name");
    }

    @Test void interfaceHasCommandOptions() {
        CommandInterface classUnderTest = new CommandInterface(App.APP_INPUT_STREAM, App.APP_PRINT_STREAM, null);
        assertNotNull(classUnderTest.getCommandOptions(), "interface should have search options");
    }
}
