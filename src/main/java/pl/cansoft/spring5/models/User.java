package pl.cansoft.spring5.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId; // PK
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToOne // OneToOne (o2o) - 1 do 1 -> @OneToOne
    @JoinColumn(name = "detailId")
    private UserDetails details;
    @OneToMany // OneToMany (o2m) - 1 do wielu -> @OneToMany
    @JoinColumn(name = "userId")
    private List<PhoneNumber> phoneNumber;
    @ManyToMany // ManyToMany (m2m) - wiele do wielu -> @ManyToMany
    @JoinTable(
            name = "user_address_pivot",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "address_id")}
    )
    private List<Address> address;
}
