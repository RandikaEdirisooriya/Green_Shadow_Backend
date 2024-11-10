package lk.ijse.Green_Shadow.Service;

import lk.ijse.Green_Shadow.Dto.CropStatus;
import lk.ijse.Green_Shadow.Dto.Impl.CropDto;

public interface CropService {
    void saveCrop(CropDto cropDto);
    CropStatus getCrop(String cropId);
}
