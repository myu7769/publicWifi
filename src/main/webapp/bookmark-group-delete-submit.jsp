<%@ page import="Database.DataBaseService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  if ("POST".equals(request.getMethod())) {

    request.setCharacterEncoding("UTF-8");

    int id = Integer.parseInt(request.getParameter("id"));

    DataBaseService dataBaseService = new DataBaseService();
    dataBaseService.deleteBookMark(id);

    response.sendRedirect("bookmark-group.jsp");
  }
%>
