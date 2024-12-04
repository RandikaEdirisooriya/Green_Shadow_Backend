package lk.ijse.Green_Shadow.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseUtill {
    private String state;
    private String message;
    private Object data;
}
