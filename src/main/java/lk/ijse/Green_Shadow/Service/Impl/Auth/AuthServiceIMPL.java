package lk.ijse.Green_Shadow.Service.Impl.Auth;

import lk.ijse.Green_Shadow.Dao.UserDao;
import lk.ijse.Green_Shadow.Dto.Impl.UserDto;
import lk.ijse.Green_Shadow.Entity.Impl.User;
import lk.ijse.Green_Shadow.Service.AuthService;
import lk.ijse.Green_Shadow.Service.JWTService;
import lk.ijse.Green_Shadow.secure.JWTAuthResponse;
import lk.ijse.Green_Shadow.secure.SignIn;
import lk.ijse.Green_Shadow.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceIMPL implements AuthService {
    private final UserDao userDao;
    private final Mapping mapping;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        var user = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(user);
        return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse signUp(UserDto userDTO) {
      //Save user
        User savedUser = userDao.save(mapping.toUserEntity(userDTO));
      //Generate the token and return it
        var generatedToken = jwtService.generateToken(savedUser);
       return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        //extract user name
        var userName = jwtService.extractUserName(accessToken);
        //check the user availability in the DB
        var findUser = userDao.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(findUser);
        return JWTAuthResponse.builder().token(refreshToken).build();
    }
}
