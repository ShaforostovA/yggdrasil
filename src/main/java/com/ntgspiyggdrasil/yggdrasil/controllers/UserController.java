package com.ntgspiyggdrasil.yggdrasil.controllers;

import com.ntgspiyggdrasil.yggdrasil.models.ERole;
import com.ntgspiyggdrasil.yggdrasil.models.Report;
import com.ntgspiyggdrasil.yggdrasil.models.User;
import com.ntgspiyggdrasil.yggdrasil.payload.request.*;
import com.ntgspiyggdrasil.yggdrasil.payload.response.*;
import com.ntgspiyggdrasil.yggdrasil.repository.UserRepository;
import com.ntgspiyggdrasil.yggdrasil.security.jwt.JwtUtils;
import com.ntgspiyggdrasil.yggdrasil.security.services.UserDetailsImpl;
import com.ntgspiyggdrasil.yggdrasil.security.services.UserDetailsServiceImpl;
import com.ntgspiyggdrasil.yggdrasil.services.DepartmentService;
import com.ntgspiyggdrasil.yggdrasil.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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
//        if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.name()))) {
//            return userService.loadAllUserNoAdmins();
//        }
//        return userService.loadAllUserByDepartmentId(user.getDepartment().getId());
        return userService.loadAllUserNoAdmins();
    }

    @GetMapping("/department/list")
    public List<UserModel> getDepartmentUserList(@RequestHeader("Authorization") String authorization) {
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

    @PostMapping("/list/find")
    public UserPageInfo getUserFindList(@RequestHeader("Authorization") String authorization, @RequestBody SearchRequest request) {
        String headerAuth = authorization;
        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
        UserShortModel user = userService.loadShortUserByUsername(username);

        Page<User> page;
        UserPageInfo pageInfo = new UserPageInfo();

        Date minDate;
        Date maxDate;
        LocalDate date;

//        System.out.println(request);

        if (request.getMinDate() == "") {
            minDate = new Date(0);
        } else {
            date = LocalDate.parse(request.getMinDate());
            minDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (request.getMaxDate() == "") {
            maxDate = new Date();
        } else {
            date = LocalDate.parse(request.getMaxDate());
            maxDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_SUPER_ADMIN.name()))) {
            page = userService.loadAllUserNoAdminsPaginate(request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getIsActive(), minDate, maxDate, request.getUserRole(), request.getDepartmentName(), request.getIsState());
        } else if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.name()))) {
            page = userService.loadAllUserNoAdminsPaginate(request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getIsActive(), minDate, maxDate, request.getUserRole(), request.getDepartmentName(), request.getIsState());
        } else {
            page = userService.loadAllUserNoModerByDepartmentId(user.getDepartment().getId(), request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getIsActive(), minDate, maxDate, request.getUserRole(), request.getDepartmentName(), request.getIsState());
        }

        pageInfo.setCurrentPage(request.getCurrentPage());
        pageInfo.setTotalItems(page.getTotalElements());
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setUsers(page.getContent().stream().map(UserModel::toModel).collect(Collectors.toList()));
        pageInfo.setSortField(request.getSortField());
        pageInfo.setSortDir(request.getSortDir());
        pageInfo.setSearchParameter(request.getParameter());

        return pageInfo;
    }

    @GetMapping("/info/{username}")
    public UserModel getCurUser(@PathVariable("username") String username) {
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

    @PostMapping("/status/update")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('SUPER_ADMIN')")
    public UserModel updateUserStatus(@RequestBody UpdateUserStatus updateUserStatus) {
        return UserModel.toModel(userService.updateUserStatus(updateUserStatus.getUserId(), updateUserStatus.getIsActive()));
    }

    @PostMapping("/password/update")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('SUPER_ADMIN')")
    public UserModel updateUserPassword(@RequestBody UpdateUserPassword updateUserPassword) {
        return UserModel.toModel(userService.updateUserPassword(updateUserPassword.getUserId(), updateUserPassword.getPassword()));
    }
}