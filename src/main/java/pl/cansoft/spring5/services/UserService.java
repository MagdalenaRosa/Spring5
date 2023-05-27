package pl.cansoft.spring5.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cansoft.spring5.models.User;
import pl.cansoft.spring5.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Integer userId) {
        return userRepository.findById(userId);
    }
}
