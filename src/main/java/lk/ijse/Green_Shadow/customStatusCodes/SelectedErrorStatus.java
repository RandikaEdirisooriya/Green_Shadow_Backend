package lk.ijse.Green_Shadow.customStatusCodes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus  {
    private int statusCode;
    private String statusMessage;
}
