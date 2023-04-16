import TbPublicWifiInfo.getController;
import org.junit.jupiter.api.Test;

public class getControllerTest {

    @Test
    public void getAllWifi(){

        getController getController = new getController();
        long beforeTime = System.currentTimeMillis();
        getController.getAllWifi();
        long afterTime = System.currentTimeMillis();

        System.out.println("시간 차이(ms) : " + (afterTime - beforeTime));



    }
}
