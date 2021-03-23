package main.java.utils;

public enum MessageType {
    TCP {
        public String toString() {
            return "T";
        }
    },
    UDP {
        public String toString() {
            return "U";
        }
    },
    MULTICAST {
        public String toString() {
            return "M";
        }
    },
    NAME_REQUEST {
        public String toString() {
            return "NR";
        }
    },
    NAME_ACCEPT {
        public String toString() {
            return "NA";
        }
    },
    NAME_REJECT {
        public String toString() {
            return "NR";
        }
    }

}
