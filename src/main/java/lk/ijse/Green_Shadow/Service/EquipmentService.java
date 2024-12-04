package lk.ijse.Green_Shadow.Service;

import lk.ijse.Green_Shadow.Dto.EquipmentStatus;
import lk.ijse.Green_Shadow.Dto.Impl.EquipmentDto;

import java.util.List;

public interface EquipmentService {
    String findLastEquipmentCode();

    void saveEquipment(EquipmentDto equipmentDto);


    List<EquipmentDto> getAllEquipment();


    EquipmentStatus getEquipment(String equipmentId);


    void deleteEquipment(String equipmentId);


    void updateEquipment(String equipmentId, EquipmentDto equipmentDto);

    long getCount();

    List<String> getAllIds();
}
