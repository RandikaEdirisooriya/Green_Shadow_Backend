package lk.ijse.Green_Shadow.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.EquipmentDao;
import lk.ijse.Green_Shadow.Dao.FieldDao;
import lk.ijse.Green_Shadow.Dto.EquipmentStatus;
import lk.ijse.Green_Shadow.Dto.Impl.EquipmentDto;
import lk.ijse.Green_Shadow.Entity.Impl.Equipment;
import lk.ijse.Green_Shadow.Entity.Impl.Field;
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

    @Autowired
    private FieldDao fieldDao;

    @Override
    public void saveEquipment(EquipmentDto equipmentDto) {
        // Validate if the field exists before proceeding
        Field field = fieldDao.findById(equipmentDto.getFieldCode())
                .orElseThrow(() -> new DataPersistException("Field not found"));

        // Map the DTO to the Equipment entity
        Equipment equipment = mapping.toEquipmentEntity(equipmentDto);

        // Set the associated Field object to the Equipment
        equipment.setFields(field);

        // Save the equipment entity
        Equipment savedEquipment = equipmentDao.save(equipment);

        if (savedEquipment == null) {
            throw new DataPersistException("Equipment not saved");
        }
    }

    @Override
    public List<EquipmentDto> getAllEquipment() {
        // Get all equipment from the database and map them to DTOs
        List<Equipment> equipmentList = equipmentDao.findAll();
        return mapping.asEquipmentDTOList(equipmentList);
    }

    @Override
    public EquipmentStatus getEquipment(String equipmentId) {
        // Check if the equipment exists
        if (equipmentDao.existsById(equipmentId)) {
            Equipment equipment = equipmentDao.getReferenceById(equipmentId);
            return mapping.toEquipmentDTO(equipment);
        } else {
            return new SelectedErrorStatus(2, "Selected Equipment not found");
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        // Find the equipment by ID, and if not found, throw an exception
        Optional<Equipment> foundEquipment = equipmentDao.findById(equipmentId);
        if (!foundEquipment.isPresent()) {
            throw new DataPersistException("Equipment not found");
        }

        // Delete the equipment from the database
        equipmentDao.deleteById(equipmentId);
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDto equipmentDto) {
        // Find the equipment by ID
        Optional<Equipment> findEquipment = equipmentDao.findById(equipmentId);
        if (!findEquipment.isPresent()) {
            throw new DataPersistException("Equipment not found");
        }

        // Update the equipment's properties
        Equipment equipment = findEquipment.get();
        equipment.setName(equipmentDto.getName());
        equipment.setType(equipmentDto.getType());
        equipment.setStatus(equipmentDto.getStatus());

        // Save the updated equipment back to the database
        equipmentDao.save(equipment);
    }
    @Override
    public String findLastEquipmentCode() {
        return equipmentDao.findLastEquipmentCode();
    }
    @Override
    public long getCount() {
        return equipmentDao.getCount();
    }

    @Override
    public List<String> getAllIds() {
        return equipmentDao.getAllIds(); // Fetch all crop IDs from DB
    }

}
