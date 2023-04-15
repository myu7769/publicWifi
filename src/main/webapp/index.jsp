<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        p {
            display: inline;
        }
    </style>
    <title>JSP - Hello World</title>
</head>
<body>
<h2><%= "와이파이 정보 구하기" %></h2>
<br/>
<a href="hello-servlet">홈</a><p> | </p>
<a href="hello-servlet">위치 히스토리 목록</a><p> | </p>
<a href="hello-servlet">Open API 와이파이 정보 가져오기</a><p> | </p>
<a href="hello-servlet">즐겨 찾기 보기</a><p> | </p>
<a href="hello-servlet">즐겨 찾기 그룹 보기</a>
<br/>
<br/>
<p>LAT: </p><input type="Text" name="LAT" size="15"><p> , LNT: </p><input type="Text" name="LNT:" size="15">
<button type="button" onclick="myFunction()">내 위치 가져오기</button>
<button type="button" onclick="myFunction()">근처 WIFI 정보 보기</button>
</body>
</html>