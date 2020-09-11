package com.assessment.bookstore.controller;

import com.assessment.bookstore.model.Book;
import com.assessment.bookstore.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This api is to control book repository. @RestController annotation tells the Spring MVC to create
 * controller bean and eliminates the need of annotating ResponseBody for every method.
 * @Autowired annotation allows Spring to resolve and inject collaborating beans into our bean.
 * @RequestMapping annotation is used to map web requests to Spring Controller methods.
 * @RequestBody annotation maps the HttpRequest body to a transfer or domain object.
 * @RequestVariable annotation extract query parameters.
 */
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookstoreService bookstoreService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return bookstoreService.getAllBooks();
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public void createBook(@RequestBody Book book) {
        bookstoreService.create(book);
    }

    @RequestMapping(value = "/booksByCategory/{category}", method = RequestMethod.GET)
    public List<Book> getBooksByCategory (@PathVariable String category)
    {
        return bookstoreService.getBooksByCategory(category);
    }

    @RequestMapping(value = "/booksByBookstore/{bookstore}", method = RequestMethod.GET)
    public List<Book> getBooksByBookstore(@PathVariable String bookstore)
    {
        return bookstoreService.getBooksByBookstore(bookstore);
    }

    @RequestMapping(value = "/changeCategory/{bookName}/{categoryName}", method = RequestMethod.PUT)
    public void changeCategory(@PathVariable String bookName, @PathVariable String categoryName) {
        bookstoreService.changeCategory(bookName,categoryName);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public void removeBook(@PathVariable int id) {
        bookstoreService.removeBook(id);
    }

}
