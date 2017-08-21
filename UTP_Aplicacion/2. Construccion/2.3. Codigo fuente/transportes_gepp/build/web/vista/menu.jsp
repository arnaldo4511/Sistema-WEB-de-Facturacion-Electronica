 
<div class="nav-side-menu">
    <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
  
        <div class="menu-list">
  
            <ul id="menu-content" class="menu-content collapse out">
                <li>
                  <a href="#">
                  <i class="fa fa-dashboard fa-lg"></i> Dashboard
                  </a>
                </li>

                <li  data-toggle="collapse" data-target="#products" class="collapsed active">
                  <a href="#"><i class="fa fa-gift fa-lg"></i> UI Elements <span class="arrow"></span></a>
                </li>
                <ul class="sub-menu collapse" id="products">
                    <li class="active"><a href="#">CSS3 Animation</a></li>
                    <li><a href="#">General</a></li>
                    <li><a href="#">Buttons</a></li>
                    <li><a href="#">Tabs & Accordions</a></li>
                    <li><a href="#">Typography</a></li>
                    <li><a href="#">FontAwesome</a></li>
                    <li><a href="#">Slider</a></li>
                    <li><a href="#">Panels</a></li>
                    <li><a href="#">Widgets</a></li>
                    <li><a href="#">Bootstrap Model</a></li>
                </ul>


                <li data-toggle="collapse" data-target="#service" class="collapsed">
                  <a href="#"><i class="fa fa-globe fa-lg"></i> Services <span class="arrow"></span></a>
                </li>  
                <ul class="sub-menu collapse" id="service">
                  <li>New Service 1</li>
                  <li>New Service 2</li>
                  <li>New Service 3</li>
                </ul>


                <li data-toggle="collapse" data-target="#new" class="collapsed">
                  <a href="#"><i class="fa fa-car fa-lg"></i> New <span class="arrow"></span></a>
                </li>
                <ul class="sub-menu collapse" id="new">
                  <li>New New 1</li>
                  <li>New New 2</li>
                  <li>New New 3</li>
                </ul>


                 <li>
                  <a href="#">
                  <i class="fa fa-user fa-lg"></i> Profile
                  </a>
                  </li>

                 <li>
                  <a href="#">
                  <i class="fa fa-users fa-lg"></i> Users
                  </a>
                </li>
            </ul>
     </div>
</div>





<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a href="<%= request.getContextPath()%>/vista/principal.jsp">
                    Inicio</a>
            </h4>
        </div>
        <div id="collapseThree" class="panel-collapse collapse">
            <div class="panel-body">
                <table class="table">
                </table>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapseAdministracion" >
                    <i class="fa fa-edit"></i> Administración <span class="fa fa-chevron-down"></span>
                </a>
            </h4>
        </div>
        <div id="collapseAdministracion" class="panel-collapse collapse">
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td>
                            <a href="<%= request.getContextPath()%>/vista/administracion/rol.jsp"><i class="fa fa-table"></i> Roles </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="<%= request.getContextPath()%>/vista/administracion/usuario.jsp"><i class="fa fa-table"></i> Usuarios</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse"  href="#collapseMantenimientos">
                    <i class="fa fa-edit"></i>Mantenimientos<span class="fa fa-chevron-down"></span></a>
            </h4>
        </div>
        <div id="collapseMantenimientos" class="panel-collapse collapse">
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td>
                            <a href="<%= request.getContextPath()%>/vista/mantenimiento/producto.jsp"><i class="fa fa-table"></i> Productos</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="<%= request.getContextPath()%>/vista/mantenimiento/cliente.jsp"><i class="fa fa-table"></i> Clientes</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse"  href="#collapseFacturacion">
                    <i class="fa fa-edit"></i>Facturación<span class="fa fa-chevron-down"></span></a>
            </h4>
        </div>
        <div id="collapseFacturacion" class="panel-collapse collapse">
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td>
                            <a href="docente.jsp"><i class="fa fa-table"></i> Ventas</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="region.jsp"><i class="fa fa-table"></i> Resumenes/Comunicaciones</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse"  href="#collapseReportes">
                    <i class="fa fa-edit"></i>Reportes<span class="fa fa-chevron-down"></span></a>
            </h4>
        </div>
        <div id="collapseReportes" class="panel-collapse collapse">
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <li>
                            <a href="docente.jsp"><i class="fa fa-table"></i> Ventas</a>
                        </li>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>