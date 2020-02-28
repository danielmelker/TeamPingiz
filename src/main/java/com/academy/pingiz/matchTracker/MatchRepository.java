package com.academy.pingiz.matchTracker;


import com.academy.pingiz.user.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class MatchRepository {


    private Map<Long, Match> matches = new HashMap<>();

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

    public List<Match> getAllMatches(){
        var mlist = matches.values().stream().collect(Collectors.toList());
        mlist.sort((m1,m2) -> m1.getTime().compareTo(m2.getTime()));
        return mlist;
    }


}
