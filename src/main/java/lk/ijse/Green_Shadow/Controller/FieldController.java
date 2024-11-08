package lk.ijse.Green_Shadow.Controller;

import lk.ijse.Green_Shadow.Dto.FieldStatus;
import lk.ijse.Green_Shadow.Dto.Impl.FieldDto;
import lk.ijse.Green_Shadow.Service.FieldService;
import lk.ijse.Green_Shadow.customStatusCodes.SelectedErrorStatus;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import lk.ijse.Green_Shadow.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/field")
@CrossOrigin
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(@RequestBody FieldDto fieldDto) {
        try {
            fieldService.SaveField(fieldDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
       }catch (DataPersistException e){
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{FieldCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldStatus getSelectedField(@PathVariable ("FieldCode") String fieldCode){
        if (!RegexProcess.FieldIdMatcher(fieldCode)) {
            return new SelectedErrorStatus(1,"Field ID is not valid");
        }
        return fieldService.getField(fieldCode);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDto> getALlNotes(){
        return fieldService.getAllField();
    }

    @DeleteMapping(value = "/{FieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable ("FieldCode") String fieldCode){
        try {
            if (!RegexProcess.FieldIdMatcher(fieldCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldService.deletefield(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
