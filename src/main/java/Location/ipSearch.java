package Location;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ipSearch {
    public String getIpAddress() {

        URL url = null;
        String ip = "";
        try {
           url = new URL("http://checkip.amazonaws.com");
           BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
           ip = br.readLine();

            // IP 주소 출력
           br.close();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ip;
    }
    public static String getIpAddress2(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
