package lk.ijse.Green_Shadow.Controller;

import lk.ijse.Green_Shadow.Dto.CropStatus;
import lk.ijse.Green_Shadow.Dto.Impl.CropDto;
import lk.ijse.Green_Shadow.Dto.Impl.FieldDto;
import lk.ijse.Green_Shadow.Dto.Impl.MoniteringLogDto;
import lk.ijse.Green_Shadow.Service.CropService;
import lk.ijse.Green_Shadow.customStatusCodes.SelectedErrorStatus;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import lk.ijse.Green_Shadow.util.AppUtil;
import lk.ijse.Green_Shadow.util.RegexProcess;

import lk.ijse.Green_Shadow.util.ResponseUtill;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
@CrossOrigin
public class CropController {
    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> saveCrop(@RequestParam("cropCode") String cropCode,
                                         @RequestParam("commonName") String commonName,
                                         @RequestParam("scientificName") String scientificName,
                                         @RequestParam("cropImage") MultipartFile cropImage,
                                         @RequestParam("category") String category,
                                         @RequestParam("cropSeason") String cropSeason,
                                         @RequestParam("fieldCode") String fieldCode ,
                                         @RequestParam("logCode") String logCode) {
        try {
            String base64ProPic = "";

                byte [] bytesProPic = cropImage.getBytes();
                base64ProPic = AppUtil.ImageToBase64(bytesProPic);

                CropDto cropDto = new CropDto();
                cropDto.setCropCode(cropCode);
                cropDto.setCommonName(commonName);
                cropDto.setScientificName(scientificName);
                cropDto.setCropImage(base64ProPic);
                cropDto.setCategory(category);
                cropDto.setCropSeason(cropSeason);
                cropDto.setFieldCode(fieldCode);
                cropDto.setLogCode(logCode);



            cropService.saveCrop(cropDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{cropId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropStatus getSelectedCrop(@PathVariable ("cropId") String cropId){
        if (!RegexProcess.CropIdMatcher(cropId)) {
            return new SelectedErrorStatus(1,"Crop ID is not valid");
        }
        return cropService.getCrop(cropId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDto> getALlCrops(){
        return cropService.getAllCrops();
    }
    @DeleteMapping(value = "/{cropId}")
    public ResponseEntity<Void> deleteCrop(@PathVariable ("cropId") String cropId){
        try {
            if (!RegexProcess.CropIdMatcher(cropId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cropService.deleteCrop(cropId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{cropId}")
    public ResponseEntity<Void> updateNote(@PathVariable ("cropId") String cropId,
                                           @RequestParam("commonName") String commonName,
                                           @RequestParam("scientificName") String scientificName,
                                           @RequestParam("cropImage") MultipartFile cropImage,
                                           @RequestParam("category") String category,
                                           @RequestParam("cropSeason") String cropSeason){
        //validations
        try {
            String base64ProPic = "";

            byte[] bytesProPic = cropImage.getBytes();
            base64ProPic = AppUtil.ImageToBase64(bytesProPic);


            CropDto cropDto = new CropDto();
            cropDto.setCropCode(cropId);
            cropDto.setCommonName(commonName);
            cropDto.setScientificName(scientificName);
            cropDto.setCropImage(base64ProPic);
            cropDto.setCategory(category);
            cropDto.setCropSeason(cropSeason);

            cropService.updateCrop(cropId,cropDto);
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
    public ResponseUtill getNewCropCode() {
        String newCropCode = AppUtil.generateCropCode(cropService.findLastCropCode());
        return new ResponseUtill("Success", "Retrieved New Crop Code", newCropCode);
    }
    @GetMapping("/ids")
    public ResponseEntity<List<String>> getAllIds() {
        List<String> cropIds = cropService.getAllCropIds();
        return new ResponseEntity<>(cropIds, HttpStatus.OK);
    }

    // Endpoint to get the total count of crops
    @GetMapping("/count")
    public ResponseEntity<Long> getCropCount() {
        long cropCount = cropService.getCropCount();
        return new ResponseEntity<>(cropCount, HttpStatus.OK);
    }
}
