package github.hmasumt52.jpa_auditing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import github.hmasumt52.jpa_auditing.dto.CollectionDto;
import github.hmasumt52.jpa_auditing.model.Collection;
import github.hmasumt52.jpa_auditing.repository.CollectionRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;


    public Collection createCollection(CollectionDto.Request collectionDTO) {
        return collectionRepository.save(collectionDTO.toEntity());      
    }

    public List<Collection> getAllCollections() {
        return collectionRepository.findAll();
    }

    public Optional<Collection> getCollectionById(Long id) {
        return collectionRepository.findById(id);
    }

    public Collection updateCollection(Long id, CollectionDto.Request collectionDTO) {
        if(!collectionRepository.existsById(id)) {
            throw new RuntimeException("Collection not found");
        }
        
        Collection updatedCollection = collectionDTO.toEntity();
        updatedCollection.setId(id); 
        return collectionRepository.save(updatedCollection);
        
    }

    public void deleteCollection(Long id) {
        collectionRepository.deleteById(id);
    }
}