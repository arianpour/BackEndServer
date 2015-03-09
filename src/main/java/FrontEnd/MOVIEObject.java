package FrontEnd;

/**
 * Created by arian on 2/20/15.
 */
class MOVIEObject {
    private String name;
    private String thumbail;
    private int year;
    private String releaseDate;

// --Commented out by Inspection START (2/20/15 9:27 AM):
//    public String getReleaseDate() {
//        return releaseDate;
//    }
// --Commented out by Inspection STOP (2/20/15 9:27 AM)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

// --Commented out by Inspection START (2/20/15 9:27 AM):
//    public String getThumbail() {
//        return thumbail;
//    }
// --Commented out by Inspection STOP (2/20/15 9:27 AM)

    public void setThumbail(String thumbail) {
        this.thumbail = thumbail;
    }

// --Commented out by Inspection START (2/20/15 9:27 AM):
//    public int getYear() {
//        return year;
//    }
// --Commented out by Inspection STOP (2/20/15 9:27 AM)

    public void setYear(int year) {
        this.year = year;
    }

    public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
    }
}
