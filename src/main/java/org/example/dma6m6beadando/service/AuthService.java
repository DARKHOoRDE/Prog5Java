package org.example.dma6m6beadando.service;


import lombok.RequiredArgsConstructor;

import org.example.dma6m6beadando.Dto.RegisterDto;
import org.example.dma6m6beadando.Dto.UserDTO;
import org.example.dma6m6beadando.database.RolesRepository;
import org.example.dma6m6beadando.database.UserRepository;
import org.example.dma6m6beadando.entity.Roles;
import org.example.dma6m6beadando.entity.UserEntity;
import org.example.dma6m6beadando.service.mapping.UserDtoMapper;
import org.example.dma6m6beadando.service.mapping.UserRegisterMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;

@Service

public class AuthService {


    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final AuthenticationManager authenticationManager;
    private final UserRegisterMapper userRegisterMapper;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, UserDtoMapper userDtoMapper,
                       PasswordEncoder passwordEncoder,
                       RolesRepository rolesRepository,
                       AuthenticationManager authenticationManager,
                       UserRegisterMapper userRegisterMapper, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.authenticationManager = authenticationManager;
        this.userRegisterMapper = userRegisterMapper;

        this.jwtService = jwtService;
    }

    public UserDTO getCurrentUser() {
        var username = getAuthenticatedUser();
        return userDtoMapper.mapToUserDto(userRepository.findByUsername(username).orElseThrow());

    }

    public String getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null && !authentication.isAuthenticated()) {
            throw new SecurityException("No authenticated user found");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();

    }

    public String login(RegisterDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authentication.getName());
        }
        return "Failed to login";

    }

    public String register(RegisterDto registerDto) {
        if(this.userRepository.existsByUsername(registerDto.getUsername())) {
            return "Username is already in use";
        }
        var user = this.userRegisterMapper.ToEnt(registerDto);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Roles role = this.rolesRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singletonList(role));
        this.userRepository.save(user);
        return "User successfully registered";
    }

}
