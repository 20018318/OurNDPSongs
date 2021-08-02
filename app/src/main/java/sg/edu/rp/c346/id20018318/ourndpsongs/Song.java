package sg.edu.rp.c346.id20018318.ourndpsongs;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int id, String title, String singers, int year, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public Song(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSingers() {
        return singers;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

//    @Override
//    public String toString() {
//        String statement = "";
//        if (stars == 1) {
//            statement = "★";
//        } else if (stars == 2) {
//            statement = "★★";
//        } else if (stars == 3) {
//            statement = "★★★";
//        } else if (stars == 4) {
//            statement = "★★★★";
//        } else if (stars == 5) {
//            statement = "★★★★★";
//        }
//        return statement;
//    }
}


