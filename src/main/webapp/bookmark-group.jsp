<%@ page import="Location.*" %>
<%@ page import="TbPublicWifiInfo.Wifi" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Database.*" %>
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
            text-align: center;
            font-size: 11px;
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
    ArrayList<BookMark> bookMarkList;
    bookMarkList = dataBaseService.getAllBookMark();
%>
<input type="button" onclick="location.href = 'bookmark-group-add.jsp'" value="북마크 그룹 이름 추가">
    <table>
        <thead>
            <tr>
            <th>ID</th>
            <th>북마크 이름</th>
            <th>순서</th>
            <th>등록일자</th>
            <th>수정일자</th>
            <th>비고</th>
            </tr>
        </thead>
        <tbody>
        <tr>
            <%
            if(bookMarkList != null && bookMarkList.size() != 0){
                for(BookMark bookMark : bookMarkList) {
                    int bookMarkId = bookMark.getId();
            %>
                <tr id="<%=bookMarkId%>">
                <td><%=bookMarkId%></td>
                <td><%=bookMark.getName()%></td>
                <td><%=bookMark.getSequence()%></td>
                <td><%=bookMark.getRegisterTime()%></td>
                <td><%=bookMark.getAlterTime() != null ? bookMark.getAlterTime() : "" %></td>
                <td><a href="bookmark-group-edit.jsp?id=<%=bookMarkId%>">수정</a> <a href="bookmark-group-delete.jsp?id=<%=bookMarkId%>">삭제</a></td>
            </tr>
            <%
                }
            }else {
            %>
        <td colspan="6"><br/>정보가 존재하지 않습니다.<br/><br/></td>
        <%
            }
        %>
        </tr>
        </tbody>
    </table>
</body>
</html>