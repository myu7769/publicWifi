package Location;

public class Location {
    private String lat;
    private String lnt;

    public Location(String lat, String lnt) {
        this.lat = lat;
        this.lnt = lnt;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLnt(String lnt) {
        this.lnt = lnt;
    }

    public String getLat() {
        return lat;
    }

    public String getLnt() {
        return lnt;
    }
}
