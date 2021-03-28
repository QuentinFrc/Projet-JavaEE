<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Service Connexion</title>
</head>
<body>
<h1><%= "Bienvenue sur le service de connexion de la bibliothèque !" %>
</h1>
<p><c:out value="${ msg }"/></p>
<br/>
<form method="post" action="connexion-servlet">

    <label for="login">Votre identifiant :</label><br>
    <input type="text" id="login" placeholder="username" name="login"/><br>
    <label for="password">Votre mot de passe :</label><br>
    <input type="password" placeholder="password" id="password" name="password"/><br>
    <button type="submit">Envoyer</button><br>

</form>
</body>
</html>