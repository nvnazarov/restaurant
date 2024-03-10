package kpo.restorant.services;

import kpo.restorant.models.Dish;
import kpo.restorant.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public List<Dish> getMenu() {
        return menuRepository.findAllByOrderByNameAsc();
    }

    public void addDish(Dish dish) {
        menuRepository.save(dish);
    }

    public void deleteDish(Long id) {
        menuRepository.deleteById(id);
    }
}
