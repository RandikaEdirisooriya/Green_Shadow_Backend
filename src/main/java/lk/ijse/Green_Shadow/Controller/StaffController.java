package lk.ijse.Green_Shadow.Controller;


import lk.ijse.Green_Shadow.Dto.Impl.StaffDto;
import lk.ijse.Green_Shadow.Dto.StaffStatus;
import lk.ijse.Green_Shadow.Service.StaffService;
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

import java.util.List;

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
    @GetMapping(value = "/{staffId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffStatus getSelectedStaff(@PathVariable ("staffId") String staffId){
        if (!RegexProcess.StaffIdMatcher(staffId)) {
            return new SelectedErrorStatus(1,"Staff ID is not valid");
        }
        return staffService.getStaff(staffId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDto> getALlStaff(){
        return staffService.getAllStaff();
    }

    @DeleteMapping(value = "/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable ("staffId") String staffId){
        try {
            if (!RegexProcess.StaffIdMatcher(staffId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.deleteStaff(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{staffId}")
    public ResponseEntity<Void> updateNote(@PathVariable ("staffId") String staffId,
                                           @RequestBody StaffDto updatedStaffDto){
        try {
            if(!RegexProcess.StaffIdMatcher(staffId) || updatedStaffDto == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.updateStaff(staffId,updatedStaffDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/nextcode")
    public ResponseUtill getNewStaffCode() {
        String newCode = AppUtil.generateStaffCode(staffService.findLastStaffCode());
        return new ResponseUtill("Success", "Retrieved New Code", newCode);
    }
    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        long Count = staffService.getCount();
        return new ResponseEntity<>(Count, HttpStatus.OK);
    }
    @GetMapping("/ids")
    public ResponseEntity<List<String>> getAllIds() {
        List<String> Ids = staffService.getAllIds();
        return new ResponseEntity<>(Ids, HttpStatus.OK);
    }
}
