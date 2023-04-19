package Database;

import TbPublicWifiInfo.Wifi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import Location.*;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseServiceTest {

    @Test
    void getNearWifi() {
        DataBaseService dataBaseService = new DataBaseService();
        ArrayList<Wifi> list = dataBaseService.getWifiList();
//        Location location = new Location("37.483276","126.951712");
        Geocoding geocoding = new Geocoding();
        Location lc = geocoding.getLocation();

        System.out.println("lc.getLnt() = " + lc.getLnt());
        System.out.println("lc.getLat() = " + lc.getLat());
        
        DisLocation[] disLocations= dataBaseService.getNearWifi(list,lc);

        for (int i = 0; i < disLocations.length; i++) {
            System.out.println(disLocations[i].wifi.toString() + "\nDistances " +disLocations[i].getDistance() + "\n");
            System.out.println("i + 1 = " + i + 1);
        }

    }
}