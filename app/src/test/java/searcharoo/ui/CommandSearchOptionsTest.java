package searcharoo.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("CommandSearchOptionsTest")
class CommandSearchOptionsTest {
    @DisplayName("values of the Options enum should exist")
    @Test
    void enumValuesExist() {
        assertNotNull(CommandSearchOptions.OPTION1);
        assertNotNull(CommandSearchOptions.OPTION1.toString());
        assertNotNull(CommandSearchOptions.OPTION2);
        assertNotNull(CommandSearchOptions.OPTION2.toString());
    }

}