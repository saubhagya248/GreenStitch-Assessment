package com.GreenStitch.loginsignupservice.DTO;

import com.GreenStitch.loginsignupservice.Enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO {
    String name;

    String username;

    String password;

    Role role;
}
