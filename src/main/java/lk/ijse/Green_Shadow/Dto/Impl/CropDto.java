package lk.ijse.Green_Shadow.Dto.Impl;

import lk.ijse.Green_Shadow.Dto.CropStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDto implements CropStatus {
    private String cropCode;
    private String commonName;
    private String scientificName;
    private String  cropImage;
    private String category;
    private String cropSeason;
    private String fieldCode;
    private String logCode;
}
