package com.assessment.bookstore.repository;

import com.assessment.bookstore.model.Book;
import com.assessment.bookstore.model.Bookstore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is for creating and manipulating database
 * @Repository annotates classes at the persistence layer, which will act as a database repository.
 * @Getter and @Setter lombok annotations that generates getters and setter automatically.
 * @AllArgsConstuctor lombok annotation generates constructor with all parameters automatically.
 */
@Repository
public class BookRepository{

    public static int bookCount = 5;
    private static Map<Bookstore, List<Book>> map = new HashMap<Bookstore, List<Book>>();

    private CategoryRepository categoryRepository;

    private BookstoreRepository bookstoreRepository;

    @Autowired
    public BookRepository(CategoryRepository categoryRepository, BookstoreRepository bookstoreRepository) {
        this.categoryRepository = categoryRepository;
        this.bookstoreRepository = bookstoreRepository;
    }

    @PostConstruct
    public void loadData()
    {
        List<Book> books = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        Book book1 = new Book(1, "John", categoryRepository.getCategory("fiction"), 4);
        Book book2 = new Book(2, "Robert", categoryRepository.getCategory("child"), 5);
        Book book3 = new Book(3, "Adam", categoryRepository.getCategory("mystery"), 3);
        Book book4 = new Book(4, "Andrew", categoryRepository.getCategory("short story"), 3);
        Book book5 = new Book(5, "Jack", categoryRepository.getCategory("biography"), 7);
        books.add(book1);
        books.add(book2);
        books.add(book4);
        books2.add(book3);
        books2.add(book4);
        books2.add(book5);
        map.put(bookstoreRepository.getBookstore("store1"),books);
        map.put(bookstoreRepository.getBookstore("store2"),books2);
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        map.values().stream().forEach(books -> books.stream().forEach(book -> {
            bookList.add(book);
        }));
        return bookList;
    }

    public Book getBook(String bookName) {
        List<Book> bookList = getAllBooks();
        return bookList.stream().filter(book -> bookName.equals(book.getName())).
                findFirst().orElse(null);
    }

    public void create(Book book, String store) {
        if (book.getId() == null) {
            book.setId(bookCount++);
        }
        List<Book> bookList = map.get(bookstoreRepository.getBookstore(store));
        bookList.add(book);
        map.put(bookstoreRepository.getBookstore(store), bookList);
        bookCount++;
    }

    public List<Book> getBooksByCategory(String categoryName) {
        List<Book> bookList = getAllBooks();
        return bookList.stream().filter(book -> book.getCategory().getName().equals(categoryName)).collect(Collectors.toList());
    }

    public List<Book> getBooksByBookstore(String bookstoreName) {
        List<Book> bookList = new ArrayList<>();
        for (Book b: map.get(bookstoreRepository.getBookstore(bookstoreName))) {
            b.setPrice(b.getPrice()*bookstoreRepository.getBookstore(bookstoreName).getBookFactor());
            bookList.add(b);
        }
        return bookList;
    }

   public void changeCategory(String bookName, String categoryName) {
       map.values().stream().forEach(books -> books.stream().forEach(book -> {
           if (book.getName().equals(bookName)) {
               book.setCategory(categoryRepository.getCategory(categoryName));
           }
       }));
    }

    public void removeBook(int id) {
        map.values().stream().forEach(books -> books.stream().forEach(book -> {
            if(book.getId().equals(id)) {
                books.remove(book);
            }
        }));
    }
}
