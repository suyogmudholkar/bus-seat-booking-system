package com.example.loginapi.Service;
import com.example.loginapi.dto.*;
import com.example.loginapi.Entity.*;
import com.example.loginapi.Repository.*;
import com.example.loginapi.Service.*;
import com.example.loginapi.security.JwtService;
import io.jsonwebtoken.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;


    public UserService(UserRepository userRepository, JwtService jwtService){
        this.userRepository=userRepository;
        this.jwtService=jwtService;
    }
    public LoginResponse login(LoginRequest request){

        System.out.println("Username = " + request.getUsername());
        System.out.println("Password = " + request.getPassword());

        User user = userRepository.findByUsernameAndPassword(
                request.getUsername(),
                request.getPassword()
        );

        System.out.println("User Found = " + user);

        if(user != null){

            String token =
                    jwtService.generateToken(
                            user.getUsername()
                    );

            return new LoginResponse(
                    "Login Successful",
                    token
            );
        }

        return new LoginResponse(
                "Invalid crediantials ",
                null
        );
    }
    
    
    
    
}
