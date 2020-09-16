package com.assessment.bookstore.service;

import com.assessment.bookstore.model.Book;
import com.assessment.bookstore.model.Bookstore;
import com.assessment.bookstore.model.Category;
import com.assessment.bookstore.repository.BookRepository;
import com.assessment.bookstore.repository.BookstoreRepository;
import com.assessment.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is for creating different layer separated from controllers.
 * @Service annotation indicates that it's holding the business logic.
 * @Autowired annotation allows Spring to resolve and inject collaborating beans into our bean.
 */
@Service
public class BookstoreServiceImpl implements BookstoreService{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BookstoreRepository bookstoreRepository;

    @Autowired
    BookRepository bookRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public void create(Category category) {
        categoryRepository.create(category);
    }

    public Category getCategory(String name) {
        return categoryRepository.getCategory(name);
    }

    public void removeCategory(int id) {
        categoryRepository.removeCategory(id);
    }

    public List<Bookstore> getAllBookstores() {
        return bookstoreRepository.getAllBookstores();
    }

    public void create(Bookstore bookstore) {
        bookstoreRepository.create(bookstore);
    }

    public Bookstore getBookstore(String bookstoreName) {
        return bookstoreRepository.getBookstore(bookstoreName);
    }

    public void removeBookstore(int id) {
        bookstoreRepository.removeBookstore(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book getBook(String bookName) {
        return bookRepository.getBook(bookName);
    }

    public void create(Book book, String store) {
        bookRepository.create(book, store);
    }

    public List<Book> getBooksByCategory(String categoryName) {
        return bookRepository.getBooksByCategory(categoryName);
    }

    public List<Book> getBooksByBookstore(String bookstoreName) {
        return bookRepository.getBooksByBookstore(bookstoreName);
    }

    public void changeCategory(String bookName, String categoryName) {
        bookRepository.changeCategory(bookName,categoryName);
    }

    public void removeBook(int id) {
        bookRepository.removeBook(id);
    }

}
