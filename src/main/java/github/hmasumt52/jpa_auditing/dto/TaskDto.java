package github.hmasumt52.jpa_auditing.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import github.hmasumt52.jpa_auditing.model.Collection;
import github.hmasumt52.jpa_auditing.model.Task;

public class TaskDto {
    public record Request(
            String name,
            String description,
            String status,
            Long collectionId
    ) {
        public Task toEntity(Collection collection) {
            return TaskDto.TaskMapper.INSTANCE.toEntity(this, collection);
        }
    }

    public static record Response(
            Long id,
            String name,
            String description,
            String status,
            Long collectionId
    ) {
        public static Response fromEntity(Task entity) {
            return TaskMapper.INSTANCE.fromEntity(entity);
        }
    }

    @Mapper
    public interface TaskMapper {
        TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "collection", source = "collection")
        @Mapping(target = "name", source = "dto.name")
        @Mapping(target = "description", source = "dto.description")
        @Mapping(target = "status", source = "dto.status")
        Task toEntity(Request dto, Collection collection);

        @Mapping(target = "collectionId", source = "collection.id")
        Response fromEntity(Task entity);
    }
}