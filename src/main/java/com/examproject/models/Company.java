package com.examproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter @Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "company_id_generator",sequenceName = "company_id_seq",allocationSize = 1)
    private Long id;
    private String companyName;
    private String LocatedCountry;

    @JsonIgnore
    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private List<Course> course;

    public void setCourse(Course course) {
        this.course.add(course);
    }

}
