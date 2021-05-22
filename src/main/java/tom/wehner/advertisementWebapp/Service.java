package tom.wehner.advertisementWebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Service implements IService{

    @Autowired
    private AdRepository adRepository;

    public Ad getAdById(Long id) {

        Ad ad = adRepository.getOne(id);

        return ad;

    }

    @Override
    public List<Ad> getSearchResults(String product, String town) {

        List<Ad> searchResults = adRepository.findAll().stream().filter(x -> x.getTown().equals(town))
                .filter(x -> x.getTitle().contains(product)).collect(Collectors.toList());

        return searchResults;

    }

    @Override
    public void createAd(Data data) {

        String title = data.getTitle();
        String description = data.getDescription();
        String town = data.getTown();
        String contact = data.getContact();

        Ad ad = new Ad(title, description, town, contact);
        adRepository.save(ad);

    }

    @Override
    public void deleteAd(Long id) {

        adRepository.deleteById(id);

    }

}
