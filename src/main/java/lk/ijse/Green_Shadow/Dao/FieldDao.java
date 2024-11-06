package lk.ijse.Green_Shadow.Dao;

import lk.ijse.Green_Shadow.Entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldDao extends JpaRepository<Field, String> {
}
