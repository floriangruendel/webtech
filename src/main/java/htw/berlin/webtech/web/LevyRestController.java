package htw.berlin.webtech.web;

import htw.berlin.webtech.persistence.LevyRepository;
import htw.berlin.webtech.service.LevyService;
import htw.berlin.webtech.web.api.Levy;
import htw.berlin.webtech.web.api.LevyCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class LevyRestController {

    private final LevyService levyService;

    public LevyRestController(LevyService levyService) {
        this.levyService = levyService;
    }

    @GetMapping(path = "/api/v1/levies")
    public ResponseEntity<List<Levy>> fetchLevies() {
        return ResponseEntity.ok(levyService.findAll());
    }

    @GetMapping(path = "/api/v1/levies/{id}")
    ResponseEntity<Levy> fetchLevyById(@PathVariable Long id) {
        var levy = levyService.findById(id);
        return levy != null? ResponseEntity.ok(levy) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/levies")
    public ResponseEntity<Void> createLevy(@RequestBody LevyCreateRequest request) throws URISyntaxException {
        var person = levyService.create(request);
        URI uri = new URI("/api/v1/levies/" + person.getId());
        return ResponseEntity.created(uri).build();
        }
}
