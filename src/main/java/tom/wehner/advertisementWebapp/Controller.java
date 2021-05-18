package tom.wehner.advertisementWebapp;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String startPage() {

        return "startPage";

    }

    @GetMapping("/login")
    public String loginPage() {

        return "login";

    }

    @GetMapping("/searchResults")
    public String searchResults() {

        return "searchResults";

    }

}
