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

    @OneToMany(mappedBy = "fields")
    private List<Equipment> equipments;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "Field_Staff",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "staffId")
    )
    private List<Staff> staffs;
    @ManyToOne
    @JoinColumn(name = "logCode",nullable = false)
    private MoniteringLog log;
}
