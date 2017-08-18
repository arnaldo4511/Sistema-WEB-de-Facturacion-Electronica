<div>
    <div class="col-md-3 pull-left">
        <a href="<%= request.getContextPath()%>/vista/principal.jsp">
            <img  class="center-block" src="<%= request.getContextPath()%>/images/gepp_transmap_logo.png" class="img-rounded" alt="Cinque Terre" width="75" height="60">
        </a>
    </div>
    <div class="col-md-6  text-center">
        <!--    
        <label>Punto de Venta:</label>
            <label class="text-primary">{{sesion.usuario.puntoVenta.nombre}}</label>
        -->
        <label>Empresa:</label>
        <label class="text-primary">{{sesion.usuario.empresa.entidad.nombre}}</label>
    </div>
</div>
<div class="col-md-3 text-right">
    <div>
        <label>Usuario:</label>
        <label class="text-primary">{{sesion.usuario.nombre}}</label>
    </div>
    <a href="<%= request.getContextPath()%>/controlador/usuario/cerrarsesion">Cerrar Sesión</a>
</div>
</div>