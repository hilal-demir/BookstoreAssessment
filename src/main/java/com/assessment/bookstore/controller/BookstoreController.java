package com.assessment.bookstore.controller;

import com.assessment.bookstore.model.Bookstore;
import com.assessment.bookstore.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This api is to control bookstore repository. @RestController annotation tells the Spring MVC to create
 * controller bean and eliminates the need of annotating ResponseBody for every method.
 * @Autowired annotation allows Spring to resolve and inject collaborating beans into our bean.
 * @RequestMapping annotation is used to map web requests to Spring Controller methods.
 * @RequestBody annotation maps the HttpRequest body to a transfer or domain object.
 * @RequestVariable annotation extract query parameters.
 */
@RestController
@RequestMapping("/api")
public class BookstoreController {

    @Autowired
    BookstoreService bookstoreService;

    @RequestMapping(value = "/bookstores", method = RequestMethod.GET)
    public List<Bookstore> getAllBookstores() {
        return bookstoreService.getAllBookstores();
    }

    @RequestMapping(value = "/bookstores", method = RequestMethod.POST)
    public void createBook(@RequestBody Bookstore bookstore) {
        bookstoreService.create(bookstore);
    }

    @RequestMapping(value = "/bookstores/{id}", method = RequestMethod.DELETE)
    public void removeBook(@PathVariable int id) {
        bookstoreService.removeBookstore(id);
    }

}
