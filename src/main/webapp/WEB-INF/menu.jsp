<%--
  Created by IntelliJ IDEA.
  User: Quentin
  Date: 03/03/2021
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Menu </title>
</head>
<body>


<h2>Bienvenue <c:out value="${ user.login() }"></c:out> ! </h2>
<form method="post">
    <p class="notification"><c:out value="${msg}"></c:out></p>
<p id="message">Quelle est la raison de votre venu aujourd'hui ?</p>

<input formaction="initCreation-servlet" type="submit" value="Ajouter un nouveau document">
<input formaction="initSuppress-servlet" type="submit" value="Supprimer un document">
</form>
</body>
</html>
