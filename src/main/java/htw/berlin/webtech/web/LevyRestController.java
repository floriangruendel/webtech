package htw.berlin.webtech.web;

import htw.berlin.webtech.web.api.Levy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LevyRestController {

    private List<Levy> levies;

    public LevyRestController() {
        this.levies = new ArrayList<>();
        levies.add(new Levy(1, "Testabgabe", "Das ist ein Test", "webtech", LocalDate.now(), "wichtig"));
        levies.add(new Levy(2, "Testabgabe2", "Das ist ein Test", "webtech", LocalDate.now(), "wichtig"));

    }

    @GetMapping(path = "/api/v1/levies")
    public ResponseEntity<List<Levy>> fetchLevies() {
        return ResponseEntity.ok(levies);
    }

}
