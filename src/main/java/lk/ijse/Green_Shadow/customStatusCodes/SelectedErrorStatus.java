package lk.ijse.Green_Shadow.customStatusCodes;


import lk.ijse.Green_Shadow.Dto.CropStatus;
import lk.ijse.Green_Shadow.Dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements FieldStatus, CropStatus {
    private int statusCode;
    private String statusMessage;
}
