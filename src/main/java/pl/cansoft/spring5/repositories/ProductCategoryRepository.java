package pl.cansoft.spring5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cansoft.spring5.models.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    boolean existsByName(String name);
}
