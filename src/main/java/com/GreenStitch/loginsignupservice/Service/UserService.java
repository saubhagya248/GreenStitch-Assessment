package com.GreenStitch.loginsignupservice.Service;

import com.GreenStitch.loginsignupservice.DTO.UserRequestDTO;
import com.GreenStitch.loginsignupservice.DTO.UserResponseDTO;
import org.springframework.stereotype.Service;

public interface UserService {
    public UserResponseDTO addUser(UserRequestDTO userRequest);

}
