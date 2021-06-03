package com.papra.magicbody.service.impl;

import com.papra.magicbody.domain.Action;
import com.papra.magicbody.repository.ActionRepository;
import com.papra.magicbody.service.ActionService;
import com.papra.magicbody.service.MinioServiceUtil;
import com.papra.magicbody.service.dto.ActionDTO;
import com.papra.magicbody.service.mapper.ActionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Action}.
 */
@Service
@Transactional
public class ActionServiceImpl implements ActionService {

    private final Logger log = LoggerFactory.getLogger(ActionServiceImpl.class);

    private final ActionRepository actionRepository;

    private final ActionMapper actionMapper;
    private final MinioServiceUtil minioServiceUtil;

    public ActionServiceImpl(ActionRepository actionRepository, ActionMapper actionMapper, MinioServiceUtil minioServiceUtil) {
        this.actionRepository = actionRepository;
        this.actionMapper = actionMapper;
        this.minioServiceUtil = minioServiceUtil;
    }

    @Override
    public ActionDTO save(ActionDTO actionDTO) {
        log.debug("Request to save Action : {}", actionDTO);
        String photoFileName = minioServiceUtil.uploadFileToMinio(actionDTO.getPhoto());
        String videoFileName = minioServiceUtil.uploadFileToMinio(actionDTO.getVideo());
        Action action = actionMapper.toEntity(actionDTO);
        action.setPhotoUrl(photoFileName);
        action.setVideoUrl(videoFileName);
        action = actionRepository.save(action);
        return actionMapper.toDto(action);
    }

    @Override
    public Optional<ActionDTO> partialUpdate(ActionDTO actionDTO) {
        log.debug("Request to partially update Action : {}", actionDTO);

        return actionRepository
            .findById(actionDTO.getId())
            .map(
                existingAction -> {
                    actionMapper.partialUpdate(existingAction, actionDTO);
                    return existingAction;
                }
            )
            .map(actionRepository::save)
            .map(getToDto());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ActionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Actions");
        return actionRepository.findAll(pageable).map(getToDto());
    }

    private Function<Action, ActionDTO> getToDto() {
         return a->{
             ActionDTO actionDTO = actionMapper.toDto(a);
             actionDTO.setPhotoUrl(minioServiceUtil.getLink(a.getPhotoUrl()));
             actionDTO.setVideoUrl(minioServiceUtil.getLink(a.getVideoUrl()));
             return actionDTO;
         };
    }

    /**
     *  Get all the actions where Action is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ActionDTO> findAllWhereActionIsNull() {
        log.debug("Request to get all actions where Action is null");
        return StreamSupport
            .stream(actionRepository.findAll().spliterator(), false)
            .filter(action -> action.getAction() == null)
            .map(getToDto())
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ActionDTO> findOne(Long id) {
        log.debug("Request to get Action : {}", id);
        return actionRepository.findById(id).map(getToDto());
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Action : {}", id);
        actionRepository.deleteById(id);
    }
}
