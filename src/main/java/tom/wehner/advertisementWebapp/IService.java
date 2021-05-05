package tom.wehner.advertisementWebapp;

import java.util.Map;

public interface IService {

        Map<Long, String> getSearchResults(String product, String town);

        void createAd(Data data);

        void deleteAd(Long id);

}
