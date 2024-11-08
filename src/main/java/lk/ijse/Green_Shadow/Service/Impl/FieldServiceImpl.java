package lk.ijse.Green_Shadow.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.FieldDao;
import lk.ijse.Green_Shadow.Dto.FieldStatus;
import lk.ijse.Green_Shadow.Dto.Impl.FieldDto;
import lk.ijse.Green_Shadow.Entity.Field;
import lk.ijse.Green_Shadow.Service.FieldService;
import lk.ijse.Green_Shadow.customStatusCodes.SelectedErrorStatus;
import lk.ijse.Green_Shadow.exception.DataPersistException;

import lk.ijse.Green_Shadow.util.Mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping modelMapper;
    @Override
    public void SaveField(FieldDto fieldDto){
        fieldDto.getFieldCode();
        Field savedfeild =
                fieldDao.save(modelMapper.toFieldEntity(fieldDto));
        if(savedfeild == null){
            throw new DataPersistException("Note not saved");
        }

    }
    @Override
    public List<FieldDto> getAllField() {
        return modelMapper.asFieldDTOList( fieldDao.findAll());
    }

    @Override
    public FieldStatus getField(String fieldId) {
        if(fieldDao.existsById(fieldId)){
            var selectedUser = fieldDao.getReferenceById(fieldId);
            return modelMapper.toNoteDTO(selectedUser);
        }else {
            return new SelectedErrorStatus(2,"Selected note not found");
        }
    }

}
