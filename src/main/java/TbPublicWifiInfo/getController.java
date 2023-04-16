package TbPublicWifiInfo;

import Database.DataBaseService;

import java.util.ArrayList;

public class getController {


    private static ArrayList<Wifi> wifis;
    private static String[] ADDRESS = {"강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구",
    "서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중량구"};

    public int getAllWifi(){
        WifiGetInfoOkhttp wifi = new WifiGetInfoOkhttp();
        DataBaseService dataBaseService = new DataBaseService();
        int successCnt = 0;
        int cnt = 0;
        try {
            for (int i = 0; i < ADDRESS.length; i++) {
                int start = 1;
                int end = 1000;
                cnt = 0;

                int wifiSize = 1000;

                while(wifiSize == 1000){

                    wifis = wifi.getWifiInfo(start,end,ADDRESS[i]);

                    if(wifis == null){
                        break;
                    }
                    wifiSize = wifis.size();

                    dataBaseService.insertWifiList(wifis);
                    successCnt += wifiSize;
                    start += 1000;
                    end += 1000;
//                cnt++;
//
//
//                System.out.println("cnt = " + cnt);
//                System.out.println("     wifiSize = " + wifiSize);
//                System.out.println("successCnt = " + successCnt);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            return successCnt;
        }
        return successCnt;
    }
}
