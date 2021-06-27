package tom.wehner.advertisementWebapp;

import java.util.List;

public interface IService {

        Ad getAdById(Long id);

        List<Ad> getAdsByUser(String eMail);

        Ad getNewestAd();

        List<Ad> getSearchResults(String product, String town);

        void createAd(String title, String description, String price, String town, String eMail, byte[] img);

        void deleteAd(Long id);

}
