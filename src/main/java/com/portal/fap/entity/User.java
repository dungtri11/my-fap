package com.portal.fap.entity;

import com.portal.fap.common.Role;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ci_id", referencedColumnName = "id")
    private CIInformation ciInformation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "academic_id", referencedColumnName = "id")
    private AcademicInformation academicInformation;

    @Column(name = "phones", length = 32)
    private String phones;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
