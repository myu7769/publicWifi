<%--
  Created by IntelliJ IDEA.
  User: myung
  Date: 2023-04-24
  Time: 오전 6:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Location.*" %>
<%@ page import="TbPublicWifiInfo.Wifi" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Database.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <style>
    p, form {
      display: inline;
    }
    table {
      border: 1px solid #ddd;
      border-collapse: collapse;
      text-align: center;
      width: 100%;
    }
    th, td {
      border: 1px solid #ddd;
      padding: 10px 20px;
      font-size: 11px;
      text-align: left;
    }

    th {
      background-color: #00AB6F;
      color: white;
    }
  </style>
  <title>JSP - Hello World</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
  </script>
</head>
<body>
<script>
  function submitForm(form) {
    form.submit();
  }
</script>
<h2><%= "와이파이 정보 구하기" %>
</h2>
<br/>
<a href="/">홈</a>
<p> | </p>
<a href="history.jsp">위치 히스토리 목록</a>
<p> | </p>
<a href="load-wifi">Open API 와이파이 정보 가져오기</a>
<p> | </p>
<a href="bookmark-list.jsp">즐겨 찾기 보기</a>
<p> | </p>
<a href="bookmark-group.jsp">즐겨 찾기 그룹 보기</a>
<br/>
<%
  String id = request.getParameter("id");
  double distance = 0.0;

  if(request != null && id != null ) {
    DataBaseService dataBaseService = new DataBaseService();
    BookMarkWikis bookMarkWikis = dataBaseService.getBookMarkWifisById(id); // id

%>
<br/>
<br/>
<br/>
<form action="bookmark-delete-submit.jsp" method="post" onsubmit="event.preventDefault(); submitForm(this);">
<table>
  <colgroups>
    <col style="width: 20%;" />
    <col style="width: 80%;" />
  </colgroups>
  <tbody>
  <tr>
  <tr>
    <th>북마크 이름</th>
    <td><%=bookMarkWikis.getBookMarkName()%></td>
  </tr>
  <tr>
    <th>와이파이명</th>
    <td><%=bookMarkWikis.getSWIFI_MAIN_NM()%> </td>
  </tr>
  <tr>
    <th>등록일자</th>
    <td><%=bookMarkWikis.getRegisterTime()%> </td>
  </tr>
  <tr>
    <input type="hidden" id="id" name="id" value="<%=bookMarkWikis.getId()%>">
    <td style="text-align: center" colspan="2"><a href="bookmark-list.jsp">돌아가기</a><p> | </p><input type="submit" value="삭제"></td>
  </tr>
  <%
    }else {
  %>
  <td colspan="6"><br/>정보가 존재하지 않습니다.<br/><br/></td>
  <%
    }
  %>
  </tr>
  </tbody>
</table>
</form>
</body>
</html>
