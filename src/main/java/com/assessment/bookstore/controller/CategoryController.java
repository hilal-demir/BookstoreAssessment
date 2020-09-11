package com.assessment.bookstore.controller;

import com.assessment.bookstore.model.Category;
import com.assessment.bookstore.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This api is to control category repository. @RestController annotation tells the Spring MVC to create
 * controller bean and eliminates the need of annotating ResponseBody for every method.
 * @Autowired annotation allows Spring to resolve and inject collaborating beans into our bean.
 * @RequestMapping annotation is used to map web requests to Spring Controller methods.
 * @RequestBody annotation maps the HttpRequest body to a transfer or domain object.
 * @RequestVariable annotation extract query parameters.
 */
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    BookstoreService bookstoreService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return bookstoreService.getAllCategories();
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public void createBook(@RequestBody Category category) {
        bookstoreService.create(category);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public void removeBook(@PathVariable int id) {
        bookstoreService.removeCategory(id);
    }

}
