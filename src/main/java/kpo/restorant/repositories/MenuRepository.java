package kpo.restorant.repositories;

import kpo.restorant.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Dish, Long> {
    @Modifying
    @Query("update Dish d set d.amount = d.amount - 1 where d.id = ?1")
    void takeDish(Long dishId);

    @Modifying
    @Query("update Dish d set d.amount = d.amount + 1 where d.id = ?1")
    void returnDish(Long dishId);

    List<Dish> findAllByOrderByNameAsc();
}
