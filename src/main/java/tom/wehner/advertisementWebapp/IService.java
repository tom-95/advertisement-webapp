package tom.wehner.advertisementWebapp;

import tom.wehner.advertisementWebapp.security.UserData;

import java.util.List;

public interface IService {

        Ad getAdById(Long id);

        List<Ad> getAdsByUser();

        Ad getNewestAd();

        List<Ad> getSearchResults(String product, String town);

        void createAd(String title, String description, String price, String town, byte[] img);

        void deleteAd(Long id);

        boolean createUser(UserData userData);

}
