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
    String fullName;
    String age;
    String address;
    String numberPhone;
    String experience;
    String oldWorkplace;
    String interest;
    @ManyToOne
    @JoinColumn(name = "profileUser_id")
    ProfileUser profileUserId;

}
