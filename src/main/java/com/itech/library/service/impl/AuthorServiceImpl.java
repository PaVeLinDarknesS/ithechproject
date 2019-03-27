package com.itech.library.service.impl;

import com.itech.library.repository.AuthorRepository;
import com.itech.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    //TODO Write

    @Autowired
    private AuthorRepository authorRepository;

}
