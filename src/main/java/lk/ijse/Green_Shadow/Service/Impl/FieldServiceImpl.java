package lk.ijse.Green_Shadow.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.FieldDao;
import lk.ijse.Green_Shadow.Dto.FieldDto;
import lk.ijse.Green_Shadow.Entity.Field;
import lk.ijse.Green_Shadow.Service.FieldService;
import lk.ijse.Green_Shadow.exception.DataPersistException;

import lk.ijse.Green_Shadow.util.Mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping modelMapper;

    public void SaveField(FieldDto fieldDto){
        fieldDto.getFieldCode();
        Field savedfeild =
                fieldDao.save(modelMapper.toFieldEntity(fieldDto));
        if(savedfeild == null){
            throw new DataPersistException("Note not saved");
        }
    }
}
