package com.GreenStitch.loginsignupservice.Service.Impl;

import com.GreenStitch.loginsignupservice.DTO.JwtRequest;
import com.GreenStitch.loginsignupservice.DTO.JwtResponse;
import com.GreenStitch.loginsignupservice.DTO.UserRequestDTO;
import com.GreenStitch.loginsignupservice.DTO.UserResponseDTO;
import com.GreenStitch.loginsignupservice.Exceptions.UserAlreadyExistsException;
import com.GreenStitch.loginsignupservice.JwtUtils.CustomUserDetailsService;
import com.GreenStitch.loginsignupservice.JwtUtils.JwtTokenUtil;
import com.GreenStitch.loginsignupservice.Model.User;
import com.GreenStitch.loginsignupservice.Repository.UserRepository;
import com.GreenStitch.loginsignupservice.Service.UserService;
import com.GreenStitch.loginsignupservice.Transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Override
    public JwtResponse addUser(UserRequestDTO userRequest) throws UserAlreadyExistsException{
        Optional<User> optionalUser = userRepository.findByUsername(userRequest.getUsername());

        if(optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("user with same username already exists");
        }

        //encoding the password before saving it into the databbase
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);

        //saving into the database
        User responseUser = userRepository.save(UserTransformer.userRequestDtoToUser(userRequest));
        
        final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(userRequest.getUsername());

        //generating the token
        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponse(token);

    }


    @Override
    public JwtResponse login(JwtRequest authenticationRequest) throws Exception{
        try{
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

            final String token = jwtTokenUtil.generateToken(userDetails);

            final JwtResponse response= new JwtResponse(token);

            return response;
        } catch(Exception e){
            throw new Exception(e);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
