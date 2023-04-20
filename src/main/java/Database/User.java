package Database;

public class User {
    String id;
    Location location;

    String checkTime;

    public User(String id, Location location, String checkTime) {
        this.id = id;
        this.location = location;
        this.checkTime = checkTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }
}
