package com.example.backendmd6.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class ApplyNow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fileCVId")
    private FileCV fileCVId;
    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitmentId;
    @ManyToOne
    @JoinColumn(name = "statusConfirm_id")
    private StatusConfirmOfApplyNow statusConfirmId;
}
