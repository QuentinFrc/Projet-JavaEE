<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Quentin
  Date: 13/03/2021
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Suppression d'un document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<script>
    $(document).ready(function (){
      init();
    })

    function init(){
        let btn = $('.SuppBtn');
        $('#docSuppr').css('visibility', 'hidden');
        btn.click(suppr);
    }
    function suppr(){
        let id = $(this).attr('id');
        let doc = id.match(/\d+/);
        $('#idDoc').attr('value', doc);
        $('#idDoc').text(id);
        $('#docSuppr').css('visibility', 'visible');
    }




</script>
<body>
<h2> Voici les documents de la base de donnée <c:out value="${user.login()}"></c:out>  </h2>
<div id="documents">
    <form id="docSuppr" action="suppression-servlet" onsubmit="return confirm('Etes vous sur de vouloir supprimer le document ?')" method="get">
        <h2 id="confirmSuppr"> Delete </h2>
        <button name="idDoc" id="idDoc"></button>
    </form>
    <!-- CDs -->
    <table id="CDs">
    <c:forEach items="${CDs}" var="doc" varStatus="status">

        <tr>
            <!-- Champs Communs -->
            <td>
                CD <c:out value="${doc.data()[0]}"></c:out>
            </td>
            <td>
                <c:out value="${doc.data()[1]}"></c:out>
            </td>
            <td>
                <c:out value="${doc.data()[2]}"></c:out>
            </td>
            <td>
                <c:out value="${doc.data()[3]}"></c:out>
            </td>
            <td>
                <c:out value="${doc.data()[4]}"></c:out>
            </td>
            <!-- Champs Spécifique -->
            <td>
                <c:out value="${doc.data()[5]}"></c:out> NbPistes
            </td>
            <td>
                <c:out value="${doc.data()[6]}"></c:out> Durée
            </td>
            <td>
                <c:out value="${doc.data()[7]}"></c:out> artiste
            </td>
            <td>
                <c:out value="${doc.data()[8]}"></c:out> maison edition
            </td>
            <td>
                <div class="pistes">
                <c:forEach items="${doc.data()[9]}" var="piste" varStatus="num">
                    <div class="piste">
                    <c:out value="${num.count}"></c:out>
                    <c:out value="${piste.data()[0]}"></c:out>
                    <c:out value="${piste.data()[1]}"></c:out>
                    </div>
                </c:forEach>
                </div>
            </td>
            <td>
                <input type="button" value="Ce document" id='CD ${doc.data()[0]}' class="SuppBtn">
            </td>
        </tr>

    </c:forEach>
    </table>

    <!-- DVDs -->
        <table id="DVDs">
            <c:forEach items="${DVDs}" var="doc" varStatus="status">
                <tr>
                    <!-- Champs Communs -->
                    <td>
                        DVD <c:out value="${doc.data()[0]}"></c:out>
                    </td>
                    <td>
                        <c:out value="${doc.data()[1]}"></c:out>
                    </td>
                    <td>
                        <c:out value="${doc.data()[2]}"></c:out>
                    </td>
                    <td>
                        <c:out value="${doc.data()[3]}"></c:out>
                    </td>
                    <td>
                        <c:out value="${doc.data()[4]}"></c:out>
                    </td>
                    <!-- Champs Spécifique -->
                    <td>
                        <c:out value="${doc.data()[5]}"></c:out> Durée
                    </td>
                    <td>
                        <c:out value="${doc.data()[6]}"></c:out> realisateur
                    </td>
                    <td>
                        <c:out value="${doc.data()[7]}"></c:out> producteur
                    </td>
                    <td>
                        <input type="button" value="Ce document" id="DVD ${doc.data()[0]}" class="SuppBtn">
                    </td>
                </tr>
            </c:forEach>
        </table>

    <!-- LIVRES -->
    <div>
        <table id="Livres">
            <c:forEach items="${Livres}" var="doc" varStatus="status">

                <tr>
                    <!-- Champs Communs -->
                    <td>
                        Livre <c:out value="${doc.data()[0]}"></c:out>
                    </td>
                    <td>
                        <c:out value="${doc.data()[1]}"></c:out>
                    </td>
                    <td>
                        <c:out value="${doc.data()[2]}"></c:out>
                    </td>
                    <td>
                        <c:out value="${doc.data()[3]}"></c:out>
                    </td>
                    <td>
                        <c:out value="${doc.data()[4]}"></c:out>
                    </td>
                    <!-- Champs Spécifique -->
                    <td>
                        <c:out value="${doc.data()[5]}"></c:out> NbPages
                    </td>
                    <td>
                        <c:out value="${doc.data()[6]}"></c:out> auteur
                    </td>
                    <td>
                        <c:out value="${doc.data()[7]}"></c:out> edition
                    </td>
                    <td>
                        <input type="button" value="Ce document" id='LIVRE ${doc.data()[0]}' class="SuppBtn">
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>

</body>
</html>
