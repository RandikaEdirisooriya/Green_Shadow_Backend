package lk.ijse.Green_Shadow.customStatusCodes;


import lk.ijse.Green_Shadow.Dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements FieldStatus, CropStatus, StaffStatus, MoniteringLogStatus, VehicleStatus, EquipmentStatus ,UserStatus{
    private int statusCode;
    private String statusMessage;
}
