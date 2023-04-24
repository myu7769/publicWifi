<%@ page import="Location.*" %>
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
        document.addEventListener("DOMContentLoaded", function () {
            const form = document.getElementById("getWifiTable");

            form.addEventListener("submit", function (event) {
                event.preventDefault();
                groupAdd();
            });
        });

        function groupAdd() {
            const name = document.getElementById("name").value;
            const sequence = document.getElementById("sequence").value;

            fetch("bookmark-group-add-submit.jsp", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: new URLSearchParams({ name: name, sequence: sequence })
            })
                .then(response => {
                    if (response.ok) {
                        alert("북마크 그룹 정보를 추가하였습니다.");
                        location.href = "bookmark-group.jsp";
                    } else {
                        throw new Error("서버에서 문제가 발생했습니다.");
                    }
                })
                .catch(error => console.error("Error:", error));
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
<form accept-charset="UTF-8" id="getWifiTable" method="post">
    <table>
        <colgroups>
            <col style="width: 20%;" />
            <col style="width: 80%;" />
        </colgroups>
        <tr>
            <th>북마크 이름</th>
            <td style="text-align: left"><input type="Text" id="name" name="name" size="15"></td>
        </tr>
        <tr>
            <th>순서</th>
            <td style="text-align: left"><input type="Text" id="sequence" name="sequence" size="15"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value=" 추가 "></td>
        </tr>
    </table>
</form>
</body>
</html>