<%@ page import="Database.DataBaseService" %>
<%@ page import="Database.DisLocation" %>
<%@ page import="TbPublicWifiInfo.Wifi" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Database.Location" %>
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
        }

        th {
            background-color: #00AB6F;
            color: white;
        }
    </style>
    <title>Wifi 정보 구하기</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function myLocation() {
            $.ajax({
                url: "getLocation.jsp",
                type: "GET",
                dataType: "json",
                success: function (data) {
                    document.getElementById("LAT").value = data.lat;
                    document.getElementById("LNT").value = data.lnt;
                },
                error: function (xhr, status, error) {
                    console.log("Error: " + error);
                }
            });
        }
    </script>
</head>
<body>
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
<br/>
<form id="getWifiTable" action="<%= request.getRequestURI() %>" method="get">
    <p>LAT: <input type="Text" id="LAT" name="LAT"
                   value="<%= request.getParameter("LAT") != null ? request.getParameter("LAT") : "0.0" %>" size="15">
        LNT: </p>
    <input type="Text" id="LNT" name="LNT"
           value="<%= request.getParameter("LNT") != null ? request.getParameter("LNT") : "0.0" %>" size="15">
    <button type="button" onclick="myLocation()"> 내 위치 가져오기</button>
    <input type="submit" value="근처 WIFI 정보 보기">
</form>
<br/>
<br/>
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

            <%
                    String LAT = request.getParameter("LNT");
                    String LNT = request.getParameter("LAT");

                    if(request != null && LAT != null && LNT != null) {
                        DataBaseService dataBaseService = new DataBaseService();
                        DataBaseService.setLat(LNT);
                        DataBaseService.setLnt(LAT);
                        dataBaseService.saveUserLocation();
                        ArrayList<Wifi> wifis = dataBaseService.getWifiList();
                        DisLocation[] disLocation = dataBaseService.getNearWifi(wifis, new Location(LAT,LNT));
                        System.out.println(disLocation[0].getDistance() * 1000 + "(m)");

                        for (int i = 0; i < disLocation.length; i++) {
                %>
    <tr>
        <td><%=String.format("%5.2f", disLocation[i].getDistance() * 1000)%>(m)</td>
        <td><%=disLocation[i].getWifi().getX_SWIFI_MGR_NO()%>
        </td>
        <td><%=disLocation[i].getWifi().getX_SWIFI_WRDOFC()%>
        </td>
        <td>
            <a href="detail.jsp?mgrNo=<%=disLocation[i].getWifi().getX_SWIFI_MGR_NO()%>">
                <%=disLocation[i].getWifi().getX_SWIFI_MAIN_NM()%>
            </a>
        </td>
        <td><%=disLocation[i].getWifi().getX_SWIFI_ADRES1()%>
        </td>
        <td><%=disLocation[i].getWifi().getX_SWIFI_ADRES2()%>
        </td>
        <td><%=disLocation[i].getWifi().getX_SWIFI_INSTL_FLOOR()%>
        </td>
        <td><%=disLocation[i].getWifi().getX_SWIFI_INSTL_TY()%>
        </td>
        <td><%=disLocation[i].getWifi().getX_SWIFI_INSTL_MBY()%>
        </td>
        <td><%=disLocation[i].getWifi().getX_SWIFI_SVC_SE()%>
        </td>
        <td><%=disLocation[i].getWifi().getX_SWIFI_CMCWR()%>
        </td>
        <td><%=disLocation[i].getWifi().getX_SWIFI_CNSTC_YEAR()%>
        </td>
        <td><%=disLocation[i].getWifi().getX_SWIFI_INOUT_DOOR()%>
        </td>
        <td><%=disLocation[i].getWifi().getX_SWIFI_REMARS3()%>
        </td>
        <td><%=disLocation[i].getWifi().getLNT()%>
        </td>
        <td><%=disLocation[i].getWifi().getLAT()%>
        </td>
        <td><%=disLocation[i].getWifi().getWORK_DTTM()%>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <td colspan="17"><br/>위치 정보 입력 후 조회해주세요.<br/><br/></td>
    <%
        }
    %>
    </tr>
    </tbody>
</table>
</body>
</html>