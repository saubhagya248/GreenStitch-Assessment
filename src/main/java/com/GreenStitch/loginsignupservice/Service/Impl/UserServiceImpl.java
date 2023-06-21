package com.GreenStitch.loginsignupservice.Service.Impl;

import com.GreenStitch.loginsignupservice.DTO.UserRequestDTO;
import com.GreenStitch.loginsignupservice.DTO.UserResponseDTO;
import com.GreenStitch.loginsignupservice.Exceptions.UserAlreadyExistsException;
import com.GreenStitch.loginsignupservice.Model.User;
import com.GreenStitch.loginsignupservice.Repository.UserRepository;
import com.GreenStitch.loginsignupservice.Service.UserService;
import com.GreenStitch.loginsignupservice.Transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserResponseDTO addUser(UserRequestDTO userRequest) throws UserAlreadyExistsException{
        Optional<User> optionalUser = userRepository.findByUsername(userRequest.getUsername());

        if(optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("user with same username already exists");
        }

        //encoding the password before saving it into the databbase
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);

        //saving into the database
        User responseUser = userRepository.save(UserTransformer.userRequestDtoToUser(userRequest));

        return UserTransformer.userToUserResponseDto(responseUser);

    }
}
