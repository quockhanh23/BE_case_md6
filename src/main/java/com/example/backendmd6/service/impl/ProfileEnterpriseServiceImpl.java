package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.ProfileEnterprise;
import com.example.backendmd6.model.UserPrinciple;
import com.example.backendmd6.repository.ProfileEnterpriseRepository;
import com.example.backendmd6.service.ProfileEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileEnterpriseServiceImpl implements ProfileEnterpriseService {
    @Autowired
    private ProfileEnterpriseRepository profileEnterpriseRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        ProfileEnterprise user = profileEnterpriseRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        if (this.checkLogin(user)) {
            return UserPrinciple.build2(user);
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
    public void save(ProfileEnterprise user) {
        profileEnterpriseRepository.save(user);
    }

    @Override
    public Iterable<ProfileEnterprise> findAll() {
       return profileEnterpriseRepository.findAll();
    }

    @Override
    public ProfileEnterprise findByEmail(String email) {
        return profileEnterpriseRepository.findByEmail(email);
    }

    @Override
    public ProfileEnterprise getCurrentUser() {
        ProfileEnterprise user;
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        user = this.findByEmail(email);
        return user;
    }
        @Override
        public Optional<ProfileEnterprise> findById (Long id){
            return profileEnterpriseRepository.findById(id);
        }

    @Override
    public UserDetails loadUserById(Long id) {
        Optional<ProfileEnterprise> user = profileEnterpriseRepository.findById(id);
        if (!user.isPresent()) {
            throw new NullPointerException();
        }
        return UserPrinciple.build2(user.get());
    }

    @Override
    public boolean checkLogin(ProfileEnterprise user) {
        Iterable<ProfileEnterprise> users = this.findAll();
        boolean isCorrectUser = false;
        for (ProfileEnterprise currentUser : users) {
            if (currentUser.getEmail().equals(user.getEmail())
                    && user.getPassword().equals(currentUser.getPassword())&&
                    currentUser.isEnabled()) {
                isCorrectUser = true;
            }
        }
        return isCorrectUser;
    }

    @Override
    public boolean isRegister(ProfileEnterprise user) {
        boolean isRegister = false;
        Iterable<ProfileEnterprise> users = this.findAll();
        for (ProfileEnterprise currentUser : users) {
            if (user.getEmail().equals(currentUser.getEmail())) {
                isRegister = true;
                break;
            }
        }
        return isRegister;
    }

    @Override
    public boolean isCorrectConfirmPassword(ProfileEnterprise user) {
        boolean isCorrentConfirmPassword = false;
        if(user.getPassword().equals(user.getConfirmPassword())){
            isCorrentConfirmPassword = true;
        }
        return isCorrentConfirmPassword;
    }

    @Override
    public void delete(ProfileEnterprise entity) {
        profileEnterpriseRepository.delete(entity);
    }

    @Override
    public Iterable<ProfileEnterprise> findAllByNameCompany(String nameCompany) {
        return profileEnterpriseRepository.findAllByNameCompany(nameCompany);
    }
}
