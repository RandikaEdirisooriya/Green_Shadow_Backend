package lk.ijse.Green_Shadow.Service;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.FieldDao;
import lk.ijse.Green_Shadow.Dto.FieldDto;
import lk.ijse.Green_Shadow.Entity.Field;
import lk.ijse.Green_Shadow.utill.Varlist;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FieldService {
    @Autowired
    private FieldDao citizanRepo;
    @Autowired
    private ModelMapper modelMapper;

    public String SaveField(FieldDto fieldDto){
        if(citizanRepo.existsById(fieldDto.getFieldCode())){
            return Varlist.RSP_DUPLICATED;
        }else {
            citizanRepo.save(modelMapper.map(fieldDto, Field.class));
            return Varlist.RSP_SUCCESS;
        }/*   */
    }
}
