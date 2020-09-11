package com.assessment.bookstore.service;

import com.assessment.bookstore.model.Book;
import com.assessment.bookstore.model.Bookstore;
import com.assessment.bookstore.model.Category;

import java.util.List;

public interface BookstoreService {

    List<Category> getAllCategories();

    void create(Category category);

    Category getCategory(String name);

    void removeCategory(int id);

    List<Bookstore> getAllBookstores();

    void create(Bookstore bookstore);

    Bookstore getBookstore(String bookstoreName);

    void removeBookstore(int id);

    List<Book> getAllBooks();

    Book getBook(String bookName);

    void create(Book book);

    List<Book> getBooksByCategory(String categoryName);

    List<Book> getBooksByBookstore(String bookstoreName);

    void changeCategory(String bookName, String categoryName);

    void removeBook(int id);

}
