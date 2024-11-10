package lk.ijse.Green_Shadow.util;

import lk.ijse.Green_Shadow.Dto.Impl.*;
import lk.ijse.Green_Shadow.Entity.*;
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
    public List<StaffDto> asStaffDTOList(List<Staff> staffList) {
        return modelMapper.map(staffList, new TypeToken<List<StaffDto>>() {}.getType());
    }

    public MoniteringLog toMoniteringLogEntity(MoniteringLogDto logDto) {
        return modelMapper.map(logDto, MoniteringLog.class);
    }
    public List<MoniteringLogDto> asLogsDTOList(List<MoniteringLog> logs) {
        return modelMapper.map(logs, new TypeToken<List<MoniteringLogDto>>() {}.getType());
    }
    public MoniteringLogDto toMoniteringLogDto(MoniteringLog logs) {
        return modelMapper.map(logs, MoniteringLogDto.class);
    }
    public Vehicle toVehicleEntity(VehicleDto vehicleDto) {
        return modelMapper.map(vehicleDto, Vehicle.class);
    }


    public VehicleDto toVehicleDTO(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDto.class);
    }
    public List<VehicleDto> asVehicleDTOList(List<Vehicle> vehicles) {
        return modelMapper.map(vehicles, new TypeToken<List<VehicleDto>>() {}.getType());
    }
}
