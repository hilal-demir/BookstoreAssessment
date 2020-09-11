# BookstoreAssessment
This is Spring Boot Rest API application that uses Maven plugin implemented in IntelliJ. 
It acts as a bookstore application with Books, Categories and Bookstores.  
It uses inmemory database implemented in the [repository](https://github.com/hilal-demir/BookstoreAssessment/tree/master/src/main/java/com/assessment/bookstore/repository) package.

### What You Will Need
JDK 8  
IntelliJ

### What It Supports
Docker  
Mac

### Bookstore Application
This application is a REST API application implemented in Spring Boot. 
Rest Api is used because it provides post, get, put, delete methods and
we can use them as web uris in our local system. 
  
User can access the application with the base address "http://localhost:8080/api/" and access the 
Apis by adding the 'books', 'categories' or 'bookstores' at the and of the base address.  

Create/Get all books --> http://localhost:8080/api/books  
Create/Get all categories --> http://localhost:8080/api/categories  
Create/Get all bookstores --> http://localhost:8080/api/bookstores  
Get all books by a category --> http://localhost:8080/api/booksByCategory/{category-name}
Get all books by a bookstore --> http://localhost:8080/api/booksByBookstore/{bookstore-name}  
Change a book's category --> http://localhost:8080/api/changeCategory/{bookName}/{categoryName}  
Remove a book --> http://localhost:8080/api/books/{id}  
Remove a book --> http://localhost:8080/api/categories/{id}  
Remove a book --> http://localhost:8080/api/books/{id}

##### Project Structure
<pre>
  |-- BookstoreAssessment
    |-- .gitignore
    |-- Dockerfile
    |-- README.md
    |-- mvnw
    |-- mvnw.cmd
    |-- pom.xml
    |-- src
        |-- main
            |-- java/com/assessment/bookstore
                |-- configuration
                    |-- BookstoreConfiguration.java
                |-- controller
                    |-- BookController.java
                    |-- BookstoreController.java
                    |-- CategoryController.java
                |-- model
                    |-- Book.java
                    |-- Bookstore.java
                    |-- Category.java
                |-- repository
                    |-- BookRepository.java
                    |-- BookstoreRepository.java
                    |-- CategoryRepository
                |-- service
                    |-- BookstoreService.java
                    |-- BookstoreServiceImpl.java
                |-- BookstoreApplication.java
            |-- resources
                |-- application.properties
        |-- test/java/com/assessment/bookstore
            |-- BookstoreApplicationTests.java
</pre>

### Bookstore Application Testing
This project includes unit and integration tests in the [test directory](https://github.com/hilal-demir/BookstoreAssessment/tree/master/src/test/java/com/assessment/bookstore).
In some of the tests, I used HttpUriRequest and HttpResponse classes for testing the API's
GET and DELETE methods. In those tests, SC_OK means its status code is 200 and it works fine. 
SC_NOT_FOUND means its status code is 404 and it could not find the object.