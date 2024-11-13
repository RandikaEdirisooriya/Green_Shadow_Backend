package lk.ijse.Green_Shadow.Service;


import lk.ijse.Green_Shadow.Dto.Impl.UserDto;
import lk.ijse.Green_Shadow.secure.JWTAuthResponse;
import lk.ijse.Green_Shadow.secure.SignIn;

public interface AuthService {
   JWTAuthResponse signIn(SignIn signIn);
   JWTAuthResponse signUp(UserDto userDTO);
   JWTAuthResponse refreshToken(String accessToken);
}
