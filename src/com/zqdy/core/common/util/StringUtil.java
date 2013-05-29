package com.zqdy.core.common.util;

import java.util.Locale;
import java.util.Random;

public class StringUtil {

    public StringUtil()
    {
    }

    public static String pack(Object src)
    {
        if(src == null)
            return "";
        else
            return src.toString();
    }

    public static String pack(Object src, String defaultValue)
    {
        if(src == null)
            return defaultValue;
        else
            return src.toString();
    }

    public static String[] split(String s, String regex)
    {
        if(s == null)
        {
            return new String[0];
        } else
        {
            String results[] = s.split(regex);
            return results;
        }
    }

    public static Locale toLocale(String s, Locale defaultLocale)
    {
        Locale locale = defaultLocale;
        if(s != null)
        {
            String locales[] = s.split("_");
            if(locales.length == 1)
                locale = new Locale(locales[0]);
            if(locales.length == 2)
                locale = new Locale(locales[0], locales[1]);
            if(locales.length == 3)
                locale = new Locale(locales[0], locales[1], locales[2]);
        }
        return locale;
    }

    public static Locale toLocale(String s)
    {
        Locale locale = Locale.getDefault();
        return toLocale(s, locale);
    }

    public static int toInt(String s, int defaultValue)
    {
        try
        {
            defaultValue = Integer.parseInt(s);
        }
        catch(NumberFormatException e) { }
        return defaultValue;
    }

    public static int toInt(String s)
    {
        return toInt(s, 0);
    }

    public static boolean empty(String s)
    {
        return s == null || s.trim().trim().length() < 1;
    }

    public static String toUpperFirst(String src)
    {
        String propertyName = src.substring(0, 1).toUpperCase() + src.substring(1);
        return propertyName;
    }

    public static String trunc(String src, int length)
    {
        if(src == null)
        {
            return "";
        } else
        {
            length = src.length() <= length ? src.length() : length;
            return src.substring(0, length);
        }
    }

    public static String truncWithDot(String src, int length)
    {
        if(src == null)
            return "";
        String s = src;
        if(src.length() > length + 1)
        {
            s = src.substring(0, length);
            s = s + "...";
        }
        return s;
    }

    public static String randomString(int length)
    {
        String newString = "";
        Random r = new Random();
        for(int i = 0; i < length; i++)
            newString = newString + letters[r.nextInt(letters.length)];

        return newString;
    }

    public static int ipToInt(String ip)
    {
        return 0;
    }

    public static float toFloat(String s)
    {
        float f = 0.0F;
        try
        {
            f = Float.parseFloat(s);
        }
        catch(NumberFormatException e) { }
        return f;
    }

    private static final char letters[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
        'u', 'v', 'w', 'x', 'y', 'z'
    };
}
