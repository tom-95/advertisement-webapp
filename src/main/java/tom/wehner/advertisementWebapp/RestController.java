package tom.wehner.advertisementWebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private Service service;

    @GetMapping(value="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Long, String>> getAds(@RequestParam("product") String product, @RequestParam("town") String town) {

        List<Ad> results = service.getSearchResults(product, town);

        Map<Long, String> json = new HashMap<>();

        results.forEach(x -> json.put(x.getId(), x.getTitle()));

        return ResponseEntity.ok(json);

    }

    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> createAd(@RequestBody Data data) {

        service.createAd(data);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable("id") String id) {

        service.deleteAd(Long.parseLong(id));

        return ResponseEntity.ok().build();

    }

}
