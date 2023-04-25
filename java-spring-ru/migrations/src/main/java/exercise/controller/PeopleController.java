package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(path = "")
    public List<String> getPeople() {
        String query = "SELECT * FROM person";
        List<Map<String, Object>> rows = jdbc.queryForList(query);

        List<String> people = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            String person = "ID: " + row.get("id") + ", " + row.get("first_name") + " " + row.get("last_name");
            people.add(person);
        }

        return people;
    }

    @GetMapping(path = "/people/{id}")
    public ResponseEntity<Map<String, String>> getPerson(@PathVariable("id") int id) {
        String countQuery = "SELECT COUNT(*) FROM person WHERE id = ?";
        int count = jdbc.queryForObject(countQuery, Integer.class, id);

        if (count == 0) {
            return ResponseEntity.notFound().build();
        }

        String query = "SELECT first_name, last_name FROM person WHERE id = ?";
        Map<String, Object> result = jdbc.queryForMap(query, id);

        Map<String, String> person = new HashMap<>();
        person.put("first_name", (String) result.get("first_name"));
        person.put("last_name", (String) result.get("last_name"));

        return ResponseEntity.ok(person);
    }
    // END
}
