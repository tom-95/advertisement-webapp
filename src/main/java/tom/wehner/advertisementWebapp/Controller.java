package tom.wehner.advertisementWebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private Service service;

    private String city = "Berlin";

    @GetMapping("/")
    public String startPage() {

        return "startPage";

    }

    @GetMapping("/getArticles")
    public String getArticles(@ModelAttribute SearchRequest searchRequest, Model model) {

        List<Ad> ads = service.getSearchResults(searchRequest.getTerm(), searchRequest.getTown());

        model.addAttribute("ads", ads);
        model.addAttribute("searchRequest", searchRequest);

        return "searchResults";

    }

    @GetMapping("/myAccount")
    public String myAccount(@AuthenticationPrincipal OidcUser user) {

        return "myAccount";

    }

    @GetMapping("/showArticle/{id}")
    public String showArticle(@PathVariable("id") String id, Model model) {

        Ad ad = service.getAdById(Long.parseLong(id));

        model.addAttribute("ad", ad);

        return "advertisement";

    }

    @GetMapping("/changeCity/{city}")
    public String changeCity(@PathVariable("city") String city) {

        this.city = city;

        return "startPage";

    }

    @ModelAttribute(name = "searchRequest")
    public SearchRequest searchRequest() {
        return new SearchRequest(city);
    }

}
