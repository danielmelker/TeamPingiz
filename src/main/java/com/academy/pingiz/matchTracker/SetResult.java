package com.academy.pingiz.matchTracker;

import javax.persistence.*;

@Entity
public class SetResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private int team1Score;
    private int team2Score;

    private int setNum;

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @ManyToOne
    private Match match;

    public SetResult() { }
    public SetResult(int setNum, int team1Score, int team2Score){
        this.setNum=setNum;
        this.team1Score=team1Score;
        this.team2Score=team2Score;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public int getSetNum() {
        return setNum;
    }

    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }

    public void setSetNum(int setNum) {
        this.setNum = setNum;
    }
}
