<%@ page import="Location.*" %>
<%@ page import="Database.DataBaseService" %>
<%@ page import="TbPublicWifiInfo.Wifi" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Database.Location" %>
<%@ page import="Database.BookMark" %>
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
    <title>JSP - Hello World</title>
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
<%
    String mgrNo = request.getParameter("mgrNo");
    double distance = 0.0;

    if(request != null && mgrNo != null ) {
        DataBaseService dataBaseService = new DataBaseService();
        Wifi wifi = dataBaseService.getWifiDetails(mgrNo);
        if(DataBaseService.getLat() == null || DataBaseService.getLnt() == null){
            Geocoding geocoding = new Geocoding();
            DataBaseService.setLnt(geocoding.getLocation().getLnt());
            DataBaseService.setLat(geocoding.getLocation().getLat());
        }

        Location location = new Location(DataBaseService.getLnt(),DataBaseService.getLat());
        location.setDistance(new Location(wifi.getLAT(),wifi.getLNT()));
        distance = location.getDistance();
        ArrayList<BookMark> bookMarks = dataBaseService.getAllBookMark();

%>
<br/>
<form id="bookMark" action="bookmark-add-wifi.jsp" method="post">
    <select name="bookmarkGroup">
        <option value="0">북마크 그룹 이름 선택</option>
        <%
            for(int i=0;i<bookMarks.size();i++){
                System.out.println("bookMarks = " + bookMarks.get(i).getName());

        %>
        <option value="<%=bookMarks.get(i).getName()%>"><%=bookMarks.get(i).getName()%></option>
        <%
                }
        %>
    </select>
    <input type="hidden" name="SWIFI_MGR_NO" value="<%=wifi.getX_SWIFI_MGR_NO()%>">
    <input type="submit" value="즐겨찾기 추가하기">
</form>
<br/>
<br/>
<table>
    <colgroups>
        <col style="width: 20%;" />
        <col style="width: 80%;" />
    </colgroups>
    <tbody>
    <tr>

    <tr>
    <tr>
        <tr>
        <th>거리(KM)</th>
        <td><%=String.format("%5.2f", distance * 1000)%>(m)</td>
        </tr>
        <tr>
        <th>관리번호</th>
        <td><%=wifi.getX_SWIFI_MGR_NO()%> </td>
        </tr>
        <tr>
        <th>자치구</th>
        <td><%=wifi.getX_SWIFI_WRDOFC()%> </td>
        </tr>
    <tr>
        <th>와이파이명</th>
        <td> <a href="detail.jsp?mgrNo=<%=wifi.getX_SWIFI_MGR_NO()%>"> <%=wifi.getX_SWIFI_MAIN_NM()%></a></td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td><%=wifi.getX_SWIFI_ADRES1()%> </td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td><%=wifi.getX_SWIFI_ADRES2()%> </td>
    </tr>
    <tr>
        <th>설치위치(층)</th>
        <td><%=wifi.getX_SWIFI_INSTL_FLOOR()%> </td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td><%=wifi.getX_SWIFI_INSTL_TY()%> </td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td><%=wifi.getX_SWIFI_INSTL_MBY()%> </td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td><%=wifi.getX_SWIFI_SVC_SE()%> </td>
    </tr>
    <tr>
        <th>망종류</th>
        <td><%=wifi.getX_SWIFI_CMCWR()%> </td>
    </tr>
    <tr>
        <th>설치년도</th>
        <td><%=wifi.getX_SWIFI_CNSTC_YEAR()%> </td>
    </tr>
    <tr>
        <th>실내외구분</th>
        <td><%=wifi.getX_SWIFI_INOUT_DOOR()%> </td>
    </tr>
    <tr>
        <th>WIFI접속환경</th>
        <td><%=wifi.getX_SWIFI_REMARS3()%></td>
    </tr>
    <tr>
        <th>X좌표</th>
        <td><%=wifi.getLNT()%></td>
    </tr>
    <tr>
        <th>Y좌표</th>
        <td><%=wifi.getLAT()%></td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td><%=wifi.getWORK_DTTM()%></td>
    </tr>
    <tr>
    </tr>
    <%
    }
    %>
    </tr>
    </tbody>
</table>
</body>
</html>