package com.portal.fap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "subject_schedule_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubjectScheduleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_schedule_id", referencedColumnName = "id")
    private SubjectSchedule subjectSchedule;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "room", length = 16, nullable = false)
    private String room;

    @Column(name = "slot", nullable = false)
    private int slot;
}
