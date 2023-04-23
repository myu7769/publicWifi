<%--
  Created by IntelliJ IDEA.
  User: myung
  Date: 2023-04-24
  Time: 오전 5:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Database.DataBaseService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Database.User" %>
<%@ page import="Database.BookMarkWikis" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        p,form {
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
            text-align: left;
        }

        th {
            background-color: #00AB6F;
            color: white;
            text-align: center;
        }
    </style>
    <title>JSP - Hello World</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function userRemove(userid) {

            $.ajax({
                url: "userRemove.jsp",
                type: "GET",
                dataType: "json",
                data : {
                    id : userid
                },
                success: function(data) {
                    alert("success" + userid);
                    $('#' + data.id).remove();
                },
                error: function(xhr, status, error) {
                    console.log("Error: " + error);
                }
            });
        }
    </script>
</head>
<body>
<h2><%= "와이파이 정보 구하기" %></h2>
<br/>
<a href="/">홈</a><p> | </p>
<a href="history.jsp">위치 히스토리 목록</a><p> | </p>
<a href="load-wifi">Open API 와이파이 정보 가져오기</a><p> | </p>
<a href="bookmark-list.jsp">즐겨 찾기 보기</a>
<p> | </p>
<a href="bookmark-group.jsp">즐겨 찾기 그룹 보기</a>
<br/>
<br/>
<%
    DataBaseService dataBaseService = new DataBaseService();
    ArrayList<BookMarkWikis> bookMarkWikisArrayList = dataBaseService.getAllBookMarkWifiS();

%>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <tr>
            <%
                for(BookMarkWikis bookMarkWikis : bookMarkWikisArrayList) {
                    int bookMarkWikisId = bookMarkWikis.getId();
            %>
    <tr id="<%=bookMarkWikisId%>">
        <td><%=bookMarkWikisId%></td>
        <td><%=bookMarkWikis.getBookMarkName()%></td>
        <td><%=bookMarkWikis.getSWIFI_MAIN_NM()%></td>
        <td><%=bookMarkWikis.getRegisterTime()%></td>
        <td style="text-align: center"><a href="bookmark-delete.jsp?id=<%=bookMarkWikisId%>">삭제</a></td>
    </tr>
    <%
        }
    %>
    </tr>
    </tbody>
</table>
</body>
</html>