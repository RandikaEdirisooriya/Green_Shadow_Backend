package lk.ijse.Green_Shadow.Entity.Impl;

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
    private String AddressTwo ;
    private String AddressThree ;
    private String AddressFour ;
    private String AddressFive ;
    private String Contact_No;
    private String Email;
    @OneToMany(mappedBy = "staff")
    private List<Vehicle> vehicles;
    private Role role;
    @OneToMany(mappedBy = "staff")
    private List<Equipment> equipment;
    @ManyToMany(mappedBy = "staffs", fetch = FetchType.EAGER)
    private List<Field> fields;
}
