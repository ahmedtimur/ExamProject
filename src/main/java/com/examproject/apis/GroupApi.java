package com.examproject.apis;

import com.examproject.dto.GroupDTO;
import com.examproject.models.Group;
import com.examproject.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company/groups")
public class GroupApi {

    private final GroupService groupService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<GroupDTO> findAllGroups() {
        return groupService.findAll().stream().map(this::convertToGroupDTO).toList();
    }

    @GetMapping("/find/{id}")
    public GroupDTO findById(@PathVariable Long id) {
        return convertToGroupDTO(groupService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateCourse(@PathVariable Long id, @RequestBody GroupDTO groupDTO) {
        groupService.updateGroup(id, convertToGroup(groupDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/save/{courseId}")
    public ResponseEntity<HttpStatus> saveGroup(@PathVariable Long courseId, @RequestBody GroupDTO groupDTO) {
        groupService.saveGroup(courseId, convertToGroup(groupDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Group convertToGroup(GroupDTO groupDTO) {
        return modelMapper.map(groupDTO, Group.class);
    }

    private GroupDTO convertToGroupDTO(Group group) {
        return modelMapper.map(group, GroupDTO.class);
    }

}
