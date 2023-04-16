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
    <script>
        function myLocation() {
            <%



            %>

            document.getElementById("myText").innerHTML = "새로운 텍스트";
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
<p>LAT: <input type="Text" name="LAT" value="0.0"  size="15"> LNT: </p> <input type="Text" name="LNT:" value="0.0" size="15">
<button type="button" onclick="myLocation()">내 위치 가져오기</button>
<button type="button" onclick="myFunction()">근처 WIFI 정보 보기</button>
</body>
</html>