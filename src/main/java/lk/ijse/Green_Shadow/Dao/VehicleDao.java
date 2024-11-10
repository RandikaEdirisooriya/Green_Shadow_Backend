package lk.ijse.Green_Shadow.Dao;


import lk.ijse.Green_Shadow.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDao  extends JpaRepository<Vehicle, String> {
}
