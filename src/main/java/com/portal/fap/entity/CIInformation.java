package com.portal.fap.entity;

import com.portal.fap.common.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "ci_information")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CIInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_card", length = 16, nullable = false)
    private String idCard;

    @Column(name = "full_name", length = 128, nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "address", length = 128)
    private String address;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "date_of_issue")
    private Date dateOfIssue;

    @Column(name = "place_of_issue")
    private String placeOfIssue;
}
