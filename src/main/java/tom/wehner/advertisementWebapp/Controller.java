package tom.wehner.advertisementWebapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

}
