package ecourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecourse.model.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    public Categories findByCategoryName(String categoryName);
       
}
