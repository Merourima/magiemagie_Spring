<%-- 
    Document   : jouer-Partie
    Created on : 13 juil. 2018, 12:13:08
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
         <meta http-equiv="refresh" content="10" />
        <link href="styleListePartie.css" val="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Galada" rel="stylesheet">
	<title></title>
</head>
 <body>
        <div  style="text-align: center; ">

            <div    style="width: 79%; display: inline-block;" >
                <div > 
                    <!--class = ""mainBox arrondi  imgBackDiv-->

                    <div class="fullLarge " >

                    </div>


                    <div class = "fullLarge design">
                        
                        <div class = "fullLarge ">
                            <c:forEach items="${partie.getJoueurs()}" var="jrPartie">
                                <c:if test="${jrPartie.id != moi.id}" >
                                <div class=" avatar joueurBox">  
                                    <table>
                                        <tr>
                                            <td><img  src="image/avatar/${jrPartie.avatar}.jpeg" alt="Avatar"></td>
                                        </tr>
                                        <tr>
                                            <td class="libelle"><span>${jrPartie.pseudo}</label></td>
                                        </tr>
                                        <tr>
                                            <td  class="libelle"><span>${jrPartie.getCartes().size()} Cartes</span></td>
                                        </tr>
                                         <tr>
                                            <td class="libelle"><span>${jrPartie.getEtatjoueur()}</label></td>
                                        </tr>
                                    </table>
                                </div>
                                </c:if>        
                            </c:forEach>
                        </div>
                        <form method="POST" action="JouerPartie">
                        <div class = "fullLarge" >
                            <div class = "fullLarge ">
                                    <input class="floatRight" type="submit" name="lancerSort" value="Lancer Sort" ${moi.getEtatjoueur().name() == "A_LA_MAIN" ? "": "disabled"}>
                                
                            </div>
                            <div class = "fullLarge ">
                                <input class="floatRight" type="submit" name="passerTour" value="Passer Tour" ${moi.getEtatjoueur().name() == "A_LA_MAIN" ? "": "disabled"}>
                            </div>

                        </div>
                        </form>
                        
                        <div class="fullLarge design">
                            <div class=" avatar joueurBox">
                                <table>
                                    <tr>
                                        <td><img  src="image/avatar/${moi.avatar}.jpeg" alt="Avatar" style="width:200px"></td>
                                    </tr>
                                    <tr>
                                        <td class="libelle"><span>Moi</span></td>
                                    </tr>
                                    <tr>
                                        <td  class="libelle"><span>${moi.getCartes().size()} Cartes</span></td>
                                    </tr>
                                    <tr>
                                            <td class="libelle"><span>${moi.getEtatjoueur()}</label></td>
                                     </tr>
                                </table>
                            </div>
                                    <div class="large80pppp">
                                    <c:forEach items="${moi.getCartes()}" var="carte">
                                        <div class="carteBox">
                                                <span>
                                                    ${carte.getTypeIngredient()}
                                                </span>
                                        </div>    
                                    </c:forEach> 
                                 </div>
                        </div>

                        <div class = "fullLarge" >
                            <br/><br/><br/><br/><br/><br/><br/><br/>

                        </div>


                    </div>
                </div>


            </div>

    </body>
</html>