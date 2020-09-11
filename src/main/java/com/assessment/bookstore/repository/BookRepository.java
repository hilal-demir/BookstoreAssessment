package com.assessment.bookstore.repository;

import com.assessment.bookstore.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is for creating and manipulating database
 * @Repository annotates classes at the persistence layer, which will act as a database repository.
 * @Getter and @Setter lombok annotations that generates getters and setter automatically.
 * @AllArgsConstuctor lombok annotation generates constructor with all parameters automatically.
 */
@Repository
@Getter
@Setter
@AllArgsConstructor
public class BookRepository{

    public static int bookCount=5;
    private static List<Book> books=new ArrayList<>();

    @Autowired
    private static CategoryRepository categoryRepository = new CategoryRepository();

    @Autowired
    private static BookstoreRepository bookstoreRepository = new BookstoreRepository();

    static
    {
        books.add(new Book(1, "John",categoryRepository.getCategory("fiction"), bookstoreRepository.getBookstore("store1"),4*bookstoreRepository.getBookstore("store1").getBookFactor()));
        books.add(new Book(2, "Robert",categoryRepository.getCategory("child"), bookstoreRepository.getBookstore("store2"), 5));
        books.add(new Book(3, "Adam", categoryRepository.getCategory("mystery"), bookstoreRepository.getBookstore("store3"), 3));
        books.add(new Book(4, "Andrew", categoryRepository.getCategory("short story"), bookstoreRepository.getBookstore("store4"), 3));
        books.add(new Book(5, "Jack", categoryRepository.getCategory("biography"), bookstoreRepository.getBookstore("store5"), 1));
        books.add(new Book(6, "Jack", categoryRepository.getCategory("biography"), bookstoreRepository.getBookstore("store2"), 3));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBook(String bookName) {
        return books.stream().filter(book -> bookName.equals(book.getName())).
                findFirst().orElse(null);
    }

    public void create(Book book) {
        if (book.getId() == null) {
            book.setId(bookCount++);
        }
        books.add(book);
        bookCount++;
    }

    public List<Book> getBooksByCategory(String categoryName) {
        List<Book> bookList = books.stream().filter(book -> book.getCategory().getName().equals(categoryName)).collect(Collectors.toList());
        return bookList;
    }

    public List<Book> getBooksByBookstore(String bookstoreName) {
        return books.stream().filter(book -> book.getBookstore().getName().equals(bookstoreName)).collect(Collectors.toList());
    }

   public void changeCategory(String bookName, String categoryName) {
        books.stream().forEach(book -> {
            if (book.getName().equals(bookName)) {
                book.setCategory(categoryRepository.getCategory(categoryName));
            }
        });
    }

    public void removeBook(int id) {
        books.stream().forEach(book -> {
            if(book.getId().equals(id)) {
                books.remove(book);
            }
        });
    }

}
