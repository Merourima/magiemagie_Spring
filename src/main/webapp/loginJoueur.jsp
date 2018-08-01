<%-- 
    Document   : loginJoueur
    Created on : 13 juil. 2018, 14:13:43
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>
        <link >
        <meta charset="UTF-8">
        <title> Log IN </title>

<link href="LoginStyle.css" type="text/css" rel="stylesheet">
<link href="styleListePartie.css" type="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Galada" rel="stylesheet">
    </head>

    <body class="body">
        
        <!--***********************************-->
        
        <section class="textStyle loginBox design">
            <h1>Connectez-vous</h1>
            <form method="POST" action="LoginServlet">
                <div class="fullLargeLogin"><span>Pseudo</span> </div>
                <div class="fullLargeLogin"><input type="text" name="pseudo" required=""/> </div>
                <div class="fullLargeLogin">
                    <div class="blocAvatar imgstyle spaceDiv">
                        <div class="form-check">
                            
                            <label class="form-check-label" for="radio"><img src="image/avatar/avatar1.jpeg" alt="Avatar" ></label><br>
                            <input  type="radio" name="radio" id="exampleRadios1" value="avatar1" checked>
                        </div>
                        <div class="form-check">
                            
                            <label class="form-check-label" for="radio"><img src="image/avatar/avatar2.jpeg" alt="Avatar" /></label><br>
                            <input  type="radio" name="radio" id="exampleRadios2" value="avatar2">
                        </div>
                        <div class="form-check disabled">
                            
                            <label class="form-check-label" for="radio"><img src="image/avatar/avatar3.jpeg" alt="Avatar" /></label><br>
                            <input  type="radio" name="radio" id="exampleRadios3" value="avatar3" >
                        </div>
                         <div class="form-check disabled">
                            
                            <label class="form-check-label" for="radio"><img src="image/avatar/avatar4.jpeg" alt="Avatar" /></label><br>
                            <input  type="radio" name="radio" id="exampleRadios4" value="avatar5" >
                        </div>
                         <div class="form-check disabled">
                            
                            <label class="form-check-label" for="radio"><img src="image/avatar/avatar5.jpeg" alt="Avatar" /></label><br>
                            <input  type="radio" name="radio" id="exampleRadios5" value="avatar5">
                        </div>

                    </div>  
                </div>  
                <div class="fullLargeLogin"><input type="submit" value="Valider"/></div>
            </form>
            
        </section>
    </body>

</html>



