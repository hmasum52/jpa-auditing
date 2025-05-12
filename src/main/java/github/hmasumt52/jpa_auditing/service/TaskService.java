package github.hmasumt52.jpa_auditing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import github.hmasumt52.jpa_auditing.dto.TaskDto;
import github.hmasumt52.jpa_auditing.model.Collection;
import github.hmasumt52.jpa_auditing.model.Task;
import github.hmasumt52.jpa_auditing.repository.CollectionRepository;
import github.hmasumt52.jpa_auditing.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final CollectionRepository collectionRepository;

    public Task createTask(TaskDto.Request taskDTO) {
        Collection collection = collectionRepository.findById(taskDTO.collectionId())
                .orElseThrow(() -> new RuntimeException("Collection not found"));
        
        return taskRepository.save(taskDTO.toEntity(collection));      
    }

    public List<TaskDto.Response> getAllTasks() {
        return taskRepository.findAll().stream()
            .map(TaskDto.Response::fromEntity)
            .toList();
    }
    
    public List<TaskDto.Response> getTasksByCollectionId(Long collectionId) {
        if (!collectionRepository.existsById(collectionId)) {
            throw new RuntimeException("Collection not found");
        }
        return taskRepository.findByCollectionId(collectionId)
            .stream()
            .map(TaskDto.Response::fromEntity)
            .toList();
    }

    public TaskDto.Response getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
       return TaskDto.Response.fromEntityWithCollection(task);
    }

    public TaskDto.Response updateTask(Long id, TaskDto.Request taskDTO) {
        if(!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }
        
        Collection collection = collectionRepository.findById(taskDTO.collectionId())
                .orElseThrow(() -> new RuntimeException("Collection not found"));
        
        Task task = taskDTO.toEntity(collection);
        task.setId(id);
        
        return TaskDto.Response.fromEntity(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}