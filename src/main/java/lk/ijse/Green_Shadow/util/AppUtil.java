package lk.ijse.Green_Shadow.util;


import java.util.Base64;

public class AppUtil {
    public static String profilePicToBase64(byte [] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }
}
