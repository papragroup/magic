package com.papra.magicbody.repository;

import com.papra.magicbody.domain.Action;
import com.papra.magicbody.domain.SubCategory;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Action entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
    Page<Action> findAllBySubCategory(SubCategory sub, Pageable page);

    @Query(value = "Select * from  magic.action where code like %:a or title like %:d", nativeQuery = true)
    List<Action> findAllByCodeOrTitle(@Param("a") String code, @Param("d") String title);
}
