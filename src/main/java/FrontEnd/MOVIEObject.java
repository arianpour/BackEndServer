package FrontEnd;

/**
 * Created by arian on 2/20/15.
 */
class MOVIEObject {
    private String name;
    private String thumbnail;
    private int year;
    private String releaseDate;

    public int getCritics_score() {
        return critics_score;
    }

    public void setCritics_score(int critics_score) {
        this.critics_score = critics_score;
    }

    public int getAudience_score() {
        return audience_score;
    }

    public void setAudience_score(int audience_score) {
        this.audience_score = audience_score;
    }

    private int critics_score;
    private int audience_score;
    public String getThumbail() {
        return thumbnail;
    }

    public int getYear() {
        return year;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setThumbail(String thumbail) {
        this.thumbnail = thumbail;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
    }
}
