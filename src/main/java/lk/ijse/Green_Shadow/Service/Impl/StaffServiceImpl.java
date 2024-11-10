package lk.ijse.Green_Shadow.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.FieldDao;
import lk.ijse.Green_Shadow.Dao.StaffDao;
import lk.ijse.Green_Shadow.Dto.Impl.FieldDto;
import lk.ijse.Green_Shadow.Dto.Impl.StaffDto;
import lk.ijse.Green_Shadow.Entity.Field;
import lk.ijse.Green_Shadow.Entity.Staff;
import lk.ijse.Green_Shadow.Service.StaffService;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import lk.ijse.Green_Shadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
