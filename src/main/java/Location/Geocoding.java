package Location;

import Database.Location;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Geocoding {

    public Location getLocation() {

        String ip = new ipSearch().getIpAddress(); // 공인 IP 입력
        ApiKeys apikeys = new ApiKeys();
        ApiClient apiClient = new ApiClient(apikeys.getAccessKey(), apikeys.getSecretKey());
        String response = "";
        String objectLat;
        String objectLnt;

        try {
            response = apiClient.run(ip);
            JsonParser jsonParser = new JsonParser();
            JsonObject object = (JsonObject) jsonParser.parse(response);
            object = (JsonObject)object.get("geoLocation");
            objectLat = object.get("long").getAsString();
            objectLnt = object.get("lat").getAsString();

            return new Location(objectLat, objectLnt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
