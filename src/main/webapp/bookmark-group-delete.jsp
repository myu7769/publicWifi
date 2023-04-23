<%--
  Created by IntelliJ IDEA.
  User: myung
  Date: 2023-04-24
  Time: 오전 2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Database.DataBaseService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  int id = Integer.parseInt(request.getParameter("id"));

  DataBaseService dataBaseService = new DataBaseService();
  dataBaseService.deleteBookMark(id);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>북마크 삭제</title>
  <script>
    alert("북마크가 삭제되었습니다.");
    window.location.href = "bookmark-group.jsp";
  </script>
</head>
<body>
</body>
</html>