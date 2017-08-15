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
                <a data-toggle="collapse"  href="#collapseAdministracion">
                    Administración</a>
            </h4>
        </div>
        <div id="collapseAdministracion" class="panel-collapse collapse">
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-pencil text-primary"></span><a href="<%= request.getContextPath()%>/vista/administracion/rol.jsp"> Roles</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-pencil text-primary"></span><a href="region.jsp"> Usuarios</a>
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
                    Mantenimientos</a>
            </h4>
        </div>
        <div id="collapseMantenimientos" class="panel-collapse collapse">
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-pencil text-primary"></span><a href="docente.jsp"> Productos</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-pencil text-primary"></span><a href="region.jsp"> Clientes</a>
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
                    Facturación</a>
            </h4>
        </div>
        <div id="collapseFacturacion" class="panel-collapse collapse">
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-pencil text-primary"></span><a href="docente.jsp"> Ventas</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-pencil text-primary"></span><a href="region.jsp"> Resumenes/Comunicaciones</a>
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
                    Reportes</a>
            </h4>
        </div>
        <div id="collapseReportes" class="panel-collapse collapse">
            <div class="panel-body">
                <table class="table">
                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-pencil text-primary"></span><a href="docente.jsp"> Ventas</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>