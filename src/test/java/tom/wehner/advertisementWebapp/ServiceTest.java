package tom.wehner.advertisementWebapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ServiceTest {

    @Autowired
    Service service;

    @MockBean
    AdRepository repository;

    /*@Test
    @DisplayName("should return ads of the user")
    void getAdsByUser() {
        Ad ad1 = new Ad();
        ad1.setTitle("Bike");
        ad1.setEMail("john@test.com");
        Ad ad2 = new Ad();
        ad2.setTitle("TV");
        ad2.setEMail("hans@test.com");
        doReturn(List.of(ad1, ad2)).when(repository).findAll();
        List<Ad> johnsAds = service.getAdsByUser("john@test.com");

        Assertions.assertEquals(1, johnsAds.size());
        Assertions.assertEquals("Bike", johnsAds.get(0).getTitle());
    }*/

    @Test
    @DisplayName("should return the right search results")
    void getSearchResults() {
        Ad ad1 = new Ad();
        ad1.setTitle("Bike");
        ad1.setTown("Berlin");
        Ad ad2 = new Ad();
        ad2.setTitle("Bike");
        ad2.setTown("Hamburg");
        Ad ad3 = new Ad();
        ad3.setTitle("TV");
        ad3.setTown("Berlin");
        doReturn(List.of(ad1, ad2, ad3)).when(repository).findAll();
        List<Ad> searchResults = service.getSearchResults("Bike", "Berlin");

        Assertions.assertEquals(1, searchResults.size());
        Assertions.assertEquals("Bike", searchResults.get(0).getTitle());
    }

}