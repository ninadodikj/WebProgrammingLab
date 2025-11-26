package mk.ukim.finki.wp.lab.controller;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;

    }
    @PostMapping
    public String postDishesPage(HttpSession session, @RequestParam Long chefId){
        session.setAttribute("chefId",chefId);
        return "redirect:/dishes";
    }

    @GetMapping
    public String getDishesPage(@RequestParam(required = false) String error, Model model, HttpSession session){
        Long chefId=(Long)session.getAttribute("chefId");
        model.addAttribute("dishes",dishService.listDishes());
        model.addAttribute("chef",chefService.findById(chefId));
        return "dishesList";
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime){
        dishService.create(dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }
    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id, @RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime){
        dishService.update(id, dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }
    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id){
        dishService.delete(id);
        return "redirect:/dishes";
    }
    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model){
        Dish dish = dishService.findById(id);
        if(dish==null){
            return "redirect:/dishes?error=DishNotFound";
        }
        model.addAttribute("dish",dish);
        return "dish-form";
    }
    @GetMapping("/dish-form")
    public String getAddDishPage(Model model){
        return "/dish-form";
    }
}
