package com.example.publicwifi;

import TbPublicWifiInfo.getController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loadWifiServlet", value = "/load-wifi")  //loadOnStartup = 1
//

public class loadWifi extends HttpServlet {
    private String message;

    public void init() {
        getController getController = new getController();
        int wifiAllCnt = getController.getAllWifi();
        message = wifiAllCnt +"";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.print("<html><head><title>Query 문자열 테스트</title></head>");
        out.println("<body>");
        out.println("<h1>" + message + "개의 Wifi 정보를 정상적으로 저장하였습니다. </h1>");
        out.println("</body></html>");
        out.println("<a href=\"index.jsp\">홈 으로 가기</a>");
        out.close();
    }

    public void destroy() {
    }
}