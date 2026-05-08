package com.ra.service;

import com.ra.dto.ResponseWrapper;
import com.ra.dto.user.request.UserLoginRequestDTO;
import com.ra.dto.user.request.UserRegisterRequestDTO;
import com.ra.dto.user.response.UserLoginResponseDTO;

public interface AuthService {
    ResponseWrapper<?> register(UserRegisterRequestDTO userRegisterRequestDTO);
    UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO);
}
