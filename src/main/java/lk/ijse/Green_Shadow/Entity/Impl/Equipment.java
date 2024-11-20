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
@Table(name = "Equipment")
public class Equipment implements SuperEntity {
    @Id
    private String equipmentId;
    private String name;
    private String type;
    private String status;
    @ManyToOne
    @JoinColumn(name = "StaffId",nullable = false)
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "fieldCode",nullable = false)
    private Field fields;

}
