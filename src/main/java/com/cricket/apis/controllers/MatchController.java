package com.cricket.apis.controllers;

import com.cricket.apis.entities.Match;
import com.cricket.apis.services.MatchService;
import com.cricket.apis.services.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/match")
@CrossOrigin(origins = {"https://cricblitzbackend-production.up.railway.app", "http://localhost:8081"})
public class MatchController {

    private MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
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
