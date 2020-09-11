package com.assessment.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bookstore {

    private Integer id;

    private String name;

    private String city;

    private double bookFactor;

}
