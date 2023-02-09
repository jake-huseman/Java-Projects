package coms309.people;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
class GroceryController
{
    Grocery grocery = new Grocery();
    HashMap<String, Grocery> groceryList = new HashMap<>();

    @GetMapping("/item")
    public @ResponseBody HashMap<String, Grocery> getAllGrocery()
    {
        return groceryList;
    }

    @PostMapping("/item")
    public @ResponseBody String createGrocery(@RequestBody Grocery grocery)
    {
        System.out.println(grocery);
        groceryList.put(grocery.getItem(), grocery);
        return "New grocery " + grocery.getItem() + " Saved";
    }

    @GetMapping("/grocery/{item}")
    public @ResponseBody Grocery getGrocery(@PathVariable String item)
    {
        Grocery p = groceryList.get(item);
        return p;
    }

    //issues because it replaces the whole list not just the grocery.
    @PutMapping("/grocery/{item}")
    public @ResponseBody Grocery updateGrocery(@PathVariable String item, @RequestBody Grocery g)
    {
        groceryList.replace(item, g);
        return groceryList.get(item);
    }

    @DeleteMapping("/grocery/{item}")
    public @ResponseBody HashMap<String, Grocery> deleteGrocery(@PathVariable String item)
    {
        groceryList.remove(item);
        return groceryList;
    }
}
