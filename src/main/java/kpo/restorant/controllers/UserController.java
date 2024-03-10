package kpo.restorant.controllers;

import kpo.restorant.models.User;
import kpo.restorant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("error","Почта или пароль неверны.");
        }
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            if (error.equals("exists")) {
                model.addAttribute("error","Почта занята.");
            }
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        try {
            userService.createUser(user);
        } catch (Exception e) {
            return "redirect:/register?error=exists";
        }
        return "redirect:/login";
    }
}
