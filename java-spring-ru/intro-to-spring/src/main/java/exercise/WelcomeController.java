package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {
    @GetMapping("/")
    public String welcomeToSpring(@RequestParam(value = "name", defaultValue = "to Spring") String name){
        return String.format("Welcome %s!", name);
    }

    @GetMapping("/users")
    public String welcomeUser(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }

}
// END
