package com.example.backendmd6.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "enterpriseTable")
public class ProfileEnterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nameCompany;
    private String description;
    @Column(nullable = false)
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
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String confirmPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "enterprise_role",
            joinColumns = {@JoinColumn(name = "enterprise_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;
    private boolean enabled = true;
    public ProfileEnterprise() {
    }

    public ProfileEnterprise(String nameCompany, String description, String image, String addressCompany, int numberOfEmployees, String phoneNumbers, String linkWebsites, String linkFacebook, String linkGoogleMaps, StatusEnterprise statusEnterpriseId, String email, String password, String confirmPassword, Set<Role> roles) {
        this.nameCompany = nameCompany;
        this.description = description;
        this.image = image;
        this.addressCompany = addressCompany;
        this.numberOfEmployees = numberOfEmployees;
        this.phoneNumbers = phoneNumbers;
        this.linkWebsites = linkWebsites;
        this.linkFacebook = linkFacebook;
        this.linkGoogleMaps = linkGoogleMaps;
        this.statusEnterpriseId = statusEnterpriseId;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddressCompany() {
        return addressCompany;
    }

    public void setAddressCompany(String addressCompany) {
        this.addressCompany = addressCompany;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getLinkWebsites() {
        return linkWebsites;
    }

    public void setLinkWebsites(String linkWebsites) {
        this.linkWebsites = linkWebsites;
    }

    public String getLinkFacebook() {
        return linkFacebook;
    }

    public void setLinkFacebook(String linkFacebook) {
        this.linkFacebook = linkFacebook;
    }

    public String getLinkGoogleMaps() {
        return linkGoogleMaps;
    }

    public void setLinkGoogleMaps(String linkGoogleMaps) {
        this.linkGoogleMaps = linkGoogleMaps;
    }

    public StatusEnterprise getStatusEnterpriseId() {
        return statusEnterpriseId;
    }

    public void setStatusEnterpriseId(StatusEnterprise statusEnterpriseId) {
        this.statusEnterpriseId = statusEnterpriseId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
