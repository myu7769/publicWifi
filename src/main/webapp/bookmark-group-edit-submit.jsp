<%@ page import="Database.DataBaseService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  if ("POST".equals(request.getMethod())) {

    request.setCharacterEncoding("UTF-8");

    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    int sequence = Integer.parseInt(request.getParameter("sequence"));

    DataBaseService dataBaseService = new DataBaseService();
    dataBaseService.updateBookMark(id, name, sequence);

    response.sendRedirect("bookmark-group.jsp");
  }
%>
