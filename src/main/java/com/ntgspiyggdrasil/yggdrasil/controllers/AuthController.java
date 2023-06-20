package com.ntgspiyggdrasil.yggdrasil.controllers;

import com.ntgspiyggdrasil.yggdrasil.payload.response.*;
import com.ntgspiyggdrasil.yggdrasil.repository.RoleRepository;
import com.ntgspiyggdrasil.yggdrasil.repository.UserRepository;
import com.ntgspiyggdrasil.yggdrasil.security.services.UserDetailsImpl;
import com.ntgspiyggdrasil.yggdrasil.payload.request.LoginRequest;
import com.ntgspiyggdrasil.yggdrasil.security.jwt.JwtUtils;
import com.ntgspiyggdrasil.yggdrasil.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
        System.out.println("[authenticateUser] " + loginRequest.getUsername() + ":" + loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);


        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getLastname(),
                userDetails.getName(),
                userDetails.getPatronymic(),
                roles));
    }


    @GetMapping("/token/check")
    public ResponseEntity<?> checkUserToken(@RequestHeader("Authorization") String authorization) {
        String headerAuth = authorization;
        String username;
        UserModel user;
        Boolean validToken;
        try {
            username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
            user = UserModel.toModel(userService.loadUserByUserName(username));
            validToken = jwtUtils.validateJwtToken(headerAuth.substring(7,headerAuth.length()));
        } catch (ExpiredJwtException e) {
            return ResponseEntity.ok(new ValidateUser(false, false));
        }
        return ResponseEntity.ok(new ValidateUser(validToken, user.getIsActive()));
    }
    @GetMapping("/ping")
    public String checkPing() {
        System.out.println("[checkPing] " + "pong");
        return "Pong";
    }
}
