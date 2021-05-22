package tom.wehner.advertisementWebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    RestController restController;

    @Autowired
    Service service;

    @GetMapping("/")
    public String startPage() {

        return "startPage";

    }

    @GetMapping("/getArticles")
    public String getArticles(@ModelAttribute SearchRequest searchRequest, Model model) {

        List<Ad> ads = service.getSearchResults(searchRequest.getTerm(), searchRequest.getTown());

        model.addAttribute("ads", ads);

        return "searchResults";

    }

    @GetMapping("/login")
    public String loginPage() {

        return "login";

    }

    @GetMapping("/searchResults")
    public String searchResults() {

        return "searchResults";

    }

    @ModelAttribute(name = "searchRequest")
    public SearchRequest searchRequest() {
        return new SearchRequest();
    }

}
