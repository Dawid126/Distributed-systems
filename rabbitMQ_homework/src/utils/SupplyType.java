package utils;

import java.util.ArrayList;

public enum SupplyType {
    OXYGEN {
        public String toString() {
            return "OXYGEN";
        }
    },
    BACKPACK {
        public String toString() {
            return "BACKPACK";
        }
    },
    BOOTS {
        public String toString() {
            return "BOOTS";
        }
    };

    public static ArrayList<String> getAllSupplyTypes() {
        ArrayList<String> supplyTypeStringList = new ArrayList<>();
        for(SupplyType type: SupplyType.values()) {
            supplyTypeStringList.add(type.toString());
        }
        return supplyTypeStringList;
    }
}
