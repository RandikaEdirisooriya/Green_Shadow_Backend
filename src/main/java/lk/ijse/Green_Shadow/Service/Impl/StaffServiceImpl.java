package lk.ijse.Green_Shadow.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.FieldDao;
import lk.ijse.Green_Shadow.Dao.StaffDao;
import lk.ijse.Green_Shadow.Dto.Impl.FieldDto;
import lk.ijse.Green_Shadow.Dto.Impl.StaffDto;
import lk.ijse.Green_Shadow.Dto.StaffStatus;
import lk.ijse.Green_Shadow.Entity.Impl.Field;
import lk.ijse.Green_Shadow.Entity.Impl.MoniteringLog;
import lk.ijse.Green_Shadow.Entity.Impl.Staff;
import lk.ijse.Green_Shadow.Service.StaffService;
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
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping modelMapper;

    @Override

    public void SaveStaff(StaffDto staffDto) {
        Staff staff = modelMapper.toStaffEntity(staffDto);

        // Fetch and set associated fields
        List<Field> fields = new ArrayList<>();
        if (staffDto.getFields() != null) {
            for (FieldDto fieldDto : staffDto.getFields()) {
                Field field = fieldDao.findById(fieldDto.getFieldCode())
                        .orElseThrow(() -> new DataPersistException("Field not found with ID: " + fieldDto.getFieldCode()));
                fields.add(field);
            }
        }
        staff.setFields(fields);

        // Save staff entity
        Staff savedStaff = staffDao.save(staff);
        if (savedStaff == null) {
            throw new DataPersistException("Staff not saved");
        }
    }

    @Override
    public StaffStatus getStaff(String staffId) {
        if (staffDao.existsById(staffId)) {
            Staff selectedStaff = staffDao.getReferenceById(staffId);
            return modelMapper.toStaffDTO(selectedStaff);
        } else {
            return new SelectedErrorStatus(2, "Selected Staff not found");
        }
    }

    @Override
    public List<StaffDto> getAllStaff() {
        return modelMapper.asStaffDTOList(staffDao.findAll());
    }

    @Override
    public void deleteStaff(String staffId) {
        Optional<Staff> foundStaff = staffDao.findById(staffId);
        if (!foundStaff.isPresent()) {
            throw new DataPersistException("Staff not found");
        } else {
            staffDao.deleteById(staffId);
        }
    }

    @Override
    public void updateStaff(String staffId, StaffDto staffDto) {
        Optional<Staff> findStaff = staffDao.findById(staffId);
        if (!findStaff.isPresent()) {
            throw new DataPersistException("Staff not found");
        } else {
            Staff staff = findStaff.get();
            staff.setFirstName(staffDto.getFirstName());
            staff.setLastName(staffDto.getLastName());
            staff.setDesignation(staffDto.getDesignation());
            staff.setGender(staffDto.getGender());
            staff.setJoinedDate(staffDto.getJoinedDate());
            staff.setDOB(staffDto.getDOB());
            staff.setAddressOne(staffDto.getAddressOne());
            staff.setAddressTwo(staffDto.getAddressTwo());
            staff.setAddressThree(staffDto.getAddressThree());
            staff.setAddressFour(staffDto.getAddressFour());
            staff.setAddressFive(staffDto.getAddressFive());
            staff.setContact_No(staffDto.getContact_No());
            staff.setEmail(staffDto.getEmail());

            // Update Many-to-Many Fields
            List<Field> fields = new ArrayList<>();
            if (staffDto.getFields() != null) {
                for (FieldDto fieldDto : staffDto.getFields()) {
                    Field field = fieldDao.findById(fieldDto.getFieldCode())
                            .orElseThrow(() -> new DataPersistException("Field not found"));
                    fields.add(field);
                }
            }
            staff.setFields(fields);
        }
    }
}
