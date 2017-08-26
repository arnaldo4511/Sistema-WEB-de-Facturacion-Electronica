/************************************************************************
*Descripción		: Script para la creación de Trigers
*Fecha de Creación	: 2017/08/26
*Fecha de Modificación: 
*Parámetros		:
*Autor			: Octavio Bedregal Flores / Arnaldo Ramírez Valverde
*Modificado Por	: 
*Versión		: 1.0
*Cambios Importantes	:
*Propiedad de 		: Transmap E.I.R.L.
************************************************************************/

CREATE TRIGGER tr_documento_venta_insertar
  BEFORE INSERT 
  ON ventas.documento_venta
  
FOR EACH ROW 
  EXECUTE PROCEDURE ventas.fun_documento_venta_insertar();