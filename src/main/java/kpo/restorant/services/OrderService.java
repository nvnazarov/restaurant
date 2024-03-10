package kpo.restorant.services;

import jakarta.transaction.Transactional;
import kpo.restorant.models.*;
import kpo.restorant.models.Process;
import kpo.restorant.repositories.MenuRepository;
import kpo.restorant.repositories.OrderRepository;
import kpo.restorant.repositories.ProcessRepository;
import kpo.restorant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProcessRepository processRepository;

    @Autowired
    private KitchenService kitchenService;

    @Transactional
    public Process getCurrentProcess(User user) {
        List<Process> processes = processRepository.findByUserId(user.getId());

        for (Process process : processes) {
            if (process.getStatus() != Status.PAYED && process.getStatus() != Status.CANCELED) {
                return process;
            }
        }

        return null;
    }

    @Transactional
    public List<Dish> getOrderDishes(User user) {
        return orderRepository.findAllByUserId(user.getId()).stream().map(Order::getDish).toList();
    }

    @Transactional
    public List<Process> getAllOrders(User user) {
        return processRepository.findByUserId(user.getId());
    }

    @Transactional
    public void orderDish(User user, Long dishId) throws Exception {
        Process currentProcess = getCurrentProcess(user);
        if (currentProcess != null) {
            throw new Exception();
        }

        menuRepository.takeDish(dishId);
        orderRepository.addOrder(user.getId(), dishId);
    }

    @Transactional
    public void cancelOrder(User user) throws Exception {
        Process currentProcess = getCurrentProcess(user);
        if (currentProcess != null && currentProcess.getStatus() == Status.READY) {
            throw new Exception();
        }

        List<Dish> dishes = getOrderDishes(user);
        for (Dish dish : dishes) {
            menuRepository.returnDish(dish.getId());
        }
        orderRepository.deleteByUserId(user.getId());

        if (currentProcess != null) {
            currentProcess.setStatus(Status.CANCELED);
            processRepository.save(currentProcess);
        }
    }

    public void finishOrder(User user) throws Exception {
        Process process = prepareFinishOrder(user);
        kitchenService.handleOrder(process);
    }

    @Transactional
    private Process prepareFinishOrder(User user) throws Exception {
        Process currentProcess = getCurrentProcess(user);
        if (currentProcess != null) {
            throw new Exception();
        }

        List<Dish> dishes = getOrderDishes(user);
        Long totalPrice = 0L;
        Long totalTime = 0L;
        for (Dish dish : dishes) {
            totalPrice += dish.getPrice();
            totalTime += dish.getTime();
        }

        return processRepository.save(Process.builder()
                .userId(user.getId())
                .totalPrice(totalPrice)
                .totalTime(totalTime)
                .status(Status.COOKING)
                .build());
    }

    @Transactional
    public void payOrder(User user) throws Exception {
        Process currentProcess = getCurrentProcess(user);
        if (currentProcess == null || currentProcess.getStatus() != Status.READY) {
            throw new Exception();
        }

        currentProcess.setStatus(Status.PAYED);
        processRepository.save(currentProcess);
        orderRepository.deleteByUserId(user.getId());
    }
}
