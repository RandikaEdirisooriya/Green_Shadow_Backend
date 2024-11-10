package lk.ijse.Green_Shadow.Controller;

import lk.ijse.Green_Shadow.Dto.Impl.FieldDto;
import lk.ijse.Green_Shadow.Dto.Impl.StaffDto;
import lk.ijse.Green_Shadow.Service.FieldService;
import lk.ijse.Green_Shadow.Service.StaffService;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/staff")
@CrossOrigin
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDto staffDto) {
        try {
            staffService.SaveStaff(staffDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
