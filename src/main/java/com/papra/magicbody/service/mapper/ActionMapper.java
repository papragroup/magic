package com.papra.magicbody.service.mapper;

import com.papra.magicbody.domain.*;
import com.papra.magicbody.service.dto.ActionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Action} and its DTO {@link ActionDTO}.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring", uses = { CategoryMapper.class, PracticeSessionMapper.class })
public interface ActionMapper extends EntityMapper<ActionDTO, Action> {
    ActionDTO toDto(Action s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ActionDTO toDtoId(Action action);
}
