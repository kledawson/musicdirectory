<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Artist Management Application</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>

    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
            <div>
                <h1> Artist Management App </h1>
            </div>

            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Artist</a></li>
            </ul>
        </nav>
    </header>
    <br>

    <div class="row">
        <div class="container">
            <h3 class="text-center">My Songs</h3>
            <hr>
            <div class="container text-left">
                <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Song</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>
                            <!-- Toggle link for sorting by artist -->
                            <c:choose>
                                <c:when test="${param.sortOrder == 'asc'}">
                                    <a href="<%=request.getContextPath()%>/list?sortBy=artist&sortOrder=desc" class="text-decoration-none">Artist (Asc)</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="<%=request.getContextPath()%>/list?sortBy=artist&sortOrder=asc" class="text-decoration-none">Artist (Desc)</a>
                                </c:otherwise>
                            </c:choose>
                        </th>
                        <th>Album</th>
                        <th>Genre</th>
                        <th>Liked</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${listUser}">
                        <tr>
                            <td><c:out value="${user.id}" /></td>
                            <td><c:out value="${user.name}" /></td>
                            <td><c:out value="${user.artist}" /></td>
                            <td><c:out value="${user.album}" /></td>
                            <td><c:out value="${user.genre}" /></td>
                            <td>
                                <c:if test="${user.liked}">
                                    ✅
                                </c:if>
                                <c:if test="${!user.liked}">
                                    ❌
                                </c:if>
                            </td>
                            <td>
                                <a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="delete?id=<c:out value='${user.id}' />">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</body>
</html>
