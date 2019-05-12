package com.dev_abraham.interview_developer_android_jr.Models;

import java.util.ArrayList;

public class ApiStarWarResponse {

    ArrayList <CharacterStarWars> results;

    public ArrayList<CharacterStarWars> getResults() {
        return results;
    }

    public void setResults(ArrayList<CharacterStarWars> results) {
        this.results = results;
    }
}
