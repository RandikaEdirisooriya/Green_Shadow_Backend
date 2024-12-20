package lk.ijse.Green_Shadow.Entity.Impl;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "field",cascade = CascadeType.REMOVE)
    @JsonManagedReference  // Serialize the crops in the field
    private List<Crop> crops;

    @OneToMany(mappedBy = "fields",cascade = CascadeType.REMOVE)
    @JsonManagedReference  // Serialize the equipments in the field
    private List<Equipment> equipments;

 /*   @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "Field_Staff",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "staffId")
    )*/
 @ManyToMany(mappedBy = "fields", cascade = {CascadeType.MERGE,}, fetch = FetchType.EAGER)
    private List<Staff> staffs;

    @ManyToOne
    @JoinColumn(name = "logCode", nullable = false)
    private MoniteringLog log;
}
