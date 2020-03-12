package com.academy.pingiz.matchTracker;

import com.academy.pingiz.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    private List<User> team1;
    @ManyToMany
    private List<User> team2;

    @ManyToMany
    private List<User> winners;

    private MatchType type;

    private LocalDateTime time;

    @OneToMany(mappedBy = "match")
    private List<SetResult> setResults;
    private int numSets;
    private int team1score;
    private int team2score;


    public void setNumSets(int numSets) {
        this.numSets = numSets;
    }

    public void setTeam1score(int team1score) {
        this.team1score = team1score;
    }

    public void setTeam2score(int team2score) {
        this.team2score = team2score;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTeam1(List<User> team1) {
        this.team1 = team1;
    }

    public void setTeam2(List<User> team2) {
        this.team2 = team2;
    }

    public void setWinners(List<User> winners) {
        this.winners = winners;
    }

    public void setType(MatchType type) {
        this.type = type;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setSetResults(List<SetResult> setResults) {
        this.setResults = setResults;
    }

    private Match(){
    }

    private Match(User u1, User u2, List<SetResult> results, LocalDateTime time ){
        type=MatchType.SINGLE;
        team1 = List.of(u1);
        team2 = List.of(u2);
        this.time = time;
        numSets=0;
        team1score=0;
        team2score=0;
        for(var res : results){
            numSets++;
            if(res.getTeam1Score() > res.getTeam2Score())
                team1score++;
            else
                team2score++;
        }

        if(team1score > team2score){
            winners=team1;
        } else if (team2score > team1score){
            winners=team2;
        } else{
            winners = null;
        }
    }

    public static class Single {
        // Required parameters
        private final User u1;
        private final User u2;

        // Optional parameters - initialized to default values
        private LocalDateTime time = LocalDateTime.now();
        private List<SetResult> setResults = new ArrayList<>();
        private int currentSetNum = 1;

        public Single(User u1, User u2) {
            this.u1=u1;
            this.u2 = u2;
        }
        public Single addSet(int u1Score, int u2Score) {
            setResults.add(new SetResult(currentSetNum, u1Score,u2Score ));
            currentSetNum++;

            return this;
        }
        public Single setTime(LocalDateTime time) {
            this.time = time;
            return this;
        }

        public Match build() {
            return new Match(u1, u2, setResults,time);
        }
    }


    public long getId() {
        return id;
    }

    private Match(User team1_u1, User team1_u2,
                  User team2_u1, User team2_u2, List<SetResult> results,
                  LocalDateTime time ){
        this();
        type=MatchType.DOUBLE;
        team1 = List.of(team1_u1,team1_u2);
        team2 = List.of(team2_u1,team2_u2);
        this.time=time;
        numSets=0;
        team1score=0;
        team2score=0;
        for(var res : results){
            numSets++;
            if(res.getTeam1Score() > res.getTeam2Score())
                team1score++;
            else
                team2score++;
        }

        if(team1score > team2score){
            winners=team1;
        } else if (team2score > team1score){
            winners=team2;
        } else{
            winners = null;
        }
    }


    public static class Double {
        // Required parameters
        private final User team1_u1, team1_u2, team2_u1, team2_u2;

        // Optional parameters - initialized to default values
        private LocalDateTime time = LocalDateTime.now();
        private List<SetResult> setResults = new ArrayList<>();
        private int currentSetNum = 1;

        public Double(User team1_u1,User team1_u2, User team2_u1, User team2_u2) {
            this.team1_u1=team1_u1;
            this.team1_u2=team1_u2;
            this.team2_u1=team2_u1;
            this.team2_u2=team2_u2;
        }
        public Double addSet(int u1Score, int u2Score) {
            setResults.add(new SetResult(currentSetNum, u1Score,u2Score ));
            currentSetNum++;
            return this;
        }
        public Double setTime(LocalDateTime time) {
            this.time = time;
            return this;
        }

        public Match build() {
            return new Match(team1_u1,team1_u2,team2_u1,team1_u2, setResults,time);
        }
    }


    public List<User> getTeam1() {
        return team1;
    }

    public List<User> getTeam2() {
        return team2;
    }

    public List<User> getWinners() {
        return winners;
    }


    public MatchType getType() {
        return type;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getTimeString() {
        return time.format(DateTimeFormatter.ofPattern("yyyy dd/MM - HH:mm"));

    }


    public List<SetResult> getSetResults() {
        return setResults;
    }

    public int getNumSets() {
        return numSets;
    }

    public int getTeam1score() {
        return team1score;
    }

    public int getTeam2score() {
        return team2score;
    }

    public Set<User> getAllPlayers(){
        Set<User> players = new HashSet<>();
        players.addAll(team1);
        players.addAll(team2);
        return players;
    }
}
