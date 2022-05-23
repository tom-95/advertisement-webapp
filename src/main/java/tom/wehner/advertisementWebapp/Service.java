package tom.wehner.advertisementWebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tom.wehner.advertisementWebapp.security.*;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Service implements IService {

    @Autowired
    private AdRepository adRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordConfig passwordConfig;

    @Override
    public Ad getAdById(Long id) {

        Ad ad = adRepository.getOne(id);

        return ad;

    }

    @Override
    public List<Ad> getAdsByUser(String eMail) {

        List<Ad> ads = adRepository.findAll().stream().filter(x -> x.getEMail().equals(eMail))
                .collect(Collectors.toList());

        return ads;

    }

    @Override
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
    public void createAd(String title, String description, String price, String town, String eMail, byte[] img) {

        Ad ad = new Ad(title, description, Integer.valueOf(price), town, eMail, img);
        adRepository.save(ad);

    }

    @Override
    public void deleteAd(Long id) {

        adRepository.deleteById(id);

    }

    @Override
    public boolean createUser(SimpleUser simpleUser) {

        User user = new User();
        user.setUsername(simpleUser.getUsername());
        user.setPassword(passwordConfig.passwordEncoder().encode(simpleUser.getPassword()));

        userRepository.save(user);

        return true;
    }

}
