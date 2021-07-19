package searcharoo.ui;

import org.junit.jupiter.api.Test;
import searcharoo.App;

import static org.junit.jupiter.api.Assertions.*;

class CommandInterfaceSearchProcessorTest {

    @Test
    void getSearchFieldValueFromUser() {
    }

    @Test
    void getSearchFieldFromUser() {
    }

    @Test
    void getSearchOptionFromUser() {
    }

    @Test
    void getSearchResults() {
    }

    @Test
    void formatSearchResult() {
    }

    @Test
    void getEntityFieldSearchOptions() {
    }

    @Test
    void getEntitySearchOptions() {
    }

    @Test void interfaceHasEntitySearchOptions() {
        CommandInterfaceSearchProcessor classUnderTest = new CommandInterfaceSearchProcessor(App.APP_PRINT_STREAM);
        assertNotNull(classUnderTest.getEntitySearchOptions(), "interface should have search sub options");
    }
}