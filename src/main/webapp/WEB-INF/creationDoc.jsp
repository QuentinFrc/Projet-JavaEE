<%@ page import="oracle.sql.DATE" %>
<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: Quentin
  Date: 13/03/2021
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Ajout d'un document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<script>


    $('body').ready(function(){
        init();
    });

    function inputPiste(){
        let nbPistes = $('#nbPistes').val();
        let inputs = "";
        console.log(nbPistes +" "+ inputs);
        for(let i =0; i<nbPistes; i++){
            let input =  "<label for=\"piste"+i+"\">Piste n°"+i+"</label>" +
                "<input type=\"text\" id=\"piste"+i+"\" name=\"piste"+i+"\" required>" +
                "<label for=\"dureePiste"+i+"\">Durée de la piste</label>" +
                "<input type=\"number\" id=\"dureePiste"+i+"\" name=\"dureePiste"+i+"\" required>";
            console.log(input);
            inputs = inputs+input;
        }
        $('#champsSpe').html(inputs);
    }

    function docInformation() {
        let type = $(this).val();
        console.log(type);
        let div =$("#champsDOC");
        let divSpe =$("#champsSpe");
        let script="";
        switch(type) {
            case "1": {
                script = "<label id=\"labArtiste\" for=\"artiste\">Artiste</label>" +
                    "<input type=\"text\" id=\"artiste\" name=\"artiste\">" +
                    "<label id=\"labMaisonEdi\" for=\"maisonEdi\">Maison d'édition</label>" +
                    "<input type=\"text\" id=\"maisonEdi\" name=\"maisEdition\">" +
                    "<div id=\"divPistes\">" +
                    "<label for=\"nbPistes\">Combien de pistes il y a t-il sur le CD ?</label>" +
                    "<input type=\"number\" min=\"1\" step=\"1\" max=\"20\" placeholder='20 pistes max' onchange=\"inputPiste()\" id=\"nbPistes\" name=\"nbPistes\">" +
                    "<div id=\"pistes\"></div></div>";
                break;
            }
            case "2":{
                divSpe.html("");
                script="<label id=\"labdureeDVD\" for=\"dureeDVD\">Durée du DVD</label>" +
                    "<input type=\"number\" id=\"dureeDVD\" name=\"dureeDVD\">" +
                    "<label id=\"labRealisateur\" for=\"realisateur\">Réalisateur</label>" +
                    "<input type=\"text\" id=\"realisateur\" name=\"realisateur\" >" +
                    "<label id=\"labProducteur\" for=\"producteur\">Producteur</label>" +
                    "<input type=\"text\" id=\"producteur\" name=\"producteur\" >";
                break;
            }

            case "3": {
                divSpe.html("");
                script = "<label id=\"labNbPages\" for=\"nbPages\">Nombre de pages</label> " +
                    "<input type=\"number\" id=\"nbPages\" name=\"nbPages\">" +
                    "<label id=\"labAuteur\" for=\"auteur\">Auteur</label>" +
                    "<input type=\"text\" id=\"auteur\" name=\"auteur\">" +
                    "<label id=\"labEdition\" for=\"edition\">Edition</label>" +
                    "<input type=\"text\" id=\"edition\" name=\"edition\" >";
                break;
            }
            default:script = "";

        }
        div.html(script);
        if(type==1) {
            $("#nbPistes").on("change", inputPiste);
            $("#nbPistes").attr('required', true);
        }
        div.each(function () {
            $(this).children("input").attr('required', true);
        })
    }

    function init(){
        let dvdTypes = $('#dvd');
        let cdTypes = $('#cd');
        let livreTypes = $('#livre');
        let blockDesc = $('#blockDesc');
        dvdTypes.click(docInformation);
        dvdTypes.attr('checked', false);
        cdTypes.click(docInformation);
        cdTypes.attr('checked', false);
        livreTypes.click(docInformation);
        livreTypes.attr('checked', false);
        $('#btn_annuler').click(history.back);

    }

</script>

<body id="body">
<h2> On crée quoi aujourd'hui <c:out value="${user.login()}"></c:out> ? </h2>
<form method="post">
    <div id="divTitre">
        <label for="titre">Titre du document</label>
        <input type="text" id="titre" name="titre" required>
    </div>
    <div id="type">
        <p>Quel type de document est-ce ?</p>
        <label for="cd">CD</label>
        <input type="radio" id="cd" name="type" value="1" required>
        <label for="dvd">DVD</label>
        <input type="radio" id="dvd" name="type" value="2" required>
        <label for="livre">Livre</label>
        <input type="radio" id="livre" name="type" value="3" required>
    </div>
    <div>
        <div id="blockDesc">
            <label for="desc">Description du document </label>
            <input type="text"maxlength="400" placeholder="(optionnel)" id="desc" name="desc">
        </div>
    </div>
    <div>
        <label for="annee">Année de sortie</label>
        <input type="number" id="annee" name="annee" minlength="4" required maxlength="4" step="1" min="1900" max="<c:out value="${currentYear}"> </c:out>">
    </div>
    <div>
        <label for="exemplaires">Nombre d'exemplaires</label>
        <input type="number" id="exemplaires" name="exemplaires" min="1" step="1" required>
    </div>
    <div id="champsDOC">

    </div>
    <div id="champsSpe">

    </div>
    <div id="btns">
        <input type="button" id="btn_annuler" name="btn_annuler" value="annuler" />
        <input type="submit" formaction="creation-servlet" name="btn_creer" value="créer"/>
    </div>
</form>
</body>
</html>
