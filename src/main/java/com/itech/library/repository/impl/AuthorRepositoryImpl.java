package com.itech.library.repository.impl;

import com.itech.library.entity.Author;
import com.itech.library.entity.Book;
import com.itech.library.repository.AuthorRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
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

    @Override
    public List<Author> getAllAuthors() {
        Session session = sessionFactory.getCurrentSession();
        Query<Author> query = session.createQuery("from Author", Author.class);
        List<Author> authors = query.list();
        return query.list();
    }

    @Override
    public Optional<Author> findOne(Author author) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Author as a " +
                "where a.firstName = :first " +
                "and a.lastName = :last", Author.class);
        query.setParameter("first", author.getFirstName());
        query.setParameter("last", author.getLastName());
        List<Author> foundAuthor = query.list();
        Optional<Author> findAuthorOptional = Optional.empty();
        if (foundAuthor.size() == 1) {
            findAuthorOptional = Optional.of(foundAuthor.get(0));
        }
        return findAuthorOptional;
    }

    @Override
    public void addBookInAuthor(Book book, Author author) {
        author.getBooks().add(book);
        book.setAuthor(author);
    }

    @Override
    public void removeBookInAuthor(Book book, Author author) {
        author.getBooks().remove(book);
        book.setAuthor(null);
    }
}
