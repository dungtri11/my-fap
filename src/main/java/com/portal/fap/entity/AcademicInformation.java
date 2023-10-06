package com.portal.fap.entity;

import com.portal.fap.common.Major;
import com.portal.fap.common.Mode;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "academic")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AcademicInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "roll_number")
    private String rollNumber;

    @Column(name = "enroll_date")
    private Date enrollDate;

    @Column(name = "mode")
    @Enumerated(EnumType.STRING)
    private Mode mode;

    @Column(name = "status")
    private String status;

    @Column(name = "current_term_no")
    private Integer currentTermNo;

    @Column(name = "major")
    private Major major;

}
