package lk.ijse.Green_Shadow.Dto.Impl;

import lk.ijse.Green_Shadow.Dto.MoniteringLogStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoniteringLogDto implements MoniteringLogStatus {

    private String logCode;
    private Date logDate;
    private String logDetails;
    private String observedImage;
    private List<CropDto> cropDtos;
}
