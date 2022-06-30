package com.examproject.services;

import com.examproject.exceptions.NotFoundException1;
import com.examproject.models.Student;
import com.examproject.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException1(
                "Student with id: " + studentId + " not found!"
        ));
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Long studentId, Student student) {
        student.setId(studentId);
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
