package lk.ijse.Green_Shadow.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.UserDao;
import lk.ijse.Green_Shadow.Dto.Impl.UserDto;
import lk.ijse.Green_Shadow.Dto.UserStatus;
import lk.ijse.Green_Shadow.Entity.Impl.User;
import lk.ijse.Green_Shadow.Service.UserService;
import lk.ijse.Green_Shadow.customStatusCodes.SelectedErrorStatus;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import lk.ijse.Green_Shadow.exception.UserNotFoundException;
import lk.ijse.Green_Shadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveUser(UserDto userDTO) {
        User savedUser =
                userDao.save(mapping.toUserEntity(userDTO));
        if (savedUser == null) {
            throw new DataPersistException("User not saved");
        }
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userDao.findAll();
        return mapping.asUserDTOList(allUsers);
    }

    @Override
    public UserStatus getUser(String userId) {
        if(userDao.existsById(userId)){
            User selectedUser = userDao.getReferenceById(userId);
            return mapping.toUserDTO(selectedUser);
        }else {
            return new SelectedErrorStatus(2, "User with id " + userId + " not found");
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<User> existedUser = userDao.findById(userId);
        if(!existedUser.isPresent()){
            throw new DataPersistException("User with id " + userId + " not found");
        }else {
            userDao.deleteById(userId);
        }
    }

    @Override
    public void updateUser(String userId, UserDto userDTO) {
        Optional<User> tmpUser = userDao.findById(userId);
        if(tmpUser.isPresent()) {
            tmpUser.get().setFirstName(userDTO.getFirstName());
            tmpUser.get().setLastName(userDTO.getLastName());
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setProfilePic(userDTO.getProfilePic());
        }
    }

    @Override
    public UserDetailsService userDetailsService() {
        return userName ->
                userDao.findByEmail(userName)
                        .orElseThrow(()-> new UserNotFoundException("User Not Found"));
    }
}
