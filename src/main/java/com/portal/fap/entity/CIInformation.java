package com.portal.fap.entity;

import com.portal.fap.common.Gender;
import jakarta.persistence.*;
import lombok.*;

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

}
