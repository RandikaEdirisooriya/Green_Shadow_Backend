package lk.ijse.Green_Shadow.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.FieldDao;
import lk.ijse.Green_Shadow.Dao.StaffDao;
import lk.ijse.Green_Shadow.Dto.Impl.FieldDto;
import lk.ijse.Green_Shadow.Dto.FieldStatus;
import lk.ijse.Green_Shadow.Dto.Impl.StaffDto;
import lk.ijse.Green_Shadow.Entity.Impl.Field;
import lk.ijse.Green_Shadow.Entity.Impl.Staff;
import lk.ijse.Green_Shadow.Service.FieldService;
import lk.ijse.Green_Shadow.customStatusCodes.SelectedErrorStatus;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import lk.ijse.Green_Shadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private Mapping modelMapper;

    @Override
    public void SaveField(FieldDto fieldDto) {
        // Map FieldDto to Field entity
        Field field = modelMapper.toFieldEntity(fieldDto);

        // Handle Many-to-Many relationship with Staffs
        List<Staff> staffs = new ArrayList<>();
        if (fieldDto.getStaffs() != null) {
            for (StaffDto staffDto : fieldDto.getStaffs()) {
                Staff staff = staffDao.findById(staffDto.getStaffId())
                        .orElseThrow(() -> new DataPersistException("Staff not found with ID: " + staffDto.getStaffId()));
                staffs.add(staff);
            }
        }
        field.setStaffs(staffs);

        // Save Field entity
        Field savedField = fieldDao.save(field);
        if (savedField == null) {
            throw new DataPersistException("Field not saved");
        }
    }

    @Override
    public List<FieldDto> getAllField() {
        // Map all Field entities to FieldDto
        return modelMapper.asFieldDTOList(fieldDao.findAll());
    }

    @Override
    public FieldStatus getField(String fieldId) {
        // Fetch Field by ID and map to FieldDto
        if (fieldDao.existsById(fieldId)) {
            Field selectedField = fieldDao.getReferenceById(fieldId);
            return modelMapper.toFieldDTO(selectedField);
        } else {
            return new SelectedErrorStatus(2, "Selected Field not found");
        }
    }

    @Override
    public void deletefield(String fieldId) {
        // Check if the Field exists before deletion
        Optional<Field> foundField = fieldDao.findById(fieldId);
        if (!foundField.isPresent()) {
            throw new DataPersistException("Field not found");
        } else {
            fieldDao.deleteById(fieldId);
        }
    }

    @Override
    public void updateField(String fieldId, FieldDto fieldDto) {
        // Fetch Field by ID and update its details
        Optional<Field> findField = fieldDao.findById(fieldId);
        if (!findField.isPresent()) {
            throw new DataPersistException("Field not found");
        } else {
            Field field = findField.get();
            field.setFieldLocation(fieldDto.getFieldLocation());
            field.setFieldName(fieldDto.getFieldName());
            field.setExtent_size(fieldDto.getExtent_size());
            field.setFieldImageOne(fieldDto.getFieldImageOne());
            field.setFieldImageTwo(fieldDto.getFieldImageTwo());

            // Update Many-to-Many relationship with Staffs
            List<Staff> staffs = new ArrayList<>();
            if (fieldDto.getStaffs() != null) {
                for (StaffDto staffDto : fieldDto.getStaffs()) {
                    Staff staff = staffDao.findById(staffDto.getStaffId())
                            .orElseThrow(() -> new DataPersistException("Staff not found with ID: " + staffDto.getStaffId()));
                    staffs.add(staff);
                }
            }
            field.setStaffs(staffs);

            // Save updated Field entity
            fieldDao.save(field);
        }
    }
}
