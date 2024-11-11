package lk.ijse.Green_Shadow.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.EquipmentDao;
import lk.ijse.Green_Shadow.Dto.EquipmentStatus;
import lk.ijse.Green_Shadow.Dto.Impl.EquipmentDto;
import lk.ijse.Green_Shadow.Entity.Equipment;
import lk.ijse.Green_Shadow.Service.EquipmentService;
import lk.ijse.Green_Shadow.customStatusCodes.SelectedErrorStatus;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import lk.ijse.Green_Shadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDto equipmentDto) {
        equipmentDto.getEquipmentId();
        Equipment savedNote =
                equipmentDao.save(mapping.toEquipmentEntity(equipmentDto));
        if(savedNote == null){
            throw new DataPersistException("Equipment not saved");
        }
    }

    @Override
    public List<EquipmentDto> getAllEquipment() {
        return mapping.asEquipmentDTOList( equipmentDao.findAll());
    }

    @Override
    public EquipmentStatus getEquipment(String equipmentId) {
        if(equipmentDao.existsById(equipmentId)){
            var selectedEquipment = equipmentDao.getReferenceById(equipmentId);
            return mapping.toEquipmentDTO(selectedEquipment);
        }else {
            return new SelectedErrorStatus(2,"Selected Equipment not found");
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<Equipment> foundEquipment = equipmentDao.findById(equipmentId);
        if (!foundEquipment.isPresent()) {
            throw new DataPersistException("Equipment not found");
        }else {
            equipmentDao.deleteById(equipmentId);
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDto equipmentDto) {
        Optional<Equipment> findEquipment = equipmentDao.findById(equipmentId);
        if (!findEquipment.isPresent()) {
            throw new DataPersistException("Equipment not found");
        }else {
            findEquipment.get().setName(equipmentDto.getName());
            findEquipment.get().setType(equipmentDto.getType());
            findEquipment.get().setStatus(equipmentDto.getStatus());

        }
    }
}
