package TbPublicWifiInfo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.ArrayList;

public class WifiGetInfoOkhttp {

    static String key = "/684252656c6d7975353070676d6f63";
    static String dataType = "/json";
    static String serviceName = "/TbPublicWifiInfo";
    public ArrayList<Wifi> getWifiInfo(int start, int end, String address) {

        if(start < 0 || start > end || address == null){ // 한번에 요청량 1000이 넘어가면 response 에러남
            return null;
        }

        StringBuilder urlSb = new StringBuilder("http://openapi.seoul.go.kr:8088" + key + dataType + serviceName +"/");
        urlSb.append(start);
        urlSb.append("/");
        urlSb.append(end);
        urlSb.append("/");
        urlSb.append(address);
        String url = urlSb.toString();

        System.out.println("url = " + url);

        try {
            // String url = "http://openapi.seoul.go.kr:8088/sample/json/TbPublicWifiInfo/1/3/서대문구/서소문로";
            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder().url(url).get();
            Request request = builder.build();
            ArrayList<Wifi> wifis = new ArrayList<>();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    String obj = body.string();
                    JsonParser jsonParser = new JsonParser();
                    JsonObject object = (JsonObject) jsonParser.parse(obj);
                    object = (JsonObject)object.get("TbPublicWifiInfo");
                    if(object == null){
                        return null;
                    }
                    JsonArray array = (JsonArray)object.get("row");
                    Gson gson = new Gson();
                    for(Object arr : array){
                        wifis.add(gson.fromJson(arr.toString(), Wifi.class));
                    }
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
