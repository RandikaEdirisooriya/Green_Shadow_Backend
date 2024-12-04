package lk.ijse.Green_Shadow.Dao;

import lk.ijse.Green_Shadow.Entity.Impl.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldDao extends JpaRepository<Field, String> {

    @Query(value = "SELECT field_code FROM field WHERE field_code LIKE 'F00%' ORDER BY CAST(SUBSTRING(field_code, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastFieldCode();
    @Query(value = "SELECT field_code FROM field", nativeQuery = true)
    List<String> getAllIds();

    @Query(value = "SELECT COUNT(*) FROM field", nativeQuery = true)
    long getCount();
}
