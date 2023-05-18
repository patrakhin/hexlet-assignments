package exercise;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import exercise.daytimes.Daytime;

// BEGIN
@RestController
public class WelcomeController {

    @Autowired
    Daytime daytime;

    @Autowired
    Meal meal;

    @GetMapping("/daytime")
    public String root() {
        String time = daytime.getName();
        return "It is " + time + " now. " + "Enjoy your " + meal.getMealForDaytime(time);
    }
}
// END
