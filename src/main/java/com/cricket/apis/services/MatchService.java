package com.cricket.apis.services;

import com.cricket.apis.entities.Match;

import java.util.List;
import java.util.Map;

public interface MatchService {
    //Get all matches
    List<Match> getAllMatches();

    //Get Live Matches
    List<Match> getLiveMatches();

    //Get Points Table
    List<List<String>> getCWCPointTable();

}