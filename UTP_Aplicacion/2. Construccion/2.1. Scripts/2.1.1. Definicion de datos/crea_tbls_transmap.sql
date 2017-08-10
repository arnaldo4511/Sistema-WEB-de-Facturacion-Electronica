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
codigodepartamento character varying(2) NOT NULL,
codigoprovincia character varying(2) NOT NULL,
codigodistrito character varying(2) NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint,
fechamodificacion timestamp without time zone
);
CREATE TABLE public.tipodocumentoentidad
(
codigo character varying(1) NOT NULL,
nombre character varying(50) NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint,
fechamodificacion timestamp without time zone
);
CREATE TABLE public.entidad
(
id bigint NOT NULL,
codigotipodocumentoentidad character varying(1) NOT NULL,
documento character varying(11) NOT NULL,
nombre character varying(250) NOT NULL,
codigopais character varying(3),
codigoubigeo character varying(6),
zona character varying(250),
direccion character varying(250),
correoelectronico1 character varying(50),
correoelectronico2 character varying(50),
correoelectronico3 character varying(50),
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE public.empresa
(
id bigint NOT NULL,
identidad bigint NOT NULL,
correoadmin character varying(250),
contrasenhiacorreo character varying(250),
logo character varying(50),
activo boolean NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE public.moneda
(
codigo character varying(3) NOT NULL,
nombre character varying(50) NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE administracion.menu
(
id bigint NOT NULL,
idmenupadre bigint,
nombre character varying(50) NOT NULL,
url character varying(100),
activo boolean NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE administracion.operacion
(
id bigint NOT NULL,
idmenu bigint NOT NULL,
nombre character varying(50) NOT NULL,
activo boolean NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE administracion.rol
(
id bigint NOT NULL,
nombre character varying(50) NOT NULL,
descripcion character varying(250) NOT NULL,
admin boolean NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE administracion.rol_menu
(
id bigint NOT NULL,
idrol bigint NOT NULL,
idmenu bigint NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE administracion.rol_menu_operacion
(
id bigint NOT NULL,
idoperacion bigint NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE administracion.usuario
(
id bigint NOT NULL,
nombre character varying(50) NOT NULL,
clave character varying(50) NOT NULL,
identidad bigint,
idrol bigint NOT NULL,
idempresa bigint NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint,
fechamodificacion timestamp without time zone
);
CREATE TABLE administracion.usuario_log
(
id bigint NOT NULL,
descripcion character varying NOT NULL,
consulta character varying NOT NULL,
ip character varying(20) NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
--ALMACEN
CREATE TABLE almacen.unidad
(
codigo character varying(3) NOT NULL,
nombre character varying(50) NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE almacen.producto
(
id bigint NOT NULL,
codigounidad character varying(3) NOT NULL,
nombre character varying(50) NOT NULL,
descripcion character varying(250) NOT NULL,
precioventa double precision NOT NULL,
preciocompra double precision NOT NULL,
idempresa bigint,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
--VENTAS
CREATE TABLE ventas.tipodocumentoventa
(
codigo character varying(2) NOT NULL,
nombre character varying(50) NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE ventas.estadodocumentoventa
(
codigo character varying(3) NOT NULL,
nombre character varying(50) NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE ventas.puntoventa
(
id bigint NOT NULL,
nombre character varying(50) NOT NULL,
idempresa bigint NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE ventas.puntoventa_serie
(
codigo character varying(4) NOT NULL,
idpuntoventa bigint NOT NULL,
codigotipodocumentoventa character varying(2) NOT NULL,
ultimocorrelativo character varying(8) NOT NULL,
activo boolean NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE ventas.cliente
(
id bigint NOT NULL,
identidad bigint NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE ventas.documentoventa
(
id bigint NOT NULL,
codigotipodocumentoventa character varying(2) NOT NULL,
codigoestadodocumentoventa character varying(3) NOT NULL,
condicion character varying(3) NOT NULL,
formapago character varying(3) NOT NULL,
banco character varying(3),
numeropago character varying(20),
fechaemision date NOT NULL,
fechavencimiento date,
guiaremision character varying(20),
serie character varying(4) NOT NULL,
numero character varying(8) NOT NULL,
idpuntoventa bigint NOT NULL,
idcliente bigint,
totalgratuito double precision NOT NULL,
totaldescuento double precision NOT NULL,
totalgrabado double precision NOT NULL,
totaligv double precision NOT NULL,
total double precision NOT NULL,
codigomoneda character varying(3) NOT NULL,
idempresa bigint NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE ventas.documentoventa_detalle
(
id bigint NOT NULL,
iddocumentoventa bigint NOT NULL,
idproducto bigint,
codigounidad character varying(3),
preciounitario double precision NOT NULL,
descuentounitario double precision NOT NULL,
fleteunitario double precision NOT NULL,
bonificacionunitario double precision NOT NULL,
preciofinal double precision NOT NULL,
cantidad double precision NOT NULL,
descuento boolean NOT NULL,
gratuito boolean NOT NULL,
totaldescuento double precision NOT NULL,
totalgrabado double precision NOT NULL,
totalIGV double precision NOT NULL,
total double precision NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE ventas.resumenventas
(
id bigint NOT NULL,
tipo character varying(3) NOT NULL,
codigoestadodocumentoventa character varying(3) NOT NULL,
fechaemision date NOT NULL,
numero character varying(8) NOT NULL,
idempresa bigint NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE ventas.resumenventas_grupo
(
id bigint NOT NULL,
idresumenventas bigint NOT NULL,
codigotipodocumentoventa character varying(2) NOT NULL,
codigopuntoventa_serie character varying(4) NOT NULL,
iniciodocumentoventa character varying(8) NOT NULL,
findocumentoventa character varying(8) NOT NULL,
descripcion character varying(50),
totalgrabado double precision,
totalIGV double precision,
total double precision,
codigomoneda character varying(3),
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);
CREATE TABLE ventas.resumenventas_grupo_venta
(
id bigint NOT NULL,
idresumenventas_grupo bigint NOT NULL,
iddocumentoventa bigint NOT NULL,
idusuariocreacion bigint NOT NULL,
fechacreacion timestamp without time zone NOT NULL,
idusuariomodificacion bigint ,
fechamodificacion timestamp without time zone
);