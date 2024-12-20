package lk.ijse.Green_Shadow.Service;

import lk.ijse.Green_Shadow.Dto.FieldStatus;
import lk.ijse.Green_Shadow.Dto.Impl.FieldDto;

import java.util.List;

public interface FieldService {
    void SaveField(FieldDto fieldDto);
    List<FieldDto> getAllField();
    FieldStatus getField(String fieldId);
    void deletefield(String fieldId);
    void updateField(String fieldId, FieldDto fieldDto);
    String findLastFieldCode();
    long getCount();
    List<String> getAllIds();
}
