<%-- 
    Document   : lister-joueur
    Created on : 17 juil. 2018, 11:45:41
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta http-equiv="refresh" content="10" />
        <link href="styleListePartie.css" val="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Galada" rel="stylesheet">
        <title> Liste Joueurs </title>
    </head>
    <body>
        <div  style="text-align: center; ">

            <div    style="width: 79%; display: inline-block;" >
                <div class = "mainBox arrondi  imgBackDiv">

                    <div class="fullLarge " >

                    </div>


                    <div class = "fullLarge design">
                        
                        <div class="textStyle"> 
                            <h1>Liste Des joueurs de la partie </h1>
                        </div>
                        
                        <div class = "fullLarge ">
                            <c:forEach items="${partie.getJoueurs()}" var="jr">  
                                <div class = "fullLarge design arrondi listePartie">
                                    <div class="large15 libelle">  
                                        <span> ${jr.pseudo}</span>
                                    </div>
                                    
<!--                                    <div class="large15 libelle">
                                        <span> {jr.cartes.size()} Cartes</span>   
                                    </div>    -->
                                </div>
                            </c:forEach>  
                        </div>
                        <!--?idPartie={jr.getPartie().id}-->
                        <div>
                            <a href="<c:url value="/JouerPartie"/>">
                            <input class="btnSubmit btnSubREjoindrePartie" type="button" value="Démarrer" ${partie.getJoueurs().size()>1 ? "":"hidden"} />
                            </a>
                        </div>
                        <div class = "fullLarge" >
                            <br/><br/><br/><br/><br/><br/><br/><br/>

                        </div>

                    </div>
                </div>


            </div>

    </body>
</html>
