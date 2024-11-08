package lk.ijse.Green_Shadow.util;

import lk.ijse.Green_Shadow.Dto.Impl.FieldDto;
import lk.ijse.Green_Shadow.Entity.Field;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    public Field toFieldEntity(FieldDto fieldDto) {
        return modelMapper.map(fieldDto, Field.class);
    }
    public List<FieldDto> asFieldDTOList(List<Field> fieldList) {
        return modelMapper.map(fieldList, new TypeToken<List<FieldDto>>() {}.getType());
    }
    public FieldDto toNoteDTO(Field field) {
        return modelMapper.map(field, FieldDto.class);
    }
}
