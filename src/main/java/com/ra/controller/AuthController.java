package com.ra.controller;

import com.ra.dto.ResponseWrapper;
import com.ra.dto.user.request.UserLoginRequestDTO;
import com.ra.dto.user.request.UserRegisterRequestDTO;
import com.ra.dto.user.response.UserLoginResponseDTO;
import com.ra.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        ResponseWrapper<?> responseWrapper =  authService.register(userRegisterRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        UserLoginResponseDTO response =  authService.login(userLoginRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).
                body(ResponseWrapper.success(response,"Dang nhap thanh cong",HttpStatus.OK.value()));
    }
    @GetMapping("/account")
    public ResponseEntity<?> account(){
        return ResponseEntity.ok("Thong tin tai khoan");
    }
}
