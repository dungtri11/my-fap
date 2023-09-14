package com.portal.fap.entity;

import com.portal.fap.common.StudentAcademicStatus;
import com.portal.fap.common.Major;
import com.portal.fap.common.Mode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "student_academic")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentAcademic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "student_id", length = 16, nullable = false)
    private String studentId;

    @Column(name = "enroll_date")
    private Date enrollDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "mode")
    private Mode mode;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private StudentAcademicStatus status;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "major")
    private Major major;

}
