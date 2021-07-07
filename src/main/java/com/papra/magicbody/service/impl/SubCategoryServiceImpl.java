package com.papra.magicbody.service.impl;

import com.papra.magicbody.domain.SubCategory;
import com.papra.magicbody.repository.SubCategoryRepository;
import com.papra.magicbody.service.MinioServiceUtil;
import com.papra.magicbody.service.SubCategoryService;
import com.papra.magicbody.service.dto.SubCategoryDTO;
import com.papra.magicbody.service.mapper.SubCategoryMapper;
import java.util.Optional;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SubCategory}.
 */
@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {

    private final Logger log = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

    private final SubCategoryRepository subCategoryRepository;

    private final SubCategoryMapper subCategoryMapper;
    private final MinioServiceUtil minioServiceUtil;

    public SubCategoryServiceImpl(
        SubCategoryRepository subCategoryRepository,
        SubCategoryMapper subCategoryMapper,
        MinioServiceUtil minioServiceUtil
    ) {
        this.subCategoryRepository = subCategoryRepository;
        this.subCategoryMapper = subCategoryMapper;
        this.minioServiceUtil = minioServiceUtil;
    }

    @Override
    public SubCategoryDTO save(SubCategoryDTO subCategoryDTO) {
        log.debug("Request to save SubCategory : {}", subCategoryDTO);
        SubCategory subCategory = subCategoryMapper.toEntity(subCategoryDTO);
        subCategory.setPhotoUrl(minioServiceUtil.uploadFileToMinio(subCategoryDTO.getPhoto()));
        subCategory.setPhoto(null);
        subCategory.setVoiceUrl(null);
        subCategory.setVoiceUrl(minioServiceUtil.uploadFileToMinio(subCategoryDTO.getVoiceFile()));
        subCategory = subCategoryRepository.save(subCategory);
        return subCategoryMapper.toDto(subCategory);
    }

    @Override
    public Optional<SubCategoryDTO> partialUpdate(SubCategoryDTO subCategoryDTO) {
        log.debug("Request to partially update SubCategory : {}", subCategoryDTO);

        return subCategoryRepository
            .findById(subCategoryDTO.getId())
            .map(
                existingSubCategory -> {
                    subCategoryMapper.partialUpdate(existingSubCategory, subCategoryDTO);
                    return existingSubCategory;
                }
            )
            .map(subCategoryRepository::save)
            .map(getToDto());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SubCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SubCategories");
        return subCategoryRepository.findAll(pageable).map(getToDto());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SubCategoryDTO> findOne(Long id) {
        log.debug("Request to get SubCategory : {}", id);
        return subCategoryRepository.findById(id).map(getToDto());
    }

    private Function<SubCategory, SubCategoryDTO> getToDto() {
        return s -> {
            SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(s);
            subCategoryDTO.setPhotoUrl(minioServiceUtil.getLink(s.getPhotoUrl()));
            subCategoryDTO.setVoiceUrl(minioServiceUtil.getLink(s.getVoiceUrl()));
            subCategoryDTO.setVoiceFile(null);
            subCategoryDTO.setPhoto(null);
            return subCategoryDTO;
        };
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SubCategory : {}", id);
        subCategoryRepository.deleteById(id);
    }
}
