package co.edu.konradlorenz.cardview;

import java.io.Serializable;
import java.util.List;
import java.util.HashMap;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Serie implements Serializable {
    private String name;
    private int numOfSeasons;
    private int thumbnail;
    private HashMap<Integer, List<String>> captBySeason;
    public Serie(String name, int numOfSeasons, int thumbnail, HashMap<Integer, List<String>> captsBySeason) {
        this.name = name;
        this.numOfSeasons = numOfSeasons;
        this.thumbnail = thumbnail;
        this.captBySeason = captsBySeason;
    }


    public HashMap<Integer, List<String>> getCaptBySeason() {
        return captBySeason;
    }

    public void setCaptBySeason(HashMap<Integer, List<String>> captBySeason) {
        this.captBySeason = captBySeason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSeasons() {
        return numOfSeasons;
    }

    public void setNumOfSeasons(int numOfSeasons) {
        this.numOfSeasons = numOfSeasons;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

}
