<%--
  Created by IntelliJ IDEA.
  User: myung
  Date: 2023-04-16
  Time: 오후 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Location.*" %>
<%@ page import="Database.DataBaseService" %>
<%@ page import="Database.Location" %>

<%
  String id = request.getParameter("id");
  DataBaseService dataBaseService = new DataBaseService();
  if(dataBaseService.deleteUser(id)) {

    String jsonData = "{\"id\": \"" + id + "\"}";
    response.setContentType("application/json; charset=UTF-8");
    response.getWriter().print(jsonData);
  }
%>
