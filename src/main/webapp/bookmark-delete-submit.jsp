<%@ page import="Database.DataBaseService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    if ("POST".equals(request.getMethod())) {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id = " + id);

        // 추출한 데이터를 이용하여 작업 처리
        // ...
        DataBaseService dataBaseService = new DataBaseService();
        dataBaseService.deleteBookMarkWifiS(id);

    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>북마크 삭제</title>
    <script>
        alert("북마크가 삭제되었습니다.");
        window.location.href = "bookmark-list.jsp";
    </script>
</head>
<body>
</body>
</html>