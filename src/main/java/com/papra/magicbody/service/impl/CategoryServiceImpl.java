package com.papra.magicbody.service.impl;

import com.papra.magicbody.domain.AccountType;
import com.papra.magicbody.domain.Category;
import com.papra.magicbody.domain.User;
import com.papra.magicbody.repository.CategoryRepository;
import com.papra.magicbody.repository.UserRepository;
import com.papra.magicbody.security.SecurityUtils;
import com.papra.magicbody.service.CategoryService;
import com.papra.magicbody.service.MinioServiceUtil;
import com.papra.magicbody.service.dto.CategoryDTO;
import com.papra.magicbody.service.mapper.CategoryMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.function.Function;

import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Service Implementation for managing {@link Category}.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private final UserRepository userRepository;

    private final MinioServiceUtil minioServiceUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, UserRepository userRepository, MinioServiceUtil minioServiceUtil) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.userRepository = userRepository;
        this.minioServiceUtil = minioServiceUtil;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        log.debug("Request to save Category : {}", categoryDTO);
        Category category = categoryMapper.toEntity(categoryDTO);
        String voiceUrl = minioServiceUtil.uploadFileToMinio(category.getVoiceFile());
        String photoUrl = minioServiceUtil.uploadFileToMinio(category.getPhoto());
        category.setPhotoUrl(photoUrl);
        category.setVoiceUrl(voiceUrl);
        category = categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public Optional<CategoryDTO> partialUpdate(CategoryDTO categoryDTO) {
        log.debug("Request to partially update Category : {}", categoryDTO);

        return categoryRepository
            .findById(categoryDTO.getId())
            .map(
                existingCategory -> {
                    categoryMapper.partialUpdate(existingCategory, categoryDTO);
                    return existingCategory;
                }
            )
            .map(categoryRepository::save)
            .map(getToDto());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Categories");
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get();
        return categoryRepository.findAll(pageable).map((c->{
            CategoryDTO categoryDTO = categoryMapper.toDto(c);
            if (user.getAccountType().equals(AccountType.SILVER)){
                categoryDTO.setLock(Boolean.FALSE);
            }
            else if (categoryDTO.getId().equals(1l) || categoryDTO.getId().equals(2l)){
                categoryDTO.setLock(Boolean.FALSE);
            }
            return categoryDTO;
        }));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryDTO> findOne(Long id) {
        log.debug("Request to get Category : {}", id);
        return categoryRepository.findById(id).map(getToDto());
    }

    private Function<Category, CategoryDTO> getToDto() {
        return c-> {
           CategoryDTO categoryDTO= categoryMapper.toDto(c);
            categoryDTO.setPhotoUrl(minioServiceUtil.getLink(c.getPhotoUrl()));
            categoryDTO.setVoiceUrl(minioServiceUtil.getLink(c.getVoiceUrl()));
            return categoryDTO;
        };
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Category : {}", id);
        categoryRepository.deleteById(id);
    }
}
