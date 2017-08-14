<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
        <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/font/Lato/fontLato.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
        <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>

    </head>

    <body style="background: url(<c:url value="/resources/img/fondo.png" />) no-repeat center center fixed;">
        
    <section class="container">
        <section class="login-form">
            <form role="login" action="ValidarUsuario.html" method="get">
                <img src="<c:url value="/resources/img/gepp_transmap.png" />" class="img-responsive" alt="" style="
                     height: 121px;
                     "/>
                <input type="text" name="usuNombre" placeholder="Usuario" required class="form-control input-lg" />
                <input type="password" name="usuContrasenhia" placeholder="Contraseña" required class="form-control input-lg" />
                <button type="submit" name="go" class="btn btn-lg btn-primary btn-block">Ingresar</button>
                
            </form>
            <div class="form-links">
                <a href="http://www.gepp.pe/" style="
                   color: #84be4d;
                   ">http://www.gepp.pe/</a>
            </div>
        </section>
    </section>


</body>
</html>
