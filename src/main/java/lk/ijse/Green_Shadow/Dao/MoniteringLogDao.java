package lk.ijse.Green_Shadow.Dao;

import lk.ijse.Green_Shadow.Entity.Impl.MoniteringLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoniteringLogDao extends JpaRepository<MoniteringLog, String> {
}
