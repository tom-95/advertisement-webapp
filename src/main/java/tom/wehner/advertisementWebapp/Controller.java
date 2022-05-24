package tom.wehner.advertisementWebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tom.wehner.advertisementWebapp.security.UserData;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private Service service;

    private String city = "Berlin";

    @GetMapping("/")
    public String startPage(Model model) {

        Ad ad = service.getNewestAd();

        if (ad == null)
            return "emptyStartPage";

        model.addAttribute("ad", new ConvertedAd(ad));

        return "startPage";

    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/getArticles")
    public String getArticles(@ModelAttribute SearchRequest searchRequest, Model model) {

        List<Ad> ads = service.getSearchResults(searchRequest.getTerm(), searchRequest.getTown());

        model.addAttribute("ads", ads);
        model.addAttribute("searchRequest", searchRequest);

        return "searchResults";

    }

    @GetMapping("/myAccount")
    public String myAccount() {

        return "myAccount";

    }

    @GetMapping("/showArticle/{id}")
    public String showArticle(@PathVariable("id") String id, Model model) {

        Ad ad = service.getAdById(Long.parseLong(id));

        ConvertedAd convertedAd = new ConvertedAd(ad);

        model.addAttribute("ad", convertedAd);

        return "advertisement";

    }

    @GetMapping("/changeCity/{city}")
    public String changeCity(Model model, @PathVariable("city") String city) {

        this.city = city;

        Ad ad = service.getNewestAd();

        if (ad == null)
            return "emptyStartPage";

        model.addAttribute("ad", new ConvertedAd(ad));

        return "startPage";

    }

    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute UserData userData) {

        if (service.createUser(userData) == true)
            return "login";
        else
            return "register";

    }

    @ModelAttribute(name = "searchRequest")
    public SearchRequest searchRequest() {
        return new SearchRequest(city);
    }

    @ModelAttribute(name = "userData")
    public UserData simpleUser() {
        return new UserData();
    }

}
