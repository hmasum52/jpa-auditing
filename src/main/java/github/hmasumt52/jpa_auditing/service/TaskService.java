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

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public List<Task> getTasksByCollectionId(Long collectionId) {
        if (!collectionRepository.existsById(collectionId)) {
            throw new RuntimeException("Collection not found");
        }
        return taskRepository.findByCollectionId(collectionId);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Long id, TaskDto.Request taskDTO) {
        if(!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }
        
        Collection collection = collectionRepository.findById(taskDTO.collectionId())
                .orElseThrow(() -> new RuntimeException("Collection not found"));
        
        Task task = taskDTO.toEntity(collection);
        task.setId(id);
        
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}