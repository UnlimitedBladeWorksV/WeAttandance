package com.demo.attend.util;

import java.util.Arrays;
import java.util.List;

public class StringListConvertUtil {

    public static String ListStringTOString(List<String> list) {

        return String.join("___", list);

    }

    public static List<String> StringTOListString(String str) {

        return Arrays.asList(str.split("___"));

    }
}
