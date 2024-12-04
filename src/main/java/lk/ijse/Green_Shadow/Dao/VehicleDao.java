package lk.ijse.Green_Shadow.Dao;


import lk.ijse.Green_Shadow.Entity.Impl.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleDao  extends JpaRepository<Vehicle, String> {
    @Query(value = "SELECT vehicle_code FROM vehicle WHERE vehicle_code LIKE 'V00%' ORDER BY CAST(SUBSTRING(vehicle_code, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastCode();
    @Query(value = "SELECT vehicle_code FROM vehicle", nativeQuery = true)
    List<String> getAllIds();

    @Query(value = "SELECT COUNT(*) FROM vehicle", nativeQuery = true)
    long getCount();
}
