/************************************************************************
*Descripción		: Script para la creación de Tablas y Esquemas
*Fecha de Creación	: 2017/08/03
*Fecha de Modificación: 
*Parámetros		:
*Autor			: Octavio Bedregal Flores / Arnaldo Ramírez Valverde
*Modificado Por	: 
*Versión		: 1.0
*Cambios Importantes	:
*Propiedad de 		: Transmap E.I.R.L.
************************************************************************/

DROP SCHEMA IF EXISTS public CASCADE;
DROP SCHEMA IF EXISTS administracion CASCADE;
DROP SCHEMA IF EXISTS almacen CASCADE;
DROP SCHEMA IF EXISTS ventas CASCADE;


CREATE SCHEMA public AUTHORIZATION postgres;
CREATE SCHEMA administracion AUTHORIZATION postgres;
CREATE SCHEMA almacen AUTHORIZATION postgres;
CREATE SCHEMA ventas AUTHORIZATION postgres;



CREATE TABLE public.ubigeo
(
codigo character varying(6) NOT NULL,
nombre character varying(250) NOT NULL,
codigo_departamento character varying(2) NOT NULL,
codigo_provincia character varying(2) NOT NULL,
codigo_distrito character varying(2) NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint,
fecha_modificacion timestamp without time zone
);
CREATE TABLE public.tipo_documento_entidad
(
codigo character varying(1) NOT NULL,
nombre character varying(50) NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint,
fecha_modificacion timestamp without time zone
);
CREATE TABLE public.entidad
(
id bigint NOT NULL,
codigo_tipo_documento_entidad character varying(1) NOT NULL,
documento character varying(11) NOT NULL,
nombre character varying(250) NOT NULL,
codigo_pais character varying(3),
codigo_ubigeo character varying(6),
zona character varying(250),
direccion character varying(250),
correo_electronico1 character varying(50),
correo_electronico2 character varying(50),
correo_electronico3 character varying(50),
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE public.empresa
(
id bigint NOT NULL,
id_entidad bigint NOT NULL,
correo_admin character varying(250),
contrasenhia_correo character varying(250),
logo character varying(50),
activo boolean NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE public.moneda
(
codigo character varying(3) NOT NULL,
nombre character varying(50) NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE administracion.menu
(
id bigint NOT NULL,
id_menu_padre bigint,
nombre character varying(50) NOT NULL,
url character varying(100),
activo boolean NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE administracion.operacion
(
id bigint NOT NULL,
id_menu bigint NOT NULL,
nombre character varying(50) NOT NULL,
activo boolean NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE administracion.rol
(
id bigint NOT NULL,
nombre character varying(50) NOT NULL,
descripcion character varying(250) NOT NULL,
admin boolean NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE administracion.rol_menu
(
id bigint NOT NULL,
id_rol bigint NOT NULL,
id_menu bigint NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE administracion.rol_menu_operacion
(
id bigint NOT NULL,
id_operacion bigint NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE administracion.usuario
(
id bigint NOT NULL,
nombre character varying(50) NOT NULL,
clave character varying(50) NOT NULL,
id_entidad bigint,
id_rol bigint NOT NULL,
id_empresa bigint NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint,
fecha_modificacion timestamp without time zone
);
CREATE TABLE administracion.usuario_log
(
id bigint NOT NULL,
descripcion character varying NOT NULL,
consulta character varying NOT NULL,
ip character varying(20) NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
--ALMACEN
CREATE TABLE almacen.unidad
(
codigo character varying(3) NOT NULL,
nombre character varying(50) NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE almacen.producto
(
id bigint NOT NULL,
codigo_unidad character varying(3) NOT NULL,
nombre character varying(50) NOT NULL,
descripcion character varying(250) NOT NULL,
precio_venta double precision NOT NULL,
precio_compra double precision NOT NULL,
id_empresa bigint,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
--VENTAS
CREATE TABLE ventas.tipo_documento_venta
(
codigo character varying(2) NOT NULL,
nombre character varying(50) NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE ventas.estado_documento_venta
(
codigo character varying(3) NOT NULL,
nombre character varying(50) NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE ventas.punto_venta
(
id bigint NOT NULL,
nombre character varying(50) NOT NULL,
id_empresa bigint NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE ventas.punto_venta_serie
(
codigo character varying(4) NOT NULL,
id_punto_venta bigint NOT NULL,
codigo_tipo_documento_venta character varying(2) NOT NULL,
ultimo_correlativo character varying(8) NOT NULL,
activo boolean NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE ventas.cliente
(
id bigint NOT NULL,
id_entidad bigint NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE ventas.documento_venta
(
id bigint NOT NULL,
codigo_tipo_documento_venta character varying(2) NOT NULL,
codigo_estado_documento_venta character varying(3) NOT NULL,
condicion character varying(3) NOT NULL,
forma_pago character varying(3) NOT NULL,
tipo_targeta character varying(3),
total_letras character varying(3) NOT NULL,
banco character varying(3),
numero_pago character varying(20),
fecha_emision date NOT NULL,
fecha_vencimiento date,
guia_remision character varying(20),
serie character varying(4) NOT NULL,
numero character varying(8) NOT NULL,
id_punto_venta bigint NOT NULL,
id_cliente bigint,
total_gratuito double precision NOT NULL,
total_descuento double precision NOT NULL,
total_grabado double precision NOT NULL,
total_igv double precision NOT NULL,
total double precision NOT NULL,
codigo_moneda character varying(3) NOT NULL,
id_empresa bigint NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE ventas.documento_venta_detalle
(
id bigint NOT NULL,
id_documento_venta bigint NOT NULL,
id_producto bigint,
codigo_unidad character varying(3),
precio_unitario double precision NOT NULL,
descuento_unitario double precision NOT NULL,
flete_unitario double precision NOT NULL,
bonificacion_unitario double precision NOT NULL,
precio_final double precision NOT NULL,
cantidad double precision NOT NULL,
descuento boolean NOT NULL,
gratuito boolean NOT NULL,
total_descuento double precision NOT NULL,
total_grabado double precision NOT NULL,
total_igv double precision NOT NULL,
total double precision NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE ventas.resumen_ventas
(
id bigint NOT NULL,
tipo character varying(3) NOT NULL,
codigo_estado_documento_venta character varying(3) NOT NULL,
fecha_emision date NOT NULL,
numero character varying(8) NOT NULL,
id_empresa bigint NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE ventas.resumen_ventas_grupo
(
id bigint NOT NULL,
id_resumen_ventas bigint NOT NULL,
codigo_tipo_documento_venta character varying(2) NOT NULL,
codigo_punto_venta_serie character varying(4) NOT NULL,
inicio_documento_venta character varying(8) NOT NULL,
fin_documento_venta character varying(8) NOT NULL,
descripcion character varying(50),
total_grabado double precision,
total_igv double precision,
total double precision,
codigo_moneda character varying(3),
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);
CREATE TABLE ventas.resumen_ventas_grupo_venta
(
id bigint NOT NULL,
id_resumen_ventas_grupo bigint NOT NULL,
id_documento_venta bigint NOT NULL,
id_usuario_creacion bigint NOT NULL,
fecha_creacion timestamp without time zone NOT NULL,
id_usuario_modificacion bigint ,
fecha_modificacion timestamp without time zone
);