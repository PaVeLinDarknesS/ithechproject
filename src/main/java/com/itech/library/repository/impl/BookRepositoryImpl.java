package com.itech.library.repository.impl;

import com.itech.library.entity.Author;
import com.itech.library.entity.Book;
import com.itech.library.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Book addBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
        return book;
    }

    @Override
    public Book deleteBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(book);
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(book);
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        Session session = sessionFactory.getCurrentSession();
        Query<Book> query = session.createQuery("from Book", Book.class);
        return query.list();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        return Optional.ofNullable(book);
    }

    @Override
    public Optional<Book> getBookByTitle(String title) {
        Session session = sessionFactory.getCurrentSession();
        Query<Book> query = session.createQuery("from Book as b where b.title= :title", Book.class);
        query.setParameter("title", title);
        return query.uniqueResultOptional();
    }

    @Override
    public Optional<Book> findOne(Book book) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Book as b " +
                "where b.title = :title " +
                "and b.year = :year " +
                "and b.count = :count", Book.class);
        query.setParameter("title", book.getTitle());
        query.setParameter("year", book.getYear());
        query.setParameter("count", book.getCount());
        List<Book> foundBook = query.list();
        Optional<Book> findBookOptional = Optional.empty();
        if (foundBook.size() == 1) {
            findBookOptional = Optional.of(foundBook.get(0));
        }
        return findBookOptional;
    }

    @Override
    public List<Book> getBookByAuthorId(Author author) {
        Session session = sessionFactory.getCurrentSession();
        Query<Book> query = session.createQuery("from Book as b where b.author = :author");
        query.setParameter("author", author);
        return query.list();
    }
}
