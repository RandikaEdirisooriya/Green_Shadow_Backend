package lk.ijse.Green_Shadow.util;


import java.util.Base64;

public class AppUtil {
    public static String ImageToBase64(byte [] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }

    public static String generateCropCode(String lastCropCode) {
        if (lastCropCode == null || lastCropCode.isEmpty() || !lastCropCode.matches("^C\\d+$")) {
            return "C001";
        } else {
            String numericPart = lastCropCode.substring(3);
            int numericValue = Integer.parseInt(numericPart);
            int nextNumericValue = numericValue + 1;
            String nextNumericPart = String.format("%0" + numericPart.length() + "d", nextNumericValue);
            return "C00" + nextNumericPart;
        }
    }
    public static String generateEquipmentCode(String lastCode) {
        if (lastCode == null || lastCode.isEmpty() || !lastCode.matches("^E\\d+$")) {
            return "E001";
        } else {
            String numericPart = lastCode.substring(3);
            int numericValue = Integer.parseInt(numericPart);
            int nextNumericValue = numericValue + 1;
            String nextNumericPart = String.format("%0" + numericPart.length() + "d", nextNumericValue);
            return "E00" + nextNumericPart;
        }
    }
    public static String generateFieldCode(String lastCode) {
        if (lastCode == null || lastCode.isEmpty() || !lastCode.matches("^F\\d+$")) {
            return "F001";
        } else {
            String numericPart = lastCode.substring(3);
            int numericValue = Integer.parseInt(numericPart);
            int nextNumericValue = numericValue + 1;
            String nextNumericPart = String.format("%0" + numericPart.length() + "d", nextNumericValue);
            return "F00" + nextNumericPart;
        }
    }
    public static String generateLogCode(String lastCode) {
        if (lastCode == null || lastCode.isEmpty() || !lastCode.matches("^L\\d+$")) {
            return "L001";
        } else {
            String numericPart = lastCode.substring(3);
            int numericValue = Integer.parseInt(numericPart);
            int nextNumericValue = numericValue + 1;
            String nextNumericPart = String.format("%0" + numericPart.length() + "d", nextNumericValue);
            return "L00" + nextNumericPart;
        }
    }
    public static String generateStaffCode(String lastCode) {
        if (lastCode == null || lastCode.isEmpty() || !lastCode.matches("^S\\d+$")) {
            return "S001";
        } else {
            String numericPart = lastCode.substring(3);
            int numericValue = Integer.parseInt(numericPart);
            int nextNumericValue = numericValue + 1;
            String nextNumericPart = String.format("%0" + numericPart.length() + "d", nextNumericValue);
            return "S00" + nextNumericPart;
        }
    }
    public static String generateUserCode(String lastCode) {
        if (lastCode == null || lastCode.isEmpty() || !lastCode.matches("^U\\d+$")) {
            return "U001";
        } else {
            String numericPart = lastCode.substring(3);
            int numericValue = Integer.parseInt(numericPart);
            int nextNumericValue = numericValue + 1;
            String nextNumericPart = String.format("%0" + numericPart.length() + "d", nextNumericValue);
            return "U00" + nextNumericPart;
        }
    }
    public static String generatedVehicleCode(String lastCode) {
        if (lastCode == null || lastCode.isEmpty() || !lastCode.matches("^V\\d+$")) {
            return "V001";
        } else {
            String numericPart = lastCode.substring(3);
            int numericValue = Integer.parseInt(numericPart);
            int nextNumericValue = numericValue + 1;
            String nextNumericPart = String.format("%0" + numericPart.length() + "d", nextNumericValue);
            return "V00" + nextNumericPart;
        }
    }
}
