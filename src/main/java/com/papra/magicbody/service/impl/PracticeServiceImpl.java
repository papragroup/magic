package com.papra.magicbody.service.impl;

import com.papra.magicbody.domain.Practice;
import com.papra.magicbody.repository.PracticeRepository;
import com.papra.magicbody.service.MinioServiceUtil;
import com.papra.magicbody.service.PracticeService;
import com.papra.magicbody.service.dto.PracticeDTO;
import com.papra.magicbody.service.mapper.PracticeMapper;
import java.util.Optional;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Practice}.
 */
@Service
@Transactional
public class PracticeServiceImpl implements PracticeService {

    private final Logger log = LoggerFactory.getLogger(PracticeServiceImpl.class);

    private final PracticeRepository practiceRepository;

    private final PracticeMapper practiceMapper;
    private final MinioServiceUtil minioServiceUtil;

    public PracticeServiceImpl(PracticeRepository practiceRepository, PracticeMapper practiceMapper, MinioServiceUtil minioServiceUtil) {
        this.practiceRepository = practiceRepository;
        this.practiceMapper = practiceMapper;
        this.minioServiceUtil = minioServiceUtil;
    }

    @Override
    public PracticeDTO save(PracticeDTO practiceDTO) {
        log.debug("Request to save Practice : {}", practiceDTO);
        Practice practice = practiceMapper.toEntity(practiceDTO);
        practice.setPhotoUrl(minioServiceUtil.uploadFileToMinio(practiceDTO.getPhoto()));
        practice.setPhotoUrl(minioServiceUtil.uploadFileToMinio(practiceDTO.getVoiceFile()));
        practice = practiceRepository.save(practice);
        return practiceMapper.toDto(practice);
    }

    @Override
    public Optional<PracticeDTO> partialUpdate(PracticeDTO practiceDTO) {
        log.debug("Request to partially update Practice : {}", practiceDTO);

        return practiceRepository
            .findById(practiceDTO.getId())
            .map(
                existingPractice -> {
                    practiceMapper.partialUpdate(existingPractice, practiceDTO);
                    return existingPractice;
                }
            )
            .map(practiceRepository::save)
            .map(getToDto());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PracticeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Practices");
        return practiceRepository.findAll(pageable).map(getToDto());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PracticeDTO> findOne(Long id) {
        log.debug("Request to get Practice : {}", id);
        return practiceRepository.findById(id).map(getToDto());
    }

    private Function<Practice, PracticeDTO> getToDto() {
        return p -> {
            PracticeDTO practiceDTO = practiceMapper.toDto(p);
            practiceDTO.setPhotoUrl(minioServiceUtil.getLink(p.getPhotoUrl()));
            practiceDTO.setVoiceUrl(minioServiceUtil.getLink(p.getVoiceUrl()));
            practiceDTO.setPhoto(null);
            practiceDTO.setVoiceFile(null);
            return practiceDTO;
        };
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Practice : {}", id);
        practiceRepository.deleteById(id);
    }
}
