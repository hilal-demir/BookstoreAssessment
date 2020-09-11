package com.assessment.bookstore;

import com.assessment.bookstore.model.Book;
import com.assessment.bookstore.model.Bookstore;
import com.assessment.bookstore.model.Category;
import com.assessment.bookstore.service.BookstoreServiceImpl;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * This class if for testing our Spring Boot application.
 * @SpringBootTest annotation provides a convenient way to start up an application context to be used in a test.
 * @RunWith annotation sets the Runner that should be used to run a test
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BookstoreApplicationTests {

    @Autowired
    BookstoreServiceImpl bookstoreService;

    Category category = new Category(7,"trial");
    Bookstore bookstore = new Bookstore(9, "store9", "Antalya", 2);
    Book book = new Book(8,"deneme",category,bookstore,10);

    @Test
    public void creationTest() {
        bookstoreService.create(category);
        bookstoreService.create(bookstore);
        bookstoreService.create(book);
        assertEquals(category.getName(), bookstoreService.getCategory("trial").getName());
        assertEquals(bookstore.getName(),bookstoreService.getBookstore("store9").getName());
        assertEquals(book.getId(), bookstoreService.getBook("deneme").getName());
    }

    @Test
    public void removeBookTest() throws IOException {
        int id = 2;
        HttpUriRequest request = new HttpDelete("http://localhost:8080/api/books" + id);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void removeBookstoreTest() throws IOException {
        int id = 2;
        HttpUriRequest request = new HttpDelete("http://localhost:8080/api/bookstores" + id);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void removeCategoryTest() throws IOException {
        int id = 2;
        HttpUriRequest request = new HttpDelete("http://localhost:8080/api/categories" + id);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void bookDoesNotExistsTest() throws IOException {
        int id = 45;
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/bookstores" + id);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void categoryDoesNotExistTest() throws IOException {
        int id = 45;
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/bookstores" + id);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void bookstoreDoesNotExistTest() throws IOException {
        int id = 45;
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/bookstores" + id);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void getAllBooksTest() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/books");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void getAllCategoriesTest() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/categories");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void getAllBookstoresTest() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/bookstores");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void changeCategoryTest() {
        bookstoreService.create(book);
        bookstoreService.create(category);
        bookstoreService.changeCategory(book.getName(), category.getName());
        assertEquals(bookstoreService.getBook(book.getName()).getCategory().getName(), category.getName());
    }

    @Test
    public void getBooksByCategoryTest() throws IOException {
        String name = "fiction";
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/booksByCategory/" + name);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void getBooksByBookstoreTest() throws IOException {
        String name = "store1";
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/booksByBookstore/" + name);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

}
