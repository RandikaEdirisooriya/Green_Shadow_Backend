package lk.ijse.Green_Shadow.Controller;

import lk.ijse.Green_Shadow.Dto.Impl.VehicleDto;
import lk.ijse.Green_Shadow.Dto.VehicleStatus;
import lk.ijse.Green_Shadow.Service.VehicleService;
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
@RequestMapping(value = "api/v1/vehicle")
@CrossOrigin
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDto vehicleDto) {
        try {
            vehicleService.saveVehicle(vehicleDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{vehicleId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleStatus getSelectedVehicle(@PathVariable ("vehicleId") String vehicleId){
        if (!RegexProcess.vehicleIdMatcher(vehicleId)) {
            return new SelectedErrorStatus(1,"vehicle ID is not valid");
        }
        return vehicleService.getVehicle(vehicleId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDto> getALlVehicles(){
        return vehicleService.getAllVehicles();
    }
    @DeleteMapping(value = "/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable ("vehicleId") String vehicleId){
        try {
            if (!RegexProcess.vehicleIdMatcher(vehicleId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vehicleService.deleteVehicle(vehicleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{vehicleId}")
    public ResponseEntity<Void> updateVehicle(@PathVariable ("vehicleId") String vehicleId,
                                           @RequestBody VehicleDto updatedVehicleDto){
        //validations
        try {
            if(!RegexProcess.vehicleIdMatcher(vehicleId) || updatedVehicleDto == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vehicleService.updateVehicle(vehicleId,updatedVehicleDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
