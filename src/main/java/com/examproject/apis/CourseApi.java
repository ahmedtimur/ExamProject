package com.examproject.apis;

import com.examproject.dto.CourseDTO;
import com.examproject.models.Course;
import com.examproject.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company/courses")
public class CourseApi {

    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @PostMapping("/save/{companyId}")
    public ResponseEntity<HttpStatus> createCourse(@PathVariable Long companyId, @RequestBody CourseDTO courseDTO) {
        courseService.saveCourse(companyId, convertToCourse(courseDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public CourseDTO findById(@PathVariable Long id) {
        return convertToDTO(courseService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        courseService.updateCourse(id, convertToCourse(courseDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<CourseDTO> findAll() {
        return courseService.findAll().stream().map(this::convertToDTO).toList();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Course convertToCourse(CourseDTO courseDTO) {
        return modelMapper.map(courseDTO, Course.class);
    }

    private CourseDTO convertToDTO(Course course) {
        return modelMapper.map(course, CourseDTO.class);
    }

}
