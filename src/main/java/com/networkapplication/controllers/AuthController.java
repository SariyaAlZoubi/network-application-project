package com.networkapplication.controllers;

import com.networkapplication.dtos.MainDTO;
import com.networkapplication.dtos.Request.AdminRegisterDTO;
import com.networkapplication.dtos.Request.UserDTORequest;
import com.networkapplication.exceptions.GlobalExceptionHandler;
import com.networkapplication.exceptions.ResponseException;
import com.networkapplication.services.AuthService;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService services;
    private final GlobalExceptionHandler exceptionHandler;
    private final List<UserDTORequest> batchedRequests= new ArrayList<>();

    @PostMapping("/login")
    public ResponseEntity<MainDTO> login(@RequestBody UserDTORequest user) {
        try {
            return ResponseEntity.ok(services.login(user));
        } catch (ResponseException ex) {
            return exceptionHandler.handleException(ex);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<MainDTO> register(@RequestBody UserDTORequest user) {
        try {
            return ResponseEntity.ok(services.register(user));
        } catch (ResponseException ex) {
            return exceptionHandler.handleException(ex);
        }
    }


    @PostMapping("/registerAsAdmin")
    public ResponseEntity<MainDTO> registerAsAdmin(@RequestBody AdminRegisterDTO admin) {
        try {
            return ResponseEntity.ok(services.adminRegister(admin));
        } catch (ResponseException ex) {
            return exceptionHandler.handleException(ex);
        }
    }



}


