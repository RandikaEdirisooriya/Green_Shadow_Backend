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
@Table(name = "Staff")
public class Staff {
    @Id
    private String StaffId;
    private String FirstName;
    private String LastName;
    private String Designation;
    private String Gender;
    private Date JoinedDate;
    private Date DOB;
    private String AddressOne;
    private String AddressTwo ;
    private String AddressThree ;
    private String AddressFour ;
    private String AddressFive ;
    private String Contact_No;
    private String Email;
}
