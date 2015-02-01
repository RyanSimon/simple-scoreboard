package me.ryansimon.simplescoreboard.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ryan Simon
 */
public class Player {
    
    @SerializedName("name")
    private String mName;
    
    @SerializedName("score")
    private Integer mScore;

    /***** CONSTRUCTORS *****/
    
    public Player() {
        
    }
    
    public Player(String name) {
        mName = name;
        mScore = 0;
    }
    
    public Player(String name, Integer initialScore) {
        mName = name;
        mScore = initialScore;
    }
    
    /***** GETTERS AND SETTERS *****/
    
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Integer getScore() {
        return mScore;
    }

    public void setScore(Integer score) {
        mScore = score;
    }
}
