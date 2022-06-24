package com.example.backendmd6.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class FileCV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String age;
    private String address;
    private String numberPhone;
    private String experience;
    private String oldWorkplace;
    private String interest;
    @ManyToOne
    @JoinColumn(name = "profileUser_id")
    private ProfileUser profileUserId;
}
