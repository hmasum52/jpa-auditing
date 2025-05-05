package github.hmasumt52.jpa_auditing.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import github.hmasumt52.jpa_auditing.model.Collection;

public class CollectionDto {
    public record Request(
            String name,
            String description
    ){
        public Collection toEntity() {
            return CollectionDto.CollectionMapper.INSTANCE.toEntity(this);
        }
    }

    public static record Response(
            Long id,
            String name,
            String description
    ){
        public static Response fromEntity(Collection entity) {
            return CollectionMapper.INSTANCE.fromEntity(entity);
        }
    }

    @Mapper
    public interface CollectionMapper {
        CollectionMapper INSTANCE = Mappers.getMapper(CollectionMapper.class);

        Collection toEntity(Request dto);

        Response fromEntity(Collection entity);
    }
}