package com.tll.smartphonebook.helpers;

/**
 * Created by abdullahtellioglu on 30/12/15.
 */
public class NumberUtils {
    public static String clearPhoneNumber(String number){
        String str1 = number.replaceAll(" ","");
        String str2 = number.replace("+","");
        return str2;
    }
}
