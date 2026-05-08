package com.ra.dto.user.response;

import com.ra.entity.Role;
import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserLoginResponseDTO {
    private String username;
    private String fullName;
    private String typeToken;
    private String token;
    private Set<Role> roles;
}
