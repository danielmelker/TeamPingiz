package com.academy.pingiz.matchTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    public List<Match>  getAllMatches(){
        return matchRepository.getAllMatches();
    }

    public void addMatch(Match m){
        matchRepository.add(m);
    }






}
