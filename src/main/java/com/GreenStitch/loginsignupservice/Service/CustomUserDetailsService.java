package com.GreenStitch.loginsignupservice.Service;

import com.GreenStitch.loginsignupservice.Model.User;
import com.GreenStitch.loginsignupservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //check if username exists
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()) throw new UsernameNotFoundException("user not found with given username");
        User user = optionalUser.get();

        //if exists then return the userdetails.User
        if(user.getUsername().equals(username)){
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
        }

        return null;
    }
}
