package me.ryansimon.simplescoreboard.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ryan Simon
 */
public class Player implements Parcelable {
    
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

    /***** PARCELABLE METHODS *****/
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeValue(this.mScore);
    }

    private Player(Parcel in) {
        this.mName = in.readString();
        this.mScore = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}
