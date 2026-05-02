package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class ParseHelper {
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^[6-9]\\d{9}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[A-Za-z])(?=.*\\d).{8,}$");
    private static final String DOB_PATTERN = "yyyy-MM-dd";

    private ParseHelper() {
    }

    public static boolean isValidEmail(String value) {
        return value != null && EMAIL_PATTERN.matcher(value.trim()).matches();
    }

    public static boolean isValidMobile(String value) {
        return value != null && MOBILE_PATTERN.matcher(value.trim()).matches();
    }

    public static boolean isValidPassword(String value) {
        return value != null && PASSWORD_PATTERN.matcher(value).matches();
    }

    public static Date parseDate(String value) {
        if (value == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(DOB_PATTERN, Locale.ENGLISH);
        formatter.setLenient(false);
        try {
            return formatter.parse(value.trim());
        } catch (ParseException exception) {
            return null;
        }
    }

    public static String getDobPattern() {
        return DOB_PATTERN;
    }


}
