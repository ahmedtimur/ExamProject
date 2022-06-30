package com.examproject.apis;

import com.examproject.dto.TeacherDTO;
import com.examproject.models.Teacher;
import com.examproject.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherApi {

    private final TeacherService teacherService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<TeacherDTO> findAll() {
        return teacherService.findAll().stream().map(this::convertToTeacherDTO).toList();
    }

    @GetMapping("/find/{teacherId}")
    public TeacherDTO findById(@PathVariable Long teacherId) {
        return convertToTeacherDTO(teacherService.findById(teacherId));
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> saveTeacher(@RequestBody TeacherDTO teacherDTO) {
        teacherService.saveTeacher(convertToTeacher(teacherDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{teacherId}")
    public ResponseEntity<HttpStatus> updateTeacher(@PathVariable Long teacherId, @RequestBody TeacherDTO teacherDTO) {
        teacherService.updateTeacher(teacherId, convertToTeacher(teacherDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{teacherId}")
    public ResponseEntity<HttpStatus> deleteTeacher(@PathVariable Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Teacher convertToTeacher(TeacherDTO teacherDTO) {
        return modelMapper.map(teacherDTO, Teacher.class);
    }

    private TeacherDTO convertToTeacherDTO(Teacher teacher) {
        return modelMapper.map(teacher, TeacherDTO.class);
    }

}
