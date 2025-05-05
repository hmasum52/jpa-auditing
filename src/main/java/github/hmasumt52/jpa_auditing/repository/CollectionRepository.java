package github.hmasumt52.jpa_auditing.repository;

import github.hmasumt52.jpa_auditing.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    // Add custom query methods if needed
}