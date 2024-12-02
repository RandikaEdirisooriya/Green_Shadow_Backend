package lk.ijse.Green_Shadow.Entity.Impl;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lk.ijse.Green_Shadow.Entity.Enum.Gender;
import lk.ijse.Green_Shadow.Entity.Enum.Role;
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
@Table(name = "Staff")
@JsonIdentityInfo(generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class, property = "StaffId")

public class Staff implements SuperEntity {
    @Id
    private String StaffId;
    private String FirstName;
    private String LastName;
    private String Designation;

    @Enumerated(EnumType.STRING)
    private Gender Gender;

    private Date JoinedDate;
    private Date DOB;
    private String AddressOne;
    private String AddressTwo;
    private String AddressThree;
    private String AddressFour;
    private String AddressFive;
    private String Contact_No;
    private String Email;

    @OneToMany(mappedBy = "staff")
    @JsonManagedReference  // Serialize the vehicles for the staff
    private List<Vehicle> vehicles;

    private Role role;

    @OneToMany(mappedBy = "staff")
    @JsonManagedReference  // Serialize the equipment for the staff
    private List<Equipment> equipment;

    @ManyToMany(mappedBy = "staffs", cascade = {CascadeType.PERSIST, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonManagedReference  // Serialize the fields for the staff
    private List<Field> fields;

    @ManyToOne
    @JsonBackReference  // Prevent infinite recursion on the log relationship
    @JoinColumn(name = "logCode", nullable = false)
    private MoniteringLog log;
}
