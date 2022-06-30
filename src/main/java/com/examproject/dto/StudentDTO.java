package com.examproject.dto;

import com.examproject.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {

    private String firstName;

    private String lastName;

    private String email;

    private StudyFormat studyFormat;

}
