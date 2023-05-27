package pl.cansoft.spring5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.cansoft.spring5.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
