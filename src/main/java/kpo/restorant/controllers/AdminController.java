package kpo.restorant.controllers;

import kpo.restorant.models.Dish;
import kpo.restorant.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("/admin/menu")
    public String getMenuPage(Model model, @RequestParam(required = false) String error) {
        model.addAttribute("error", error);
        model.addAttribute("dishes", menuService.getMenu());
        return "menu";
    }

    @PostMapping("/admin/menu/add")
    public String addDishToMenu(Dish dish) {
        try {
            menuService.addDish(dish);
        } catch (Exception e) {
            return "redirect:/admin/menu?error=add";
        }
        return "redirect:/admin/menu";
    }

    @PostMapping("/admin/menu/delete/{id}")
    public String deleteDishFromMenu(@PathVariable Long id) {
        try {
            menuService.deleteDish(id);
        } catch (Exception e) {
            return "redirect:/admin/menu?error=delete";
        }
        return "redirect:/admin/menu";
    }
}
