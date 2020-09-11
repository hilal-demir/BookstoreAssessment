package com.assessment.bookstore.repository;

import com.assessment.bookstore.model.Bookstore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
public class BookstoreRepository {

    private static List<Bookstore> bookstores = new ArrayList<>();
    public static int bookstoreCount = 5;

    static {
        bookstores.add(new Bookstore(1, "store1", "Istanbul", 4));
        bookstores.add(new Bookstore(2, "store2", "Izmir", 3));
        bookstores.add(new Bookstore(3, "store3", "Bursa", 2));
        bookstores.add(new Bookstore(4, "store4", "Izmit", 2));
        bookstores.add(new Bookstore(5, "store5", "Van", 1));
    }

    public List<Bookstore> getAllBookstores() {
        return bookstores;
    }

    public void create(Bookstore bookstore) {
        if (bookstore.getId() == null) {
            bookstore.setId(bookstoreCount++);
        }
        bookstores.add(bookstore);
        bookstoreCount++;
    }

    public Bookstore getBookstore(String bookstoreName) {
        return bookstores.stream().filter(bookstore -> bookstoreName.equals(bookstore.getName())).
                findFirst().orElse(null);
    }

    public void removeBookstore(int id) {
        bookstores.stream().forEach(bookstore -> {
            if(bookstore.getId().equals(id)) {
                bookstores.remove(bookstore);
            }
        });
    }

}
