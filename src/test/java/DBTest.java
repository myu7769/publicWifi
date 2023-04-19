import Database.DataBaseService;
import Database.Location;
import TbPublicWifiInfo.Wifi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DBTest {

    @Test
    public void dbSelect(){
        ArrayList<Wifi> wifiArrayList ;
        Location location = new Location("12.000","123.00");
        DataBaseService dataBaseService = new DataBaseService();
        wifiArrayList = dataBaseService.getWifiList();
        System.out.println("wifiArrayList = " + wifiArrayList.size());


    }
}
