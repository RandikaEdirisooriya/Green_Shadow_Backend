package lk.ijse.Green_Shadow.Dao;


import lk.ijse.Green_Shadow.Entity.Impl.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentDao extends JpaRepository<Equipment, String> {
    @Query(value = "SELECT equipment_id FROM equipment WHERE equipment_id LIKE 'E00%' ORDER BY CAST(SUBSTRING(equipment_id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastEquipmentCode();

    @Query(value = "SELECT equipment_id FROM equipment", nativeQuery = true)
    List<String> getAllIds();

    @Query(value = "SELECT COUNT(*) FROM equipment", nativeQuery = true)
    long getCount();
}
