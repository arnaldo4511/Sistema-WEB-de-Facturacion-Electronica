/************************************************************************
*Descripción		: Script para la creación de PK, FK, UK valores por default y secuencias
*Fecha de Creación	: 2017/08/03
*Fecha de Modificación: 
*Parámetros		:
*Autor			: Octavio Bedregal Flores / Arnaldo Ramírez Valverde
*Modificado Por	: 
*Versión		: 1.0
*Cambios Importantes	:
*Propiedad de 		: Transmap E.I.R.L.
************************************************************************/


CREATE SEQUENCE public.sq_entidad_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE public.sq_empresa_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
--

CREATE SEQUENCE administracion.sq_menu_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE administracion.sq_operacion_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE administracion.sq_rol_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE administracion.sq_rol_menu_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE administracion.sq_rol_menu_operacion_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE administracion.sq_usuario_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE administracion.sq_usuario_log_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
--
CREATE SEQUENCE almacen.sq_producto_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
--
CREATE SEQUENCE ventas.sq_puntoventa_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE ventas.sq_cliente_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE ventas.sq_documentoventa_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE ventas.sq_documentoventa_detalle_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE ventas.sq_resumenventas_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE ventas.sq_resumenventas_numero
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE ventas.sq_resumenventas_grupo_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE ventas.sq_resumenventas_grupo_venta_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;

ALTER TABLE administracion.usuario
ADD CONSTRAINT pk_usuario PRIMARY KEY (id);

ALTER TABLE public.ubigeo
ADD CONSTRAINT pk_ubigeo PRIMARY KEY (codigo),
ADD CONSTRAINT fk_ubigeo_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_ubigeo_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE public.tipodocumentoentidad
ADD CONSTRAINT pk_tipodocumentoentidad PRIMARY KEY (codigo),
ADD CONSTRAINT fk_tipodocumentoentidad_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_tipodocumentoentidad_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE public.entidad
ADD CONSTRAINT pk_entidad PRIMARY KEY (id),
ADD CONSTRAINT fk_entidad_tipodocumentoentidad FOREIGN KEY (codigotipodocumentoentidad) REFERENCES public.tipodocumentoentidad(codigo),
ADD CONSTRAINT fk_entidad_ubigeo FOREIGN KEY (codigoubigeo) REFERENCES public.ubigeo(codigo),
ADD CONSTRAINT fk_entidad_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_entidad_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_entidad_documento UNIQUE (documento),
ALTER COLUMN id SET DEFAULT nextval('public.sq_entidad_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE public.empresa
ADD CONSTRAINT pk_empresa PRIMARY KEY (id),
ADD CONSTRAINT fk_empresa_entidad FOREIGN KEY (identidad) REFERENCES public.entidad(id),
ADD CONSTRAINT fk_empresa_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_empresa_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_empresa_identidad UNIQUE (identidad),
ALTER COLUMN id SET DEFAULT nextval('public.sq_empresa_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE public.moneda
ADD CONSTRAINT pk_moneda PRIMARY KEY (codigo),
ADD CONSTRAINT fk_moneda_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_moneda_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN fechacreacion SET DEFAULT now();

--ADMINISTRACION

ALTER TABLE administracion.menu
ADD CONSTRAINT pk_menu PRIMARY KEY (id),
ADD CONSTRAINT fk_menu_menu FOREIGN KEY (idmenupadre) REFERENCES administracion.menu(id),
ADD CONSTRAINT fk_menu_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_menu_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_menu_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE administracion.operacion
ADD CONSTRAINT pk_operacion PRIMARY KEY (id),
ADD CONSTRAINT fk_operacion_menu FOREIGN KEY (idmenu) REFERENCES administracion.menu(id),
ADD CONSTRAINT fk_operacion_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_operacion_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_operacion_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE administracion.rol
ADD CONSTRAINT pk_rol PRIMARY KEY (id),
ADD CONSTRAINT fk_rol_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_rol_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_rol_nombre UNIQUE (nombre),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_rol_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE administracion.rol_menu
ADD CONSTRAINT pk_rol_menu PRIMARY KEY (id),
ADD CONSTRAINT fk_rol_menu_rol FOREIGN KEY (idrol) REFERENCES administracion.rol(id),
ADD CONSTRAINT fk_rol_menu_menu FOREIGN KEY (idmenu) REFERENCES administracion.menu(id),
ADD CONSTRAINT fk_rol_menu_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_rol_menu_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_rol_menu_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE administracion.rol_menu_operacion
ADD CONSTRAINT pk_rol_menu_operacion PRIMARY KEY (id),
ADD CONSTRAINT fk_rol_menu_operacion_operacion FOREIGN KEY (idoperacion) REFERENCES administracion.operacion(id),
ADD CONSTRAINT fk_rol_menu_operacion_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_rol_menu_operacion_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_rol_menu_operacion_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE administracion.usuario
--ADD CONSTRAINT pk_usuario PRIMARY KEY (id),
ADD CONSTRAINT fk_usuario_entidad FOREIGN KEY (identidad) REFERENCES public.entidad(id),
ADD CONSTRAINT fk_usuario_rol FOREIGN KEY (idrol) REFERENCES administracion.rol(id),
ADD CONSTRAINT fk_usuario_empresa FOREIGN KEY (idempresa) REFERENCES public.empresa(id),
ADD CONSTRAINT fk_usuario_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_usuario_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_usuario_nombre UNIQUE (nombre),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_usuario_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE administracion.usuario_log
ADD CONSTRAINT pk_usuario_log PRIMARY KEY (id),
ADD CONSTRAINT fk_usuario_log_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_usuario_log_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_usuario_log_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

--ALMACEN

ALTER TABLE almacen.unidad
ADD CONSTRAINT pk_unidad PRIMARY KEY (codigo),
ADD CONSTRAINT fk_unidad_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_unidad_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_unidad_nombre UNIQUE (nombre),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE almacen.producto
ADD CONSTRAINT pk_producto PRIMARY KEY (id),
ADD CONSTRAINT fk_producto_unidad FOREIGN KEY (codigounidad) REFERENCES almacen.unidad(codigo),
ADD CONSTRAINT fk_producto_empresa FOREIGN KEY (idempresa) REFERENCES public.empresa(id),
ADD CONSTRAINT fk_producto_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_producto_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_producto_nombre UNIQUE (nombre),
ALTER COLUMN id SET DEFAULT nextval('almacen.sq_producto_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

--VENTAS

ALTER TABLE ventas.tipodocumentoventa
ADD CONSTRAINT pk_tipodocumentoventa PRIMARY KEY (codigo),
ADD CONSTRAINT fk_tipodocumentoventa_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_tipodocumentoventa_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_tipodocumentoventa_nombre UNIQUE (nombre),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE ventas.estadodocumentoventa
ADD CONSTRAINT pk_estadodocumentoventa PRIMARY KEY (codigo),
ADD CONSTRAINT fk_estadodocumentoventa_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_estadodocumentoventa_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_estadodocumentoventa_nombre UNIQUE (nombre),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE ventas.puntoventa
ADD CONSTRAINT pk_puntoventa PRIMARY KEY (id),
ADD CONSTRAINT fk_puntoventa_empresa FOREIGN KEY (idempresa) REFERENCES public.empresa,
ADD CONSTRAINT fk_puntoventa_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_puntoventa_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_puntoventa_nombre UNIQUE (nombre,idempresa),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_puntoventa_id') ,
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE ventas.puntoventa_serie
ADD CONSTRAINT pk_puntoventa_serie PRIMARY KEY (codigo),
ADD CONSTRAINT fk_puntoventa_serie_puntoventa FOREIGN KEY (idpuntoventa) REFERENCES ventas.puntoventa(id),
ADD CONSTRAINT fk_puntoventa_serie_tipodocumentoventa FOREIGN KEY (codigotipodocumentoventa) REFERENCES ventas.tipodocumentoventa(codigo),
ADD CONSTRAINT fk_puntoventa_serie_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_puntoventa_serie_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN ultimocorrelativo SET DEFAULT '00000000',
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE ventas.cliente
ADD CONSTRAINT pk_cliente PRIMARY KEY (id),
ADD CONSTRAINT fk_cliente_entidad FOREIGN KEY (identidad) REFERENCES public.entidad(id),
ADD CONSTRAINT fk_cliente_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_cliente_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_cliente_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE ventas.documentoventa
ADD CONSTRAINT pk_documentoventa PRIMARY KEY (id),
ADD CONSTRAINT fk_documentoventa_tipodocumentoventa FOREIGN KEY (codigotipodocumentoventa) REFERENCES ventas.tipodocumentoventa(codigo),
ADD CONSTRAINT fk_documentoventa_estadodocumentoventa FOREIGN KEY (codigoestadodocumentoventa) REFERENCES ventas.estadodocumentoventa(codigo),
ADD CONSTRAINT fk_documentoventa_puntoventa FOREIGN KEY (idpuntoventa) REFERENCES ventas.puntoventa(id),
ADD CONSTRAINT fk_documentoventa_puntoventa_serie FOREIGN KEY (serie) REFERENCES ventas.puntoventa_serie(codigo),
ADD CONSTRAINT fk_documentoventa_cliente FOREIGN KEY (idcliente) REFERENCES ventas.cliente(id),
ADD CONSTRAINT fk_documentoventa_moneda FOREIGN KEY (codigomoneda) REFERENCES public.moneda(codigo),
ADD CONSTRAINT fk_documentoventa_empresa FOREIGN KEY (idempresa) REFERENCES public.empresa(id),
ADD CONSTRAINT fk_documentoventa_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_documentoventa_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_documentoventa_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE ventas.documentoventa_detalle
ADD CONSTRAINT pk_documentoventa_detalle PRIMARY KEY (id),
ADD CONSTRAINT fk_documentoventa_detalle_documentoventa FOREIGN KEY (iddocumentoventa) REFERENCES ventas.documentoventa(id),
ADD CONSTRAINT fk_documentoventa_detalle_producto FOREIGN KEY (idproducto) REFERENCES almacen.producto(id),
ADD CONSTRAINT fk_documentoventa_detalle_unidad FOREIGN KEY (codigounidad) REFERENCES almacen.unidad(codigo),
ADD CONSTRAINT fk_documentoventa_detalle_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_documentoventa_detalle_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_documentoventa_detalle_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE ventas.resumenventas
ADD CONSTRAINT pk_resumenventas PRIMARY KEY (id),
ADD CONSTRAINT fk_resumenventas_estadodocumentoventa FOREIGN KEY (codigoestadodocumentoventa) REFERENCES ventas.estadodocumentoventa(codigo),
ADD CONSTRAINT fk_resumenventas_empresa FOREIGN KEY (idempresa) REFERENCES public.empresa(id),
ADD CONSTRAINT fk_resumenventas_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_resumenventas_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_resumenventas_id'),
ALTER COLUMN numero SET DEFAULT nextval('ventas.sq_resumenventas_numero'),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE ventas.resumenventas_grupo
ADD CONSTRAINT pk_resumenventas_grupo PRIMARY KEY (id),
add CONSTRAINT fk_resumenventas_grupo_resumenventas  FOREIGN KEY (idresumenventas) REFERENCES ventas.resumenventas(id),
ADD CONSTRAINT fk_resumenventas_grupo_tipodocumentoventa FOREIGN KEY (codigotipodocumentoventa) REFERENCES ventas.tipodocumentoventa(codigo),
ADD CONSTRAINT fk_resumenventas_grupo_puntoventa_serie FOREIGN KEY (codigopuntoventa_serie) REFERENCES ventas.puntoventa_serie(codigo),
ADD CONSTRAINT fk_resumenventas_grupo_moneda FOREIGN KEY (codigomoneda) REFERENCES public.moneda(codigo),
ADD CONSTRAINT fk_resumenventas_grupo_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_resumenventas_grupo_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_resumenventas_grupo_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();

ALTER TABLE ventas.resumenventas_grupo_venta
ADD CONSTRAINT pk_resumenventas_grupo_venta PRIMARY KEY (id),
add CONSTRAINT fk_resumenventas_grupo_venta_resumenventas_grupo  FOREIGN KEY (idresumenventas_grupo) REFERENCES ventas.resumenventas_grupo(id),
ADD CONSTRAINT fk_resumenventas_grupo_venta_documentoventa FOREIGN KEY (iddocumentoventa) REFERENCES ventas.documentoventa(id),
ADD CONSTRAINT fk_resumenventas_grupo_venta_usuario_creacion FOREIGN KEY (idusuariocreacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_resumenventas_grupo_venta_usuario_modificacion FOREIGN KEY (idusuariomodificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_resumenventas_grupo_venta_id'),
ALTER COLUMN fechacreacion SET DEFAULT now();
