package github.hmasumt52.jpa_auditing.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import github.hmasumt52.jpa_auditing.dto.CollectionDto;
import github.hmasumt52.jpa_auditing.model.Collection;
import github.hmasumt52.jpa_auditing.service.CollectionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
@SecurityRequirement(name = "User-Id")
public class CollectionController {

    private final CollectionService collectionService;

    @PostMapping
    public ResponseEntity<CollectionDto.Response> createCollection(@RequestBody CollectionDto.Request collectionDTO) {
        Collection createdCollection = collectionService.createCollection(collectionDTO);
        return new ResponseEntity<>(CollectionDto.Response.fromEntity(createdCollection), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CollectionDto.Response>> getAllCollections() {
        List<CollectionDto.Response> collections = collectionService.getAllCollections().stream()
            .map(CollectionDto.Response::fromEntity)
            .collect(Collectors.toList());
        return new ResponseEntity<>(collections, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionDto.Response> getCollectionById(@PathVariable Long id) {
        Optional<Collection> collection = collectionService.getCollectionById(id);
        return collection.map(value -> new ResponseEntity<>(
                CollectionDto.Response.fromEntity(value), 
                HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionDto.Response> updateCollection(@PathVariable Long id, @RequestBody CollectionDto.Request collectionDTO) {
        try {
            Collection updatedCollection = collectionService.updateCollection(id, collectionDTO);
            return new ResponseEntity<>(
                CollectionDto.Response.fromEntity(updatedCollection),
                HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable Long id) {
        collectionService.deleteCollection(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}