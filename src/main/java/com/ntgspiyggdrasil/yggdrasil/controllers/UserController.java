package com.ntgspiyggdrasil.yggdrasil.controllers;

import com.ntgspiyggdrasil.yggdrasil.models.ERole;
import com.ntgspiyggdrasil.yggdrasil.payload.request.CreateUserRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.UserUpdateRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.UsernameRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.response.*;
import com.ntgspiyggdrasil.yggdrasil.repository.UserRepository;
import com.ntgspiyggdrasil.yggdrasil.security.jwt.JwtUtils;
import com.ntgspiyggdrasil.yggdrasil.security.services.UserDetailsImpl;
import com.ntgspiyggdrasil.yggdrasil.security.services.UserDetailsServiceImpl;
import com.ntgspiyggdrasil.yggdrasil.services.DepartmentService;
import com.ntgspiyggdrasil.yggdrasil.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor

public class UserController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/token/check")
    public ResponseEntity<?> checkUserToken(@RequestHeader("Authorization") String authorization) {
        String headerAuth = authorization;
        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
        UserShortModel user = userService.loadShortUserByUsername(username);

        return ResponseEntity.ok(new TokenInfo(true, user));
    }



    @GetMapping("/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> getUserInfo(@PathVariable("username") String username) {
        if (!userRepository.existsByUsername(username)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: There is no user with such username!"));
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(new UserInfoResponse(
                userDetails.getId(),
                userDetails.getLastname(),
                userDetails.getName(),
                userDetails.getPatronymic(),
                userDetails.getBirthday(),
                userDetails.getAcademicTitle(),
                userDetails.getAcademicDegree(),
                userDetails.getEmail(),
                userDetails.getPhone(),
                userDetails.getJobTitle(),
                userDetails.getOrcid(),
                userDetails.getSpinCode(),
                userDetails.isState(),
                userDetails.getImgUrl(),
                userDetails.getDepartment().getFaculty().getId(),
                userDetails.getDepartment().getId()
                )
        );
    }
    @GetMapping("/{username}/department")
    public ResponseEntity<?> getUserDepartment(@PathVariable("username") String username) {
        if (!userRepository.existsByUsername(username)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: There is no user with such username!"));
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(new DepartmentResponse(
                        userDetails.getDepartment()
                )
        );
    }
    @GetMapping("/{username}/faculty")
    public ResponseEntity<?> getUserFaculty(@PathVariable("username") String username) {
        if (!userRepository.existsByUsername(username)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: There is no user with such username!"));
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(new FacultyResponse(
                        userDetails.getDepartment().getFaculty()
                )
        );
    }
    @GetMapping("/list")
    public List<UserModel> getUserList(@RequestHeader("Authorization") String authorization) {
        String headerAuth = authorization;
        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
        UserShortModel user = userService.loadShortUserByUsername(username);
        if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_SUPER_ADMIN.name()))) {
            return userService.loadAllUserNoCurrentSupAdmins(user.getId());
        }
        if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.name()))) {
            return userService.loadAllUserNoAdmins();
        }
        return userService.loadAllUserByDepartmentId(user.getDepartment().getId());
    }

    @GetMapping("/list/find")
    public List<UserModel> getUserFindList(@RequestHeader("Authorization") String authorization) {
        String headerAuth = authorization;
        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
        UserShortModel user = userService.loadShortUserByUsername(username);
        if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_SUPER_ADMIN.name()))) {
            return userService.loadAllUserNoAdmins();
        }
        if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.name()))) {
            return userService.loadAllUserNoAdmins();
        }
        return userService.loadAllUserNoModerByDepartmentId(user.getDepartment().getId());
    }

    @GetMapping("/info/{username}")
    public UserModel getCurUser(@PathVariable("username") String username) {
        System.out.println(userRepository.findByUsername(username).orElseThrow());
        return UserModel.toModel(userRepository.findByUsername(username).orElseThrow());
    }
    @PostMapping("/update")
    public UserInfoResponse updateUserData(@RequestBody UserUpdateRequest userDate) {
        return userService.updateUserData(userDate);
    }

    @GetMapping("/profile/{userId}/info")
    public UserModel getEmployeeInfo(@PathVariable("userId") long userId) {
        return UserModel.toModel(userService.loadUserByUserId(userId));
    }

    @PostMapping("/username/check")
    public Boolean checkCanCreateUser(@RequestBody UsernameRequest username) {
        return !userService.checkCanCreateUserWithUsername(username.getUsername());
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('SUPER_ADMIN')")
    public UserModel createUser(@RequestBody CreateUserRequest userData) {
        return UserModel.toModel(userService.createUser(userData));
    }
}