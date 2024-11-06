package lk.ijse.Green_Shadow.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDto {

    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private Double Extent_size;
}
