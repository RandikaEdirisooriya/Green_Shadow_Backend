package lk.ijse.Green_Shadow.util;
import java.util.regex.Pattern;

public class RegexProcess {

    public static boolean FieldIdMatcher(String userId) {
        String regexForUserID = "^F\\d{3}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(userId).matches();
    }


}
