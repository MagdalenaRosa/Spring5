package pl.cansoft.spring5.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cansoft.spring5.models.Product;
import pl.cansoft.spring5.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service // IoC
public class ProductService {

    final ProductRepository productRepository;
    final ProductCategoryService productCategoryService;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public boolean existsProductByName(Product productFrom) {
        return productRepository.existsByName(productFrom.getName());
    }

    public void insertProduct(Product productFrom) {
        var productExists = existsProductByName(productFrom);
        if (!productExists) {
            productFrom.setId(null);
            productRepository.save(productFrom); // sql: insert jeżeli obiekt NIE posiada id
        } else {
            // todo: komunikat dla usera?
        }
    }

    public void updateProduct(Product productFrom, Integer productId) {
        productFrom.setId(productId);
        productRepository.save(productFrom); // sql: update jeżeli obiekt posiada id
    }

    public void removeProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    public Optional<Product> findProductById(Integer productId) {
        return productRepository.findById(productId);
    }
}
