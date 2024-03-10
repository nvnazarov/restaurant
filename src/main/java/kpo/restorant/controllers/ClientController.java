package kpo.restorant.controllers;

import jakarta.transaction.Transactional;
import kpo.restorant.models.Process;
import kpo.restorant.models.Status;
import kpo.restorant.models.User;
import kpo.restorant.services.MenuService;
import kpo.restorant.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/client")
    @Transactional
    public String getClientPage(
            Model model, @RequestParam(required = false) String error, @AuthenticationPrincipal User user) {

        model.addAttribute("email", user.getEmail());
        model.addAttribute("menu", menuService.getMenu());
        model.addAttribute("order", orderService.getOrderDishes(user));
        model.addAttribute("all", orderService.getAllOrders(user));
        model.addAttribute("error", error);

        Process process = orderService.getCurrentProcess(user);
        if (process == null) {
            return "client";
        }

        if (process.getStatus() == Status.COOKING) {
            return "client-cooking";
        }

        return "client-pay";
    }

    @PostMapping("/order/add/{id}")
    public String addDishToOrder(@PathVariable Long id, @AuthenticationPrincipal User user) {
        try {
            orderService.orderDish(user, id);
        } catch (Exception e) {
            return "redirect:/client?error=add";
        }
        return "redirect:/client";
    }

    @PostMapping("/cancel")
    public String cancelOrder(@AuthenticationPrincipal User user) {
        try {
            orderService.cancelOrder(user);
        } catch (Exception e) {
            return "redirect:/client?error=cancel";
        }
        return "redirect:/client";
    }

    @PostMapping("/finish")
    public String finishOrder(@AuthenticationPrincipal User user) {
        try {
            orderService.finishOrder(user);
        } catch (Exception e) {
            return "redirect:/client?error=finish";
        }
        return "redirect:/client";
    }

    @PostMapping("/pay")
    public String payOrder(@AuthenticationPrincipal User user) {
        try {
            orderService.payOrder(user);
        } catch (Exception e) {
            return "redirect:/client?error=pay";
        }
        return "redirect:/client";
    }
}
