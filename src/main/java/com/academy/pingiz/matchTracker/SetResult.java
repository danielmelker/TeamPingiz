package com.academy.pingiz.matchTracker;

public class SetResult {

    private int team1Score;
    private int team2Score;

    private int setNum;

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
}
