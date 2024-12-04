package lk.ijse.Green_Shadow.Dto.Impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.ijse.Green_Shadow.Dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FieldDto implements FieldStatus {

    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private Double Extent_size;
    private String fieldImageOne;
    private String fieldImageTwo;
    private List<CropDto> crops;
    private List<EquipmentDto> equipmentDtos;
    @ToString.Exclude
    @JsonIgnore
    private List<StaffDto> staffs;
    private String logCode;


}
