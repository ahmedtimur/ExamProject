package com.examproject.models;

import com.examproject.models.Company;
import com.examproject.models.Group;
import com.examproject.models.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter @Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_id_generator")
    @SequenceGenerator(name = "course_id_generator",sequenceName = "course_id_seq",allocationSize = 1)
    private Long id;
    private String courseName;
    private String duration;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses",cascade = CascadeType.ALL)
    private List<Group> groups;

    @JsonIgnore
    @OneToOne(mappedBy = "course",fetch = FetchType.LAZY)
    private Teacher teacher;

    public void setGroup(Group group) {
        this.groups.add(group);
    }

}
