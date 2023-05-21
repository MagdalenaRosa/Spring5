package pl.cansoft.spring5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelperController {

    private static final String PHONE = "+48 667 845 837";
    private static final String EMAIL = "info@supersklep.pl";

    @GetMapping("/aboutUs")
    public String showAboutUs() {
        return "about-us";
    }

    @GetMapping("/contact")
    public String showContact(Model model) {
        model.addAttribute("phone", PHONE);
        model.addAttribute("email", EMAIL);
        return "contact";
    }
}
