package com.cricket.apis.controllers;

import com.cricket.apis.entities.Match;
import com.cricket.apis.services.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.http.HttpClient;
import java.util.List;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/match")
@CrossOrigin("*")
public class MatchController {

    private MatchService matchService;
    private HttpClient httpClient; // Inject HttpClient

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
        this.httpClient = httpClient;
    }

    // api for getting latest news
    @GetMapping("/home")
    public ResponseEntity<?> getNews() {
        try {
            String apiKey = "87c5d120ae02459fb660f9188bceb3fc";
            String query = "cricket";
            String apiUrl = "https://newsapi.org/v2/top-headlines?q=" + query + "&apiKey=" + apiKey;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return ResponseEntity.ok(response.body());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching news");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching news");
        }
    }

    // api for getting live matches
    @GetMapping("/live")
    public ResponseEntity<?> getLiveMatchScores() throws InterruptedException {
        System.out.println("getting live match");
        return new ResponseEntity<>(this.matchService.getLiveMatches(), HttpStatus.OK);
    }

    // api for getting point table
    @GetMapping("/point-table")
    public ResponseEntity<?> getCWCPointTable() {
        return new ResponseEntity<>(this.matchService.getCWCPointTable(), HttpStatus.OK);
    }

    // api for getting all matches
    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        return new ResponseEntity<>(this.matchService.getAllMatches(), HttpStatus.OK);
    }
}
