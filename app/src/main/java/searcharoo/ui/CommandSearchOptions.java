package searcharoo.ui;

public enum CommandSearchOptions {
    OPTION1 {
        @Override
        public String toString() {
            return "Search";
        }
    },

    OPTION2 {
        @Override
        public String toString() {
            return "Exit";
        }
    }
}
