package lk.ijse.Green_Shadow.Dao;

import lk.ijse.Green_Shadow.Entity.Impl.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT user_id FROM user WHERE user_id LIKE 'S00%' ORDER BY CAST(SUBSTRING(user_id, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastCode();
}
