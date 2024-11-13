package lk.ijse.Green_Shadow.Service;

import lk.ijse.Green_Shadow.Dto.Impl.UserDto;
import lk.ijse.Green_Shadow.Dto.UserStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDTO);
    List<UserDto> getAllUsers();
    UserStatus getUser(String userId);
    void deleteUser(String userId);
    void updateUser(String userId, UserDto userDTO);
    UserDetailsService userDetailsService();
}
