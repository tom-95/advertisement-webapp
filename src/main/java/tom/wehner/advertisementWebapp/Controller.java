package tom.wehner.advertisementWebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Controller {

    @Autowired
    Service service;

    @GetMapping(value="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> getAds(@RequestParam("product") String product, @RequestParam("town") String town) {

        Map<String, String> results = service.getSearchResults(product, town);

        return ResponseEntity.ok(results);

    }

    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> createAd(@RequestBody Data data) {

        service.createAd(data);

        return ResponseEntity.ok().build();

    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable("id") String id) {

        service.deleteAd(id);

        return ResponseEntity.ok().build();

    }

}
