<%--
  Created by IntelliJ IDEA.
  User: myung
  Date: 2023-04-24
  Time: 오전 4:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Database.DataBaseService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    request.setCharacterEncoding("UTF-8");

    if ("POST".equals(request.getMethod())) {
        String bookmarkName = request.getParameter("bookmarkGroup");
        String wifiMgrNo = request.getParameter("SWIFI_MGR_NO");

        // 데이터베이스 작업을 수행하는 코드를 작성합니다.

        DataBaseService dataBaseService = new DataBaseService();
        dataBaseService.addBookMarkWifi(bookmarkName, wifiMgrNo);

        // 처리 후 원하는 페이지로 리다이렉트합니다.
        response.sendRedirect("bookmark-list.jsp");
    }
%>
