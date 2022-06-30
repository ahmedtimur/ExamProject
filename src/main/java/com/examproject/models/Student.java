package com.examproject.models;

import com.examproject.enums.StudyFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter @Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_id_generator",sequenceName = "student_id_seq",allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private StudyFormat studyFormat;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Group group;

}
