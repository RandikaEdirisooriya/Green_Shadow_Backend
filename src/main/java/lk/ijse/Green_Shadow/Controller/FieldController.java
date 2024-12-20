package lk.ijse.Green_Shadow.Controller;

import lk.ijse.Green_Shadow.Dto.FieldStatus;
import lk.ijse.Green_Shadow.Dto.Impl.FieldDto;
import lk.ijse.Green_Shadow.Dto.Impl.StaffDto;
import lk.ijse.Green_Shadow.Service.FieldService;
import lk.ijse.Green_Shadow.customStatusCodes.SelectedErrorStatus;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import lk.ijse.Green_Shadow.util.AppUtil;
import lk.ijse.Green_Shadow.util.RegexProcess;
import lk.ijse.Green_Shadow.util.ResponseUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/field")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class FieldController {

    @Autowired
    private FieldService fieldService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestParam("fieldCode") String fieldCode,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocation[x]") int x,
            @RequestParam("fieldLocation[y]") int y,
            @RequestParam("extent_size") Double extentSize,
            @RequestParam("fieldImageOne") MultipartFile fieldImageOne,
            @RequestParam("fieldImageTwo") MultipartFile fieldImageTwo,
            @RequestParam("logCode") String logCode) {
        try {
            // Convert image files to Base64 strings
            String base64ImageOne = AppUtil.ImageToBase64(fieldImageOne.getBytes());
            String base64ImageTwo = AppUtil.ImageToBase64(fieldImageTwo.getBytes());

            // Prepare StaffDto list


            // Create and set FieldDto
            FieldDto fieldDto = new FieldDto();
            fieldDto.setFieldCode(fieldCode);
            fieldDto.setFieldName(fieldName);
            fieldDto.setFieldLocation(new Point(x, y));
            fieldDto.setExtent_size(extentSize);
            fieldDto.setFieldImageOne(base64ImageOne);
            fieldDto.setFieldImageTwo(base64ImageTwo);

            fieldDto.setLogCode(logCode);

            // Save the field
            fieldService.SaveField(fieldDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{FieldCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldStatus getSelectedField(@PathVariable("FieldCode") String fieldCode) {
        if (!RegexProcess.FieldIdMatcher(fieldCode)) {
            return new SelectedErrorStatus(1, "Field ID is not valid");
        }
        return fieldService.getField(fieldCode);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDto> getALlFields() {
        return fieldService.getAllField();
    }

    @DeleteMapping(value = "/{FieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("FieldCode") String fieldCode) {
        try {
            if (!RegexProcess.FieldIdMatcher(fieldCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldService.deletefield(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{FieldCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateField(@PathVariable("FieldCode") String fieldCode,

                                            @RequestParam("fieldName") String fieldName,
                                            @RequestParam("fieldLocation[x]") int x,
                                            @RequestParam("fieldLocation[y]") int y,
                                            @RequestParam("extent_size") Double extentSize,
                                            @RequestParam("fieldImageOne") MultipartFile fieldImageOne,
                                            @RequestParam("fieldImageTwo") MultipartFile fieldImageTwo,
                                            @RequestParam("logCode") String logCode) {
        try {
            // Convert image files to Base64 strings
            String base64ImageOne = AppUtil.ImageToBase64(fieldImageOne.getBytes());
            String base64ImageTwo = AppUtil.ImageToBase64(fieldImageTwo.getBytes());



            // Create and set FieldDto
            FieldDto updatedFieldDTO = new FieldDto();
            updatedFieldDTO.setFieldCode(fieldCode);
            updatedFieldDTO.setFieldName(fieldName);
            updatedFieldDTO.setFieldLocation(new Point(x, y));
            updatedFieldDTO.setExtent_size(extentSize);
            updatedFieldDTO.setFieldImageOne(base64ImageOne);
            updatedFieldDTO.setFieldImageTwo(base64ImageTwo);
            updatedFieldDTO.setLogCode(logCode);
            fieldService.updateField(fieldCode, updatedFieldDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/nextcode")
    public ResponseUtill getNewFieldCode() {
        String newCode = AppUtil.generateFieldCode(fieldService.findLastFieldCode());
        return new ResponseUtill("Success", "Retrieved New Code", newCode);
    }
    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        long Count = fieldService.getCount();
        return new ResponseEntity<>(Count, HttpStatus.OK);
    }
    @GetMapping("/ids")
    public ResponseEntity<List<String>> getAllIds() {
        List<String> Ids = fieldService.getAllIds();
        return new ResponseEntity<>(Ids, HttpStatus.OK);
    }
}
