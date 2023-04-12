package TbPublicWifiInfo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.api.scripting.JSObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.ArrayList;

public class WifiGetInfoOkhttp {

    static String key = "/684252656c6d7975353070676d6f63";
    static String dataType = "/json";
    static String serviceName = "/TbPublicWifiInfo";
    public ArrayList<Wifi> getUserInfo() {
        try {
            String url = "http://openapi.seoul.go.kr:8088" + key + dataType + serviceName + "/1/3/관악구/봉천로";
//            String url = "http://openapi.seoul.go.kr:8088/sample/json/TbPublicWifiInfo/1/3/서대문구/서소문로";
            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder().url(url).get();
            Request request = builder.build();
            ArrayList<Wifi> wifis = new ArrayList<>();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    String obj = body.string();
//                    System.out.println(obj);
                    JsonParser jsonParser = new JsonParser();
                    JsonObject object = (JsonObject) jsonParser.parse(obj);
                    object = (JsonObject)object.get("TbPublicWifiInfo");
                    JsonArray array = (JsonArray)object.get("row");
                    Gson gson = new Gson();
                    for(Object arr : array){
//                        System.out.println(arr.toString());
                        wifis.add(gson.fromJson(arr.toString(), Wifi.class));
                    }

//                    Wifi wifi = gson.fromJson(Json, Wifi.class);
//                    wifi.toString();
                }
            }
            else
                System.err.println("Error Occurred");

            return wifis;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
