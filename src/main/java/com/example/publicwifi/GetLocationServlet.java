package com.example.publicwifi;
import Location.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "getLocationServlet", value = "/getLocation")
public class GetLocationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetLocationServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 사용자의 위치 정보를 가져오는 로직
//        double lat = 37.1234;
//        double lnt = 127.5678;

        Geocoding geocoding = new Geocoding();
        Location location = geocoding.getLocation();
        String lat = location.getLat();
        String lnt = location.getLnt();

        // 응답 데이터 생성
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("lat", lat);
        responseData.put("lnt", lnt);



        // JSON 형식으로 응답
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(responseData));
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
