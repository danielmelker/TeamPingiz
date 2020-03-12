package com.academy.pingiz.matchTracker;

import com.academy.pingiz.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {


    @Autowired
    private MatchRepositoryJPA matchRepository;

    public List<Match>  getAllMatches(){
        return (List)matchRepository.findAll();
    }

    public void addMatch(Match m){
        matchRepository.save(m);
    }

    public List<Match> getMatchesWithUser(User user){
        var matches = getAllMatches();
        return matches.stream().filter( m -> m.getAllPlayers().contains(user))
                .collect(Collectors.toList());
    }





}
