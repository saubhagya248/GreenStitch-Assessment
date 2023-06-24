package com.GreenStitch.loginsignupservice.Controller;

import com.GreenStitch.loginsignupservice.DTO.JwtRequest;
import com.GreenStitch.loginsignupservice.DTO.JwtResponse;
import com.GreenStitch.loginsignupservice.DTO.UserRequestDTO;
import com.GreenStitch.loginsignupservice.DTO.UserResponseDTO;
import com.GreenStitch.loginsignupservice.Exceptions.UserAlreadyExistsException;
import com.GreenStitch.loginsignupservice.JwtUtils.CustomUserDetailsService;
import com.GreenStitch.loginsignupservice.JwtUtils.JwtTokenUtil;
import com.GreenStitch.loginsignupservice.Service.UserService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/public")
    public String dummy(){
        return "Hello";
    }

    @PostMapping("/signup")
    public ResponseEntity addUser(@RequestBody UserRequestDTO userRequest){
        try{
            JwtResponse response = userService.addUser(userRequest);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/login")
    public ResponseEntity createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
       try{
            JwtResponse response = userService.login(authenticationRequest);
            return ResponseEntity.ok(response);
       }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }
}
