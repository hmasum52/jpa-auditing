package github.hmasumt52.jpa_auditing.controller;

import java.util.List;
import java.util.Optional;

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
        List<TaskDto.Response> tasks = taskService.getAllTasks().stream()
            .map(TaskDto.Response::fromEntity)
            .toList();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    
    @GetMapping("/collection/{collectionId}")
    public ResponseEntity<List<TaskDto.Response>> getTasksByCollectionId(@PathVariable Long collectionId) {
        try {
            List<TaskDto.Response> tasks = taskService.getTasksByCollectionId(collectionId).stream()
                .map(TaskDto.Response::fromEntity)
                .toList();
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto.Response> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(value -> new ResponseEntity<>(TaskDto.Response.fromEntity(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto.Response> updateTask(@PathVariable Long id, @RequestBody TaskDto.Request taskDTO) {
        try {
            Task updatedTask = taskService.updateTask(id, taskDTO);
            return new ResponseEntity<>(TaskDto.Response.fromEntity(updatedTask), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}