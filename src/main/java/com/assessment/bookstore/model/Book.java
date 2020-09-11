package com.assessment.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Integer id;

    private String name;

    private Category category;

    private Bookstore bookstore;

    private double price;

}
