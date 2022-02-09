package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.ProfileUser;
import com.example.backendmd6.model.UserPrinciple;
import com.example.backendmd6.repository.ProfileUserRepository;
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
    private ProfileUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        ProfileUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        if (this.checkLogin(user)) {
            return UserPrinciple.build(user);
        }
        boolean enable = false;
        boolean accountNonExpired = false;
        boolean credentialsNonExpired = false;
        boolean accountNonLocked = false;
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), enable, accountNonExpired, credentialsNonExpired,
                accountNonLocked, null);
    }


    @Override
    public void save(ProfileUser user) {
        userRepository.save(user);
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
        ProfileUser user;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        user = this.findByUsername(userName);
        return user;
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
    public boolean checkLogin(ProfileUser user) {
        Iterable<ProfileUser> users = this.findAll();
        boolean isCorrectUser = false;
        for (ProfileUser currentUser : users) {
            if (currentUser.getEmail().equals(user.getEmail())
                    && user.getPassword().equals(currentUser.getPassword())&&
                    currentUser.isEnabled()) {
                isCorrectUser = true;
            }
        }
        return isCorrectUser;
    }

    @Override
    public boolean isRegister(ProfileUser user) {
        boolean isRegister = false;
        Iterable<ProfileUser> users = this.findAll();
        for (ProfileUser currentUser : users) {
            if (user.getEmail().equals(currentUser.getEmail())) {
                isRegister = true;
                break;
            }
        }
        return isRegister;
    }

    @Override
    public boolean isCorrectConfirmPassword(ProfileUser user) {
        boolean isCorrentConfirmPassword = false;
        if(user.getPassword().equals(user.getConfirmPassword())){
            isCorrentConfirmPassword = true;
        }
        return isCorrentConfirmPassword;
    }
    @Override
    public void delete(ProfileUser user){
        userRepository.delete(user);
    }
}
