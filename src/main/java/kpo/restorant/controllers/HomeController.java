package kpo.restorant.controllers;

import kpo.restorant.models.Role;
import kpo.restorant.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getAdminOrClientHomePage(@AuthenticationPrincipal User user) {
        if (user.getRoles().contains(Role.ADMIN)) {
            return "redirect:/admin";
        }
        return "redirect:/client";
    }
}
