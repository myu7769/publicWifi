import TbPublicWifiInfo.getController;
import TbPublicWifiInfo.getControllersub;
import org.junit.jupiter.api.Test;

public class getControllersubTest {

    @Test
    public void getAllWifi(){

        getControllersub getControllersub = new getControllersub();
        long beforeTime = System.currentTimeMillis();
        getControllersub.getAllWifi();
        long afterTime = System.currentTimeMillis();

        System.out.println("시간 차이(ms) : " + (afterTime - beforeTime));

    }
}
