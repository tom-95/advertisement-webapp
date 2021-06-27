package tom.wehner.advertisementWebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Service implements IService{

    @Autowired
    private AdRepository adRepository;

    public Ad getAdById(Long id) {

        Ad ad = adRepository.getOne(id);

        return ad;

    }

    public List<Ad> getAdsByUser(String eMail) {

        List<Ad> ads = adRepository.findAll().stream().filter(x -> x.getEMail().equals(eMail))
                .collect(Collectors.toList());

        return ads;

    }

    public Ad getNewestAd() {

        Optional<Long> newestId = adRepository.findAll().stream().map(x -> x.getId()).max(Comparator.naturalOrder());

        if (newestId.isEmpty())
            return null;

        Ad newestAd = adRepository.getOne(newestId.get());

        return newestAd;

    }

    @Override
    public List<Ad> getSearchResults(String product, String town) {

        List<Ad> searchResults = adRepository.findAll().stream().filter(x -> x.getTown().equals(town))
                .filter(x -> x.getTitle().contains(product)).collect(Collectors.toList());

        return searchResults;

    }

    @Override
    public void createAd(Data data, String eMail) {

        String title = data.getTitle();
        String description = data.getDescription();
        int price = Integer.valueOf(data.getPrice());
        String town = data.getTown();

        Ad ad = new Ad(title, description, price, town, eMail);
        adRepository.save(ad);

    }

    @Override
    public void deleteAd(Long id) {

        adRepository.deleteById(id);

    }

}
