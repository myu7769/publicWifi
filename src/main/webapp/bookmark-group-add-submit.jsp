<%@ page import="Database.DataBaseService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    request.setCharacterEncoding("UTF-8");

    if ("POST".equals(request.getMethod())) {
        String name = request.getParameter("name");
        int sequence = Integer.parseInt(request.getParameter("sequence"));

        // 추출한 데이터를 이용하여 작업 처리
        // ...
        DataBaseService dataBaseService = new DataBaseService();
        dataBaseService.creatBookMark(name,sequence);

        response.sendRedirect("bookmark-group.jsp");
    }
%>
