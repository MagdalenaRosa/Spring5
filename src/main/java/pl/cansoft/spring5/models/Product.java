package pl.cansoft.spring5.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity // potrzebne dla Hibernate aby odbić model jako tabela w bazie
public class Product {

    @Id // potrzebne do zdefiniowanie klucza podst w basie
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // hibernate generuje klucze, strategia określa że istnieje unikalny klucz per tabela
    private Integer id;

    @NotBlank(message = "Nazwa produktu nie może być pusta")
    @Size(min = 5, max = 255, message = "Nazwa produktu musi być od 5 do 255 znaków")
    // @Column(unique = true)
    private String name; // domyślnie 255 znaków

    @NotBlank(message = "Opis produktu nie może być pusty")
    @Size(min = 10, max = 1023, message = "Opis produktu musi być od 10 do 1023 znaków")
    @Column(length = 1023, name = "description") // pole w bazie varchar 1023 znaki
    // @Column(columnDefinition = "TEXT") // typ text mysql
    private String desc;

    @NotBlank(message = "Link do obrazka produktu nie może być pusty")
    @Size(min = 5, max = 255, message = "Link do obrazka produktu musi być od 5 do 255 znaków")
    private String imgUri; // domyślnie 255 znaków

    @NotNull(message = "Cena jest wymagana")
    private BigDecimal price;
}
