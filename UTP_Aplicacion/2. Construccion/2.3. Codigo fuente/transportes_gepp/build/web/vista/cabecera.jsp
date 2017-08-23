<div class='row'>
    <div class="top_nav">
        <div class="nav_menu">
            <nav>
                <ul class="nav navbar-nav navbar-left">
                    <li class="">
                        <a href="<%= request.getContextPath()%>/vista/principal.jsp">
                            <img  class="center-block" src="<%= request.getContextPath()%>/images/imagotipo (1).png" class="img-rounded" style="
                                  width: 128px;
                                  ">
                        </a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="">
                        <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            <img src="<%= request.getContextPath()%>/images/imagen_usuario.png" alt="">{{sesion.usuario.nombre}}
                            <span class=" fa fa-angle-down"></span>
                        </a>
                        <ul class="dropdown-menu dropdown-usermenu pull-right">
                            <li><a href="<%= request.getContextPath()%>/controlador/usuario/cerrarsesion"><i class="fa fa-sign-out pull-right"></i> Cerrar Sesión</a></li>
                        </ul>
                    </li>

                    <li role="presentation" class="dropdown">
                        <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                            <i class="fa fa-map-marker"></i>
                            <label>Punto de Venta:</label>
                            <label class="text-primary">{{sesion.usuario.puntoVenta.nombre || 'Ninguna'}}</label>
                        </a>
                    </li>
                    <li role="presentation" class="dropdown">
                        <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                            <i class="fa fa-building"></i>
                            <label>Empresa:</label>
                            <label class="text-primary">{{sesion.usuario.empresa.entidad.nombre || 'Ninguna'}}</label>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>



