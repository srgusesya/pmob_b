package net.husnilkamil.layartancap.model;

public class MovieItem {
    int id;
    String title;
    String overview;
    double vote_average;
    String release_date;
    String poster_path;

    public MovieItem(int id, String title, String overview, double vote_average, String release_date, String poster_path) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }
}
