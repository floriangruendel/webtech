package htw.berlin.webtech.web;

import htw.berlin.webtech.persistence.LevyRepository;
import htw.berlin.webtech.service.LevyService;
import htw.berlin.webtech.web.api.Levy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
