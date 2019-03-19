package com.itech.library.repository.impl;

import com.itech.library.entity.Author;
import com.itech.library.repository.AuthorRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class AuthorRepositoryImpl implements AuthorRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Optional<Author> getAuthorById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Author author = session.get(Author.class, id);
        return Optional.ofNullable(author);
    }

    @Override
    public Author addAuthor(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.save(author);
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(author);
        return author;
    }

    @Override
    public Author deleteAuthor(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(author);
        return author;
    }


}
