package com.papra.magicbody.service.impl;

import com.papra.magicbody.domain.BookMarkAction;
import com.papra.magicbody.domain.User;
import com.papra.magicbody.repository.BookMarkActionRepository;
import com.papra.magicbody.repository.UserRepository;
import com.papra.magicbody.security.SecurityUtils;
import com.papra.magicbody.service.BookMarkActionService;
import com.papra.magicbody.service.dto.ActionDTO;
import com.papra.magicbody.service.dto.BookMarkActionDTO;
import com.papra.magicbody.service.mapper.ActionMapper;
import com.papra.magicbody.service.mapper.BookMarkActionMapper;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BookMarkAction}.
 */
@Service
@Transactional
public class BookMarkActionServiceImpl implements BookMarkActionService {

    private final Logger log = LoggerFactory.getLogger(BookMarkActionServiceImpl.class);

    private final BookMarkActionRepository bookMarkActionRepository;

    private final BookMarkActionMapper bookMarkActionMapper;

    private final ActionMapper actionMapper;

    public BookMarkActionServiceImpl(BookMarkActionRepository bookMarkActionRepository, BookMarkActionMapper bookMarkActionMapper, ActionMapper actionMapper) {
        this.bookMarkActionRepository = bookMarkActionRepository;
        this.bookMarkActionMapper = bookMarkActionMapper;
        this.actionMapper = actionMapper;
    }

    @Autowired
    private UserRepository userRepository;
    @Override
    public BookMarkActionDTO save(BookMarkActionDTO bookMarkActionDTO) {
        log.debug("Request to save BookMarkAction : {}", bookMarkActionDTO);
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get();
        BookMarkAction bookMarkAction = bookMarkActionMapper.toEntity(bookMarkActionDTO);
        bookMarkAction.setUser(user);
        bookMarkAction = bookMarkActionRepository.save(bookMarkAction);
        return bookMarkActionMapper.toDto(bookMarkAction);
    }

    @Override
    public Optional<BookMarkActionDTO> partialUpdate(BookMarkActionDTO bookMarkActionDTO) {
        log.debug("Request to partially update BookMarkAction : {}", bookMarkActionDTO);

        return bookMarkActionRepository
            .findById(bookMarkActionDTO.getId())
            .map(
                existingBookMarkAction -> {
                    bookMarkActionMapper.partialUpdate(existingBookMarkAction, bookMarkActionDTO);
                    return existingBookMarkAction;
                }
            )
            .map(bookMarkActionRepository::save)
            .map(bookMarkActionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookMarkActionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BookMarkActions");
        return bookMarkActionRepository.findAll(pageable).map(bookMarkActionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookMarkActionDTO> findOne(Long id) {
        log.debug("Request to get BookMarkAction : {}", id);
        return bookMarkActionRepository.findById(id).map(bookMarkActionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BookMarkAction : {}", id);
        bookMarkActionRepository.deleteById(id);
    }

    @Override
    public Page<ActionDTO> findAllByUser(Pageable pageable) {
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get();
        return bookMarkActionRepository.findAllByUser(user,pageable).map(bm-> {
            return actionMapper.toDto(bm.getAction());
        });
    }
}
