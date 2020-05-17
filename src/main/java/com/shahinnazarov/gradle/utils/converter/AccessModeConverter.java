package com.shahinnazarov.gradle.utils.converter;

import java.util.ArrayList;
import java.util.List;

public class AccessModeConverter {

    public String convert(String data) {
        switch (data.toLowerCase()) {
            case "rwo":
                return "ReadWriteOnce";
            case "rox":
                return "ReadOnlyMany";
            case "rwx":
                return "ReadWriteMany";
        }
        return data;
    }

    public List<String> convert(String listOfData, String splitter) {
        List<String> list = new ArrayList<>();
        for(String item : listOfData.split(splitter)) {
            list.add(convert(item.trim()));
        }
        return list.isEmpty() ? null : list;
    }
}
