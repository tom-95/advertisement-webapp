package tom.wehner.advertisementWebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<Ad> getAdsByUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();

        List<Ad> ads = adRepository.findAll().stream().filter(x -> x.getEMail().equals(mail))
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
    public void createAd(String title, String description, String price, String town, byte[] img) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();

        Ad ad = new Ad(title, description, Integer.valueOf(price), town, mail, img);
        adRepository.save(ad);

    }

    @Override
    public void deleteAd(Long id) {

        adRepository.deleteById(id);

    }

    @Override
    public boolean createUser(UserData userData) {

        if (!(userData.getUsername().contains("@")) || !(userData.getUsername().contains(".")))
            return false;

        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers)
            if (user.getUsername().equals(userData.getUsername()))
                return false;

        User user = new User();
        user.setUsername(userData.getUsername());
        user.setPassword(passwordConfig.passwordEncoder().encode(userData.getPassword()));

        userRepository.save(user);

        return true;
    }

}
