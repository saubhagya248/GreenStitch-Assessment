package com.GreenStitch.loginsignupservice.Controller;

import com.GreenStitch.loginsignupservice.DTO.JwtRequest;
import com.GreenStitch.loginsignupservice.DTO.JwtResponse;
import com.GreenStitch.loginsignupservice.DTO.UserRequestDTO;
import com.GreenStitch.loginsignupservice.DTO.UserResponseDTO;
import com.GreenStitch.loginsignupservice.Exceptions.UserAlreadyExistsException;
import com.GreenStitch.loginsignupservice.JwtUtils.CustomUserDetailsService;
import com.GreenStitch.loginsignupservice.JwtUtils.JwtTokenUtil;
import com.GreenStitch.loginsignupservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/public")
    public String dummy(){
        return "Hello";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody UserRequestDTO userRequest){
        try{
            UserResponseDTO response = userService.addUser(userRequest);
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(userRequest.getUsername());


            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token));
        } catch (UserAlreadyExistsException e) {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
       
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        final JwtResponse response= new JwtResponse(token);

        return ResponseEntity.ok(response);
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
