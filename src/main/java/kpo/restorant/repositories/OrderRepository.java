package kpo.restorant.repositories;

import kpo.restorant.models.Dish;
import kpo.restorant.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
    void deleteByUserId(Long userId);

    @Modifying
    @Query(value = "insert into orders(user_id, dish_id) values (?1, ?2)", nativeQuery = true)
    void addOrder(Long userId, Long dishId);
}
