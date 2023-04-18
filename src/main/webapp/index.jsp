<%@ page import="Location.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        p,form {
            display: inline;
        }
    </style>
    <title>JSP - Hello World</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function myLocation() {
        // 37.4832772, 126.9517056 오차가 있네
            $.ajax({
                url: "getLocation",
                type: "GET",
                dataType: "json",
                success: function(data) {
                    // alert(data.lat);
                    // alert(data.lnt);
                    // $("#LAT").text(data.lat);
                    // $("#LNT").text(data.lnt);
                    document.getElementById("LAT").value = data.lat;
                    document.getElementById("LNT").value = data.lnt;
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
<a href="hello-servlet">홈</a><p> | </p>
<a href="hello-servlet">위치 히스토리 목록</a><p> | </p>
<a href="load-wifi">Open API 와이파이 정보 가져오기</a><p> | </p>
<a href="hello-servlet">즐겨 찾기 보기</a><p> | </p>
<a href="hello-servlet">즐겨 찾기 그룹 보기</a>
<br/>
<br/>
<p>LAT: <input type="Text" id="LAT" value="<%= request.getAttribute("LAT") != null ? request.getAttribute("LAT") : "0.0" %>" size="15"> LNT: </p>
<input type="Text" id="LNT" value= "<%= request.getAttribute("LNT") != null ? request.getAttribute("LNT") : "0.0" %>" size="15">
<button type="button" onclick="myLocation()"> 내 위치 가져오기</button>
<%--<form action="gApiButton" method="get">--%>
<%--    <label for="LAT"></label>--%>
<%--    <input type="text" name="LAT" required>--%>
<%--    <label for="LNT"></label>--%>
<%--    <input type="text" name="LNT" required>--%>
<%--    <input type="submit" value="근처 WIFI 정보 보기">--%>
<%--</form>--%>
<%--<button type="button" onclick="myFunction()">근처 WIFI 정보 보기</button>--%>
</body>
</html>