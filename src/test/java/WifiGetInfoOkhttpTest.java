import TbPublicWifiInfo.Wifi;
import TbPublicWifiInfo.WifiGetInfoOkhttp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class WifiGetInfoOkhttpTest {

    @Test
    public void wifiGetInfoOkhttp(){
        WifiGetInfoOkhttp wifiGetInfoOkhttp = new WifiGetInfoOkhttp();
        ArrayList<Wifi> wifisTest = new ArrayList<>();
        int start = 1;
        int end = 1000;
        wifisTest = wifiGetInfoOkhttp.getWifiInfo( start,end,"");

        if (wifisTest == null) {
            System.out.println("error");
            return;
        }

        Assertions.assertEquals(end - start + 1, wifisTest.size());
    }
}
