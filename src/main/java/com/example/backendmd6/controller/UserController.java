package com.example.backendmd6.controller;

import com.example.backendmd6.model.*;
import com.example.backendmd6.service.ProfileEnterpriseService;
import com.example.backendmd6.service.RoleService;
import com.example.backendmd6.service.ProfileUserService;
import com.example.backendmd6.service.StatusEnterpriseService;
import com.example.backendmd6.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
//@RequestMapping("/pages")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ProfileUserService userService;

    @Autowired
    private ProfileEnterpriseService profileEnterpriseService;

    @Autowired
    private StatusEnterpriseService statusEnterpriseService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //Tìm tất cả danh sách người dùng
    @GetMapping("/users")
    public ResponseEntity<Iterable<ProfileUser>> showAllUser() {
        Iterable<ProfileUser> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //Tạo profile doanh nghiệp
    @PostMapping("/register/enterprise")
    public ResponseEntity<Boolean> createEnterprise(@RequestBody ProfileEnterprise user, BindingResult bindingResult) {
        ProfileEnterprise us = new ProfileEnterprise();
        ProfileEnterprise us2 = user;
        us.setPassword(us2.getPassword());
        us.setEmail(us2.getEmail());
        us.setNameCompany(us2.getNameCompany());
        Long id = 1L;
        Optional<StatusEnterprise> statusEnterprise1 = statusEnterpriseService.findById(id);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<ProfileEnterprise> users = profileEnterpriseService.findAll();
        for (ProfileEnterprise currentUser : users) {
            if (currentUser.getEmail().equals(us2.getEmail())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (!profileEnterpriseService.isCorrectConfirmPassword(us2)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (us2.getRoles() != null) {
            Role role = roleService.findByName("ROLE_ADMIN");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            us2.setRoles(roles);
        } else {
            Role role1 = roleService.findByName("ROLE_ENTERPRISE");
            Set<Role> roles1 = new HashSet<>();
            roles1.add(role1);
            us2.setRoles(roles1);
        }

        us2.setPassword(passwordEncoder.encode(us2.getPassword()));
        us2.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        us2.setStatusEnterpriseId(statusEnterprise1.get());
        profileEnterpriseService.save(us2);

        return new ResponseEntity<>(profileEnterpriseService.create(us), HttpStatus.CREATED);
    }

    //Tạo profile người dùng
    @PostMapping("/register/user")
    public ResponseEntity<Boolean> createUser(@RequestBody ProfileUser user, BindingResult bindingResult) {
        ProfileUser us = new ProfileUser();
        us.setPassword(user.getPassword());
        us.setFullName(user.getFullName());
        us.setEmail(user.getEmail());
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<ProfileUser> users = userService.findAll();
        for (ProfileUser currentUser : users) {
            if (currentUser.getEmail().equals(user.getEmail())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (!userService.isCorrectConfirmPassword(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (user.getRoles() != null) {
            Role role = roleService.findByName("ROLE_ADMIN");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
        } else {
            Role role1 = roleService.findByName("ROLE_USER");
            Set<Role> roles1 = new HashSet<>();
            roles1.add(role1);
            user.setRoles(roles1);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        userService.save(user);
        return new ResponseEntity<>(userService.create(us), HttpStatus.CREATED);
    }

    //Đăng nhập người dùng
    @PostMapping("/login/user")
    public ResponseEntity<?> login(@RequestBody ProfileUser user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ProfileUser currentUser = userService.findByEmail(user.getEmail());
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
    }

    //Đăng nhập doanh nghiệp
    @PostMapping("/login/enterprise")
    public ResponseEntity<?> login2(@RequestBody ProfileEnterprise user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ProfileEnterprise currentUser = profileEnterpriseService.findByEmail(user.getEmail());
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
    }

    //Tìm người dùng theo id
    @GetMapping("/users/{id}")
    public ResponseEntity<ProfileUser> getProfile(@PathVariable Long id) {
        Optional<ProfileUser> userOptional = this.userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Sửa người dùng
    @PutMapping("/users/{id}")
    public ResponseEntity<ProfileUser> updateUserProfile(@PathVariable Long id, @RequestBody ProfileUser user) {
        Optional<ProfileUser> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(userOptional.get().getId());
        user.setEmail(userOptional.get().getEmail());
        user.setEnabled(userOptional.get().isEnabled());
        user.setPassword(userOptional.get().getPassword());
        user.setRoles(userOptional.get().getRoles());
        user.setConfirmPassword(userOptional.get().getConfirmPassword());

        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //Xóa người dùng
    @DeleteMapping("/delete")
    public ResponseEntity<ProfileUser> delete(Long idU) {
        Optional<ProfileUser> user = this.userService.findById(idU);
        this.userService.delete(user.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
