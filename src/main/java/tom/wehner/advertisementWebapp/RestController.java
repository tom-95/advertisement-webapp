package tom.wehner.advertisementWebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private Service service;

    @GetMapping(value="/myAds", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Ad>> getMyAds(@AuthenticationPrincipal OidcUser user) {

        List<Ad> results = service.getAdsByUser(user.getEmail());

        Map<String, Ad> json = new HashMap<>();

        results.forEach(x -> json.put(x.getId().toString(), x));

        return ResponseEntity.ok(json);

    }

    @PostMapping(value="/create")
    public ResponseEntity<Void> createAd(@AuthenticationPrincipal OidcUser user, @RequestPart("title") String title,
                                         @RequestPart("description") String description, @RequestPart("price") String price,
                                         @RequestPart("town") String town, @RequestPart("image") MultipartFile image) throws IOException {

        byte[] img = image.getBytes();

        service.createAd(title, description, price, town, user.getEmail(), img);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable("id") String id) {

        service.deleteAd(Long.parseLong(id));

        return ResponseEntity.ok().build();

    }

}
