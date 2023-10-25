package com.portal.fap.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    @OneToOne(mappedBy = "profile")
    private User belongToUser;
}
