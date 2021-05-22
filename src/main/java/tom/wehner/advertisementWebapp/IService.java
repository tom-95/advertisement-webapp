package tom.wehner.advertisementWebapp;

import java.util.List;
import java.util.Map;

public interface IService {

        List<Ad> getSearchResults(String product, String town);

        void createAd(Data data);

        void deleteAd(Long id);

}
