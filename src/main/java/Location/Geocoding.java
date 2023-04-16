package Location;

public class Geocoding {

    public static void main(String[] args) {

        String ip = new ipSearch().getIpAddress(); // 공인 IP 입력
        ApiKeys apikeys = new ApiKeys();
        ApiClient apiClient = new ApiClient(apikeys.getAccessKey(), apikeys.getSecretKey());
        try {
            apiClient.run(ip);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
