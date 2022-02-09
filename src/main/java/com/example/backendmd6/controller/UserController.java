package com.example.backendmd6.controller;

import com.example.backendmd6.model.JwtResponse;
import com.example.backendmd6.model.Role;
import com.example.backendmd6.model.ProfileUser;
import com.example.backendmd6.service.RoleService;
import com.example.backendmd6.service.UserService;
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
    private Environment env;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/users")
    public ResponseEntity<Iterable<ProfileUser>> showAllUser() {
        Iterable<ProfileUser> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register/enterprise")
    public ResponseEntity<ProfileUser> createEnterprise(@RequestBody ProfileUser profileUser, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<ProfileUser> users = userService.findAll();
        for (ProfileUser currentProfileUser : users) {
            if (currentProfileUser.getUsername().equals(profileUser.getUsername())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (!userService.isCorrectConfirmPassword(profileUser)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (profileUser.getRoles() != null) {
            Role role = roleService.findByName("ROLE_ADMIN");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            profileUser.setRoles(roles);
        } else {
            Role role1 = roleService.findByName("ROLE_ENTERPRISE");
            Set<Role> roles1 = new HashSet<>();
            roles1.add(role1);
            profileUser.setRoles(roles1);
        }
        profileUser.setPassword(passwordEncoder.encode(profileUser.getPassword()));
        profileUser.setConfirmPassword(passwordEncoder.encode(profileUser.getConfirmPassword()));
        userService.save(profileUser);
        return new ResponseEntity<>(profileUser, HttpStatus.CREATED);
    }

    @PostMapping("/register/user")
    public ResponseEntity<ProfileUser> createUser(@RequestBody ProfileUser profileUser, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<ProfileUser> users = userService.findAll();
        for (ProfileUser currentProfileUser : users) {
            if (currentProfileUser.getUsername().equals(profileUser.getUsername())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (!userService.isCorrectConfirmPassword(profileUser)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (profileUser.getRoles() != null) {
            Role role = roleService.findByName("ROLE_ADMIN");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            profileUser.setRoles(roles);
        } else {
            Role role1 = roleService.findByName("ROLE_USER");
            Set<Role> roles1 = new HashSet<>();
            roles1.add(role1);
            profileUser.setRoles(roles1);
        }
        profileUser.setPassword(passwordEncoder.encode(profileUser.getPassword()));
        profileUser.setConfirmPassword(passwordEncoder.encode(profileUser.getConfirmPassword()));
        userService.save(profileUser);
        return new ResponseEntity<>(profileUser, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ProfileUser profileUser) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(profileUser.getUsername(), profileUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ProfileUser currentProfileUser = userService.findByUsername(profileUser.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, currentProfileUser.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ProfileUser> getProfile(@PathVariable Long id) {
        Optional<ProfileUser> userOptional = this.userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ProfileUser> updateUserProfile(@PathVariable Long id, @RequestBody ProfileUser profileUser) {
        Optional<ProfileUser> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        profileUser.setId(userOptional.get().getId());
        profileUser.setUsername(userOptional.get().getUsername());
        profileUser.setEnabled(userOptional.get().isEnabled());
        profileUser.setPassword(userOptional.get().getPassword());
        profileUser.setRoles(userOptional.get().getRoles());
        profileUser.setConfirmPassword(userOptional.get().getConfirmPassword());

        userService.save(profileUser);
        return new ResponseEntity<>(profileUser, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ProfileUser> delete(Long idU){
        Optional<ProfileUser> user = this.userService.findById(idU);
        this.userService.delete(user.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
