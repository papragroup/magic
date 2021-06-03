package com.papra.magicbody.service;

import com.papra.magicbody.service.dto.PracticeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.papra.magicbody.domain.Practice}.
 */
public interface PracticeService {
    /**
     * Save a practice.
     *
     * @param practiceDTO the entity to save.
     * @return the persisted entity.
     */
    PracticeDTO save(PracticeDTO practiceDTO);

    /**
     * Partially updates a practice.
     *
     * @param practiceDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PracticeDTO> partialUpdate(PracticeDTO practiceDTO);

    /**
     * Get all the practices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PracticeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" practice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PracticeDTO> findOne(Long id);

    /**
     * Delete the "id" practice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
