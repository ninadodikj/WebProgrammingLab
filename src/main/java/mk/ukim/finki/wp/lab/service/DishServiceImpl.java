package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return dishRepository.findByDishId(dishId);
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id).orElse(null);
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime) {
        Dish dish1=new Dish(dishId,name,cuisine,preparationTime);
        dishRepository.save(dish1);
        return dish1;
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime) {
        Dish dish1=dishRepository.findById(id).orElse(null);
        if(dish1==null){
            return null;
        }
        dish1.setDishId(dishId);
        dish1.setName(name);
        dish1.setCuisine(cuisine);
        dish1.setPreparationTime(preparationTime);
        dishRepository.save(dish1);
        return dish1;
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}
