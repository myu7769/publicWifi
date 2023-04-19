package TbPublicWifiInfo;

import Database.DataBaseService;

import java.util.ArrayList;

public class getController {


    private static ArrayList<Wifi> wifis;

    public int getAllWifi() {
        WifiGetInfoOkhttp wifi = new WifiGetInfoOkhttp();
        DataBaseService dataBaseService = new DataBaseService();
        int successCnt = 0;
        int cnt = 0;
        int start = 1;
        int end = 1000;
        cnt = 0;
        try {
            int wifiSize = 1000;

            while (wifiSize == 1000) {
                wifis = wifi.getWifiInfo(start, end, "");
                if (wifis == null) {
                    break;
                }
                wifiSize = wifis.size();

                dataBaseService.insertWifiList(wifis);
                successCnt += wifiSize;
                start += 1000;
                end += 1000;
//                  cnt++;//
//                  System.out.println("cnt = " + cnt);
//                  System.out.println("     wifiSize = " + wifiSize);
                System.out.println("successCnt = " + successCnt);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return successCnt;
        }
        return successCnt;
    }
}
