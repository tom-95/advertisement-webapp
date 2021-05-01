package tom.wehner.advertisementWebapp;

import java.util.Map;

public interface IService {

        Map<String, String> getSearchResults(String product, String town);

        void createAd(Data data);

        void deleteAd(String id);

}
