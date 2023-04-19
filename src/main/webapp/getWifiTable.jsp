<%@ page import="Location.*" %>
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
        }

        th {
            background-color: #00AB6F;
            color: white;
        }
    </style>
    <title>JSP - Hello World</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function myLocation() {
            $.ajax({
                url: "getLocation.jsp",
                type: "GET",
                dataType: "json",
                success: function(data) {
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
<form id="getWifiTable" action="index.jsp" method="get">
<p>LAT: <input type="Text" id="LAT" name="LAT" value="<%= request.getAttribute("LAT") != null ? request.getAttribute("LAT") : "0.0" %>" size="15"> LNT: </p>
<input type="Text" id="LNT" name="LNT" value= "<%= request.getAttribute("LNT") != null ? request.getAttribute("LNT") : "0.0" %>" size="15">
<button type="button" onclick="myLocation()"> 내 위치 가져오기</button>
<input type="submit" value="근처 WIFI 정보 보기">
</form>
<br/>
<br/>
<%--<button type="button" onclick="myFunction()">근처 WIFI 정보 보기</button>--%>
    <table>
        <thead>
            <tr>
            <th>거리(KM)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="17"><br/>위치 정보 입력 후 조회해주세요.<br/><br/></td>
            </tr>
        </tbody>
    </table>
</body>
</html>