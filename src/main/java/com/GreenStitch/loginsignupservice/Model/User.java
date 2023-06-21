package com.GreenStitch.loginsignupservice.Model;

import com.GreenStitch.loginsignupservice.Enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="`user`")
public class User{
    @Id
    @GeneratedValue
    Integer id;


    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "username", nullable = false, unique = true)
    String username;

    @Column(name = "password", nullable = false, unique = true)
    String password;

    @Column(name = "role", nullable = false)
    Role role;


}
