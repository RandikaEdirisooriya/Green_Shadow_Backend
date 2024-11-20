package lk.ijse.Green_Shadow.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.CropDao;
import lk.ijse.Green_Shadow.Dto.CropStatus;
import lk.ijse.Green_Shadow.Dto.Impl.CropDto;
import lk.ijse.Green_Shadow.Entity.Impl.Crop;
import lk.ijse.Green_Shadow.Service.CropService;
import lk.ijse.Green_Shadow.customStatusCodes.SelectedErrorStatus;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import lk.ijse.Green_Shadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Override
    public List<CropDto> getAllCrops() {
        return noteMapping.asNoteDTOList( cropDao.findAll());
    }

    @Override
    public void deleteCrop(String cropId) {
        Optional<Crop> foundCrop = cropDao.findById(cropId);
        if (!foundCrop.isPresent()) {
            throw new DataPersistException("Note not found");
        }else {
            cropDao.deleteById(cropId);
        }
    }

    @Override
    public void updateCrop(String CropId, CropDto cropDto) {
        Optional<Crop> findNote = cropDao.findById(CropId);
        if (!findNote.isPresent()) {
            throw new DataPersistException("Note not found");
        }else {
            findNote.get().setCategory(cropDto.getCategory());
            findNote.get().setCropImage(cropDto.getCropImage());
            findNote.get().setCropSeason(cropDto.getCropSeason());
            findNote.get().setCommonName(cropDto.getCommonName());
            findNote.get().setScientificName(cropDto.getScientificName());
        }
    }


}
