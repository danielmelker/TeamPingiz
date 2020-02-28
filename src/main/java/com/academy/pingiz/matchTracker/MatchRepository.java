package com.academy.pingiz.matchTracker;


import com.academy.pingiz.user.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class MatchRepository {


    private Map<Long, Match> matches ;

    public Match getMatchById(long id){
        return matches.get(id);
    }

    public Set<Match> getMatchesWithUser(User player){
        return  matches.values().stream()
                .filter( m -> m.getAllPlayers().contains(player) )
                .collect(Collectors.toSet());
    }

    public void add(Match m){
        matches.put(m.getId(), m);
    }

    public Match getById(long id){
        return matches.get(id);
    }




}
