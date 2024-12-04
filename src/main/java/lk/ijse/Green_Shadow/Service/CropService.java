package lk.ijse.Green_Shadow.Service;

import lk.ijse.Green_Shadow.Dto.CropStatus;
import lk.ijse.Green_Shadow.Dto.Impl.CropDto;

import java.util.List;

public interface CropService  {
    void saveCrop(CropDto cropDto);
    CropStatus getCrop(String cropId);
    List<CropDto> getAllCrops();
    void deleteCrop(String cropId);
    void updateCrop(String CropId, CropDto cropDto);
    String findLastCropCode();
    List<String> getAllCropIds();
     long getCropCount();
}
