<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<h1> User Management App </h1>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${user != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${user == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${user != null}">
                                    Edit User
                                </c:if>
                                <c:if test="${user == null}">
                                    Add New User
                                </c:if>
                            </h2>
                        </caption>

                        
				<c:if test="${user != null}">
    				<input type="hidden" name="id" value="${user.id}" />
				</c:if>

			    <fieldset class="form-group">
			        <label>Song Name</label>
			        <input type="text" value="<c:out value='${user.name}' />" class="form-control" name="name" required="required">
			    </fieldset>
			
			    <fieldset class="form-group">
			        <label>Artist</label>
			        <input type="text" value="<c:out value='${user.artist}' />" class="form-control" name="artist">
			    </fieldset>
			
			    <fieldset class="form-group">
			        <label>Genre</label>
			        <input type="text" value="<c:out value='${user.genre}' />" class="form-control" name="genre">
			    </fieldset>
			
			    <fieldset class="form-group">
			        <label>Album</label>
			        <input type="text" value="<c:out value='${user.album}' />" class="form-control" name="album">
			    </fieldset>
			
			    <fieldset class="form-group">
			        <label for="liked">Liked</label>
			        <br>
			        <input type="checkbox" id="liked" name="liked" class="form-check-input" <c:if test="${user != null && user.liked}">checked</c:if> />
			    </fieldset>
			
			    <button type="submit" class="btn btn-success">Save</button>
			</form>

        </div>
    </div>
</div>


</body>
</html>