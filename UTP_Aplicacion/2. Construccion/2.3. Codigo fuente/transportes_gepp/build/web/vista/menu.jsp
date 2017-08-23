
<div class="nav-side-menu">
    <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>

    <div class="menu-list">

        <ul id="menu-content" class="menu-content collapse out">
            <li class="active">
                <a href="<%= request.getContextPath()%>/vista/principal.jsp"><i class="fa fa-home fa-lg"></i>Inicio</a>
            </li>
            <li  data-toggle="collapse" data-target="#administracion" class="collapsed">
                <a href="#"><i class="fa fa-edit fa-lg"></i>Administración<span class="arrow"></span></a>
            </li>
            <ul class="sub-menu collapse" id="administracion">
                <li onclick='window.location.href="<%= request.getContextPath()%>/vista/administracion/rol.jsp"'>
                    <a><i class="fa fa-table fa-lg"></i>Roles</a>
                </li>
                <li onclick='window.location.href="<%= request.getContextPath()%>/vista/administracion/usuario.jsp"'>
                    <a><i class="fa fa-table fa-lg"></i>Usuarios</a>
                </li>
            </ul>
            <li data-toggle="collapse" data-target="#mantenimientos" class="collapsed">
                <a href="#"><i class="fa fa-edit fa-lg"></i> Mantenimientos <span class="arrow"></span></a>
            </li>  
            <ul class="sub-menu collapse" id="mantenimientos">
                <li onclick='window.location.href="<%= request.getContextPath()%>/vista/mantenimiento/producto.jsp"'>
                    <a><i class="fa fa-table fa-lg"></i>Productos</a>
                </li>
                <li onclick='window.location.href="<%= request.getContextPath()%>/vista/mantenimiento/cliente.jsp"'>
                    <a><i class="fa fa-table fa-lg"></i>Clientes</a>
                </li>
            </ul>
            <li data-toggle="collapse" data-target="#facturacion" class="collapsed">
                <a><i class="fa fa-edit fa-lg"></i> Facturación <span class="arrow"></span></a>
            </li>  
            <ul class="sub-menu collapse" id="facturacion">
                <li>
                    <a><i class="fa fa-table fa-lg"></i>Ventas</a>
                </li>
                <li>
                    <a><i class="fa fa-table fa-lg"></i>Resumenes/Comunicaciones</a>
                </li>
            </ul>
            <li data-toggle="collapse" data-target="#reportes" class="collapsed">
                <a><i class="fa fa-edit fa-lg"></i> Reportes <span class="arrow"></span></a>
            </li>  
            <ul class="sub-menu collapse" id="reportes">
                <li>
                    <a><i class="fa fa-table fa-lg"></i>Ventas</a>
                </li>
            </ul>
        </ul>
    </div>
</div>



