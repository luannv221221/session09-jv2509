package com.ra.service.impl;

import com.ra.dto.ResponseWrapper;
import com.ra.dto.user.request.UserLoginRequestDTO;
import com.ra.dto.user.request.UserRegisterRequestDTO;
import com.ra.dto.user.response.UserLoginResponseDTO;
import com.ra.entity.Role;
import com.ra.entity.User;
import com.ra.mapper.UserRegisterMapper;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import com.ra.security.UserPrinciple;
import com.ra.security.jwt.JwtProvider;
import com.ra.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RoleRepository roleRepository;
    private final UserRegisterMapper userRegisterMapper;
    private final UserRepository userRepository;
    private final AuthenticationProvider authenticationProvider;
    private final JwtProvider jwtProvider;
    @Override
    public ResponseWrapper<?> register(UserRegisterRequestDTO userRegisterRequestDTO) {
        // kiem tra xem username da ton tai hay chua validate
        // gan quyen mac dinh cho no CUSTOMER
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.getRoleByRoleName("CUSTOMER");
        roles.add(role);
        // chuyen DTO -> Entity
        User user = userRegisterMapper.toUser(userRegisterRequestDTO);
        // ma hoa mat khau
        user.setPassword(new BCryptPasswordEncoder().encode(userRegisterRequestDTO.getPassword()));
        // set trang thai tai khoan moi dang nhap la true
        user.setStatus(true);
        // set quyen cho user
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseWrapper.success(null,"Dang ky tai khoan thanh cong", HttpStatus.CREATED.value());
    }

    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO) {
        Authentication authentication;
        authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUsername(),
                userLoginRequestDTO.getPassword()));
        assert authentication != null;
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        assert userPrinciple != null;
        return UserLoginResponseDTO.builder()
                .username(userPrinciple.getUsername())
                .token(jwtProvider.generateToken(userPrinciple))
                .fullName(userPrinciple.getUser().getFullName())
                .roles(userPrinciple.getUser().getRoles())
                .typeToken("Bearer Token")
                .build();
    }
}
