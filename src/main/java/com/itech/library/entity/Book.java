package com.itech.library.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String title;
    private Integer year;
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;

    @ManyToMany(mappedBy = "books")
    private Set<User> users = new HashSet<>(0);

    public Book() {
    }

    public Book(String title, Integer year, Integer count) {
        this.title = title;
        this.year = year;
        this.count = count;
    }

    public void addUser(User user) {
        this.getUsers().add(user);
        user.getBooks().add(this);
    }

    public void removeUser(User user) {
        this.getUsers().remove(user);
        user.getBooks().remove(this);
    }


    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * For adding and remove User, use special method.
     */
    public Set<User> getUsers() {
        return users;
    }

    @Deprecated
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(year, book.year) &&
                Objects.equals(count, book.count) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, count, author);
    }
}
