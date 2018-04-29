<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th></th>
        <th>操作</th>
        <th>音乐标题</th>
        <th>歌手</th>
        <th>专辑</th>
        <th>热度</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="songList" items="${requestScope.get('songList')}" varStatus="status">
    <tr>
        <td>${songList.songId}</td>
        <td>${songList.songUrl}</td>
        <td>${songList.songName}</td>
        <td>${songList.singer}</td>
        <td>${songList.album}</td>
        <td>${songList.popular}</td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
