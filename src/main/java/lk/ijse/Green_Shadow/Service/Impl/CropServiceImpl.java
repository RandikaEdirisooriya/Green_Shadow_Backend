package lk.ijse.Green_Shadow.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.CropDao;
import lk.ijse.Green_Shadow.Dto.CropStatus;
import lk.ijse.Green_Shadow.Dto.Impl.CropDto;
import lk.ijse.Green_Shadow.Entity.Crop;
import lk.ijse.Green_Shadow.Service.CropService;
import lk.ijse.Green_Shadow.customStatusCodes.SelectedErrorStatus;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import lk.ijse.Green_Shadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CropServiceImpl  implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping noteMapping;

    @Override
    public void saveCrop(CropDto cropDto) {
        cropDto.getCropCode();
        Crop savedCrop =
                cropDao.save(noteMapping.toCropEntity(cropDto));
        if(savedCrop == null){
            throw new DataPersistException("CROP not saved");
        }
    }
    @Override
    public CropStatus getCrop(String cropId) {
        if(cropDao.existsById(cropId)){
            var selectedCrop = cropDao.getReferenceById(cropId);
            return noteMapping.toCropDTO(selectedCrop);
        }else {
            return new SelectedErrorStatus(2,"Selected note not found");
        }
    }
}
