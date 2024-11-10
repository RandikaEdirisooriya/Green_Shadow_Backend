package lk.ijse.Green_Shadow.Dto.Impl;

import lk.ijse.Green_Shadow.Dto.FieldStatus;
import lk.ijse.Green_Shadow.Dto.StaffStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDto implements StaffStatus {
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
