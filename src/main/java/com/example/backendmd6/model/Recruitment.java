package com.example.backendmd6.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String salary;
    private String address;
    private String description;
    private String experience;
    private String numberOfRecruitments;
    private Date dateEnd;
    private String gender;
    @ManyToOne
    @JoinColumn(name = "statusRecruitment_id")
    private StatusRecruitment statusRecruitmentId;
    @ManyToOne
    @JoinColumn(name = "work_id")
    private Work workId;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position positionId;
    @OneToMany(targetEntity = ProfileEnterprise.class)
    private List<ProfileEnterprise> enterprise;
}