package org.example.dma6m6beadando.controller;

import org.example.dma6m6beadando.Dto.AuthRespDTO;
import org.example.dma6m6beadando.Dto.RegisterDto;
import org.example.dma6m6beadando.database.RolesRepository;
import org.example.dma6m6beadando.database.UserRepository;
import org.example.dma6m6beadando.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

@RestController
@RequestMapping(value = "/api/auth",produces = MediaType.APPLICATION_JSON_VALUE)

public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService,
                          UserRepository userRepository,
                          RolesRepository rolesRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;

    }


    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {

        var retState = this.authService.register(registerDto);
        return ResponseEntity.ok(retState);
    }

    @PostMapping(value = "login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthRespDTO> login(@RequestBody RegisterDto loginDto) {

        var jwt = this.authService.login(loginDto);
        AuthRespDTO resp = new AuthRespDTO(jwt);
        return ResponseEntity.ok(resp);

    }

    @PostMapping(value = "asd")
    @PreAuthorize("hasRole('USER')")
    public void asd(@RequestBody String asd) {
        var dsa = asd;
    }

}
