package searcharoo;

public enum SearchOptions {
    OPTION1 {
        @Override
        public String toString() {
            return "Search";
        }
    },

    OPTION2 {
        @Override
        public String toString() {
            return "List searchable fields";
        }
    },

    OPTION3 {
        @Override
        public String toString() {
            return "Exit";
        }
    },

    SUBOPTION1 {
        @Override
        public String toString() {
            return "Users";
        }
    },

    SUBOPTION2 {
        @Override
        public String toString() {
            return "Tickets";
        }
    },

    SUBOPTION3 {
        @Override
        public String toString() {
            return "Organizations";
        }
    }
}
