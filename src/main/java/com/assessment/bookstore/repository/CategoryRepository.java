package com.assessment.bookstore.repository;

import  com.assessment.bookstore.model.Category;
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
public class CategoryRepository {

    private static List<Category> categories = new ArrayList<>();
    public static int categoryCount = 5;

    static {
        categories.add(new Category(1, "fiction"));
        categories.add(new Category(2, "mystery"));
        categories.add(new Category(3, "child"));
        categories.add(new Category(4, "biography"));
        categories.add(new Category(5, "short story"));
    }

    public List<Category> getAllCategories() {
        return categories;
    }

    public void create(Category category) {
        if (category.getId() == null) {
            category.setId(categoryCount++);
        }
        categories.add(category);
        categoryCount++;
    }

    public Category getCategory(String categoryName) {
        return categories.stream().filter(category -> categoryName.equals(category.getName())).
                findFirst().orElse(null);
    }

    public void removeCategory(int id) {
        categories.stream().forEach(category -> {
            if(category.getId().equals(id)) {
                categories.remove(category);
            }
        });
    }

}
