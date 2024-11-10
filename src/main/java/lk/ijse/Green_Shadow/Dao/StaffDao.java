package lk.ijse.Green_Shadow.Dao;


import lk.ijse.Green_Shadow.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDao extends JpaRepository<Staff, String> {
}
