package com.example.backendmd6.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class ProfileEnterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCompany;
    private String description;
    private String image;
    private String addressCompany;
    private int numberOfEmployees;
    private String phoneNumbers;
    private String linkWebsites;
    private String linkFacebook;
    private String linkGoogleMaps;
    @ManyToOne
    @JoinColumn(name = "statusEnterprise_id")
    private StatusEnterprise statusEnterpriseId;
    private String email;
    private String password;
    private String confirmPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "enterprise_role",
            joinColumns = {@JoinColumn(name = "enterprise_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

}
