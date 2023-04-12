package TbPublicWifiInfo;

import java.util.ArrayList;

public class getController {

    private static ArrayList<Wifi> wifis ;
    public static void main(String[] args) {
        WifiGetInfoOkhttp wifi = new WifiGetInfoOkhttp();
        wifis = wifi.getUserInfo();

        for (int i = 0; i < wifis.size(); i++) {
            System.out.println(wifis.get(i).LAT + " " + wifis.get(i).LNT);
        }
    }
}
