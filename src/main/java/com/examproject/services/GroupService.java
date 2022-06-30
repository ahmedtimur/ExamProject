package com.examproject.services;

import com.examproject.dto.GroupDTO;
import com.examproject.exceptions.NotFoundException1;
import com.examproject.models.Course;
import com.examproject.models.Group;
import com.examproject.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final CourseService courseService;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group findById(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new NotFoundException1(
                "Group with this id:" + id + " not found!"
        ));
    }

    public void updateGroup(Long id, Group newGroup) {
        newGroup.setId(id);
        groupRepository.save(newGroup);
    }

    public void saveGroup(Long courseId, Group newGroup) {
        Course course = courseService.findById(courseId);
        newGroup.setCourse(course);
        course.setGroup(newGroup);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
