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
CREATE SEQUENCE ventas.sq_punto_venta_id
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
CREATE SEQUENCE ventas.sq_documento_venta_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE ventas.sq_documento_venta_detalle_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE ventas.sq_resumen_ventas_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE ventas.sq_resumen_ventas_numero
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE ventas.sq_resumen_ventas_grupo_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
CREATE SEQUENCE ventas.sq_resumen_ventas_grupo_venta_id
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;

ALTER TABLE administracion.usuario
ADD CONSTRAINT pk_usuario PRIMARY KEY (id);

ALTER TABLE public.ubigeo
ADD CONSTRAINT pk_ubigeo PRIMARY KEY (codigo),
ADD CONSTRAINT fk_ubigeo_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_ubigeo_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE public.tipo_documento_entidad
ADD CONSTRAINT pk_tipo_documento_entidad PRIMARY KEY (codigo),
ADD CONSTRAINT fk_tipo_documento_entidad_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_tipo_documento_entidad_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE public.entidad
ADD CONSTRAINT pk_entidad PRIMARY KEY (id),
ADD CONSTRAINT fk_entidad_tipo_documento_entidad FOREIGN KEY (codigo_tipo_documento_entidad) REFERENCES public.tipo_documento_entidad(codigo),
ADD CONSTRAINT fk_entidad_ubigeo FOREIGN KEY (codigo_ubigeo) REFERENCES public.ubigeo(codigo),
ADD CONSTRAINT fk_entidad_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_entidad_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_entidad_documento UNIQUE (documento),
ALTER COLUMN id SET DEFAULT nextval('public.sq_entidad_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE public.empresa
ADD CONSTRAINT pk_empresa PRIMARY KEY (id),
ADD CONSTRAINT fk_empresa_entidad FOREIGN KEY (id_entidad) REFERENCES public.entidad(id),
ADD CONSTRAINT fk_empresa_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_empresa_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_empresa_id_entidad UNIQUE (id_entidad),
ALTER COLUMN id SET DEFAULT nextval('public.sq_empresa_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE public.moneda
ADD CONSTRAINT pk_moneda PRIMARY KEY (codigo),
ADD CONSTRAINT fk_moneda_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_moneda_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN fecha_creacion SET DEFAULT now();

--ADMINISTRACION

ALTER TABLE administracion.menu
ADD CONSTRAINT pk_menu PRIMARY KEY (id),
ADD CONSTRAINT fk_menu_menu FOREIGN KEY (id_menu_padre) REFERENCES administracion.menu(id),
ADD CONSTRAINT fk_menu_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_menu_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_menu_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE administracion.operacion
ADD CONSTRAINT pk_operacion PRIMARY KEY (id),
ADD CONSTRAINT fk_operacion_menu FOREIGN KEY (id_menu) REFERENCES administracion.menu(id),
ADD CONSTRAINT fk_operacion_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_operacion_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_operacion_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE administracion.rol
ADD CONSTRAINT pk_rol PRIMARY KEY (id),
ADD CONSTRAINT fk_rol_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_rol_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_rol_nombre UNIQUE (nombre),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_rol_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE administracion.rol_menu
ADD CONSTRAINT pk_rol_menu PRIMARY KEY (id),
ADD CONSTRAINT fk_rol_menu_rol FOREIGN KEY (id_rol) REFERENCES administracion.rol(id),
ADD CONSTRAINT fk_rol_menu_menu FOREIGN KEY (id_menu) REFERENCES administracion.menu(id),
ADD CONSTRAINT fk_rol_menu_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_rol_menu_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_rol_menu_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE administracion.rol_menu_operacion
ADD CONSTRAINT pk_rol_menu_operacion PRIMARY KEY (id),
ADD CONSTRAINT fk_rol_menu_operacion_operacion FOREIGN KEY (id_operacion) REFERENCES administracion.operacion(id),
ADD CONSTRAINT fk_rol_menu_operacion_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_rol_menu_operacion_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_rol_menu_operacion_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE administracion.usuario
--ADD CONSTRAINT pk_usuario PRIMARY KEY (id),
ADD CONSTRAINT fk_usuario_entidad FOREIGN KEY (id_entidad) REFERENCES public.entidad(id),
ADD CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES administracion.rol(id),
ADD CONSTRAINT fk_usuario_empresa FOREIGN KEY (id_empresa) REFERENCES public.empresa(id),
ADD CONSTRAINT fk_usuario_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_usuario_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_usuario_nombre UNIQUE (nombre),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_usuario_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE administracion.usuario_log
ADD CONSTRAINT pk_usuario_log PRIMARY KEY (id),
ADD CONSTRAINT fk_usuario_log_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_usuario_log_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('administracion.sq_usuario_log_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

--ALMACEN

ALTER TABLE almacen.unidad
ADD CONSTRAINT pk_unidad PRIMARY KEY (codigo),
ADD CONSTRAINT fk_unidad_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_unidad_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_unidad_nombre UNIQUE (nombre),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE almacen.producto
ADD CONSTRAINT pk_producto PRIMARY KEY (id),
ADD CONSTRAINT fk_producto_unidad FOREIGN KEY (codigo_unidad) REFERENCES almacen.unidad(codigo),
ADD CONSTRAINT fk_producto_empresa FOREIGN KEY (id_empresa) REFERENCES public.empresa(id),
ADD CONSTRAINT fk_producto_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_producto_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_producto_nombre UNIQUE (nombre),
ALTER COLUMN id SET DEFAULT nextval('almacen.sq_producto_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

--VENTAS

ALTER TABLE ventas.tipo_documento_venta
ADD CONSTRAINT pk_tipo_documento_venta PRIMARY KEY (codigo),
ADD CONSTRAINT fk_tipo_documento_venta_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_tipo_documento_venta_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_tipo_documento_venta_nombre UNIQUE (nombre),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE ventas.estado_documento_venta
ADD CONSTRAINT pk_estado_documento_venta PRIMARY KEY (codigo),
ADD CONSTRAINT fk_estado_documento_venta_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_estado_documento_venta_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_estado_documento_venta_nombre UNIQUE (nombre),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE ventas.punto_venta
ADD CONSTRAINT pk_punto_venta PRIMARY KEY (id),
ADD CONSTRAINT fk_punto_venta_empresa FOREIGN KEY (id_empresa) REFERENCES public.empresa,
ADD CONSTRAINT fk_punto_venta_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_punto_venta_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT uk_punto_venta_nombre UNIQUE (nombre,id_empresa),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_punto_venta_id') ,
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE ventas.punto_venta_serie
ADD CONSTRAINT pk_punto_venta_serie PRIMARY KEY (codigo),
ADD CONSTRAINT fk_punto_venta_serie_punto_venta FOREIGN KEY (id_punto_venta) REFERENCES ventas.punto_venta(id),
ADD CONSTRAINT fk_punto_venta_serie_tipo_documento_venta FOREIGN KEY (codigo_tipo_documento_venta) REFERENCES ventas.tipo_documento_venta(codigo),
ADD CONSTRAINT fk_punto_venta_serie_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_punto_venta_serie_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN ultimo_correlativo SET DEFAULT '00000000',
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE ventas.cliente
ADD CONSTRAINT pk_cliente PRIMARY KEY (id),
ADD CONSTRAINT fk_cliente_entidad FOREIGN KEY (id_entidad) REFERENCES public.entidad(id),
ADD CONSTRAINT fk_cliente_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_cliente_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_cliente_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE ventas.documento_venta
ADD CONSTRAINT pk_documento_venta PRIMARY KEY (id),
ADD CONSTRAINT fk_documento_venta_tipo_documento_venta FOREIGN KEY (codigo_tipo_documento_venta) REFERENCES ventas.tipo_documento_venta(codigo),
ADD CONSTRAINT fk_documento_venta_estado_documento_venta FOREIGN KEY (codigo_estado_documento_venta) REFERENCES ventas.estado_documento_venta(codigo),
ADD CONSTRAINT fk_documento_venta_punto_venta FOREIGN KEY (id_punto_venta) REFERENCES ventas.punto_venta(id),
ADD CONSTRAINT fk_documento_venta_punto_venta_serie FOREIGN KEY (serie) REFERENCES ventas.punto_venta_serie(codigo),
ADD CONSTRAINT fk_documento_venta_cliente FOREIGN KEY (id_cliente) REFERENCES ventas.cliente(id),
ADD CONSTRAINT fk_documento_venta_moneda FOREIGN KEY (codigo_moneda) REFERENCES public.moneda(codigo),
ADD CONSTRAINT fk_documento_venta_empresa FOREIGN KEY (id_empresa) REFERENCES public.empresa(id),
ADD CONSTRAINT fk_documento_venta_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_documento_venta_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_documento_venta_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE ventas.documento_venta_detalle
ADD CONSTRAINT pk_documento_venta_detalle PRIMARY KEY (id),
ADD CONSTRAINT fk_documento_venta_detalle_documento_venta FOREIGN KEY (id_documento_venta) REFERENCES ventas.documento_venta(id),
ADD CONSTRAINT fk_documento_venta_detalle_producto FOREIGN KEY (id_producto) REFERENCES almacen.producto(id),
ADD CONSTRAINT fk_documento_venta_detalle_unidad FOREIGN KEY (codigo_unidad) REFERENCES almacen.unidad(codigo),
ADD CONSTRAINT fk_documento_venta_detalle_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_documento_venta_detalle_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_documento_venta_detalle_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE ventas.resumen_ventas
ADD CONSTRAINT pk_resumen_ventas PRIMARY KEY (id),
ADD CONSTRAINT fk_resumen_ventas_estado_documento_venta FOREIGN KEY (codigo_estado_documento_venta) REFERENCES ventas.estado_documento_venta(codigo),
ADD CONSTRAINT fk_resumen_ventas_empresa FOREIGN KEY (id_empresa) REFERENCES public.empresa(id),
ADD CONSTRAINT fk_resumen_ventas_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_resumen_ventas_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_resumen_ventas_id'),
ALTER COLUMN numero SET DEFAULT nextval('ventas.sq_resumen_ventas_numero'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE ventas.resumen_ventas_grupo
ADD CONSTRAINT pk_resumen_ventas_grupo PRIMARY KEY (id),
add CONSTRAINT fk_resumen_ventas_grupo_resumen_ventas  FOREIGN KEY (id_resumen_ventas) REFERENCES ventas.resumen_ventas(id),
ADD CONSTRAINT fk_resumen_ventas_grupo_tipo_documento_venta FOREIGN KEY (codigo_tipo_documento_venta) REFERENCES ventas.tipo_documento_venta(codigo),
ADD CONSTRAINT fk_resumen_ventas_grupo_punto_venta_serie FOREIGN KEY (codigo_punto_venta_serie) REFERENCES ventas.punto_venta_serie(codigo),
ADD CONSTRAINT fk_resumen_ventas_grupo_moneda FOREIGN KEY (codigo_moneda) REFERENCES public.moneda(codigo),
ADD CONSTRAINT fk_resumen_ventas_grupo_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_resumen_ventas_grupo_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_resumen_ventas_grupo_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();

ALTER TABLE ventas.resumen_ventas_grupo_venta
ADD CONSTRAINT pk_resumen_ventas_grupo_venta PRIMARY KEY (id),
add CONSTRAINT fk_resumen_ventas_grupo_venta_resumen_ventas_grupo  FOREIGN KEY (id_resumen_ventas_grupo) REFERENCES ventas.resumen_ventas_grupo(id),
ADD CONSTRAINT fk_resumen_ventas_grupo_venta_documento_venta FOREIGN KEY (id_documento_venta) REFERENCES ventas.documento_venta(id),
ADD CONSTRAINT fk_resumen_ventas_grupo_venta_usuario_creacion FOREIGN KEY (id_usuario_creacion) REFERENCES administracion.usuario(id),
ADD CONSTRAINT fk_resumen_ventas_grupo_venta_usuario_modificacion FOREIGN KEY (id_usuario_modificacion) REFERENCES administracion.usuario(id),
ALTER COLUMN id SET DEFAULT nextval('ventas.sq_resumen_ventas_grupo_venta_id'),
ALTER COLUMN fecha_creacion SET DEFAULT now();
