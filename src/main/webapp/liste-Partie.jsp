<%-- 
    Document   : liste-Partie
    Created on : 13 juil. 2018, 11:47:17
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <link >
        <meta charset="UTF-8">
        <meta http-equiv="refresh" content="10" />
        <title> Liste Des Parties </title>
        <link href="styleListePartie.css" type="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Galada" rel="stylesheet">
    </head>

    <body >

        <div  style="text-align: center; ">
            <!-- <div style="width: 10%; display: inline-block;background-color: red;">
            </div> -->

            <div    style="width: 79%; display: inline-block;" >
                <div class = "mainBox arrondi  imgBackDiv">
                    <div class="fullLarge " >

                    </div>

                    <div class = "fullLarge " >
                        <div class="large5 avatar libelle">
                            
                        </div>
                        <div class="large60 design libelle floatRight">
                            <form method="post" action="listePartieServlet">
                                <div class="sameLine"><span> Créer une partie </span></div>
                                <div class="sameLine"><input  type="text" name="nomPartie" placeholder="Nom de la partie" required="Entrez un nom pour la partie"></div>
                                <div class="sameLine"><input type="submit" value="Créer"></div>
                            </form>

                        </div>

                    </div>
                    <div class = "fullLarge">
                        <div class="textStyle"> 
                            <h1>  Liste Des Parties </h1>
                        </div>
                        <div class = "fullLarge ">

                            <c:forEach items="${listeParNoDema}" var="parAct"> 
                                <div class = "fullLarge design arrondi listePartie">
                                    <div class="large15 libelle">

                                        <span> ${parAct.nom} </span>

                                    </div>

                                    <div class="large15 libelle"> 
                                        <span>${parAct.joueurs.size()} Joueurs</span> 
                                    </div>
                                    <div class="large20 floatRight">

                                        <a class="inputA" href="<c:url value="/LoginServlet"/>?idPartie=${parAct.id}">  
                                            <input type="button" value="Rejoindre"  >
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>   

                        </div>
                        <div class = "fullLarge" >
                            <br/><br/><br/><br/><br/><br/><br/><br/>

                        </div>

                    </div>
                </div>
            </div>

    </body>


</html>