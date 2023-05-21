package pl.cansoft.spring5.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.cansoft.spring5.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    long countByName(String name);

    boolean existsByName(String name);

    /*
    private Integer id;
    private String name;
    private String desc;
    private String imgUri;
    private BigDecimal price;
     */
    Optional<Product> findByName(String name);

    // select * from Product where BETWEEN from AND to;
    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    // select * from Product where name = ? OR desc = ?
    List<Product> findAllByNameOrDesc(String name, String desc);

    // select * from Product where name = ? AND desc = ?
    List<Product> findAllByNameAndDesc(String name, String desc);

    // select * from Product where name like ?
    List<Product> findAllByNameLike(String name);
}
