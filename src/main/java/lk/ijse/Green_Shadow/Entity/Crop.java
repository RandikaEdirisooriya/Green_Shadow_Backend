package lk.ijse.Green_Shadow.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Crop")
public class Crop {
    @Id
    private String cropCode;          // Unique code for each crop
    private String commonName;         // Common name of the crop
    private String scientificName;     // Scientific name of the crop
    private String  cropImage;          // Image of the crop as a Long text (e.g., URL or base64)
    private String category;           // Category of the crop (e.g., Cereal)
    private String cropSeason;
}
