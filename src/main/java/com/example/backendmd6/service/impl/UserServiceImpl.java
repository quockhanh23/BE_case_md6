package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.ProfileUser;
import com.example.backendmd6.model.UserPrinciple;
import com.example.backendmd6.repository.UserRepository;
import com.example.backendmd6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        ProfileUser profileUser = userRepository.findByUsername(username);
        if (profileUser == null) {
            throw new UsernameNotFoundException(username);
        }
        if (this.checkLogin(profileUser)) {
            return UserPrinciple.build(profileUser);
        }
        boolean enable = false;
        boolean accountNonExpired = false;
        boolean credentialsNonExpired = false;
        boolean accountNonLocked = false;
        return new org.springframework.security.core.userdetails.User(profileUser.getUsername(),
                profileUser.getPassword(), enable, accountNonExpired, credentialsNonExpired,
                accountNonLocked, null);
    }


    @Override
    public void save(ProfileUser profileUser) {
        userRepository.save(profileUser);
    }

    @Override
    public Iterable<ProfileUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public ProfileUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public ProfileUser getCurrentUser() {
        ProfileUser profileUser;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        profileUser = this.findByUsername(userName);
        return profileUser;
    }

    @Override
    public Optional<ProfileUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserById(Long id) {
        Optional<ProfileUser> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new NullPointerException();
        }
        return UserPrinciple.build(user.get());
    }

    @Override
    public boolean checkLogin(ProfileUser profileUser) {
        Iterable<ProfileUser> users = this.findAll();
        boolean isCorrectUser = false;
        for (ProfileUser currentProfileUser : users) {
            if (currentProfileUser.getUsername().equals(profileUser.getUsername())
                    && profileUser.getPassword().equals(currentProfileUser.getPassword())&&
                    currentProfileUser.isEnabled()) {
                isCorrectUser = true;
            }
        }
        return isCorrectUser;
    }

    @Override
    public boolean isRegister(ProfileUser profileUser) {
        boolean isRegister = false;
        Iterable<ProfileUser> users = this.findAll();
        for (ProfileUser currentProfileUser : users) {
            if (profileUser.getUsername().equals(currentProfileUser.getUsername())) {
                isRegister = true;
                break;
            }
        }
        return isRegister;
    }

    @Override
    public boolean isCorrectConfirmPassword(ProfileUser profileUser) {
        boolean isCorrentConfirmPassword = false;
        if(profileUser.getPassword().equals(profileUser.getConfirmPassword())){
            isCorrentConfirmPassword = true;
        }
        return isCorrentConfirmPassword;
    }
    @Override
    public void delete(ProfileUser profileUser){
        userRepository.delete(profileUser);
    }
}
