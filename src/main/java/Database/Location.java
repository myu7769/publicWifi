package Database;

public class Location {
    private String lat;
    private String lnt;
    private double distance;

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

    public double getDistance() {
        return distance;
    }

    public void setDistance(Location location) {
        double r = 6371; // 지구의 반지름(km)
        double dLat = Math.toRadians(Double.parseDouble(this.lat) - Double.parseDouble(location.getLat()));
        double dLnt = Math.toRadians(Double.parseDouble(this.lnt) - Double.parseDouble(location.getLnt()));
        double tmp1 = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(Double.parseDouble(this.lat)))
                * Math.cos(Math.toRadians(Double.parseDouble(location.getLat())))
                * Math.sin(dLnt / 2) * Math.sin(dLnt / 2);
        double tmp2 = 2 * Math.atan2(Math.sqrt(tmp1), Math.sqrt(1 - tmp1));
        this.distance = r * tmp2;
    }
}
