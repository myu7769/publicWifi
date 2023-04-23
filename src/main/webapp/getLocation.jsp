<%--
  Created by IntelliJ IDEA.
  User: myung
  Date: 2023-04-16
  Time: 오후 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Location.*" %>
<%@ page import="Database.Location" %>

<%
  Geocoding geocoding = new Geocoding();
  Location location = geocoding.getLocation();
  String lat = location.getLat();
  String lnt = location.getLnt();

  String jsonData = "{\"lat\": \"" + lat + "\", \"lnt\": \"" + lnt + "\"}";
  response.setContentType("application/json; charset=UTF-8");
  response.getWriter().print(jsonData);
%>
