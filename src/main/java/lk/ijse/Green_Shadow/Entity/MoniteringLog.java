package lk.ijse.Green_Shadow.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "MoniteringLog")
public class MoniteringLog {
    @Id
    private String logCode;
    private Date logDate;
    private String logDetails;
    private String observedImage;
}
