package lk.ijse.Green_Shadow.util;

import lk.ijse.Green_Shadow.Dto.Impl.CropDto;
import lk.ijse.Green_Shadow.Dto.Impl.FieldDto;
import lk.ijse.Green_Shadow.Dto.Impl.StaffDto;
import lk.ijse.Green_Shadow.Entity.Crop;
import lk.ijse.Green_Shadow.Entity.Field;
import lk.ijse.Green_Shadow.Entity.Staff;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    public Field toFieldEntity(FieldDto fieldDto) {
        return modelMapper.map(fieldDto, Field.class);
    }
    public List<FieldDto> asFieldDTOList(List<Field> fieldList) {
        return modelMapper.map(fieldList, new TypeToken<List<FieldDto>>() {}.getType());
    }
    public FieldDto toNoteDTO(Field field) {
        return modelMapper.map(field, FieldDto.class);
    }

    public Crop toCropEntity(CropDto cropDto) {
        return modelMapper.map(cropDto, Crop.class);
    }
    public CropDto toCropDTO(Crop crop) {
        return modelMapper.map(crop, CropDto.class);
    }
    public List<CropDto> asNoteDTOList(List<Crop> crops) {
        return modelMapper.map(crops, new TypeToken<List<CropDto>>() {}.getType());
    }

    public Staff toStaffEntity(StaffDto staffDto) {return modelMapper.map(staffDto, Staff.class);
    }
    public StaffDto toStaffDTO(Staff staff) {
        return modelMapper.map(staff, StaffDto.class);
    }
}
