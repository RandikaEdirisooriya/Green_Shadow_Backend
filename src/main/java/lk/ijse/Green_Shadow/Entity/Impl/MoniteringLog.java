package lk.ijse.Green_Shadow.Entity.Impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lk.ijse.Green_Shadow.Entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "MoniteringLog")
public class MoniteringLog implements SuperEntity {
    @Id
    private String logCode;
    private String logDate;
    private String logDetails;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;

    @OneToMany(mappedBy = "log")
    @JsonManagedReference  // Serialize the crops in the log
    private List<Crop> crops;

    @OneToMany(mappedBy = "log")
    @JsonManagedReference  // Serialize the staff in the log
    private List<Staff> staff;

    @OneToMany(mappedBy = "log")
    @JsonManagedReference  // Serialize the fields in the log
    private List<Field> fields;
}
