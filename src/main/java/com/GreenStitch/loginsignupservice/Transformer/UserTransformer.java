package com.GreenStitch.loginsignupservice.Transformer;

import com.GreenStitch.loginsignupservice.DTO.UserRequestDTO;
import com.GreenStitch.loginsignupservice.DTO.UserResponseDTO;
import com.GreenStitch.loginsignupservice.Model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserTransformer {

    public static User userRequestDtoToUser(UserRequestDTO userRequestDTO){
        return User.builder()
                .name(userRequestDTO.getName())
                .username(userRequestDTO.getUsername())
                .password(userRequestDTO.getPassword())
                .role(userRequestDTO.getRole())
                .build();
    }

    public static UserResponseDTO userToUserResponseDto(User user){
        return UserResponseDTO.builder()
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }
}
