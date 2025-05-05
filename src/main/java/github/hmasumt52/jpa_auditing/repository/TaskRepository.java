package github.hmasumt52.jpa_auditing.repository;

import github.hmasumt52.jpa_auditing.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCollectionId(Long collectionId);
}