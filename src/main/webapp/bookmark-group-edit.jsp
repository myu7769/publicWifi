<%@ page import="Database.DataBaseService" %>
<%@ page import="Database.BookMark" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  int id = Integer.parseInt(request.getParameter("id"));
  System.out.println("id = " + id);
  DataBaseService dataBaseService = new DataBaseService();
  BookMark bookMark = dataBaseService.getBookMarkById(id);
%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
      font-size: 14px;
    }

    th {
      background-color: #00AB6F;
      color: white;
      text-align: center;
    }
  </style>
  <title>북마크 수정</title>
</head>
<script>
  function submitForm(form) {
    alert("북마크 정보가 수정되었습니다.");
    form.submit();
  }
</script>
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
<a href="bookmark-group.jsp">즐겨 찾기 보기</a>
<p> | </p>
<a href="bookmark-group.jsp">즐겨 찾기 그룹 보기</a>
<br/>
<br/>
<form action="bookmark-group-edit-submit.jsp" method="post" onsubmit="event.preventDefault(); submitForm(this);">
  <table>
    <colgroups>
      <col style="width: 20%;" />
      <col style="width: 80%;" />
    </colgroups>
    <tr>
      <input type="hidden" name="id" value="<%=id%>">
      <th>북마크 이름</th>
      <td style="text-align: left"><input type="text" id="name" name="name" value="<%=bookMark.getName()%>"></td>
    </tr>
    <tr>
      <th>순서</th>
      <td style="text-align: left"><input type="text" id="sequence" name="sequence" value="<%=bookMark.getSequence()%>"></td>
    </tr>
    <tr>
      <td colspan="2"><a href="bookmark-group.jsp">돌아가기</a><p> | </p><input type="submit" value="수정"></td>
    </tr>
  </table>
</form>
</body>
</html>
