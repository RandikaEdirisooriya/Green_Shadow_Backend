package lk.ijse.Green_Shadow.Entity.Impl;

import jakarta.persistence.*;
import lk.ijse.Green_Shadow.Entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Field")
public class Field implements SuperEntity {
    @Id
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private Double Extent_size;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImageOne;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImageTwo;
    @OneToMany(mappedBy = "field")
    private List<Crop> crops;
}
