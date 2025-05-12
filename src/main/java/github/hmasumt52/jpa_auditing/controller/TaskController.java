package github.hmasumt52.jpa_auditing.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.hmasumt52.jpa_auditing.dto.TaskDto;
import github.hmasumt52.jpa_auditing.model.Task;
import github.hmasumt52.jpa_auditing.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@SecurityRequirement(name = "User-Id")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto.Response> createTask(@RequestBody TaskDto.Request taskDTO) {
        Task createdTask = taskService.createTask(taskDTO);
        return new ResponseEntity<>(TaskDto.Response.fromEntity(createdTask), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto.Response>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }
    
    @GetMapping("/collection/{collectionId}")
    public ResponseEntity<List<TaskDto.Response>> getTasksByCollectionId(@PathVariable(name = "collectionId") Long collectionId) {
        return new ResponseEntity<>(taskService.getTasksByCollectionId(collectionId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto.Response> getTaskById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);   
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto.Response> updateTask(@PathVariable(name = "id") Long id, @RequestBody TaskDto.Request taskDTO) {
        return new ResponseEntity<>(taskService.updateTask(id, taskDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}