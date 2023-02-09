package coms309;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class WelcomeController
{
    @GetMapping("/")
    public String welcome()
    {
        return "Welcome to Jake's Grocery list";
    }
}


