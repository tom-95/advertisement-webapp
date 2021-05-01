package tom.wehner.advertisementWebapp;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Service implements IService{

    List<Ad> allAds = new ArrayList<>();

    @Override
    public Map<String, String> getSearchResults(String product, String town) {

        List<Ad> searchResults = new ArrayList<>();
        searchResults = allAds.stream().filter(x -> x.getTown().equals(town)).filter(x -> x.getTitle().contains(product)).collect(Collectors.toList());
        Map<String, String> json = new HashMap<>();
        searchResults.forEach(x -> json.put(x.getId(), x.getTitle()));

        return json;

    }

    @Override
    public void createAd(Data data) {

        String title = data.getTitle();
        String description = data.getDescription();
        String town = data.getTown();
        String contact = data.getContact();

        Ad ad = new Ad(title, description, town, contact);

        allAds.add(ad);

    }

    @Override
    public void deleteAd(String id) {

        allAds.removeIf(x -> x.getId().equals(id));

    }

}
