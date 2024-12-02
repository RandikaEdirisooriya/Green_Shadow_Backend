package lk.ijse.Green_Shadow.Controller;

import lk.ijse.Green_Shadow.Dto.Impl.CropDto;
import lk.ijse.Green_Shadow.Dto.Impl.MoniteringLogDto;
import lk.ijse.Green_Shadow.Dto.MoniteringLogStatus;
import lk.ijse.Green_Shadow.Service.MoniteringLogService;
import lk.ijse.Green_Shadow.customStatusCodes.SelectedErrorStatus;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import lk.ijse.Green_Shadow.util.AppUtil;
import lk.ijse.Green_Shadow.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/logs")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class MoniteringLogController {
    @Autowired
    private MoniteringLogService moniteringLogService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLogs( @RequestParam("logCode") String logCode,
                                          @RequestParam("logDate") String logDate,
                                          @RequestParam("logDetails") String logDetails,
                                          @RequestParam("observedImage") MultipartFile observedImage ) {
        try {

            String base64ProPic = "";

            byte [] bytesProPic = observedImage.getBytes();
            base64ProPic = AppUtil.ImageToBase64(bytesProPic);



            MoniteringLogDto logDto = new MoniteringLogDto();
            logDto.setLogCode(logCode);
            logDto.setLogDate(logDate);
            logDto.setLogDetails(logDetails);
            logDto.setObservedImage(base64ProPic);




            moniteringLogService.saveLogs(logDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{logID}",produces = MediaType.APPLICATION_JSON_VALUE)
    public MoniteringLogStatus getSelectedLogs(@PathVariable ("logID") String logID){
        if (!RegexProcess.LogsIdMatcher(logID)) {
            return new SelectedErrorStatus(1,"Log ID is not valid");
        }
        return moniteringLogService.getLogs(logID);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MoniteringLogDto> getALlLogs(){
        return moniteringLogService.getAllLogs();
    }
    @DeleteMapping(value = "/{logID}")
    public ResponseEntity<Void> deleteLogs(@PathVariable ("logID") String logID){
        try {
            if (!RegexProcess.LogsIdMatcher(logID)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            moniteringLogService.deleteLogs(logID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{logID}" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatedLogs(@PathVariable ("logID") String logID,

                                            @RequestParam("logDate") String logDate,
                                            @RequestParam("logDetails") String logDetails,
                                            @RequestParam("observedImage") MultipartFile observedImage ) {

        try {
            String base64ProPic = "";

            byte[] bytesProPic = observedImage.getBytes();
            base64ProPic = AppUtil.ImageToBase64(bytesProPic);


            MoniteringLogDto logDto = new MoniteringLogDto();
            logDto.setLogCode(logID);
            logDto.setLogDate(logDate);
            logDto.setLogDetails(logDetails);
            logDto.setObservedImage(base64ProPic);

            //Build the Object
            moniteringLogService.updatedLogs(logID, logDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
