package pl.cansoft.spring5.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.cansoft.spring5.services.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping("/user/all")
    public String getUsers(Model model) {
        var users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/user/id/{id}")
    public String getUsers(@PathVariable Integer id, Model model) {
        var userOptional = userService.getUser(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
        } else {
            // todo: 404
        }
        return "user/user";
    }
}
