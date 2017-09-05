<div class="top_nav">
    <div class="nav_menu">
        <nav>
            <ul class="nav navbar-nav navbar-left">
                <li class="">
                    <a href="<%= request.getContextPath()%>/vista/principal.jsp">
                        <img  class="center-block" src="<%= request.getContextPath()%>/images/gepp_transmap_logo.png" class="img-rounded" style="width: 50px;">
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav" style="width: auto !important;">
                <li>
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        Administración
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                        <li onclick='window.location.href = "<%= request.getContextPath()%>/vista/administracion/rol.jsp"'>
                            <a>Roles</a>
                        </li>
                        <li onclick='window.location.href = "<%= request.getContextPath()%>/vista/administracion/usuario.jsp"'>
                            <a>Usuarios</a>
                        </li></ul>
                </li>
                <li>
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        Mantenimientos
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                        <li onclick='window.location.href = "<%= request.getContextPath()%>/vista/almacen/producto.jsp"'>
                            <a>Productos</a>
                        </li>
                        <li onclick='window.location.href = "<%= request.getContextPath()%>/vista/ventas/cliente.jsp"'>
                            <a>Clientes</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        Facturación
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                        <li onclick='window.location.href = "<%= request.getContextPath()%>/vista/ventas/documentoventas.jsp"'>
                            <a>Ventas</a>
                        </li>
                        <li onclick='window.location.href = "<%= request.getContextPath()%>/vista/ventas/resumenventa.jsp"'>
                            <a>Resumenes/Comunicaciones</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        Reportes
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">

                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right" style="width: auto;">
                <li>
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <img src="<%= request.getContextPath()%>/images/imagen_usuario.png" alt="">{{sesion.usuario.nombre}}
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                        <li><a href="<%= request.getContextPath()%>/controlador/usuario/cerrarsesion"><i class="fa fa-sign-out pull-right"></i> Cerrar Sesión</a></li>
                    </ul>
                </li>
                <li role="presentation" class="pull-right dropdown" style="width: auto">
                    <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                        <i class="fa fa-map-marker"></i>
                        <label>Punto de Venta:</label>
                        <label class="text-primary">{{sesion.usuario.puntoVenta.nombre || 'Ninguna'}}</label>
                    </a>
                </li>
                <li role="presentation" class="pull-right dropdown">
                    <a  class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                        <i class="fa fa-building"></i>
                        <label>Empresa:</label>
                        <label class="text-primary">{{sesion.usuario.empresa.entidad.nombre || 'Ninguna'}}</label>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>