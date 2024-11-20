package lk.ijse.Green_Shadow.Dto.Impl;

import lk.ijse.Green_Shadow.Dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDto implements FieldStatus {

    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private Double Extent_size;
    private String fieldImageOne;
    private String fieldImageTwo;
    private List<CropDto> crops;
    private List<EquipmentDto> equipmentDtos;
    private List<StaffDto> staffs;
    private String logCode;


}
