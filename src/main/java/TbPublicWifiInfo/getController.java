package TbPublicWifiInfo;

import Database.DataBaseService;

import java.util.ArrayList;

public class getController {

    private static ArrayList<Wifi> wifis;
    public static void main(String[] args) {
        WifiGetInfoOkhttp wifi = new WifiGetInfoOkhttp();
        wifis = wifi.getUserInfo();


        DataBaseService dataBaseService = new DataBaseService();
        dataBaseService.insertWifiList(wifis);
    }
}
