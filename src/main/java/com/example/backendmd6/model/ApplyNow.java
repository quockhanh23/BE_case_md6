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
    FileCV fileCVId;
    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    Recruitment recruitmentId;
    @ManyToOne
    @JoinColumn(name = "statusConfirm_id")
    StatusConfirmOfApplyNow statusConfirmId;

    public ApplyNow(FileCV fileCVId, Recruitment recruitmentId, StatusConfirmOfApplyNow statusConfirmId) {
        this.fileCVId = fileCVId;
        this.recruitmentId = recruitmentId;
        this.statusConfirmId = statusConfirmId;
    }
}
