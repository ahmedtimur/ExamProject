package com.examproject.apis;

import com.examproject.dto.StudentDTO;
import com.examproject.models.Student;
import com.examproject.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentApi {

    private final StudentService studentService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<StudentDTO> findAllStudents() {
       return studentService.findAll().stream().map(this::convertToStudentDTO).toList();
    }

    @GetMapping("/find/{studentId}")
    public StudentDTO findById(@PathVariable Long studentId) {
        return convertToStudentDTO(studentService.findById(studentId));
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> saveStudent(@RequestBody StudentDTO studentDTO) {
        studentService.saveStudent(convertToStudent(studentDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<HttpStatus> updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO) {
        studentService.updateStudent(studentId, convertToStudent(studentDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Student convertToStudent(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, Student.class);
    }

    private StudentDTO convertToStudentDTO(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }

}
