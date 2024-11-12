package lk.ijse.Green_Shadow.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.StaffDao;
import lk.ijse.Green_Shadow.Dto.Impl.StaffDto;
import lk.ijse.Green_Shadow.Dto.StaffStatus;
import lk.ijse.Green_Shadow.Entity.Impl.Staff;
import lk.ijse.Green_Shadow.Service.StaffService;
import lk.ijse.Green_Shadow.customStatusCodes.SelectedErrorStatus;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import lk.ijse.Green_Shadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private Mapping modelMapper;
    @Override
    public void SaveStaff(StaffDto staffDto){
        staffDto.getStaffId();
        Staff savedStaff =
                staffDao.save(modelMapper.toStaffEntity(staffDto));
        if(savedStaff == null){
            throw new DataPersistException("Staff not saved");
        }

    }
    @Override
    public StaffStatus getStaff(String staffId) {
        if(staffDao.existsById(staffId)){
            var selectedStaff = staffDao.getReferenceById(staffId);
            return modelMapper.toStaffDTO(selectedStaff);
        }else {
            return new SelectedErrorStatus(2,"Selected Staff not found");
        }
    }
    @Override
    public List<StaffDto> getAllStaff() {
        return modelMapper.asStaffDTOList( staffDao.findAll());
    }
    @Override
    public void deleteStaff(String staffId) {
        Optional<Staff> foundStaff = staffDao.findById(staffId);
        if (!foundStaff.isPresent()) {
            throw new DataPersistException("Staff not found");
        }else {
            staffDao.deleteById(staffId);
        }
    }
    @Override
    public void updateStaff(String staffId, StaffDto staffDto) {
        Optional<Staff> findStaff = staffDao.findById(staffId);
        if (!findStaff.isPresent()) {
            throw new DataPersistException("Note not found");
        }else {
            findStaff.get().setFirstName(staffDto.getFirstName());
            findStaff.get().setLastName(staffDto.getLastName());
            findStaff.get().setDesignation(staffDto.getDesignation());
            findStaff.get().setGender(staffDto.getGender());
            findStaff.get().setJoinedDate(staffDto.getJoinedDate());
            findStaff.get().setDOB(staffDto.getDOB());
            findStaff.get().setAddressOne(staffDto.getAddressOne());
            findStaff.get().setAddressTwo(staffDto.getAddressTwo());
            findStaff.get().setAddressThree(staffDto.getAddressThree());
            findStaff.get().setAddressFour(staffDto.getAddressFour());
            findStaff.get().setAddressFive(staffDto.getAddressFive());
            findStaff.get().setContact_No(staffDto.getContact_No());
            findStaff.get().setEmail(staffDto.getEmail());


        }
    }
}
