package pl.cansoft.spring5.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nazwa kategorii nie może być pusta")
    @Size(min = 5, max = 255, message = "Nazwa kategorii musi być od 5 do 255 znaków")
    private String name;

    @NotBlank(message = "Opis kategorii nie może być pusty")
    @Size(min = 5, max = 255, message = "Opis kategorii musi być od 10 do 1023 znaków")
    @Column(name = "description")
    private String desc;

    @NotBlank(message = "Link do obrazka kategorii nie może być pusty")
    @Size(min = 5, max = 255, message = "Link do obrazka kategorii musi być od 5 do 255 znaków")
    private String imgUri;
}
