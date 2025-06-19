package org.example.blooddonationapp.controller.auth;


import jakarta.validation.Valid;
import org.example.blooddonationapp.controller.auth.dto.LoginDto;
import org.example.blooddonationapp.controller.auth.dto.LoginResponseDto;
import org.example.blooddonationapp.controller.auth.dto.RegisterDto;
import org.example.blooddonationapp.controller.auth.dto.RegisterResponseDto;
import org.example.blooddonationapp.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto requestBody){
        try {
            System.out.println("Register request received: " + requestBody.getUsername());
            RegisterResponseDto dto = authService.register(requestBody);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();  // Log the full error stack trace
            return new ResponseEntity<>("Register failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto requestBody){
        LoginResponseDto dto = authService.login(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
}
