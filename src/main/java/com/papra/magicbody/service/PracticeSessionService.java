package com.papra.magicbody.service;

import com.papra.magicbody.service.dto.PracticeSessionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.papra.magicbody.domain.PracticeSession}.
 */
public interface PracticeSessionService {
    /**
     * Save a practiceSession.
     *
     * @param practiceSessionDTO the entity to save.
     * @return the persisted entity.
     */
    PracticeSessionDTO save(PracticeSessionDTO practiceSessionDTO);

    /**
     * Partially updates a practiceSession.
     *
     * @param practiceSessionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PracticeSessionDTO> partialUpdate(PracticeSessionDTO practiceSessionDTO);

    /**
     * Get all the practiceSessions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PracticeSessionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" practiceSession.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PracticeSessionDTO> findOne(Long id);

    /**
     * Delete the "id" practiceSession.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
