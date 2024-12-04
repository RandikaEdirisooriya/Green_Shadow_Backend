package lk.ijse.Green_Shadow.Dao;

import lk.ijse.Green_Shadow.Entity.Impl.MoniteringLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoniteringLogDao extends JpaRepository<MoniteringLog, String> {
    @Query(value = "SELECT log_code FROM monitering_log WHERE log_code LIKE 'L00%' ORDER BY CAST(SUBSTRING(log_code, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastLogCode();

    @Query(value = "SELECT log_code FROM monitering_log", nativeQuery = true)
    List<String> getAllIds();

    @Query(value = "SELECT COUNT(*) FROM monitering_log", nativeQuery = true)
    long getCount();
}
