package com.papra.magicbody.repository;

import com.papra.magicbody.domain.Practice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Practice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {}
