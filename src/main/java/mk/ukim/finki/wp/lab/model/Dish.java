package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Dish {
    String dishId;
    String name;
    String cuisine;
    int preparationTime;
    private Long id;

    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
        this.id = (long) (Math.random() * 1000);
    }
}
