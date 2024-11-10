package lk.ijse.Green_Shadow.util;
import java.util.regex.Pattern;

public class RegexProcess {

    public static boolean FieldIdMatcher(String userId) {
        String regexForUserID = "^F\\d{3}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(userId).matches();
    }
    public static boolean CropIdMatcher(String userId) {
        String regexForUserID = "^C\\d{3}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(userId).matches();
    }


    public static boolean StaffIdMatcher(String staffId) {
        String regexForUserID = "^S\\d{3}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(staffId).matches();
    }

    public static boolean LogsIdMatcher(String logID) {
        String regexForUserID = "^L\\d{3}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(logID).matches();
    }

    public static boolean vehicleIdMatcher(String vehicleId) {
        String regexForUserID = "^V\\d{3}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(vehicleId).matches();
    }
}
