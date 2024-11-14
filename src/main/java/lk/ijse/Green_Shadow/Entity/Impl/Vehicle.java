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
@Table(name = "Vehicle")
public class Vehicle implements SuperEntity {
    @Id
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    @ManyToOne
    @JoinColumn(name = "StaffId",nullable = false)
    private Staff staff;
}
