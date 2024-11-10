package lk.ijse.Green_Shadow.Service;

import lk.ijse.Green_Shadow.Dto.Impl.StaffDto;
import lk.ijse.Green_Shadow.Dto.StaffStatus;

import java.util.List;

public interface  StaffService {
    void SaveStaff(StaffDto staffDto);
    StaffStatus getStaff(String staffId);
    List<StaffDto> getAllStaff();
    void deleteStaff(String staffId);
}
