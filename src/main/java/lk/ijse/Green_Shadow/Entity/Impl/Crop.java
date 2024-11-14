package lk.ijse.Green_Shadow.Entity.Impl;

import jakarta.persistence.*;
import lk.ijse.Green_Shadow.Entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Crop")
public class Crop implements SuperEntity {
    @Id
    private String cropCode;
    private String commonName;
    private String scientificName;
    @Column(columnDefinition = "LONGTEXT")
    private String  cropImage;
    private String category;
    private String cropSeason;
    @ManyToOne
    @JoinColumn(name = "fieldCode",nullable = false)
    private Field field;
}
