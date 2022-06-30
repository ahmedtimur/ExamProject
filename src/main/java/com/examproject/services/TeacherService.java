package com.examproject.services;

import com.examproject.dto.TeacherDTO;
import com.examproject.exceptions.NotFoundException1;
import com.examproject.models.Teacher;
import com.examproject.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Teacher findById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(() -> new NotFoundException1(
                "Teacher with id: " + teacherId + " not found!"
        ));
    }

    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Long teacherId, Teacher teacher) {
        teacher.setId(teacherId);
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }
}
