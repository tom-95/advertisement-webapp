package tom.wehner.advertisementWebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private Service service;

    /*@GetMapping(value="/myAds")
    public ResponseEntity<List<Ad>> getMyAds(@AuthenticationPrincipal OidcUser user) {

        List<Ad> results = service.getAdsByUser(user.getEmail());

        return ResponseEntity.ok(results);

    }

    @PostMapping(value="/create")
    public ResponseEntity<Void> createAd(@AuthenticationPrincipal OidcUser user, @RequestPart("title") String title,
                                         @RequestPart("description") String description, @RequestPart("price") String price,
                                         @RequestPart("town") String town, @RequestPart("image") MultipartFile image) throws IOException {

        byte[] img = image.getBytes();

        service.createAd(title, description, price, town, user.getEmail(), img);

        return ResponseEntity.ok().build();

    }*/

    @GetMapping(value="/myAds")
    public ResponseEntity<List<Ad>> getMyAds() {

        List<Ad> results = service.getAdsByUser("");

        return ResponseEntity.ok(results);

    }

    @PostMapping(value="/create")
    public ResponseEntity<Void> createAd(@RequestPart("title") String title,
                                         @RequestPart("description") String description, @RequestPart("price") String price,
                                         @RequestPart("town") String town, @RequestPart("image") MultipartFile image) throws IOException {

        byte[] img = image.getBytes();

        service.createAd(title, description, price, town, "", img);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable("id") String id) {

        service.deleteAd(Long.parseLong(id));

        return ResponseEntity.ok().build();

    }

}
