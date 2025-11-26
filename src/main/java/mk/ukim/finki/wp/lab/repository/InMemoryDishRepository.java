package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryDishRepository implements DishRepository {
    @Override
    public List<Dish> findAll() {
        return DataHolder.dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        return DataHolder.dishes.stream().filter(d->d.getDishId().equals(dishId)).findFirst().orElse(null);
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return DataHolder.dishes.stream().filter(d->d.getId().equals(id)).findFirst();
    }

    @Override
    public Dish save(Dish dish) {
        Dish dish1=DataHolder.dishes.stream().filter(d->d.getId().equals(dish.getId())).findFirst().orElse(null);
        if(dish1==null){
            DataHolder.dishes.add(dish);
        } else {
            DataHolder.dishes.remove(dish1);
            DataHolder.dishes.add(dish);
        }
        return dish;
    }

    @Override
    public void deleteById(Long id) {
         DataHolder.dishes.removeIf(d->d.getId().equals(id));
    }
}
