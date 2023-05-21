package pl.cansoft.spring5.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cansoft.spring5.models.ProductCategory;
import pl.cansoft.spring5.repositories.ProductCategoryRepository;

import java.util.List;
import java.util.Optional;

// IoC
@RequiredArgsConstructor
@Service // Bean (java ee) / Component (spring)
public class ProductCategoryService {

    final ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> findAllCategoryProducts() {
        return productCategoryRepository.findAll();
    }

    public boolean existsProductCategoryByName(ProductCategory productCategory) {
        return productCategoryRepository.existsByName(productCategory.getName());
    }

    public void insertProductCategory(ProductCategory productCategory) {
        var categoryExists = existsProductCategoryByName(productCategory);
        if (!categoryExists) {
            productCategory.setId(null);
            productCategoryRepository.save(productCategory); // insert!
        } else {
            // todo: komunikat dla usera?
        }
    }

    public void updateProductCategory(Integer categoryId, ProductCategory categoryForm) {
        categoryForm.setId(categoryId);
        productCategoryRepository.save(categoryForm); // update!
    }

    public void removeProductCategory(Integer categoryId) {
        productCategoryRepository.deleteById(categoryId);
    }

    public Optional<ProductCategory> findProductCategoryById(Integer categoryId) {
        return productCategoryRepository.findById(categoryId);
    }
}
