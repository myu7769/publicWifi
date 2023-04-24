<%@ page import="Location.*" %>
<%@ page import="Database.DataBaseService" %>
<%@ page import="Database.DisLocation" %>
<%@ page import="TbPublicWifiInfo.Wifi" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Database.Location" %>
<%@ page import="Database.User" %>
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
    <title>Wifi 정보 구하기</title>
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
                    alert("삭제되었습니다.");
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
    ArrayList<User> userList = new ArrayList<>();
    userList = dataBaseService.getUserHistory();
%>
    <table>
        <thead>
            <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
            </tr>
        </thead>
        <tbody>
        <tr>
            <%
                for(User user : userList) {
                    String userId = user.getId();
            %>
                <tr id="<%=userId%>">
                <td><%=userId%></td>
                <td><%=user.getLocation().getLat()%></td>
                <td><%=user.getLocation().getLnt()%></td>
                <td><%=user.getCheckTime()%></td>
                <td style="text-align: center"><button type="button" onclick="userRemove(<%=userId%>)"> 삭제</button></td>
            </tr>
            <%
                }
            %>
        </tr>
        </tbody>
    </table>
</body>
</html>