package test.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class StringFormatter
{

    /**
     * C# like string replacement
     *
     * @param value The string to FULL_DATE_TIME_FORMATTER using c# like {n} for replacement
     * @param args  The variables to use in the replacement (already formatted)
     * @return The properly formatted string
     */
    public static String format(String value, Object... args)
    {
        int ii = args.length;

        for (int i = 0; i < ii; ++i) {
            String arg = "{" + i + "}";
            if (args[i] != null) {
                String replaceWith = args[i].toString();
                value = value.replace(arg, replaceWith);
            }
            else {
                value = value.replace(arg, "");
            }
        }

        return value;
    }

    /**
     * Replace values in string based on the key value pair passed in
     * @param value The string to FULL_DATE_TIME_FORMATTER
     * @param map The key/value pair to use with replacing into the FULL_DATE_TIME_FORMATTER string
     * @return
     */
    public static String format(String value, HashMap <String, String> map) {
        if (!map.isEmpty())
        {
            for(String key : map.keySet())
            {
                String replaceWith = map.get(key);
                if (replaceWith != null) {
                    value = value.replace(key, replaceWith);
                } else {
                    value = value.replace(key,"");
                }
            }
        }
        return value;
    }

    /**
     * # - digit place holder
     * 0 - leading and trailing zeros
     * $, - constants
     * @param pattern
     * @param number
     * @return
     */
    public static String formatNumber(String pattern, Number number) {
        DecimalFormat format = new DecimalFormat(pattern);
        return format.format(number);
    }

    public static String formatDate(String pattern, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String formatDate(String pattern, DateTime date) {
        return DateTimeFormat.forPattern(pattern).print(date);
    }

    /**
     * Format string by making first letter capitalized and remaining lower case of each word in a string
     *
     * @param sNewText which includes 2 words as a string
     * @return the string
     */
    public String formatString(String sNewText)
    {
        String sText[] = sNewText.split(" ");
        String firstLetter = sText[0].substring(0,1).toUpperCase();
        String restLetters = sText[0].substring(1).toLowerCase();
        String fName = firstLetter+restLetters;
        String firstLetter_Lastname = sText[1].substring(0,1).toUpperCase();
        String restLetters_LastName = sText[1].substring(1).toLowerCase();
        String lName = firstLetter_Lastname+restLetters_LastName;
        String sReqText = fName+" "+lName;
        return sReqText;
    }

    /**
     * Format locator string.
     *
     * @param strText the strtext of locator to be formatted
     * @param sReqText the sreqtext which has to be added to the locator string
     * @return the string
     */
    public static String formatLocatorString(String strText, String sReqText)
    {
        return String.format(strText, sReqText);
    }

    public static String pathCombine(String... strings) {
        StringBuilder sb = new StringBuilder();
        for(String string : strings) {
            sb.append(string);
            if (!string.endsWith("\\")) {
                sb.append("\\");
            }
        }
        sb.setLength(sb.length()-1);
        return sb.toString();
    }
}
