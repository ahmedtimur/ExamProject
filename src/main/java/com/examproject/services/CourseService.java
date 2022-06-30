package com.examproject.services;

import com.examproject.dto.CourseDTO;
import com.examproject.exceptions.NotFoundException1;
import com.examproject.models.Company;
import com.examproject.models.Course;
import com.examproject.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final CompanyService companyService;

    public void saveCourse(Long companyId, Course course) {
        Company company = companyService.findById(companyId);
        course.setCompany(company);
        courseRepository.save(course);
        company.setCourse(course);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException1(
                "Course with this id: " + id + " not found!"
        ));
    }

    public List<Course> findAll() {
       return courseRepository.findAll();
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public void updateCourse(Long id, Course newCourse) {
        newCourse.setId(id);
        courseRepository.save(newCourse);
    }
}
