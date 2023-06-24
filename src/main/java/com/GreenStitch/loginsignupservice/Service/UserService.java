package com.GreenStitch.loginsignupservice.Service;

import com.GreenStitch.loginsignupservice.DTO.JwtRequest;
import com.GreenStitch.loginsignupservice.DTO.JwtResponse;
import com.GreenStitch.loginsignupservice.DTO.UserRequestDTO;
import com.GreenStitch.loginsignupservice.DTO.UserResponseDTO;
import org.springframework.stereotype.Service;

public interface UserService {
    public JwtResponse addUser(UserRequestDTO userRequest);

    public JwtResponse login(JwtRequest request) throws Exception;

}
