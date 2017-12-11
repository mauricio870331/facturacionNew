-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-12-2017 a las 11:44:40
-- Versión del servidor: 10.1.10-MariaDB
-- Versión de PHP: 7.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `codigo_pymesapp`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`codigo`@`localhost` PROCEDURE `actualizarActividadEconomica` (`id_actividad` INT, `nombre` VARCHAR(20), `descripcion` VARCHAR(100))  BEGIN
IF EXISTS ( SELECT ae.id_actividad_economica
FROM actividades_economicas AS ae
WHERE ae.id_actividad_economica = id_actividad) THEN
UPDATE actividades_economicas set nombre = nombre, descripcion = descripcion WHERE id_actividad_economica = id_actividad;
ELSE
SELECT 'Esta actividad no existe en la base de datos!';
END IF;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `actualizarCliente` (IN `idcliente` INT(11), IN `nombre_razon` VARCHAR(100), IN `identificaion` VARCHAR(15), IN `direccion` VARCHAR(120), IN `id_ciudad` INT(11), IN `correo` VARCHAR(100), IN `telefono1` VARCHAR(10), IN `telefono2` VARCHAR(10), IN `fax` VARCHAR(10), IN `celular` VARCHAR(10), IN `cliente` TINYINT(1), IN `proveedor` TINYINT(1), IN `observaciones` VARCHAR(100), IN `id_empresa` VARCHAR(15), IN `id_tipo_identificacion` INT(1), IN `transacion` VARCHAR(30), IN `id_nacionalidad` INT(11), IN `fecha_nacimiento` DATE)  NO SQL
BEGIN
IF EXISTS ( SELECT c.id_cliente
FROM clientes AS c
WHERE c.id_cliente = idcliente) THEN
UPDATE clientes set nombre_razon = nombre_razon, identificacion = identificacion, direccion = direccion, id_ciudad = id_ciudad, correo = correo, telefono1 = telefono1, telefono2 = telefono2, fax = fax, celular = celular, cliente = cliente, proveedor = proveedor, observaciones = observaciones, id_empresa = id_empresa, id_tipo_identificacion = id_tipo_identificacion, transacion = transacion, id_nacionalidad = id_nacionalidad, fecha_nacimiento = fecha_nacimiento WHERE id_cliente = idcliente;
SELECT 'Datos Actualizados Correctamente!';
ELSE
SELECT 'Este cliente no existe en la base de datos!';
END IF;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `anularFactura` (IN `idfactura` VARCHAR(20), IN `concepto` VARCHAR(200), IN `id_usuario` VARCHAR(15), IN `fecha` DATETIME, IN `transacion` VARCHAR(30))  NO SQL
BEGIN
INSERT INTO facturas_anuladas(id_factura, concepto, id_usuario,  fecha, transacion) 
VALUES (idfactura, concepto, id_usuario, fecha, transacion);
SELECT 'Factura Anulada..';
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `cargarImpuestos` ()  NO SQL
BEGIN
SELECT * FROM impuestos;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `cargarModulos` (IN `vendedor` VARCHAR(20))  NO SQL
SELECT md.id_modulo from usuarios_app ua, app_empresas av, modulos_licencias md
where ua.id_empresa = av.id_empresa and av.id_licencia = md.id_licencia and av.id_empresa = vendedor$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `eliminarActividadEconomica` (IN `id_actividad` INT)  BEGIN
IF EXISTS ( SELECT ae.id_actividad_economica
FROM actividades_economicas AS ae
WHERE ae.id_actividad_economica = id_actividad) THEN
DELETE FROM  actividades_economicas WHERE id_actividad_economica = id_actividad ;
SELECT 'Todo ok!';
ELSE
SELECT 'Esta Actividad no existe en la base de datos!';
END IF;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `existUser` (IN `user` VARCHAR(20), IN `pass` VARCHAR(100))  NO SQL
BEGIN
select * from usuarios_app where id_usuario = user and password = pass
and id_estado_usuario = 1;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `insertarActividadEconomica` (`nombre` VARCHAR(20), `descripcion` VARCHAR(100))  BEGIN
IF NOT EXISTS ( SELECT ae.nombre 
FROM actividades_economicas AS ae 
WHERE ae.nombre = nombre) THEN
INSERT INTO actividades_economicas (nombre, descripcion)
VALUES (nombre,descripcion);
ELSE
SELECT 'Esta actividad  ya existe en la base de datos!';
END IF;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `insertarCliente` (IN `nombre_razon` VARCHAR(100), IN `identificacion` VARCHAR(15), IN `direccion` VARCHAR(120), IN `id_ciudad` INT(11), IN `correo` VARCHAR(50), IN `telefono1` VARCHAR(15), IN `telefono2` VARCHAR(15), IN `fax` VARCHAR(15), IN `celular` VARCHAR(15), IN `cliente` BOOLEAN, IN `proveedor` BOOLEAN, IN `observaciones` VARCHAR(100), IN `id_empresa` VARCHAR(15), IN `id_tipo_identificacion` INT(11), IN `transacion` VARCHAR(30), IN `id_nacionalidad` INT(11), IN `fecha_nacimiento` DATE)  NO SQL
BEGIN
IF NOT EXISTS (SELECT c.identificacion 
FROM clientes AS c 
WHERE c.identificacion = identificacion) THEN
INSERT INTO clientes (nombre_razon, identificacion, direccion, id_ciudad, correo, telefono1, telefono2, fax, celular, cliente, proveedor, observaciones, id_empresa, id_tipo_identificacion, transacion, id_nacionalidad, fecha_nacimiento)
VALUES (nombre_razon, identificacion, direccion, id_ciudad, correo, telefono1, telefono2, fax, celular, cliente, proveedor, observaciones, id_empresa, id_tipo_identificacion, transacion, id_nacionalidad, fecha_nacimiento);
SELECT 'Cliente Creado Con Exito';
ELSE
SELECT 'Este Cliente ya existe en la base de datos!';
END IF;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `insertarConsecutivoImprimible` (IN `id_consecutivo` INT(11), IN `prefijo` VARCHAR(10), IN `consecutivo` INT(11), IN `id_tipo_imprimible` INT(11), IN `id_empresa` VARCHAR(15), IN `transacion` VARCHAR(30))  BEGIN
IF NOT EXISTS ( SELECT ci.id_consecutivo 
FROM consecutivos_imprimibles AS ci 
WHERE ci.id_tipo_imprimible= id_tipo_imprimible) THEN
INSERT INTO consecutivos_imprimibles (prefijo, consecutivo, id_tipo_imprimible, id_empresa, transacion)
VALUES (prefijo, consecutivo, id_tipo_imprimible, id_empresa, transacion);
SELECT 'Numeración registrada!';
ELSE
SELECT 'ya se definió la numeración!';
END IF;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `insertarDetalleFacturaCotizacion` (IN `id_producto_servicio` INT(11), IN `cantidad` INT(11), IN `id_factura` VARCHAR(20), IN `id_impuesto` INT(2), IN `transacion` VARCHAR(30), IN `valor` FLOAT, IN `descuento` FLOAT)  NO SQL
BEGIN
INSERT INTO detalle_factura_cotizacion (id_producto_servicio, cantidad, id_factura, id_impuesto, transacion, valor, descuento)
VALUES (id_producto_servicio, cantidad, id_factura, id_impuesto, transacion, valor, descuento);
SELECT 'Detalle factura creada con exito';
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `insertarFactura` (IN `id_factura` VARCHAR(20), IN `fecha` DATETIME, IN `id_resolucion` VARCHAR(15), IN `id_cliente` INT(11), IN `subtotal` FLOAT, IN `iva` FLOAT, IN `total` FLOAT, IN `id_estado_factura` INT(11), IN `id_empresa` VARCHAR(15), IN `id_usuario` VARCHAR(15), IN `id_termino_pago` INT(11), IN `vencimiento` DATE, IN `nota` VARCHAR(100), IN `transacion` VARCHAR(30))  NO SQL
BEGIN
IF NOT EXISTS (SELECT f.id_factura 
FROM facturas AS f 
WHERE f.id_factura = id_factura ) THEN
INSERT INTO facturas (id_factura, fecha, id_resolucion, id_cliente, subtotal, iva, total, id_estado_factura, id_empresa, id_usuario, id_termino_pago, vencimiento, nota, transacion)
VALUES (id_factura, fecha, id_resolucion, id_cliente, subtotal, iva, total, id_estado_factura, id_empresa, id_usuario, id_termino_pago, vencimiento, nota, transacion);
SELECT 'Factura creada con exito';
ELSE
SELECT 'ya se encuentra registrada esta factura!';
END IF;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `insertarFacturaAnulada` (IN `id_factura` VARCHAR(20), IN `concepto` VARCHAR(200), IN `id_usuario` VARCHAR(15), IN `fecha` DATE, IN `transacion` VARCHAR(30))  NO SQL
BEGIN
IF NOT EXISTS (SELECT fa.id_factura 
FROM facturas_anuladas AS fa 
WHERE fa.id_factura = id_factura) THEN
INSERT INTO facturas_anuladas (id_factura, concepto, id_usuario, fecha, transacion)
VALUES (id_factura, concepto, id_usuario, fecha, transacion);
SELECT 'Factura anulada correctamente!';
ELSE
SELECT 'Ya se anulo la factura previamente';
END IF;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `insertarHospedaje` (IN `id_motivo` INT(11), IN `fecha_ingreso` DATETIME, IN `fecha_salida` DATETIME, IN `id_habitacion` INT(11), IN `id_cliente` INT(11), IN `numero_adultos` INT(11), IN `numero_ninos` INT(11), IN `fecha_registro` DATETIME, IN `id_factura` VARCHAR(15), IN `recepcionista` VARCHAR(15), IN `valor_noche` FLOAT, IN `transacion` VARCHAR(30))  NO SQL
BEGIN
INSERT INTO hospedajes (id_motivo, fecha_ingreso, fecha_salida, id_habitacion, id_cliente, numero_adultos, numero_ninos, fecha_registro, id_factura, recepcionista, valor_noche, transacion)
VALUES (id_motivo, fecha_ingreso, fecha_salida, id_habitacion, id_cliente, numero_adultos, numero_ninos, fecha_registro, id_factura, recepcionista, valor_noche, transacion);
SELECT 'Hospedaje registrado con exito';
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `insertarPagos` (IN `id_cliente` INT(11), IN `id_cuenta` INT(5), IN `fecha` DATE, IN `observaciones` VARCHAR(200), IN `nota_egreso` VARCHAR(200), IN `id_tipo_pago` INT(11), IN `id_comprobante_egreso` INT(11), IN `id_recibo_caja` INT(11), IN `valor` FLOAT, IN `id_factura_proveedor` INT(15), IN `id_factura` VARCHAR(20), IN `cuatro_digitos` VARCHAR(4), IN `voucher` VARCHAR(15), IN `transacion` VARCHAR(30), IN `id_franquicia` VARCHAR(100), IN `recepcionista` VARCHAR(15), IN `id_hospedaje` INT(15))  NO SQL
BEGIN
INSERT INTO pagos(id_cliente, id_cuenta, fecha, observaciones, nota_egreso, id_tipo_pago, id_comprobante_egreso, id_recibo_caja, valor, id_factura_proveedor, id_factura, cuatro_digitos, voucher, id_franquicia, transacion, recepcionista, id_hospedaje) 
VALUES (id_cliente, id_cuenta, fecha, observaciones, nota_egreso, id_tipo_pago, id_comprobante_egreso, id_recibo_caja, valor, id_factura_proveedor, id_factura, cuatro_digitos, voucher, id_franquicia, transacion, recepcionista,id_hospedaje);
SELECT 'Pago realizado con exito';
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `insertarProductoServicio` (IN `nombre` VARCHAR(50), IN `referencia` VARCHAR(100), IN `descripcion` VARCHAR(200), IN `costo_unidad` FLOAT, IN `precio_venta` FLOAT, IN `imagen` BLOB, IN `garantia` VARCHAR(200), IN `id_unidad_medida` INT(11), IN `stock` INT(11), IN `producto` BOOLEAN, IN `codigo_barra` VARCHAR(100), IN `transacion` VARCHAR(30), IN `id_empresa` VARCHAR(15))  NO SQL
BEGIN
IF NOT EXISTS (SELECT ps.nombre 
FROM productos_servicios AS ps 
WHERE ps.nombre = nombre) THEN
INSERT INTO productos_servicios (nombre, referencia, descripcion, costo_unidad, precio_venta, imagen, garantia, id_unidad_medida, stock, producto, codigo_barra, transacion, id_empresa)
VALUES (nombre, referencia, descripcion, costo_unidad, precio_venta, imagen, garantia, id_unidad_medida, stock, producto, codigo_barra, transacion, id_empresa);
SELECT 'Producto o servicio creado correctamente!';
ELSE
SELECT 'Ya existe un producto con el mismo nombre!!';
END IF;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `insertarReciboCaja` (IN `factura` VARCHAR(20), IN `consec` VARCHAR(11), IN `fech` DATE, IN `empresa` VARCHAR(15), IN `cliente` INT, IN `val` FLOAT, IN `concept` VARCHAR(200), IN `chek` VARCHAR(50), IN `tipo_pago` INT, IN `trans` VARCHAR(30), IN `id_hospedaje` INT(11))  NO SQL
BEGIN
INSERT INTO recibos_caja (id_factura, consecutivo, fecha, id_empresa, id_cliente, valor, concepto, cheque, id_tipo_pago, transacion, id_hospedaje)
VALUES (factura, consec, fech, empresa, cliente, val, concept, chek, tipo_pago, trans, id_hospedaje);
SELECT AUTO_INCREMENT AS LastId FROM information_schema.tables WHERE TABLE_SCHEMA='codigo_pymesapp' AND TABLE_NAME='recibos_caja';
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `insertarResolucionFactura` (IN `id_resolucion` VARCHAR(15), IN `fecha` DATE, IN `prefijo` VARCHAR(15), IN `consecutivo_inicial` INT(11), IN `consecutivo_final` INT(11), IN `id_tipo_resolucion` INT(11), IN `activo` BOOLEAN, IN `id_empresa` VARCHAR(15), IN `transacion` VARCHAR(30))  BEGIN
IF NOT EXISTS ( SELECT rf.id_resolucion 
FROM resoluciones_facturas AS rf 
WHERE rf.id_resolucion = id_resolucion) THEN
INSERT INTO resoluciones_facturas (id_resolucion, fecha, prefijo, consecutivo_inicial, consecutivo_final, id_tipo_resolucion, activo, id_empresa, transacion)
VALUES (id_resolucion, fecha, prefijo, consecutivo_inicial, consecutivo_final, id_tipo_resolucion, activo, id_empresa, transacion);
SELECT 'Resolución Creada Con Exito';
ELSE
SELECT 'Resolución ya existe en la base de datos!';
END IF;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `insertarRetencionPago` (IN `id_factura` VARCHAR(15), IN `id_retencion` INT(11), IN `transacion` VARCHAR(30), IN `valor` FLOAT)  NO SQL
BEGIN
INSERT INTO retenciones_pagos (id_factura, id_retencion, transacion, valor)
VALUES (id_factura, id_retencion, transacion, valor);
SELECT 'Retencion creada con exito';
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `insertarUsuarioApp` (IN `id_usuario` VARCHAR(15), IN `id_tipo_identificacion` INT(11), IN `id_empresa` VARCHAR(15), IN `id_rol` INT(11), IN `password` VARCHAR(100), IN `nombres` VARCHAR(50), IN `apellidos` VARCHAR(50), IN `id_estado_usuario` INT(11), IN `transacion` VARCHAR(30))  NO SQL
BEGIN
IF NOT EXISTS (SELECT ua.id_usuario 
FROM usuarios_app AS ua 
WHERE ua.id_usuario = id_usuario ) THEN
INSERT INTO usuarios_app (id_usuario, id_tipo_identificacion, id_empresa, id_rol, password, nombres, apellidos, id_estado_usuario, transacion)
VALUES (id_usuario, id_tipo_identificacion, id_empresa, id_rol, password, nombres, apellidos, id_estado_usuario, transacion);
SELECT 'Usuario vinculado con exito';
ELSE
SELECT 'ya se encuentra registrado el usuario!';
END IF;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarActividadEconomica` ()  BEGIN
SELECT * FROM actividades_economicas;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarCiudad` ()  NO SQL
BEGIN
SELECT * FROM ciudades;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarCotizacion` (IN `empresa` VARCHAR(15))  BEGIN
SELECT f.*, ef.nombre FROM facturas f, estados_facturas ef where f.id_empresa = empresa and f.id_estado_factura = ef.id_estado_factura and f.id_estado_factura = 6 order by fecha DESC;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarEntidadesBancarias` ()  NO SQL
SELECT * from entidades_bancarias$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarFactura` (IN `empresa` VARCHAR(15))  BEGIN
SELECT * FROM facturas where id_empresa = empresa and id_estado_factura <> 6 order by fecha DESC;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarFacturaN` (IN `empresa` VARCHAR(15))  NO SQL
BEGIN
SELECT f.*, case when (select SUM(valor) from pagos where id_factura = f.id_factura) is not null then (select SUM(valor) from pagos where id_factura = f.id_factura) else 0 end as abonos,
case when (select SUM(valor) from retenciones_pagos where id_factura = f.id_factura) is not null then (select SUM(valor) from retenciones_pagos where id_factura = f.id_factura) else 0 end as retenciones FROM facturas f where f.id_empresa = empresa 
and id_estado_factura <> 6 order by fecha DESC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarFacturasN2` (IN `empresa` VARCHAR(30))  NO SQL
BEGIN
SELECT f.*, ef.nombre FROM facturas f, estados_facturas ef where f.id_empresa = empresa and f.id_estado_factura = ef.id_estado_factura and f.id_estado_factura <> 6 order by fecha DESC;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarFranquicia` ()  NO SQL
SELECT * from franquicias$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarMotivo` ()  NO SQL
select * from motivos_estadia$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarNacionalidad` ()  NO SQL
select * from nacionalidades$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarProductoServicio` (IN `empresa` VARCHAR(15))  NO SQL
BEGIN
SELECT * FROM productos_servicios where id_empresa = empresa;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarResolucionFactura` ()  BEGIN
SELECT * FROM resoluciones_facturas;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarRetencion` (IN `empresa` VARCHAR(15))  NO SQL
SELECT  r.id_retencion, r.nombre, r.tarifa, r.transacion from retenciones r, retenciones_empresas e where r.id_retencion = e.id_retencion and e.id_empresa = empresa$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarRol` ()  NO SQL
SELECT * FROM `roles` WHERE nombre <> "Administrador"$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarTerminoPago` ()  NO SQL
BEGIN 
 SELECT * FROM terminos_de_pago;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarTipoDeHabitacion` (IN `empresa` VARCHAR(15))  NO SQL
SELECT * from tipos_de_habitacion where id_empresa = empresa$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarTipoDeIdentificacion` ()  NO SQL
    DETERMINISTIC
BEGIN
SELECT * FROM tipos_de_identificacion;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarTipoDepago` ()  NO SQL
BEGIN 
 SELECT * FROM tipos_de_pagos;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarTipoImprimible` ()  NO SQL
BEGIN
SELECT * FROM tipos_imprimibles;
END$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarTipoImprimible()` ()  NO SQL
select * from tipos_imprimibles$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarTipoResolucion` ()  NO SQL
SELECT * FROM tipos_resoluciones$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listarUnidadMedida` ()  NO SQL
select * from unidades_medida$$

CREATE DEFINER=`codigo`@`localhost` PROCEDURE `listDetalleFactura` (IN `idFactura` VARCHAR(20))  NO SQL
select f.id_detalle, f.id_producto_servicio, f.cantidad, f.id_factura, f.id_impuesto, f.valor, f.descuento, u.descripcion from detalle_factura_cotizacion f,  productos_servicios p, unidades_medida u where id_factura = idFactura AND p.id_producto_servicio = f.id_producto_servicio AND p.id_unidad_medida = u.id_unidad_medida$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividades_economicas`
--

CREATE TABLE `actividades_economicas` (
  `id_actividad_economica` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividades_empresas`
--

CREATE TABLE `actividades_empresas` (
  `id_actividad_empresa` int(11) NOT NULL,
  `id_empresa` varchar(15) NOT NULL,
  `id_actividad_economica` int(11) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `app_vendedores`
--

CREATE TABLE `app_vendedores` (
  `id_app_vendedor` int(11) NOT NULL,
  `id_version` int(11) NOT NULL,
  `id_licencia` varchar(100) NOT NULL,
  `id_empresa` varchar(15) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bancos`
--

CREATE TABLE `bancos` (
  `id_banco` int(11) NOT NULL,
  `id_cuenta` int(5) NOT NULL,
  `nombre_cuenta` varchar(100) NOT NULL,
  `numero_cuenta` varchar(100) DEFAULT NULL,
  `saldo` float NOT NULL,
  `fecha` date NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `id_empresa` varchar(15) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias_pagos`
--

CREATE TABLE `categorias_pagos` (
  `id_categoria_pago` int(5) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `detalle` varchar(100) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categorias_pagos`
--

INSERT INTO `categorias_pagos` (`id_categoria_pago`, `nombre`, `detalle`, `transacion`) VALUES
(1, 'Alquiler de equipos y licencias', NULL, ''),
(2, 'Comisiones y honorarios', NULL, ''),
(3, 'Transporte y mensajería ', NULL, ''),
(4, 'Legales', NULL, ''),
(5, 'Nómina - prestaciones sociales', NULL, ''),
(6, 'Nómina - salarios', NULL, ''),
(7, 'Nómina - seguridad social y parafiscales', NULL, ''),
(8, 'Nómina - dotación', NULL, ''),
(9, 'Publicidad', NULL, ''),
(10, 'Seguros y seguridad', NULL, ''),
(11, 'Servicios bancarios', NULL, ''),
(12, 'Suscripciones y afiliaciones', NULL, ''),
(13, 'Varios', NULL, ''),
(14, 'Mantenimiento e instalaciones', NULL, ''),
(15, 'Viajes y viaticos', NULL, ''),
(16, 'Costo de la mercancía vendida - Flete y envíos', NULL, ''),
(17, 'Costo de la mercancía vendida - Mano de obra', NULL, ''),
(18, 'Costo de la mercancía vendida - Materias primas', NULL, ''),
(19, 'Costo de la mercancía vendida - ajustes al inventario', NULL, ''),
(20, 'Gastos bancarios', NULL, ''),
(21, 'Cuentas incobrables', NULL, ''),
(22, 'Otros impuestos', NULL, ''),
(23, 'Gastos administrativos - Arrendamiento', NULL, ''),
(24, 'Gastos administrativos - Internet y telecomunicaciones', NULL, ''),
(25, 'Gastos administrativos - Aseo y cafetería', NULL, ''),
(26, 'Gastos administrativos - papelería', NULL, ''),
(27, 'Gastos administrativos - Servicios públicos', NULL, ''),
(28, 'Costo servicios vendidos', NULL, ''),
(29, 'Egresos no operacionales - Diferencia en cambio', NULL, ''),
(30, 'Egresos no operacionales - Descuento financiero', NULL, ''),
(31, 'Activos Fijos', NULL, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudades`
--

CREATE TABLE `ciudades` (
  `id_ciudad` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `id_departamento` int(11) NOT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ciudades`
--

INSERT INTO `ciudades` (`id_ciudad`, `nombre`, `id_departamento`, `transacion`) VALUES
(1, 'EL ENCANTO', 1, ''),
(2, 'LA CHORRERA', 1, ''),
(3, 'LA PEDRERA', 1, ''),
(4, 'LA VICTORIA', 1, ''),
(5, 'LETICIA', 1, ''),
(6, 'MIRITI', 1, ''),
(7, 'PUERTO ALEGRIA', 1, ''),
(8, 'PUERTO ARICA', 1, ''),
(9, 'PUERTO NARIÑO', 1, ''),
(10, 'PUERTO SANTANDER', 1, ''),
(11, 'TURAPACA', 1, ''),
(12, 'ABEJORRAL', 2, ''),
(13, 'ABRIAQUI', 2, ''),
(14, 'ALEJANDRIA', 2, ''),
(15, 'AMAGA', 2, ''),
(16, 'AMALFI', 2, ''),
(17, 'ANDES', 2, ''),
(18, 'ANGELOPOLIS', 2, ''),
(19, 'ANGOSTURA', 2, ''),
(20, 'ANORI', 2, ''),
(21, 'ANTIOQUIA', 2, ''),
(22, 'ANZA', 2, ''),
(23, 'APARTADO', 2, ''),
(24, 'ARBOLETES', 2, ''),
(25, 'ARGELIA', 2, ''),
(26, 'ARMENIA', 2, ''),
(27, 'BARBOSA', 2, ''),
(28, 'BELLO', 2, ''),
(29, 'BELMIRA', 2, ''),
(30, 'BETANIA', 2, ''),
(31, 'BETULIA', 2, ''),
(32, 'BOLIVAR', 2, ''),
(33, 'BRICEÑO', 2, ''),
(34, 'BURITICA', 2, ''),
(35, 'CACERES', 2, ''),
(36, 'CAICEDO', 2, ''),
(37, 'CALDAS', 2, ''),
(38, 'CAMPAMENTO', 2, ''),
(39, 'CANASGORDAS', 2, ''),
(40, 'CARACOLI', 2, ''),
(41, 'CARAMANTA', 2, ''),
(42, 'CAREPA', 2, ''),
(43, 'CARMEN DE VIBORAL', 2, ''),
(44, 'CAROLINA DEL PRINCIPE', 2, ''),
(45, 'CAUCASIA', 2, ''),
(46, 'CHIGORODO', 2, ''),
(47, 'CISNEROS', 2, ''),
(48, 'COCORNA', 2, ''),
(49, 'CONCEPCION', 2, ''),
(50, 'CONCORDIA', 2, ''),
(51, 'COPACABANA', 2, ''),
(52, 'DABEIBA', 2, ''),
(53, 'DONMATIAS', 2, ''),
(54, 'EBEJICO', 2, ''),
(55, 'EL BAGRE', 2, ''),
(56, 'EL PENOL', 2, ''),
(57, 'EL RETIRO', 2, ''),
(58, 'ENTRERRIOS', 2, ''),
(59, 'ENVIGADO', 2, ''),
(60, 'FREDONIA', 2, ''),
(61, 'FRONTINO', 2, ''),
(62, 'GIRALDO', 2, ''),
(63, 'GIRARDOTA', 2, ''),
(64, 'GOMEZ PLATA', 2, ''),
(65, 'GRANADA', 2, ''),
(66, 'GUADALUPE', 2, ''),
(67, 'GUARNE', 2, ''),
(68, 'GUATAQUE', 2, ''),
(69, 'HELICONIA', 2, ''),
(70, 'HISPANIA', 2, ''),
(71, 'ITAGUI', 2, ''),
(72, 'ITUANGO', 2, ''),
(73, 'JARDIN', 2, ''),
(74, 'JERICO', 2, ''),
(75, 'LA CEJA', 2, ''),
(76, 'LA ESTRELLA', 2, ''),
(77, 'LA PINTADA', 2, ''),
(78, 'LA UNION', 2, ''),
(79, 'LIBORINA', 2, ''),
(80, 'MACEO', 2, ''),
(81, 'MARINILLA', 2, ''),
(82, 'MEDELLIN', 2, ''),
(83, 'MONTEBELLO', 2, ''),
(84, 'MURINDO', 2, ''),
(85, 'MUTATA', 2, ''),
(86, 'NARINO', 2, ''),
(87, 'NECHI', 2, ''),
(88, 'NECOCLI', 2, ''),
(89, 'OLAYA', 2, ''),
(90, 'PEQUE', 2, ''),
(91, 'PUEBLORRICO', 2, ''),
(92, 'PUERTO BERRIO', 2, ''),
(93, 'PUERTO NARE', 2, ''),
(94, 'PUERTO TRIUNFO', 2, ''),
(95, 'REMEDIOS', 2, ''),
(96, 'RIONEGRO', 2, ''),
(97, 'SABANALARGA', 2, ''),
(98, 'SABANETA', 2, ''),
(99, 'SALGAR', 2, ''),
(100, 'SAN ANDRES DE CUERQUIA', 2, ''),
(101, 'SAN CARLOS', 2, ''),
(102, 'SAN FRANCISCO', 2, ''),
(103, 'SAN JERONIMO', 2, ''),
(104, 'SAN JOSE DE LA MONTAÑA', 2, ''),
(105, 'SAN JUAN DE URABA', 2, ''),
(106, 'SAN LUIS', 2, ''),
(107, 'SAN PEDRO DE LOS MILAGROS', 2, ''),
(108, 'SAN PEDRO DE URABA', 2, ''),
(109, 'SAN RAFAEL', 2, ''),
(110, 'SAN ROQUE', 2, ''),
(111, 'SAN VICENTE', 2, ''),
(112, 'SANTA BARBARA', 2, ''),
(113, 'SANTA ROSA DE OSOS', 2, ''),
(114, 'SANTO DOMINGO', 2, ''),
(115, 'SANTUARIO', 2, ''),
(116, 'SEGOVIA', 2, ''),
(117, 'SONSON', 2, ''),
(118, 'SOPETRAN', 2, ''),
(119, 'TAMESIS', 2, ''),
(120, 'TARAZA', 2, ''),
(121, 'TARSO', 2, ''),
(122, 'TITIRIBI', 2, ''),
(123, 'TOLEDO', 2, ''),
(124, 'TURBO', 2, ''),
(125, 'URAMITA', 2, ''),
(126, 'URRAO', 2, ''),
(127, 'VALDIVIA', 2, ''),
(128, 'VALPARAISO', 2, ''),
(129, 'VEGACHI', 2, ''),
(130, 'VENECIA', 2, ''),
(131, 'VIGIA DEL FUERTE', 2, ''),
(132, 'YALI', 2, ''),
(133, 'YARUMAL', 2, ''),
(134, 'YOLOMBO', 2, ''),
(135, 'YONDO', 2, ''),
(136, 'ZARAGOZA', 2, ''),
(137, 'ARAUCA', 3, ''),
(138, 'ARAUQUITA', 3, ''),
(139, 'CRAVO NORTE', 3, ''),
(140, 'FORTUL', 3, ''),
(141, 'PUERTO RONDON', 3, ''),
(142, 'SARAVENA', 3, ''),
(143, 'TAME', 3, ''),
(144, 'BARANOA', 4, ''),
(145, 'BARRANQUILLA', 4, ''),
(146, 'CAMPO DE LA CRUZ', 4, ''),
(147, 'CANDELARIA', 4, ''),
(148, 'GALAPA', 4, ''),
(149, 'JUAN DE ACOSTA', 4, ''),
(150, 'LURUACO', 4, ''),
(151, 'MALAMBO', 4, ''),
(152, 'MANATI', 4, ''),
(153, 'PALMAR DE VARELA', 4, ''),
(154, 'PIOJO', 4, ''),
(155, 'POLO NUEVO', 4, ''),
(156, 'PONEDERA', 4, ''),
(157, 'PUERTO COLOMBIA', 4, ''),
(158, 'REPELON', 4, ''),
(159, 'SABANAGRANDE', 4, ''),
(160, 'SABANALARGA', 4, ''),
(161, 'SANTA LUCIA', 4, ''),
(162, 'SANTO TOMAS', 4, ''),
(163, 'SOLEDAD', 4, ''),
(164, 'SUAN', 4, ''),
(165, 'TUBARA', 4, ''),
(166, 'USIACURI', 4, ''),
(167, 'ACHI', 5, ''),
(168, 'ALTOS DEL ROSARIO', 5, ''),
(169, 'ARENAL', 5, ''),
(170, 'ARJONA', 5, ''),
(171, 'ARROYOHONDO', 5, ''),
(172, 'BARRANCO DE LOBA', 5, ''),
(173, 'BRAZUELO DE PAPAYAL', 5, ''),
(174, 'CALAMAR', 5, ''),
(175, 'CANTAGALLO', 5, ''),
(176, 'CARTAGENA DE INDIAS', 5, ''),
(177, 'CICUCO', 5, ''),
(178, 'CLEMENCIA', 5, ''),
(179, 'CORDOBA', 5, ''),
(180, 'EL CARMEN DE BOLIVAR', 5, ''),
(181, 'EL GUAMO', 5, ''),
(182, 'EL PENION', 5, ''),
(183, 'HATILLO DE LOBA', 5, ''),
(184, 'MAGANGUE', 5, ''),
(185, 'MAHATES', 5, ''),
(186, 'MARGARITA', 5, ''),
(187, 'MARIA LA BAJA', 5, ''),
(188, 'MONTECRISTO', 5, ''),
(189, 'MORALES', 5, ''),
(190, 'MORALES', 5, ''),
(191, 'NOROSI', 5, ''),
(192, 'PINILLOS', 5, ''),
(193, 'REGIDOR', 5, ''),
(194, 'RIO VIEJO', 5, ''),
(195, 'SAN CRISTOBAL', 5, ''),
(196, 'SAN ESTANISLAO', 5, ''),
(197, 'SAN FERNANDO', 5, ''),
(198, 'SAN JACINTO', 5, ''),
(199, 'SAN JACINTO DEL CAUCA', 5, ''),
(200, 'SAN JUAN DE NEPOMUCENO', 5, ''),
(201, 'SAN MARTIN DE LOBA', 5, ''),
(202, 'SAN PABLO', 5, ''),
(203, 'SAN PABLO NORTE', 5, ''),
(204, 'SANTA CATALINA', 5, ''),
(205, 'SANTA CRUZ DE MOMPOX', 5, ''),
(206, 'SANTA ROSA', 5, ''),
(207, 'SANTA ROSA DEL SUR', 5, ''),
(208, 'SIMITI', 5, ''),
(209, 'SOPLAVIENTO', 5, ''),
(210, 'TALAIGUA NUEVO', 5, ''),
(211, 'TUQUISIO', 5, ''),
(212, 'TURBACO', 5, ''),
(213, 'TURBANA', 5, ''),
(214, 'VILLANUEVA', 5, ''),
(215, 'ZAMBRANO', 5, ''),
(216, 'AQUITANIA', 6, ''),
(217, 'ARCABUCO', 6, ''),
(218, 'BELÉN', 6, ''),
(219, 'BERBEO', 6, ''),
(220, 'BETÉITIVA', 6, ''),
(221, 'BOAVITA', 6, ''),
(222, 'BOYACÁ', 6, ''),
(223, 'BRICEÑO', 6, ''),
(224, 'BUENAVISTA', 6, ''),
(225, 'BUSBANZÁ', 6, ''),
(226, 'CALDAS', 6, ''),
(227, 'CAMPO HERMOSO', 6, ''),
(228, 'CERINZA', 6, ''),
(229, 'CHINAVITA', 6, ''),
(230, 'CHIQUINQUIRÁ', 6, ''),
(231, 'CHÍQUIZA', 6, ''),
(232, 'CHISCAS', 6, ''),
(233, 'CHITA', 6, ''),
(234, 'CHITARAQUE', 6, ''),
(235, 'CHIVATÁ', 6, ''),
(236, 'CIÉNEGA', 6, ''),
(237, 'CÓMBITA', 6, ''),
(238, 'COPER', 6, ''),
(239, 'CORRALES', 6, ''),
(240, 'COVARACHÍA', 6, ''),
(241, 'CUBARA', 6, ''),
(242, 'CUCAITA', 6, ''),
(243, 'CUITIVA', 6, ''),
(244, 'DUITAMA', 6, ''),
(245, 'EL COCUY', 6, ''),
(246, 'EL ESPINO', 6, ''),
(247, 'FIRAVITOBA', 6, ''),
(248, 'FLORESTA', 6, ''),
(249, 'GACHANTIVÁ', 6, ''),
(250, 'GÁMEZA', 6, ''),
(251, 'GARAGOA', 6, ''),
(252, 'GUACAMAYAS', 6, ''),
(253, 'GÜICÁN', 6, ''),
(254, 'IZA', 6, ''),
(255, 'JENESANO', 6, ''),
(256, 'JERICÓ', 6, ''),
(257, 'LA UVITA', 6, ''),
(258, 'LA VICTORIA', 6, ''),
(259, 'LABRANZA GRANDE', 6, ''),
(260, 'MACANAL', 6, ''),
(261, 'MARIPÍ', 6, ''),
(262, 'MIRAFLORES', 6, ''),
(263, 'MONGUA', 6, ''),
(264, 'MONGUÍ', 6, ''),
(265, 'MONIQUIRÁ', 6, ''),
(266, 'MOTAVITA', 6, ''),
(267, 'MUZO', 6, ''),
(268, 'NOBSA', 6, ''),
(269, 'NUEVO COLÓN', 6, ''),
(270, 'OICATÁ', 6, ''),
(271, 'OTANCHE', 6, ''),
(272, 'PACHAVITA', 6, ''),
(273, 'PÁEZ', 6, ''),
(274, 'PAIPA', 6, ''),
(275, 'PAJARITO', 6, ''),
(276, 'PANQUEBA', 6, ''),
(277, 'PAUNA', 6, ''),
(278, 'PAYA', 6, ''),
(279, 'PAZ DE RÍO', 6, ''),
(280, 'PESCA', 6, ''),
(281, 'PISBA', 6, ''),
(282, 'PUERTO BOYACA', 6, ''),
(283, 'QUÍPAMA', 6, ''),
(284, 'RAMIRIQUÍ', 6, ''),
(285, 'RÁQUIRA', 6, ''),
(286, 'RONDÓN', 6, ''),
(287, 'SABOYÁ', 6, ''),
(288, 'SÁCHICA', 6, ''),
(289, 'SAMACÁ', 6, ''),
(290, 'SAN EDUARDO', 6, ''),
(291, 'SAN JOSÉ DE PARE', 6, ''),
(292, 'SAN LUÍS DE GACENO', 6, ''),
(293, 'SAN MATEO', 6, ''),
(294, 'SAN MIGUEL DE SEMA', 6, ''),
(295, 'SAN PABLO DE BORBUR', 6, ''),
(296, 'SANTA MARÍA', 6, ''),
(297, 'SANTA ROSA DE VITERBO', 6, ''),
(298, 'SANTA SOFÍA', 6, ''),
(299, 'SANTANA', 6, ''),
(300, 'SATIVANORTE', 6, ''),
(301, 'SATIVASUR', 6, ''),
(302, 'SIACHOQUE', 6, ''),
(303, 'SOATÁ', 6, ''),
(304, 'SOCHA', 6, ''),
(305, 'SOCOTÁ', 6, ''),
(306, 'SOGAMOSO', 6, ''),
(307, 'SORA', 6, ''),
(308, 'SORACÁ', 6, ''),
(309, 'SOTAQUIRÁ', 6, ''),
(310, 'SUSACÓN', 6, ''),
(311, 'SUTARMACHÁN', 6, ''),
(312, 'TASCO', 6, ''),
(313, 'TIBANÁ', 6, ''),
(314, 'TIBASOSA', 6, ''),
(315, 'TINJACÁ', 6, ''),
(316, 'TIPACOQUE', 6, ''),
(317, 'TOCA', 6, ''),
(318, 'TOGÜÍ', 6, ''),
(319, 'TÓPAGA', 6, ''),
(320, 'TOTA', 6, ''),
(321, 'TUNJA', 6, ''),
(322, 'TUNUNGUÁ', 6, ''),
(323, 'TURMEQUÉ', 6, ''),
(324, 'TUTA', 6, ''),
(325, 'TUTAZÁ', 6, ''),
(326, 'UMBITA', 6, ''),
(327, 'VENTA QUEMADA', 6, ''),
(328, 'VILLA DE LEYVA', 6, ''),
(329, 'VIRACACHÁ', 6, ''),
(330, 'ZETAQUIRA', 6, ''),
(331, 'AGUADAS', 7, ''),
(332, 'ANSERMA', 7, ''),
(333, 'ARANZAZU', 7, ''),
(334, 'BELALCAZAR', 7, ''),
(335, 'CHINCHINÁ', 7, ''),
(336, 'FILADELFIA', 7, ''),
(337, 'LA DORADA', 7, ''),
(338, 'LA MERCED', 7, ''),
(339, 'MANIZALES', 7, ''),
(340, 'MANZANARES', 7, ''),
(341, 'MARMATO', 7, ''),
(342, 'MARQUETALIA', 7, ''),
(343, 'MARULANDA', 7, ''),
(344, 'NEIRA', 7, ''),
(345, 'NORCASIA', 7, ''),
(346, 'PACORA', 7, ''),
(347, 'PALESTINA', 7, ''),
(348, 'PENSILVANIA', 7, ''),
(349, 'RIOSUCIO', 7, ''),
(350, 'RISARALDA', 7, ''),
(351, 'SALAMINA', 7, ''),
(352, 'SAMANA', 7, ''),
(353, 'SAN JOSE', 7, ''),
(354, 'SUPÍA', 7, ''),
(355, 'VICTORIA', 7, ''),
(356, 'VILLAMARÍA', 7, ''),
(357, 'VITERBO', 7, ''),
(358, 'ALBANIA', 8, ''),
(359, 'BELÉN ANDAQUIES', 8, ''),
(360, 'CARTAGENA DEL CHAIRA', 8, ''),
(361, 'CURILLO', 8, ''),
(362, 'EL DONCELLO', 8, ''),
(363, 'EL PAUJIL', 8, ''),
(364, 'FLORENCIA', 8, ''),
(365, 'LA MONTAÑITA', 8, ''),
(366, 'MILÁN', 8, ''),
(367, 'MORELIA', 8, ''),
(368, 'PUERTO RICO', 8, ''),
(369, 'SAN  VICENTE DEL CAGUAN', 8, ''),
(370, 'SAN JOSÉ DE FRAGUA', 8, ''),
(371, 'SOLANO', 8, ''),
(372, 'SOLITA', 8, ''),
(373, 'VALPARAÍSO', 8, ''),
(374, 'AGUAZUL', 9, ''),
(375, 'CHAMEZA', 9, ''),
(376, 'HATO COROZAL', 9, ''),
(377, 'LA SALINA', 9, ''),
(378, 'MANÍ', 9, ''),
(379, 'MONTERREY', 9, ''),
(380, 'NUNCHIA', 9, ''),
(381, 'OROCUE', 9, ''),
(382, 'PAZ DE ARIPORO', 9, ''),
(383, 'PORE', 9, ''),
(384, 'RECETOR', 9, ''),
(385, 'SABANA LARGA', 9, ''),
(386, 'SACAMA', 9, ''),
(387, 'SAN LUIS DE PALENQUE', 9, ''),
(388, 'TAMARA', 9, ''),
(389, 'TAURAMENA', 9, ''),
(390, 'TRINIDAD', 9, ''),
(391, 'VILLANUEVA', 9, ''),
(392, 'YOPAL', 9, ''),
(393, 'ALMAGUER', 10, ''),
(394, 'ARGELIA', 10, ''),
(395, 'BALBOA', 10, ''),
(396, 'BOLÍVAR', 10, ''),
(397, 'BUENOS AIRES', 10, ''),
(398, 'CAJIBIO', 10, ''),
(399, 'CALDONO', 10, ''),
(400, 'CALOTO', 10, ''),
(401, 'CORINTO', 10, ''),
(402, 'EL TAMBO', 10, ''),
(403, 'FLORENCIA', 10, ''),
(404, 'GUAPI', 10, ''),
(405, 'INZA', 10, ''),
(406, 'JAMBALÓ', 10, ''),
(407, 'LA SIERRA', 10, ''),
(408, 'LA VEGA', 10, ''),
(409, 'LÓPEZ', 10, ''),
(410, 'MERCADERES', 10, ''),
(411, 'MIRANDA', 10, ''),
(412, 'MORALES', 10, ''),
(413, 'PADILLA', 10, ''),
(414, 'PÁEZ', 10, ''),
(415, 'PATIA (EL BORDO)', 10, ''),
(416, 'PIAMONTE', 10, ''),
(417, 'PIENDAMO', 10, ''),
(418, 'POPAYÁN', 10, ''),
(419, 'PUERTO TEJADA', 10, ''),
(420, 'PURACE', 10, ''),
(421, 'ROSAS', 10, ''),
(422, 'SAN SEBASTIÁN', 10, ''),
(423, 'SANTA ROSA', 10, ''),
(424, 'SANTANDER DE QUILICHAO', 10, ''),
(425, 'SILVIA', 10, ''),
(426, 'SOTARA', 10, ''),
(427, 'SUÁREZ', 10, ''),
(428, 'SUCRE', 10, ''),
(429, 'TIMBÍO', 10, ''),
(430, 'TIMBIQUÍ', 10, ''),
(431, 'TORIBIO', 10, ''),
(432, 'TOTORO', 10, ''),
(433, 'VILLA RICA', 10, ''),
(434, 'AGUACHICA', 11, ''),
(435, 'AGUSTÍN CODAZZI', 11, ''),
(436, 'ASTREA', 11, ''),
(437, 'BECERRIL', 11, ''),
(438, 'BOSCONIA', 11, ''),
(439, 'CHIMICHAGUA', 11, ''),
(440, 'CHIRIGUANÁ', 11, ''),
(441, 'CURUMANÍ', 11, ''),
(442, 'EL COPEY', 11, ''),
(443, 'EL PASO', 11, ''),
(444, 'GAMARRA', 11, ''),
(445, 'GONZÁLEZ', 11, ''),
(446, 'LA GLORIA', 11, ''),
(447, 'LA JAGUA IBIRICO', 11, ''),
(448, 'MANAURE BALCÓN DEL CESAR', 11, ''),
(449, 'PAILITAS', 11, ''),
(450, 'PELAYA', 11, ''),
(451, 'PUEBLO BELLO', 11, ''),
(452, 'RÍO DE ORO', 11, ''),
(453, 'ROBLES (LA PAZ)', 11, ''),
(454, 'SAN ALBERTO', 11, ''),
(455, 'SAN DIEGO', 11, ''),
(456, 'SAN MARTÍN', 11, ''),
(457, 'TAMALAMEQUE', 11, ''),
(458, 'VALLEDUPAR', 11, ''),
(459, 'ACANDI', 12, ''),
(460, 'ALTO BAUDO (PIE DE PATO)', 12, ''),
(461, 'ATRATO', 12, ''),
(462, 'BAGADO', 12, ''),
(463, 'BAHIA SOLANO (MUTIS)', 12, ''),
(464, 'BAJO BAUDO (PIZARRO)', 12, ''),
(465, 'BOJAYA (BELLAVISTA)', 12, ''),
(466, 'CANTON DE SAN PABLO', 12, ''),
(467, 'CARMEN DEL DARIEN', 12, ''),
(468, 'CERTEGUI', 12, ''),
(469, 'CONDOTO', 12, ''),
(470, 'EL CARMEN', 12, ''),
(471, 'ISTMINA', 12, ''),
(472, 'JURADO', 12, ''),
(473, 'LITORAL DEL SAN JUAN', 12, ''),
(474, 'LLORO', 12, ''),
(475, 'MEDIO ATRATO', 12, ''),
(476, 'MEDIO BAUDO (BOCA DE PEPE)', 12, ''),
(477, 'MEDIO SAN JUAN', 12, ''),
(478, 'NOVITA', 12, ''),
(479, 'NUQUI', 12, ''),
(480, 'QUIBDO', 12, ''),
(481, 'RIO IRO', 12, ''),
(482, 'RIO QUITO', 12, ''),
(483, 'RIOSUCIO', 12, ''),
(484, 'SAN JOSE DEL PALMAR', 12, ''),
(485, 'SIPI', 12, ''),
(486, 'TADO', 12, ''),
(487, 'UNGUIA', 12, ''),
(488, 'UNIÓN PANAMERICANA', 12, ''),
(489, 'AYAPEL', 13, ''),
(490, 'BUENAVISTA', 13, ''),
(491, 'CANALETE', 13, ''),
(492, 'CERETÉ', 13, ''),
(493, 'CHIMA', 13, ''),
(494, 'CHINÚ', 13, ''),
(495, 'CIENAGA DE ORO', 13, ''),
(496, 'COTORRA', 13, ''),
(497, 'LA APARTADA', 13, ''),
(498, 'LORICA', 13, ''),
(499, 'LOS CÓRDOBAS', 13, ''),
(500, 'MOMIL', 13, ''),
(501, 'MONTELÍBANO', 13, ''),
(502, 'MONTERÍA', 13, ''),
(503, 'MOÑITOS', 13, ''),
(504, 'PLANETA RICA', 13, ''),
(505, 'PUEBLO NUEVO', 13, ''),
(506, 'PUERTO ESCONDIDO', 13, ''),
(507, 'PUERTO LIBERTADOR', 13, ''),
(508, 'PURÍSIMA', 13, ''),
(509, 'SAHAGÚN', 13, ''),
(510, 'SAN ANDRÉS SOTAVENTO', 13, ''),
(511, 'SAN ANTERO', 13, ''),
(512, 'SAN BERNARDO VIENTO', 13, ''),
(513, 'SAN CARLOS', 13, ''),
(514, 'SAN PELAYO', 13, ''),
(515, 'TIERRALTA', 13, ''),
(516, 'VALENCIA', 13, ''),
(517, 'AGUA DE DIOS', 14, ''),
(518, 'ALBAN', 14, ''),
(519, 'ANAPOIMA', 14, ''),
(520, 'ANOLAIMA', 14, ''),
(521, 'ARBELAEZ', 14, ''),
(522, 'BELTRÁN', 14, ''),
(523, 'BITUIMA', 14, ''),
(524, 'BOGOTÁ DC', 14, ''),
(525, 'BOJACÁ', 14, ''),
(526, 'CABRERA', 14, ''),
(527, 'CACHIPAY', 14, ''),
(528, 'CAJICÁ', 14, ''),
(529, 'CAPARRAPÍ', 14, ''),
(530, 'CAQUEZA', 14, ''),
(531, 'CARMEN DE CARUPA', 14, ''),
(532, 'CHAGUANÍ', 14, ''),
(533, 'CHIA', 14, ''),
(534, 'CHIPAQUE', 14, ''),
(535, 'CHOACHÍ', 14, ''),
(536, 'CHOCONTÁ', 14, ''),
(537, 'COGUA', 14, ''),
(538, 'COTA', 14, ''),
(539, 'CUCUNUBÁ', 14, ''),
(540, 'EL COLEGIO', 14, ''),
(541, 'EL PEÑÓN', 14, ''),
(542, 'EL ROSAL1', 14, ''),
(543, 'FACATATIVA', 14, ''),
(544, 'FÓMEQUE', 14, ''),
(545, 'FOSCA', 14, ''),
(546, 'FUNZA', 14, ''),
(547, 'FÚQUENE', 14, ''),
(548, 'FUSAGASUGA', 14, ''),
(549, 'GACHALÁ', 14, ''),
(550, 'GACHANCIPÁ', 14, ''),
(551, 'GACHETA', 14, ''),
(552, 'GAMA', 14, ''),
(553, 'GIRARDOT', 14, ''),
(554, 'GRANADA2', 14, ''),
(555, 'GUACHETÁ', 14, ''),
(556, 'GUADUAS', 14, ''),
(557, 'GUASCA', 14, ''),
(558, 'GUATAQUÍ', 14, ''),
(559, 'GUATAVITA', 14, ''),
(560, 'GUAYABAL DE SIQUIMA', 14, ''),
(561, 'GUAYABETAL', 14, ''),
(562, 'GUTIÉRREZ', 14, ''),
(563, 'JERUSALÉN', 14, ''),
(564, 'JUNÍN', 14, ''),
(565, 'LA CALERA', 14, ''),
(566, 'LA MESA', 14, ''),
(567, 'LA PALMA', 14, ''),
(568, 'LA PEÑA', 14, ''),
(569, 'LA VEGA', 14, ''),
(570, 'LENGUAZAQUE', 14, ''),
(571, 'MACHETÁ', 14, ''),
(572, 'MADRID', 14, ''),
(573, 'MANTA', 14, ''),
(574, 'MEDINA', 14, ''),
(575, 'MOSQUERA', 14, ''),
(576, 'NARIÑO', 14, ''),
(577, 'NEMOCÓN', 14, ''),
(578, 'NILO', 14, ''),
(579, 'NIMAIMA', 14, ''),
(580, 'NOCAIMA', 14, ''),
(581, 'OSPINA PÉREZ', 14, ''),
(582, 'PACHO', 14, ''),
(583, 'PAIME', 14, ''),
(584, 'PANDI', 14, ''),
(585, 'PARATEBUENO', 14, ''),
(586, 'PASCA', 14, ''),
(587, 'PUERTO SALGAR', 14, ''),
(588, 'PULÍ', 14, ''),
(589, 'QUEBRADANEGRA', 14, ''),
(590, 'QUETAME', 14, ''),
(591, 'QUIPILE', 14, ''),
(592, 'RAFAEL REYES', 14, ''),
(593, 'RICAURTE', 14, ''),
(594, 'SAN  ANTONIO DEL  TEQUENDAMA', 14, ''),
(595, 'SAN BERNARDO', 14, ''),
(596, 'SAN CAYETANO', 14, ''),
(597, 'SAN FRANCISCO', 14, ''),
(598, 'SAN JUAN DE RIOSECO', 14, ''),
(599, 'SASAIMA', 14, ''),
(600, 'SESQUILÉ', 14, ''),
(601, 'SIBATÉ', 14, ''),
(602, 'SILVANIA', 14, ''),
(603, 'SIMIJACA', 14, ''),
(604, 'SOACHA', 14, ''),
(605, 'SOPO', 14, ''),
(606, 'SUBACHOQUE', 14, ''),
(607, 'SUESCA', 14, ''),
(608, 'SUPATÁ', 14, ''),
(609, 'SUSA', 14, ''),
(610, 'SUTATAUSA', 14, ''),
(611, 'TABIO', 14, ''),
(612, 'TAUSA', 14, ''),
(613, 'TENA', 14, ''),
(614, 'TENJO', 14, ''),
(615, 'TIBACUY', 14, ''),
(616, 'TIBIRITA', 14, ''),
(617, 'TOCAIMA', 14, ''),
(618, 'TOCANCIPÁ', 14, ''),
(619, 'TOPAIPÍ', 14, ''),
(620, 'UBALÁ', 14, ''),
(621, 'UBAQUE', 14, ''),
(622, 'UBATÉ', 14, ''),
(623, 'UNE', 14, ''),
(624, 'UTICA', 14, ''),
(625, 'VERGARA', 14, ''),
(626, 'VIANI', 14, ''),
(627, 'VILLA GOMEZ', 14, ''),
(628, 'VILLA PINZÓN', 14, ''),
(629, 'VILLETA', 14, ''),
(630, 'VIOTA', 14, ''),
(631, 'YACOPÍ', 14, ''),
(632, 'ZIPACÓN', 14, ''),
(633, 'ZIPAQUIRÁ', 14, ''),
(634, 'BARRANCO MINAS', 15, ''),
(635, 'CACAHUAL', 15, ''),
(636, 'INÍRIDA', 15, ''),
(637, 'LA GUADALUPE', 15, ''),
(638, 'MAPIRIPANA', 15, ''),
(639, 'MORICHAL', 15, ''),
(640, 'PANA PANA', 15, ''),
(641, 'PUERTO COLOMBIA', 15, ''),
(642, 'SAN FELIPE', 15, ''),
(643, 'CALAMAR', 16, ''),
(644, 'EL RETORNO', 16, ''),
(645, 'MIRAFLOREZ', 16, ''),
(646, 'SAN JOSÉ DEL GUAVIARE', 16, ''),
(647, 'ACEVEDO', 17, ''),
(648, 'AGRADO', 17, ''),
(649, 'AIPE', 17, ''),
(650, 'ALGECIRAS', 17, ''),
(651, 'ALTAMIRA', 17, ''),
(652, 'BARAYA', 17, ''),
(653, 'CAMPO ALEGRE', 17, ''),
(654, 'COLOMBIA', 17, ''),
(655, 'ELIAS', 17, ''),
(656, 'GARZÓN', 17, ''),
(657, 'GIGANTE', 17, ''),
(658, 'GUADALUPE', 17, ''),
(659, 'HOBO', 17, ''),
(660, 'IQUIRA', 17, ''),
(661, 'ISNOS', 17, ''),
(662, 'LA ARGENTINA', 17, ''),
(663, 'LA PLATA', 17, ''),
(664, 'NATAGA', 17, ''),
(665, 'NEIVA', 17, ''),
(666, 'OPORAPA', 17, ''),
(667, 'PAICOL', 17, ''),
(668, 'PALERMO', 17, ''),
(669, 'PALESTINA', 17, ''),
(670, 'PITAL', 17, ''),
(671, 'PITALITO', 17, ''),
(672, 'RIVERA', 17, ''),
(673, 'SALADO BLANCO', 17, ''),
(674, 'SAN AGUSTÍN', 17, ''),
(675, 'SANTA MARIA', 17, ''),
(676, 'SUAZA', 17, ''),
(677, 'TARQUI', 17, ''),
(678, 'TELLO', 17, ''),
(679, 'TERUEL', 17, ''),
(680, 'TESALIA', 17, ''),
(681, 'TIMANA', 17, ''),
(682, 'VILLAVIEJA', 17, ''),
(683, 'YAGUARA', 17, ''),
(684, 'ALBANIA', 18, ''),
(685, 'BARRANCAS', 18, ''),
(686, 'DIBULLA', 18, ''),
(687, 'DISTRACCIÓN', 18, ''),
(688, 'EL MOLINO', 18, ''),
(689, 'FONSECA', 18, ''),
(690, 'HATO NUEVO', 18, ''),
(691, 'LA JAGUA DEL PILAR', 18, ''),
(692, 'MAICAO', 18, ''),
(693, 'MANAURE', 18, ''),
(694, 'RIOHACHA', 18, ''),
(695, 'SAN JUAN DEL CESAR', 18, ''),
(696, 'URIBIA', 18, ''),
(697, 'URUMITA', 18, ''),
(698, 'VILLANUEVA', 18, ''),
(699, 'ALGARROBO', 19, ''),
(700, 'ARACATACA', 19, ''),
(701, 'ARIGUANI', 19, ''),
(702, 'CERRO SAN ANTONIO', 19, ''),
(703, 'CHIVOLO', 19, ''),
(704, 'CIENAGA', 19, ''),
(705, 'CONCORDIA', 19, ''),
(706, 'EL BANCO', 19, ''),
(707, 'EL PIÑON', 19, ''),
(708, 'EL RETEN', 19, ''),
(709, 'FUNDACION', 19, ''),
(710, 'GUAMAL', 19, ''),
(711, 'NUEVA GRANADA', 19, ''),
(712, 'PEDRAZA', 19, ''),
(713, 'PIJIÑO DEL CARMEN', 19, ''),
(714, 'PIVIJAY', 19, ''),
(715, 'PLATO', 19, ''),
(716, 'PUEBLO VIEJO', 19, ''),
(717, 'REMOLINO', 19, ''),
(718, 'SABANAS DE SAN ANGEL', 19, ''),
(719, 'SALAMINA', 19, ''),
(720, 'SAN SEBASTIAN DE BUENAVISTA', 19, ''),
(721, 'SAN ZENON', 19, ''),
(722, 'SANTA ANA', 19, ''),
(723, 'SANTA BARBARA DE PINTO', 19, ''),
(724, 'SANTA MARTA', 19, ''),
(725, 'SITIONUEVO', 19, ''),
(726, 'TENERIFE', 19, ''),
(727, 'ZAPAYAN', 19, ''),
(728, 'ZONA BANANERA', 19, ''),
(729, 'ACACIAS', 20, ''),
(730, 'BARRANCA DE UPIA', 20, ''),
(731, 'CABUYARO', 20, ''),
(732, 'CASTILLA LA NUEVA', 20, ''),
(733, 'CUBARRAL', 20, ''),
(734, 'CUMARAL', 20, ''),
(735, 'EL CALVARIO', 20, ''),
(736, 'EL CASTILLO', 20, ''),
(737, 'EL DORADO', 20, ''),
(738, 'FUENTE DE ORO', 20, ''),
(739, 'GRANADA', 20, ''),
(740, 'GUAMAL', 20, ''),
(741, 'LA MACARENA', 20, ''),
(742, 'LA URIBE', 20, ''),
(743, 'LEJANÍAS', 20, ''),
(744, 'MAPIRIPÁN', 20, ''),
(745, 'MESETAS', 20, ''),
(746, 'PUERTO CONCORDIA', 20, ''),
(747, 'PUERTO GAITÁN', 20, ''),
(748, 'PUERTO LLERAS', 20, ''),
(749, 'PUERTO LÓPEZ', 20, ''),
(750, 'PUERTO RICO', 20, ''),
(751, 'RESTREPO', 20, ''),
(752, 'SAN  JUAN DE ARAMA', 20, ''),
(753, 'SAN CARLOS GUAROA', 20, ''),
(754, 'SAN JUANITO', 20, ''),
(755, 'SAN MARTÍN', 20, ''),
(756, 'VILLAVICENCIO', 20, ''),
(757, 'VISTA HERMOSA', 20, ''),
(758, 'ALBAN', 21, ''),
(759, 'ALDAÑA', 21, ''),
(760, 'ANCUYA', 21, ''),
(761, 'ARBOLEDA', 21, ''),
(762, 'BARBACOAS', 21, ''),
(763, 'BELEN', 21, ''),
(764, 'BUESACO', 21, ''),
(765, 'CHACHAGUI', 21, ''),
(766, 'COLON (GENOVA)', 21, ''),
(767, 'CONSACA', 21, ''),
(768, 'CONTADERO', 21, ''),
(769, 'CORDOBA', 21, ''),
(770, 'CUASPUD', 21, ''),
(771, 'CUMBAL', 21, ''),
(772, 'CUMBITARA', 21, ''),
(773, 'EL CHARCO', 21, ''),
(774, 'EL PEÑOL', 21, ''),
(775, 'EL ROSARIO', 21, ''),
(776, 'EL TABLÓN', 21, ''),
(777, 'EL TAMBO', 21, ''),
(778, 'FUNES', 21, ''),
(779, 'GUACHUCAL', 21, ''),
(780, 'GUAITARILLA', 21, ''),
(781, 'GUALMATAN', 21, ''),
(782, 'ILES', 21, ''),
(783, 'IMUES', 21, ''),
(784, 'IPIALES', 21, ''),
(785, 'LA CRUZ', 21, ''),
(786, 'LA FLORIDA', 21, ''),
(787, 'LA LLANADA', 21, ''),
(788, 'LA TOLA', 21, ''),
(789, 'LA UNION', 21, ''),
(790, 'LEIVA', 21, ''),
(791, 'LINARES', 21, ''),
(792, 'LOS ANDES', 21, ''),
(793, 'MAGUI', 21, ''),
(794, 'MALLAMA', 21, ''),
(795, 'MOSQUEZA', 21, ''),
(796, 'NARIÑO', 21, ''),
(797, 'OLAYA HERRERA', 21, ''),
(798, 'OSPINA', 21, ''),
(799, 'PASTO', 21, ''),
(800, 'PIZARRO', 21, ''),
(801, 'POLICARPA', 21, ''),
(802, 'POTOSI', 21, ''),
(803, 'PROVIDENCIA', 21, ''),
(804, 'PUERRES', 21, ''),
(805, 'PUPIALES', 21, ''),
(806, 'RICAURTE', 21, ''),
(807, 'ROBERTO PAYAN', 21, ''),
(808, 'SAMANIEGO', 21, ''),
(809, 'SAN BERNARDO', 21, ''),
(810, 'SAN LORENZO', 21, ''),
(811, 'SAN PABLO', 21, ''),
(812, 'SAN PEDRO DE CARTAGO', 21, ''),
(813, 'SANDONA', 21, ''),
(814, 'SANTA BARBARA', 21, ''),
(815, 'SANTACRUZ', 21, ''),
(816, 'SAPUYES', 21, ''),
(817, 'TAMINANGO', 21, ''),
(818, 'TANGUA', 21, ''),
(819, 'TUMACO', 21, ''),
(820, 'TUQUERRES', 21, ''),
(821, 'YACUANQUER', 21, ''),
(822, 'ABREGO', 22, ''),
(823, 'ARBOLEDAS', 22, ''),
(824, 'BOCHALEMA', 22, ''),
(825, 'BUCARASICA', 22, ''),
(826, 'CÁCHIRA', 22, ''),
(827, 'CÁCOTA', 22, ''),
(828, 'CHINÁCOTA', 22, ''),
(829, 'CHITAGÁ', 22, ''),
(830, 'CONVENCIÓN', 22, ''),
(831, 'CÚCUTA', 22, ''),
(832, 'CUCUTILLA', 22, ''),
(833, 'DURANIA', 22, ''),
(834, 'EL CARMEN', 22, ''),
(835, 'EL TARRA', 22, ''),
(836, 'EL ZULIA', 22, ''),
(837, 'GRAMALOTE', 22, ''),
(838, 'HACARI', 22, ''),
(839, 'HERRÁN', 22, ''),
(840, 'LA ESPERANZA', 22, ''),
(841, 'LA PLAYA', 22, ''),
(842, 'LABATECA', 22, ''),
(843, 'LOS PATIOS', 22, ''),
(844, 'LOURDES', 22, ''),
(845, 'MUTISCUA', 22, ''),
(846, 'OCAÑA', 22, ''),
(847, 'PAMPLONA', 22, ''),
(848, 'PAMPLONITA', 22, ''),
(849, 'PUERTO SANTANDER', 22, ''),
(850, 'RAGONVALIA', 22, ''),
(851, 'SALAZAR', 22, ''),
(852, 'SAN CALIXTO', 22, ''),
(853, 'SAN CAYETANO', 22, ''),
(854, 'SANTIAGO', 22, ''),
(855, 'SARDINATA', 22, ''),
(856, 'SILOS', 22, ''),
(857, 'TEORAMA', 22, ''),
(858, 'TIBÚ', 22, ''),
(859, 'TOLEDO', 22, ''),
(860, 'VILLA CARO', 22, ''),
(861, 'VILLA DEL ROSARIO', 22, ''),
(862, 'COLÓN', 23, ''),
(863, 'MOCOA', 23, ''),
(864, 'ORITO', 23, ''),
(865, 'PUERTO ASÍS', 23, ''),
(866, 'PUERTO CAYCEDO', 23, ''),
(867, 'PUERTO GUZMÁN', 23, ''),
(868, 'PUERTO LEGUÍZAMO', 23, ''),
(869, 'SAN FRANCISCO', 23, ''),
(870, 'SAN MIGUEL', 23, ''),
(871, 'SANTIAGO', 23, ''),
(872, 'SIBUNDOY', 23, ''),
(873, 'VALLE DEL GUAMUEZ', 23, ''),
(874, 'VILLAGARZÓN', 23, ''),
(875, 'ARMENIA', 24, ''),
(876, 'BUENAVISTA', 24, ''),
(877, 'CALARCÁ', 24, ''),
(878, 'CIRCASIA', 24, ''),
(879, 'CÓRDOBA', 24, ''),
(880, 'FILANDIA', 24, ''),
(881, 'GÉNOVA', 24, ''),
(882, 'LA TEBAIDA', 24, ''),
(883, 'MONTENEGRO', 24, ''),
(884, 'PIJAO', 24, ''),
(885, 'QUIMBAYA', 24, ''),
(886, 'SALENTO', 24, ''),
(887, 'APIA', 25, ''),
(888, 'BALBOA', 25, ''),
(889, 'BELÉN DE UMBRÍA', 25, ''),
(890, 'DOS QUEBRADAS', 25, ''),
(891, 'GUATICA', 25, ''),
(892, 'LA CELIA', 25, ''),
(893, 'LA VIRGINIA', 25, ''),
(894, 'MARSELLA', 25, ''),
(895, 'MISTRATO', 25, ''),
(896, 'PEREIRA', 25, ''),
(897, 'PUEBLO RICO', 25, ''),
(898, 'QUINCHÍA', 25, ''),
(899, 'SANTA ROSA DE CABAL', 25, ''),
(900, 'SANTUARIO', 25, ''),
(901, 'PROVIDENCIA', 26, ''),
(902, 'SAN ANDRES', 26, ''),
(903, 'SANTA CATALINA', 26, ''),
(904, 'AGUADA', 27, ''),
(905, 'ALBANIA', 27, ''),
(906, 'ARATOCA', 27, ''),
(907, 'BARBOSA', 27, ''),
(908, 'BARICHARA', 27, ''),
(909, 'BARRANCABERMEJA', 27, ''),
(910, 'BETULIA', 27, ''),
(911, 'BOLÍVAR', 27, ''),
(912, 'BUCARAMANGA', 27, ''),
(913, 'CABRERA', 27, ''),
(914, 'CALIFORNIA', 27, ''),
(915, 'CAPITANEJO', 27, ''),
(916, 'CARCASI', 27, ''),
(917, 'CEPITA', 27, ''),
(918, 'CERRITO', 27, ''),
(919, 'CHARALÁ', 27, ''),
(920, 'CHARTA', 27, ''),
(921, 'CHIMA', 27, ''),
(922, 'CHIPATÁ', 27, ''),
(923, 'CIMITARRA', 27, ''),
(924, 'CONCEPCIÓN', 27, ''),
(925, 'CONFINES', 27, ''),
(926, 'CONTRATACIÓN', 27, ''),
(927, 'COROMORO', 27, ''),
(928, 'CURITÍ', 27, ''),
(929, 'EL CARMEN', 27, ''),
(930, 'EL GUACAMAYO', 27, ''),
(931, 'EL PEÑÓN', 27, ''),
(932, 'EL PLAYÓN', 27, ''),
(933, 'ENCINO', 27, ''),
(934, 'ENCISO', 27, ''),
(935, 'FLORIÁN', 27, ''),
(936, 'FLORIDABLANCA', 27, ''),
(937, 'GALÁN', 27, ''),
(938, 'GAMBITA', 27, ''),
(939, 'GIRÓN', 27, ''),
(940, 'GUACA', 27, ''),
(941, 'GUADALUPE', 27, ''),
(942, 'GUAPOTA', 27, ''),
(943, 'GUAVATÁ', 27, ''),
(944, 'GUEPSA', 27, ''),
(945, 'HATO', 27, ''),
(946, 'JESÚS MARIA', 27, ''),
(947, 'JORDÁN', 27, ''),
(948, 'LA BELLEZA', 27, ''),
(949, 'LA PAZ', 27, ''),
(950, 'LANDAZURI', 27, ''),
(951, 'LEBRIJA', 27, ''),
(952, 'LOS SANTOS', 27, ''),
(953, 'MACARAVITA', 27, ''),
(954, 'MÁLAGA', 27, ''),
(955, 'MATANZA', 27, ''),
(956, 'MOGOTES', 27, ''),
(957, 'MOLAGAVITA', 27, ''),
(958, 'OCAMONTE', 27, ''),
(959, 'OIBA', 27, ''),
(960, 'ONZAGA', 27, ''),
(961, 'PALMAR', 27, ''),
(962, 'PALMAS DEL SOCORRO', 27, ''),
(963, 'PÁRAMO', 27, ''),
(964, 'PIEDECUESTA', 27, ''),
(965, 'PINCHOTE', 27, ''),
(966, 'PUENTE NACIONAL', 27, ''),
(967, 'PUERTO PARRA', 27, ''),
(968, 'PUERTO WILCHES', 27, ''),
(969, 'RIONEGRO', 27, ''),
(970, 'SABANA DE TORRES', 27, ''),
(971, 'SAN ANDRÉS', 27, ''),
(972, 'SAN BENITO', 27, ''),
(973, 'SAN GIL', 27, ''),
(974, 'SAN JOAQUÍN', 27, ''),
(975, 'SAN JOSÉ DE MIRANDA', 27, ''),
(976, 'SAN MIGUEL', 27, ''),
(977, 'SAN VICENTE DE CHUCURÍ', 27, ''),
(978, 'SANTA BÁRBARA', 27, ''),
(979, 'SANTA HELENA', 27, ''),
(980, 'SIMACOTA', 27, ''),
(981, 'SOCORRO', 27, ''),
(982, 'SUAITA', 27, ''),
(983, 'SUCRE', 27, ''),
(984, 'SURATA', 27, ''),
(985, 'TONA', 27, ''),
(986, 'VALLE SAN JOSÉ', 27, ''),
(987, 'VÉLEZ', 27, ''),
(988, 'VETAS', 27, ''),
(989, 'VILLANUEVA', 27, ''),
(990, 'ZAPATOCA', 27, ''),
(991, 'BUENAVISTA', 28, ''),
(992, 'CAIMITO', 28, ''),
(993, 'CHALÁN', 28, ''),
(994, 'COLOSO', 28, ''),
(995, 'COROZAL', 28, ''),
(996, 'EL ROBLE', 28, ''),
(997, 'GALERAS', 28, ''),
(998, 'GUARANDA', 28, ''),
(999, 'LA UNIÓN', 28, ''),
(1000, 'LOS PALMITOS', 28, ''),
(1001, 'MAJAGUAL', 28, ''),
(1002, 'MORROA', 28, ''),
(1003, 'OVEJAS', 28, ''),
(1004, 'PALMITO', 28, ''),
(1005, 'SAMPUES', 28, ''),
(1006, 'SAN BENITO ABAD', 28, ''),
(1007, 'SAN JUAN DE BETULIA', 28, ''),
(1008, 'SAN MARCOS', 28, ''),
(1009, 'SAN ONOFRE', 28, ''),
(1010, 'SAN PEDRO', 28, ''),
(1011, 'SINCÉ', 28, ''),
(1012, 'SINCELEJO', 28, ''),
(1013, 'SUCRE', 28, ''),
(1014, 'TOLÚ', 28, ''),
(1015, 'TOLUVIEJO', 28, ''),
(1016, 'ALPUJARRA', 29, ''),
(1017, 'ALVARADO', 29, ''),
(1018, 'AMBALEMA', 29, ''),
(1019, 'ANZOATEGUI', 29, ''),
(1020, 'ARMERO (GUAYABAL)', 29, ''),
(1021, 'ATACO', 29, ''),
(1022, 'CAJAMARCA', 29, ''),
(1023, 'CARMEN DE APICALÁ', 29, ''),
(1024, 'CASABIANCA', 29, ''),
(1025, 'CHAPARRAL', 29, ''),
(1026, 'COELLO', 29, ''),
(1027, 'COYAIMA', 29, ''),
(1028, 'CUNDAY', 29, ''),
(1029, 'DOLORES', 29, ''),
(1030, 'ESPINAL', 29, ''),
(1031, 'FALÁN', 29, ''),
(1032, 'FLANDES', 29, ''),
(1033, 'FRESNO', 29, ''),
(1034, 'GUAMO', 29, ''),
(1035, 'HERVEO', 29, ''),
(1036, 'HONDA', 29, ''),
(1037, 'IBAGUÉ', 29, ''),
(1038, 'ICONONZO', 29, ''),
(1039, 'LÉRIDA', 29, ''),
(1040, 'LÍBANO', 29, ''),
(1041, 'MARIQUITA', 29, ''),
(1042, 'MELGAR', 29, ''),
(1043, 'MURILLO', 29, ''),
(1044, 'NATAGAIMA', 29, ''),
(1045, 'ORTEGA', 29, ''),
(1046, 'PALOCABILDO', 29, ''),
(1047, 'PIEDRAS PLANADAS', 29, ''),
(1048, 'PRADO', 29, ''),
(1049, 'PURIFICACIÓN', 29, ''),
(1050, 'RIOBLANCO', 29, ''),
(1051, 'RONCESVALLES', 29, ''),
(1052, 'ROVIRA', 29, ''),
(1053, 'SALDAÑA', 29, ''),
(1054, 'SAN ANTONIO', 29, ''),
(1055, 'SAN LUIS', 29, ''),
(1056, 'SANTA ISABEL', 29, ''),
(1057, 'SUÁREZ', 29, ''),
(1058, 'VALLE DE SAN JUAN', 29, ''),
(1059, 'VENADILLO', 29, ''),
(1060, 'VILLAHERMOSA', 29, ''),
(1061, 'VILLARRICA', 29, ''),
(1062, 'ALCALÁ', 30, ''),
(1063, 'ANDALUCÍA', 30, ''),
(1064, 'ANSERMA NUEVO', 30, ''),
(1065, 'ARGELIA', 30, ''),
(1066, 'BOLÍVAR', 30, ''),
(1067, 'BUENAVENTURA', 30, ''),
(1068, 'BUGA', 30, ''),
(1069, 'BUGALAGRANDE', 30, ''),
(1070, 'CAICEDONIA', 30, ''),
(1071, 'CALI', 30, ''),
(1072, 'CALIMA (DARIEN)', 30, ''),
(1073, 'CANDELARIA', 30, ''),
(1074, 'CARTAGO', 30, ''),
(1075, 'DAGUA', 30, ''),
(1076, 'EL AGUILA', 30, ''),
(1077, 'EL CAIRO', 30, ''),
(1078, 'EL CERRITO', 30, ''),
(1079, 'EL DOVIO', 30, ''),
(1080, 'FLORIDA', 30, ''),
(1081, 'GINEBRA GUACARI', 30, ''),
(1082, 'JAMUNDÍ', 30, ''),
(1083, 'LA CUMBRE', 30, ''),
(1084, 'LA UNIÓN', 30, ''),
(1085, 'LA VICTORIA', 30, ''),
(1086, 'OBANDO', 30, ''),
(1087, 'PALMIRA', 30, ''),
(1088, 'PRADERA', 30, ''),
(1089, 'RESTREPO', 30, ''),
(1090, 'RIO FRÍO', 30, ''),
(1091, 'ROLDANILLO', 30, ''),
(1092, 'SAN PEDRO', 30, ''),
(1093, 'SEVILLA', 30, ''),
(1094, 'TORO', 30, ''),
(1095, 'TRUJILLO', 30, ''),
(1096, 'TULÚA', 30, ''),
(1097, 'ULLOA', 30, ''),
(1098, 'VERSALLES', 30, ''),
(1099, 'VIJES', 30, ''),
(1100, 'YOTOCO', 30, ''),
(1101, 'YUMBO', 30, ''),
(1102, 'ZARZAL', 30, ''),
(1103, 'CARURÚ', 31, ''),
(1104, 'MITÚ', 31, ''),
(1105, 'PACOA', 31, ''),
(1106, 'PAPUNAUA', 31, ''),
(1107, 'TARAIRA', 31, ''),
(1108, 'YAVARATÉ', 31, ''),
(1109, 'CUMARIBO', 32, ''),
(1110, 'LA PRIMAVERA', 32, ''),
(1111, 'PUERTO CARREÑO', 32, ''),
(1112, 'SANTA ROSALIA', 32, ''),
(1113, 'Extranjero ', 0, ''),
(1114, 'Extranjero ', 0, ''),
(1115, 'Extranjero ', 33, ''),
(1116, 'Extranjero ', 33, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `nombre_razon` varchar(100) NOT NULL,
  `identificacion` varchar(15) NOT NULL,
  `direccion` varchar(120) DEFAULT NULL,
  `id_ciudad` int(11) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `telefono1` varchar(10) DEFAULT NULL,
  `telefono2` varchar(10) DEFAULT NULL,
  `fax` varchar(10) DEFAULT NULL,
  `celular` varchar(10) DEFAULT NULL,
  `cliente` tinyint(1) NOT NULL,
  `proveedor` tinyint(1) NOT NULL,
  `observaciones` varchar(100) DEFAULT NULL,
  `id_empresa` varchar(15) NOT NULL,
  `id_tipo_identificacion` int(1) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL,
  `id_nacionalidad` int(11) NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `nombre_razon`, `identificacion`, `direccion`, `id_ciudad`, `correo`, `telefono1`, `telefono2`, `fax`, `celular`, `cliente`, `proveedor`, `observaciones`, `id_empresa`, `id_tipo_identificacion`, `transacion`, `id_nacionalidad`, `fecha_nacimiento`) VALUES
(2, 'AGENCIA DE VIAJES Y TURISMO AVIATUR S.A. ', '8600000182', 'Avenida 19 No. 4 - 62', 1116, NULL, '3817111', '', '', '3817111', 1, 0, '', '51744384-1', 3, '51744384-1_12', 52, NULL),
(3, '	ASOCIACION DE ZOOTECNISTAS DEL VALLE DEL CAUCA "AZOOVALLE"', '8913046670', 'CARRERA 29A # 21-51', 82, NULL, NULL, '', '', '3154853998', 1, 0, '', '51744384-1', 3, '51744384-1_12', 52, NULL),
(4, 'MARIO ALBERTO GALVIS VAHOS', '8010503', 'CARRERA 71A # 9-53 ', 82, NULL, NULL, '', '', '3188640396', 1, 0, '', '51744384-1', 1, '51744384-1_12', 52, NULL),
(7, 'EQUIPO CONTINENTAL DE CICLISMO', '9010395370', 'CARRERA 73 No C1-34', 82, NULL, '4124434', '', '', '4124434', 1, 0, '', '51744384-1', 3, '51744384-1_12', 52, NULL),
(8, 'OLMUE COLOMBIA S.A.S.', '9003430995', 'CALLEJON VIA BOLO LA ITALIA KM. 1 N', 1087, 'info@olmue.com.co', '', '', '', '000', 1, 0, '', '51744384-1', 3, '51744384-1_16', 52, NULL),
(9, 'LINA MACHADO', '53108530', 'CARRERA 80A # 70-61', 82, NULL, NULL, '', '', '3143791870', 1, 0, '', '51744384-1', 1, '51744384-1_12', 52, NULL),
(10, 'ARMAND CARRERA', '15AA71092', '', 82, '', '', '', '', '', 1, 0, '', '51744384-1', 4, '51744384-1_17', 75, '1976-04-09'),
(11, 'ORIENTE S.A.', '8913006712', 'CARRERA 32 # 1-10 HACIENDA LA ITALIA', 1087, NULL, '2705555', '', '', '2705555', 1, 0, '', '51744384-1', 3, '51744384-1_12', 52, NULL),
(13, 'EXPRESO VIAJES Y TURISMO S.A.S', '8002069792', 'CALLE 19 No 70-24  BOGOTA', 1116, 'hoteles3@expresoviajes.com', '7466363', '7466363', '', '7466363', 1, 0, '', '51744384-1', 3, '51744384-1_14', 52, NULL),
(14, ' MARTHA LUCIA GONZALEZ', '31173884', 'CALLE 40 #20-30', 1087, 'MARTHA@HOTMAIL.COM', '', '', '', '3108950777', 1, 0, '', '51744384-1', 1, '51744384-1_18', 52, NULL),
(15, 'CONTROL UNION PERU S.A.C', '20504194796', '', 1116, '', '', '', '', '', 1, 0, '', '51744384-1', 3, '51744384-1_19', 173, '2017-04-05'),
(16, 'ANA MARIA CEDIEL', '1077861939', '208#XMOUTH', 1071, 'ANA@HOTMAIL.COM', '', '', '', '3174852160', 1, 0, '', '51744384-1', 1, '51744384-1_20', 52, NULL),
(17, 'JHONATAN MENESES', '1113694398', 'CR 29 SUR #10A-24', 1087, 'MENESES@HOTMAIL.COM', '', '', '', '3173126415', 1, 0, '', '51744384-1', 1, '51744384-1_21', 52, NULL),
(18, 'FRANKLIN LASPRILLA', '1045739941', 'CALLE 31 No40-59 PALMIRA', 1087, 'frnklin@hotmail.com', '0000000', '', '', '00000000', 1, 0, '', '51744384-1', 1, '51744384-1_22', 52, NULL),
(19, 'CONTROL UNION COLOMBIA LTDA', '8001319964', 'CALLE 77B  No 59-61 EDIF. TORRE AMERICAS 2 OFC.709 BARRANQUILLA', 145, 'alopez@controlunion.com', '3851274', '', '', '3851274', 1, 0, '', '51744384-1', 3, '51744384-1_23', 52, NULL),
(20, 'jesus maria castillo', '17070456', 'k9c #119-48', 524, 'jesusmaria@gmai.com', '', '', '', '3108677657', 1, 0, '', '51744384-1', 1, '51744384-1_24', 52, NULL),
(21, 'LIGA DE ACTIVIDADES SUBACUATICAS DEL META', '9004672340', 'PISCINAS OLIMPICAS META', 1116, 'JORDANGARCIA208@HOTMAIL.COM', '', '', '', '3203040752', 1, 0, '', '51744384-1', 3, '51744384-1_25', 52, NULL),
(22, 'VICTOR MANUEL MONTIEL', '79388388', 'CALLE 18  No86-55 BOGOTA', 1116, 'victor35@hotmail.es', '', '', '', '3134779150', 1, 0, '', '51744384-1', 1, '51744384-1_26', 52, NULL),
(23, 'MICROFERTISA S.A.S', '800013455', 'CALLE 10 N. 90A-54', 654, 'MICRO@HOTMAIL.COM', '4244990', '', '', '3615856470', 1, 0, '', '51744384-1', 3, '51744384-1_27', 52, NULL),
(24, 'LIGA VALLECAUCANA DE ACT.SUBACUATICAS', '8050103008', 'CALLE 5B No34-51', 1071, 'ligavalle@hotmail.com', '5146171', '', '', '5146171', 1, 0, '', '51744384-1', 3, '51744384-1_28', 52, NULL),
(25, 'HOTELS GROUP S.A.S', '9007716190', 'CRA. 29C#10C-100 OFIC 1202', 82, 'RESERVAS@HOTELSGROUP.COM', '43221055', '', '', '3204481015', 1, 0, '', '51744384-1', 3, '51744384-1_29', 52, NULL),
(26, 'ESTRUCTURAS FLEXIBLES S.A.S', '9010163834', 'CALLE 42  No 56-39 OFC. 406 RIONEGRO ANT.', 96, 'estructurasflexibles@hotmail.com', '3107233505', '', '', '3107233505', 1, 0, '', '51744384-1', 3, '51744384-1_30', 52, NULL),
(27, 'LUIS GUILLERMO JARAMILLO', '98547366', 'CRA 75 No 30A-54 MEDELLIN', 82, 'luisg@hotmail.com', '3103982799', '', '', '3103982799', 1, 0, '', '51744384-1', 1, '51744384-1_31', 52, NULL),
(28, 'FREDY ESCOBAR', '71675817', 'CRA 82C#30A-105', 82, 'FREDY@HOTMAIL.COM', '3534045', '', '', '3124444885', 1, 0, '', '51744384-1', 1, '51744384-1_32', 52, NULL),
(29, 'BETTO RUA GALLEGO', '8102789', 'CALLE 45 No 21-85 MEDELLIN', 82, 'betto@hotmail.com', '2221677', '', '', '2221677', 1, 0, '', '51744384-1', 1, '51744384-1_33', 52, NULL),
(30, 'ELDER SANCHEZ', '14250001', 'DIAG. 30#20-14', 1087, 'EIDE@HOTMAIL.COM', '', '', '', '3162853009', 1, 0, '', '51744384-1', 1, '51744384-1_34', 52, NULL),
(31, 'RAIMUNDO CARRASQUILLA', '16250462', 'BARRANQUILLA', 145, 'rai@hotmail.com', '2881412', '', '', '2881412', 1, 0, '', '51744384-1', 1, '51744384-1_35', 52, NULL),
(32, 'INSTITUTO MUNICIPAL DEL DEPORTE Y LA RECREACION DE PALMIRA', '8150003406', 'CALLE 27 No 35-00', 1087, 'inder@hotmail.com', '3013576143', '', '', '3013576143', 1, 0, '', '51744384-1', 3, '51744384-1_36', 52, NULL),
(33, 'EDWIN AGUDELO', '14699267', 'CRA 42 No 41-74 PALMIRA', 1087, 'edwin@hotmail.com', '3154025681', '', '', '3154025681', 1, 0, '', '51744384-1', 1, '51744384-1_37', 52, NULL),
(34, 'ANEGELO BACCI', '73242953', 'CALLE 40#50-60', 1087, 'ANGELO@HOTMAIL.COM', '', '', '', '3148422253', 1, 0, '', '51744384-1', 1, '51744384-1_38', 52, NULL),
(35, 'ORLANDO DUQUE', '8171200', 'CRA 70 NO 32B-125 MEDELLIN', 82, 'lando@hotmail.com', '3176827354', '', '', '3176827354', 1, 0, '', '51744384-1', 1, '51744384-1_39', 52, NULL),
(36, 'JAVIER ANDRADE', '16593693', 'TRANS.9 No 57N-80 POPAYAN', 418, 'javier@hotmail.com', '3155701437', '', '', '3155701437', 1, 0, '', '51744384-1', 1, '51744384-1_40', 52, NULL),
(37, 'MARIA JOSE ORTEGA', '1124315072', 'CALLR 3# 6-55', 862, 'HOLA@HOTMAIL.COM', '', '', '', '314554021', 1, 0, '', '51744384-1', 1, '51744384-1_41', 52, NULL),
(38, 'JAMES OREJUELA', '16667862', 'CRA 26 No 90-62 CALI', 1071, 'jame@hotmail.es', '3046141238', '', '', '3046141238', 1, 0, '', '51744384-1', 1, '51744384-1_42', 52, NULL),
(39, 'A Y G SEÑALIZACIONES S.A.S', '9007728080', 'KR 2#3-56', 1087, 'SENALES@HOTMAIL.COM', '', '', '', '3155659427', 1, 0, '', '51744384-1', 3, '51744384-1_43', 52, NULL),
(40, 'SERVIMOTOS CDA', '9002127231', 'CALLE 42#34-363', 1087, 'SERVIMOTOS@HOTMAIL.COM', '2818422', '', '', '317444444', 1, 0, '', '51744384-1', 3, '51744384-1_44', 52, NULL),
(41, 'RAUL PARRA DOMINGUEZ', 'XDC138819', '', 1116, 'raul@hotmail.com', '', '', '', '', 1, 0, '', '51744384-1', 4, '51744384-1_45', 73, '1977-04-27'),
(42, 'SMART WIRELESS EU', '9002653014', 'CALLE 106 No 58-27 BOGOTA', 1116, 'ggaleanorell@gmail.com', '7442517', '', '', '7442517', 1, 0, '', '51744384-1', 3, '51744384-1_46', 52, NULL),
(43, 'KAL TIRE', '9000363470', 'CALLE 30 No19-55 BARRANQUILLA', 145, 'mauricio.marino@kaltire.com', '3630511', '', '', '3630511', 1, 0, '', '51744384-1', 3, '51744384-1_47', 52, NULL),
(44, 'ACAC', '8902039449', 'CRA 16 No 31A-36 BOGOTA', 1116, 'maria@hotmail.com', '4320370', '', '', '4320370', 1, 0, '', '51744384-1', 3, '51744384-1_48', 52, NULL),
(45, 'ASTRID ALDANA', '51791652', 'CALLE 160 No 58-75 BOGOTA', 1116, 'astrid@hotmail.com', '', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_49', 52, NULL),
(46, 'YASED COLLAZOS', '10533711', 'CALLE 44 1SN#64-69', 418, 'YESIDED@GMAIL.COM', '', '', '', '3184454445', 1, 0, '', '51744384-1', 1, '51744384-1_50', 52, NULL),
(47, 'ASCENETH GONZALEZ', '38864150', 'CALLE 16 No13-21 BUGA', 1068, 'aga313@hotmail.com', '', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_51', 52, NULL),
(48, 'FREDDY MUÑOZ', '76334265', 'POPAYAN', 418, 'gofreddy74@hotmail.com', '0', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_52', 52, NULL),
(49, 'HERNAN MUÑOZ', '17098182', 'KR25#14A-19', 1087, 'H@HOTMAIL.COM', '', '', '', '3146826087', 1, 0, '', '51744384-1', 1, '51744384-1_53', 52, NULL),
(50, 'ANAYELA ORTIZ', '2965986', 'CR37#34A-64', 1087, 'HTA@HOTMAIL.COM', '', '', '', '3127334454', 1, 0, '', '51744384-1', 1, '51744384-1_54', 52, NULL),
(51, 'Andres Mauricio Ramirez', '5827909', 'Calle 13 N. 24a-26', 1087, 'andres.ramirez@hotmail.com', '3154470897', '', '', '3154470897', 1, 0, '', '51744384-1', 1, '51744384-1_55', 52, NULL),
(52, 'MICROFERTISA S.A.S', '8000134556', 'CALLE 10 No 90A-54 BOGOTA', 1116, 'microf@hotmail.com', '4244990', '', '', '4244990', 1, 0, '', '51744384-1', 3, '51744384-1_56', 52, NULL),
(53, 'AVON COLOMBIA S.A.S', '9000419147', 'CALLE 14  No52A-272 MEDELLIN', 82, 'avon@hotmail.com', '3567600', '', '', '3567600', 1, 0, '', '51744384-1', 3, '51744384-1_57', 52, NULL),
(54, 'FUNDACION CARDIOVASCULAR DE COLOMBIA', '8902125680', 'CALLE 150A #23-58', 524, 'CAR@HOTMAIL.COM', '', '', '', '3105633060', 1, 0, '', '51744384-1', 3, '51744384-1_58', 52, NULL),
(55, 'ARLENE MELENDEZ', '489059199', '', 1116, 'arlen@hotmail.com', '', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_59', 178, '1960-03-26'),
(56, 'IVES VALLECIA GRAVINA', '001', 'SENA- SANTA MARTA', 724, 'lmora175@hotmail.com', '', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_60', 52, NULL),
(57, 'EDGAR MAIGUA CACERES', '1001906088', '', 1116, '', '', '', '', '3144565550', 1, 0, '', '51744384-1', 2, '51744384-1_61', 66, '1969-06-11'),
(58, 'CESAR CHACON', '79964900', 'CALLE 6B #79-81', 524, 'CS@HOTMAIL.COM', '', '', '', '3153350910', 1, 0, '', '51744384-1', 1, '51744384-1_62', 52, NULL),
(59, 'JORGE BOWIE', '73206792', 'SAN ANDRES ISLAS', 902, 'jorge@hotmail.com', '0', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_63', 52, NULL),
(60, 'JORGE BRITO', '84096643', 'SAN ANDRES ISLAS', 902, 'jo.12@hotmail.com', '', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_64', 52, NULL),
(61, 'PAOLA AVELLA', '52013030', 'CALLE 12#35-04', 799, 'PAOLA@HOTMAIL.COM', '', '', '', '3154812470', 1, 0, '', '51744384-1', 2, '51744384-1_65', 52, NULL),
(62, 'JUAN JOSE VASQUEZ', '1113040886', 'CALLE126#25-26', 1096, 'juanfo1280@hotmail.com', '', '', '', '3154852135', 1, 0, '', '51744384-1', 1, '51744384-1_66', 52, NULL),
(63, 'VIVIAN JARAMILLO', '1113620635', 'CALLE 38 N.21-27', 1087, 'VIVIAN.JARAMILLO@HOTMAIL.COM', '3164153046', '', '', '3164153046', 1, 0, '', '51744384-1', 1, '51744384-1_67', 52, NULL),
(64, ' MAURICIO DRADA', '16284869', 'PALMIRA', 1087, 'MAO.DRADA@HOTMAIL.COM', '3162502203', '', '', '3162502203', 1, 0, '', '51744384-1', 1, '51744384-1_68', 52, NULL),
(65, 'FRANCISCO OCORO', '94420068', 'CALLE 18#3N -121', 1087, 'A@A.COM', '', '', '', '314445555', 1, 0, '', '51744384-1', 1, '51744384-1_69', 52, NULL),
(66, 'LUIS DAVID JIMENEZ', '1113655631', 'CAR. 43#46-28', 1087, 'A@A.COM', '', '', '', '3185122705', 1, 0, '', '51744384-1', 1, '51744384-1_70', 52, NULL),
(67, 'NELSON ERAZO', '10522281', 'CRA 64 No 8N-119 POPAYAN', 418, 'ferroestacion@hotmail.com', '', '', '', '3216485235', 1, 0, '', '51744384-1', 1, '51744384-1_71', 52, NULL),
(68, 'SEA S.A.S', '8300106762', 'CALLE 167A # 16C-21', 1087, 'A@A.COM', '6680985', '', '', '3105509552', 1, 0, '', '51744384-1', 3, '51744384-1_72', 52, NULL),
(69, 'CIDETER SAS', '8301058972', 'CALLE 28A#13-97', 524, 'R@R.HOTMAIL.COM', '', '', '', '3132841717', 1, 0, '', '51744384-1', 3, '51744384-1_73', 52, NULL),
(70, 'EDWARD JIMENEZ', '02017233', '', 1116, 'EDWARD.JIMENEZ@HOTMAIL.COM', '100200300', '', '', '', 1, 0, '', '51744384-1', 2, '51744384-1_74', 65, '1981-11-28'),
(71, 'ALEXANDRA HEDIANEAL RUIZ', '1026558785', 'CARRERA 69B N. 31-51 SUR', 524, 'ALEX.HED@HOTMAIL.COM', '148569', '', '', '148569', 1, 0, '', '51744384-1', 1, '51744384-1_75', 52, NULL),
(72, 'GIZ', '8300991931', 'CALLE 26 #13-19 PISO 27', 1116, 'ESMERALDA.RODRIGUEZ@GIZ.DE', '', '', '', '3174330637', 1, 0, '', '51744384-1', 2, '51744384-1_76', 52, NULL),
(73, 'VIAJES ELITE S.A', '9000710736', 'CALLE 34 No43-66  LOCAL 2244 C.C. SAN DIEGO MEDELLIN', 82, 'asesor3viajeselite@gmail.com', '2620009', '', '', '2620009', 1, 0, '', '51744384-1', 3, '51744384-1_77', 52, NULL),
(74, 'ADRIANA SOTO', '3887955', 'CALLE', 1087, 'H@H.COM', '', '', '', '3142354510', 1, 0, '', '51744384-1', 1, '51744384-1_78', 52, NULL),
(75, 'EPSA', '8008002498601', 'AUTOPISTA CALI YUMBO CALLE 15 -29B-30', 1071, 'A@A.COM', '', '', '', '3206965607', 1, 0, '', '51744384-1', 3, '51744384-1_79', 52, NULL),
(76, 'CENTRO MISIONERO BETHESDA', '8600639527', 'POPAYAN', 418, 'misonero@hotmail.com', '3113332417', '', '', '3113332417', 1, 0, '', '51744384-1', 3, '51744384-1_80', 52, NULL),
(77, 'JORGE H. SAMUDIO', '79499337', 'POPAYAN', 418, 'betehesda@hotmail.com', '3215417439', '', '', '3215417439', 1, 0, '', '51744384-1', 1, '51744384-1_81', 52, NULL),
(78, 'SANDRA SUAREZ', '51920344', 'POPAYAN', 418, 'bethesda@hotmail.com', '3215417439', '', '', '3215417439', 1, 0, '', '51744384-1', 1, '51744384-1_82', 52, NULL),
(79, 'SUSANA BARRERA LOBATON', '39694866', 'CALLE 128 No 7D-19 BOGOTA', 1116, 'msbarrera@unal.edu.co', '3123720635', '', '', '3123720635', 1, 0, '', '51744384-1', 1, '51744384-1_83', 52, NULL),
(80, 'MIGUEL JARAMILLO', '6625481', 'CALLE 37 No 11-55 PALMIRA', 1087, 'jaramillo@hotmail.com', '3103692941', '', '', '3103692941', 1, 0, '', '51744384-1', 1, '51744384-1_84', 52, NULL),
(81, 'LIGA QUINDIANA DE LEVANTAMIENTO DE PESAS', '8900035337', 'CARRERA 15 ENTE CALLE 25-26 PARQUE CAFETERO', 1116, 'S64S@S.COM', '', '', '', '3005719794', 1, 0, '', '51744384-1', 3, '51744384-1_85', 52, NULL),
(82, 'VELEZ TOURS S.A.S', '8600252836', 'CRA 15 No 106-66 BOGOTA', 1116, 'ventas2@veleztours.com.co', '', '', '', '6204040', 1, 0, '', '51744384-1', 3, '51744384-1_86', 52, NULL),
(83, 'ANGELA CORRALES', '38460452', 'CAR 24C#2-122', 1087, 'CA@CA.COM', '', '', '', '3154445544', 1, 0, '', '51744384-1', 1, '51744384-1_87', 52, NULL),
(84, 'ANATRUJILLO', '1144064080', 'CRA 116 No 18-10 PALMIRA', 1087, 'ana@hotmail.com', '0', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_88', 52, NULL),
(85, 'KSB COLOMBIA', '9007021314', 'K.M 7 AUTOPISTA BOGOTA- MEDELLIN', 1116, 'ksb@hotmail.com', '8237576', '', '', '8237576', 1, 0, '', '51744384-1', 3, '51744384-1_89', 52, NULL),
(86, 'VICENTE TORRES', 'PAD928493', '0', 1087, 'A@A.COM', '', '', '', '310444444', 1, 0, '', '51744384-1', 4, '51744384-1_90', 52, NULL),
(87, 'MARIA YANETH CHICA PATIÑO', '31170446', 'NUEVA YORK', 1116, 'A@A.COM', '', '', '', '3157079021', 1, 0, '', '51744384-1', 1, '51744384-1_91', 52, NULL),
(88, 'FUNDACION EDUARDOÑO S.A', '8110056899', 'CRA 48 No 14-68 MEDELLIN', 82, 'cmperez@eduardono.com', '4445888', '', '', '3137590616', 1, 0, '', '51744384-1', 3, '51744384-1_92', 52, NULL),
(89, 'PEDRO AGUSTIN PEREZ', '19409077', 'CALLE 119A No 57-60 BOGOTA', 1116, 'pedro@hotmail.com', '3153563814', '', '', '3153563814', 1, 0, '', '51744384-1', 1, '51744384-1_93', 52, NULL),
(90, 'PLINIO TEHERAN', '72123186', 'CALLE 164 No 19-50', 1116, 'plinio@hotmail.com', '3007569457', '', '', '3007569457', 1, 0, '', '51744384-1', 1, '51744384-1_94', 52, NULL),
(91, 'ITALCOL DE OCCIDENTE ', '8913047622', 'KILOMETRO 11 VIA CALI PALMIRA ', 1087, 'I@I.COM', '2750505', '', '', '3147279510', 1, 0, '', '51744384-1', 3, '51744384-1_95', 52, NULL),
(92, 'BAYER SA', '8600019428', 'AV AMERICAS # 5876', 1087, 'A@S.COM', '', '', '', '3155569244', 1, 0, '', '51744384-1', 3, '51744384-1_96', 52, NULL),
(93, 'AGROAVICOLA SAN MARINO S.A', '8300168687', 'KM. 11 RECTA PALMIRA - CALI EDF. ITALCOL P.3', 1087, 'alexagallego@sanmarino.com.co', '2818900', '', '', '2818900', 1, 0, '', '51744384-1', 3, '51744384-1_97', 52, NULL),
(94, 'ROHDE & SCHWARZ COLOMBIA S.A', '9000427715', 'CRA 17A No 119A-80 BOGOTA', 1116, 'recepcion@hotmail.com', '6019760', '', '', '6019760', 1, 0, '', '51744384-1', 3, '51744384-1_98', 52, NULL),
(95, 'FELIX HUMBERTO SORIANO', '79413583', 'CRAETE 28-20SUER', 1116, 'C@C.COM', '', '', '', '314445662', 1, 0, '', '51744384-1', 1, '51744384-1_99', 52, NULL),
(96, 'lina duque', '66784245', 'call', 1087, 'z@z.com', '', '', '', '3188278644', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(97, 'LUISA CHAPARRO', '39577413', 'CALLE9 DIAB25#189', 1087, 'C@A.COM', '', '', '', '3154664543', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(98, 'TRANSPORTES MONTEBELLO S.A', '8000042838', 'PALMIRA', 1087, 'montebello@hotmail.com', '3164699172', '', '', '3164699172', 1, 0, '', '51744384-1', 3, '51744384-1_100', 52, NULL),
(99, 'JEISON MORENO ROJAS', '1022334797', 'CR 51#84-184', 1116, 'C@C.COM', '', '', '', '3105467269', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(100, 'MARRY DCUTSCH', 'CCVJNWVZN', '', 1116, 'C@C.COM', '', '', '', '', 1, 0, '', '51744384-1', 4, '51744384-1_100', 4, '1958-02-23'),
(101, 'INGNACIO LONDOÑO', '95380597', 'CAR43 #1348', 418, 'C@C.COM', '', '', '', '314444444', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(102, 'EILEEN DAYAN ESCOBAR', '1115069603', 'CRA 40A No 41-50 PALMIRA', 1087, 'eile@hotmail.com', '', '', '', '3183048149', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(103, 'FERRO AGUAS M.L S.A.S', '9004561516', 'CARRERA 32 No11-40', 1087, 'palmi@hotmail.com', '', '', '', '3176440913', 1, 0, '', '51744384-1', 3, '51744384-1_100', 52, NULL),
(104, 'MICHEL KATRUNI', '10130230', 'PEREIRA', 896, 'katruni@hotmail.com', '', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(105, 'KATHERINE LOAIZA', '425026132', '', 1116, 'C@D.COM', '', '', '', '321111111', 1, 0, '', '51744384-1', 4, '51744384-1_100', 75, '1991-06-09'),
(106, 'MAURICIO LEMA', '87100395', 'AIDA LUCIA CASA 187', 1087, 'barlem2005@hotmail.com', '3113648793', '', '', '3113648793', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(107, 'JUAN TELLEZ', '9101741', 'BOGOTA', 1116, 'juan@hotmail.com', '', '', '', '3102879678', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(108, 'WILSON ORTEGA', '104961313', 'BATALLON CODAZZI', 1087, 'C@C.COM', '', '', '', '3203425369', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(109, 'CELSO RODRIGUEZ', '79358293', 'CALLE 37A No 6D-47 PALMIRA', 1087, 'celso@hotmail.com', '', '', '', '3105762087', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(110, 'DAVEY MATTHEW BENNETT', '548456009', '', 1116, '', '', '', '', '0', 1, 0, '', '51744384-1', 4, '51744384-1_100', 75, '1974-09-14'),
(111, 'CARLOS DONADO', '7465198', '', 1116, '', '', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_100', 75, '1952-01-26'),
(112, 'SUMINISTROS DEL CASTILLO', '397070688', 'CRA 4 No 14-56 FUNZA', 546, 'suminist@hotmail.com', '7555611', '', '', '0', 1, 0, '', '51744384-1', 3, '51744384-1_100', 52, NULL),
(113, 'JULIAN PAREJA', '1113655974', 'PALMIRA', 1087, 'jul@hotmail.com', '', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(114, 'ACCURACY MANAGEMENT', '8300943671', 'TRASVERSAL 93#53-32', 1116, 'C@F.COM', '', '', '', '3153194476', 1, 0, '', '51744384-1', 3, '51744384-1_100', 52, NULL),
(115, 'COMARCA', '8160033252', 'CA8#8-29', 350, 'C@C.COM', '', '', '', '3113835897', 1, 0, '', '51744384-1', 3, '51744384-1_100', 52, NULL),
(116, 'DAVID MARSHALL', '515385891', '', 1116, 'C@C.COM', '', '', '', '', 1, 0, '', '51744384-1', 4, '51744384-1_100', 75, '1953-03-27'),
(117, 'SANADRA MONDRAGON ', '25292018', 'CRA.11#55-27', 418, 'C@C.COM', '', '', '', '3217159918', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(118, 'GUILLERMO LEAL', '19288862', 'BOGOTA', 524, 'C@C.COM', '', '', '', '315555555', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(119, 'VALREX S.A.S', '8605251564', 'CALLE 106 No 23-61 OFC. 505 BOGOTA', 1116, 'valrex@hotmail.com', '3111109', '', '', '3111109', 1, 0, '', '51744384-1', 3, '51744384-1_100', 52, NULL),
(120, 'Luz Marina Parra', '24326969', 'Calle 13 N. 13a-09', 896, 'luz.parra@icbf.gov.co', '3168351065', '', '', '3168351065', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(121, 'RAFAEL CASTAÑEDA', '16245571', 'CARTAGO', 1074, 'raf@hotmail.com', '0', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(122, 'PORKCOLOMBIA FNP', '8603256384', 'CALLE 37#16-52', 1116, 'C@C.COM', '', '', '', '3114801076', 1, 0, '', '51744384-1', 3, '51744384-1_100', 52, NULL),
(123, 'SARA GUTIERREZ', '41428510', 'CALLE 35 40-21', 524, 'C@C.COM', '', '', '', '3151111111', 1, 0, '', '51744384-1', 2, '51744384-1_100', 52, '1948-08-27'),
(124, 'IGOR YURIEVICH KHOMYAKOV', '561561246', '', 1116, '', '', '', '', '', 1, 0, '', '51744384-1', 4, '51744384-1_100', 75, '1967-05-02'),
(125, 'ALEX MONTEZ', '454446455', '', 1116, '', '', '', '', '', 1, 0, '', '51744384-1', 4, '51744384-1_100', 75, '1976-12-26'),
(126, 'MANUEL GRUESO', '1114823998', 'CALLE1#4-14', 1087, 'C@C.COM', '', '', '', '3204852121', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(127, 'CADICOMEX SAS', '9006994670', 'CALLE 20 No 8N-22 CALI', 1071, 'cadicomex@hotmail.com', '3168707025', '', '', '3168707025', 1, 0, '', '51744384-1', 3, '51744384-1_100', 52, NULL),
(128, 'LAURA CAMILA USECHE LEAL', '1010231992', 'CRA7·6-49', 1116, 'LAURAUSECHEL@HOTMAIL.COM', '', '', '', '3155555555', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(129, 'MARIA GONZALEZ', '1130586566', 'CALI', 1071, 'mari@hotmail.com', '3177001025', '', '', '3177001025', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(130, 'LUISA MENESES', '52713166', 'CALLE 52 No 74-74  MEDELLIN', 82, 'luisa@hotmail.com', '0', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(131, 'LUISA MEJIA', '52877297', 'BOGOTA', 1116, 'luisamej@hotmail.com', '0', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(132, 'CESAR MOSQUERA', '16701861', 'CALI', 1071, 'emiliodanza@hotmail.com', '0', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(133, 'SAMBA PRODUCE S.A.S', '9010266951', 'KM.2 PICALEÑA', 1037, 'samb@hotmail.com', '3152174150', '', '', '3152174150', 1, 0, 'PAGO ALOJAMIENTO HAB 118-121', '51744384-1', 3, '51744384-1_100', 52, NULL),
(134, 'SYNGENTA S.A', '8300742227', 'PEREIRA', 896, 'singen@hotmail.com', '3102010345', '', '', '3102010345', 1, 0, '', '51744384-1', 3, '51744384-1_100', 52, NULL),
(135, 'PAOLA ANDREA BOBADILLA ORTIZ', '66785443', 'CALLE15A#25-107', 1087, 'C@C.COM', '', '', '', '3166988042', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(136, 'LILINA RODRIGUEZ', '52708783', 'A', 524, 'C@C.COM', '', '', '', '3005697851', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(137, 'PIEDAD HOYOS', '52141185', 'AVENIDA 5 OESTE 10-19', 1116, 'C@C.COM', '', '', '', '3158944231', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(138, 'GLORIA RUIZ', '31170070', 'CALLE3', 1087, 'C@A.COM', '', '', '', '3154655525', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(139, 'BEATRIZ REYES', '29663715', 'CALLE 25D 36-58', 1087, 'C@C.COM', '', '', '', '3154844446', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(140, 'CEA ROEZ PALMIRA', '690294641', 'CALLE 70  No 34-80', 1087, 'roez@hotmail.com', '3043398117', '', '', '3043398117', 1, 0, '', '51744384-1', 3, '51744384-1_100', 52, NULL),
(141, 'CEA ROEZ PALMIRA', '690294041', 'CALLE 70 No 34-80', 1087, 'roez@hotmail.com', '3043398117', '', '', '3043398117', 1, 0, '', '51744384-1', 3, '51744384-1_100', 52, NULL),
(142, 'JAIRO CASTRO', '1113645895', 'CRA 26 No 62-27 PALMIRA', 1087, 'angelaforero@hotmail.com', '0', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(143, 'JIMMY CAYCEDO', '76331125', 'CARRERA 13 No 7-27', 418, 'jimmy@hotmail.com', '3176555014', '', '', '3176555014', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(144, 'OSCAR JURADO', '79722209', 'CAR 32 No 13-54', 799, 'oscarj@hotmail.com', '3016084135', '', '', '3016084135', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(145, 'SANTO GARGIOLO', '472627491', '', 1116, 'ejmpgargiolo@yahoo.com', '0', '', '', '0', 1, 0, '', '51744384-1', 4, '51744384-1_100', 75, '1969-06-24'),
(146, 'BRIGITTE CALDERON', '1033705746', 'CALLE 50B No 31-24 SUR BOGOTA', 1116, 'brig@hotmail.com', '3205919293', '', '', '3205919293', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(147, 'ANA SOFIA CORTES', '27094709', 'CALI', 1071, 'ana@hotmail.com', '3006584398', '', '', '3006584398', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(148, 'BCD TRAVEL', '8000786924', 'CRA 16 No 93-08 BOGOTA', 1116, 'asesorgrupos.12@cdme.co', '7956038', '', '', '7956038', 1, 0, '', '51744384-1', 3, '51744384-1_100', 52, NULL),
(149, 'CARLOS RAMIREZ', '14223605', 'IBAGUE', 1037, 'car@hotmail.com', '0', '', '', '0', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL),
(150, 'UNIVERSIDAD NACIONAL DE COLOMBIA', '8999990633', 'CRA 30 No45-03 BOGOTA', 1116, 'unal@hotmail.com', '3165000', '', '', '3165000', 1, 0, '', '51744384-1', 3, '51744384-1_100', 52, NULL),
(151, 'LUIS DUARTE', '96123990', 'CRA 6A No 25-45 BOGOTA', 1116, 'luis@hotmail.com', '3118694894', '', '', '3118694894', 1, 0, '', '51744384-1', 1, '51744384-1_100', 52, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobantes_egreso`
--

CREATE TABLE `comprobantes_egreso` (
  `id_comprobante_egreso` int(11) NOT NULL,
  `consecutivo` int(11) NOT NULL,
  `id_ciudad` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `valor` float NOT NULL,
  `concepto` varchar(200) NOT NULL,
  `beneficiario` varchar(15) NOT NULL,
  `id_empresa` varchar(15) NOT NULL,
  `id_tipo_pago` int(11) NOT NULL,
  `id_entidad_bancaria` int(11) DEFAULT NULL,
  `cheque` varchar(50) DEFAULT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consecutivos_imprimibles`
--

CREATE TABLE `consecutivos_imprimibles` (
  `id_consecutivo` int(11) NOT NULL,
  `prefijo` varchar(10) DEFAULT NULL,
  `consecutivo` int(11) NOT NULL,
  `id_tipo_imprimible` int(11) NOT NULL,
  `id_empresa` varchar(15) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `consecutivos_imprimibles`
--

INSERT INTO `consecutivos_imprimibles` (`id_consecutivo`, `prefijo`, `consecutivo`, `id_tipo_imprimible`, `id_empresa`, `transacion`) VALUES
(1, 'FVH', 218, 1, '51744384-1', '51744384-1_1'),
(2, '', 1, 2, '51744384-1', '51744384-1_2'),
(3, '', 200, 3, '51744384-1', '51744384-1_3');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE `cuentas` (
  `id_cuenta` int(5) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `puc` varchar(100) NOT NULL,
  `detalle` varchar(200) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`id_cuenta`, `nombre`, `puc`, `detalle`, `transacion`) VALUES
(1, 'Banco', '111001', 'Banco', ''),
(2, 'Caja general', '110505', 'Efectivo', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamentos`
--

CREATE TABLE `departamentos` (
  `id_departamento` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `departamentos`
--

INSERT INTO `departamentos` (`id_departamento`, `nombre`, `transacion`) VALUES
(1, 'AMAZONAS', ''),
(2, 'ANTIOQUIA', ''),
(3, 'ARAUCA', ''),
(4, 'ATLÁNTICO', ''),
(5, 'BOLÍVAR', ''),
(6, 'BOYACÁ', ''),
(7, 'CALDAS', ''),
(8, 'CAQUETÁ', ''),
(9, 'CASANARE', ''),
(10, 'CAUCA', ''),
(11, 'CESAR', ''),
(12, 'CHOCÓ', ''),
(13, 'CÓRDOBA', ''),
(14, 'CUNDINAMARCA', ''),
(15, 'GUAINÍA', ''),
(16, 'GUAVIARE', ''),
(17, 'HUILA', ''),
(18, 'LA GUAJIRA', ''),
(19, 'MAGDALENA', ''),
(20, 'META', ''),
(21, 'NARIÑO', ''),
(22, 'NORTE DE SANTANDER', ''),
(23, 'PUTUMAYO', ''),
(24, 'QUINDÍO', ''),
(25, 'RISARALDA', ''),
(26, 'SAN ANDRÉS Y ROVIDENCIA', ''),
(27, 'SANTANDER', ''),
(28, 'SUCRE', ''),
(29, 'TOLIMA', ''),
(30, 'VALLE DEL CAUCA', ''),
(31, 'VAUPÉS', ''),
(32, 'VICHADA', ''),
(33, 'Extranjero ', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuentos`
--

CREATE TABLE `descuentos` (
  `id_descuento` int(11) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `porcentaje_descuento` int(3) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuentos_clientes`
--

CREATE TABLE `descuentos_clientes` (
  `id_descuentos_clientes` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_descuento` int(11) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_factura_cotizacion`
--

CREATE TABLE `detalle_factura_cotizacion` (
  `id_detalle` int(11) NOT NULL,
  `id_producto_servicio` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `id_factura` varchar(20) DEFAULT NULL,
  `id_impuesto` int(2) NOT NULL,
  `transacion` varchar(30) NOT NULL,
  `valor` float DEFAULT NULL,
  `descuento` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `detalle_factura_cotizacion`
--

INSERT INTO `detalle_factura_cotizacion` (`id_detalle`, `id_producto_servicio`, `cantidad`, `id_factura`, `id_impuesto`, `transacion`, `valor`, `descuento`) VALUES
(1, 4, 1, 'FVH 1', 2, '51744384-1_1', 414117, 0),
(2, 3, 1, 'FVH 2', 1, '51744384-1_2', 328000, 0),
(3, 3, 1, 'FVH 3', 1, '51744384-1_3', 131000, 0),
(4, 4, 2, 'FVH 4', 2, '51744384-1_4', 103529, 22184),
(5, 4, 2, 'FVH 4', 2, '51744384-1_4', 103529, 22184),
(6, 4, 2, 'FVH 4', 2, '51744384-1_4', 103529, 22184),
(7, 4, 2, 'FVH 4', 2, '51744384-1_4', 125043, 26556),
(8, 4, 2, 'FVH 4', 2, '51744384-1_4', 125043, 26556),
(9, 4, 2, 'FVH 4', 2, '51744384-1_4', 125043, 26556),
(10, 4, 2, 'FVH 4', 2, '51744384-1_4', 125043, 26556),
(11, 4, 2, 'FVH 4', 2, '51744384-1_4', 125043, 26556),
(12, 4, 2, 'FVH 4', 2, '51744384-1_4', 158656, 33278),
(13, 4, 2, 'FVH 4', 2, '51744384-1_4', 158656, 33278),
(14, 4, 2, 'FVH 4', 2, '51744384-1_4', 158656, 33278),
(15, 4, 2, 'FVH 4', 2, '51744384-1_4', 158656, 33278),
(16, 4, 2, 'FVH 4', 2, '51744384-1_4', 158656, 33278),
(17, 3, 1, 'FVH 4', 1, '51744384-1_4', 2576000, 0),
(18, 3, 1, 'FVH 5', 1, '51744384-1_5', 17000, 0),
(19, 3, 1, 'FVH 6', 1, '51744384-1_6', 17000, 0),
(20, 3, 1, 'FVH 7', 1, '51744384-1_7', 24000, 0),
(21, 3, 1, 'FVH 8', 1, '51744384-1_8', 16000, 0),
(22, 4, 1, 'FVH 9', 2, '51744384-1_9', 103529, 0),
(23, 4, 1, 'FVH 10', 2, '51744384-1_10', 125042, 0),
(24, 3, 1, 'FVH 11', 1, '51744384-1_10', 17000, 0),
(25, 3, 1, 'FVH 12', 1, '51744384-1_10', 17000, 0),
(26, 3, 1, 'FVH 13', 1, '51744384-1_10', 560000, 0),
(27, 4, 1, 'FVH 14', 2, '51744384-1_10', 103529, 0),
(28, 3, 1, 'FVH 14', 1, '51744384-1_10', 48000, 0),
(29, 4, 1, 'FVH 15', 2, '51744384-1_10', 137142, 0),
(30, 3, 1, 'FVH 15', 1, '51744384-1_10', 72000, 0),
(31, 3, 1, 'FVH 16', 1, '51744384-1_10', 40000, 0),
(32, 4, 2, 'FVH 17', 2, '51744384-1_10', 103529, 0),
(33, 4, 1, 'FVH 18', 2, '51744384-1_10', 125043, 0),
(34, 4, 3, 'FVH 19', 2, '51744384-1_10', 103529, 0),
(35, 4, 1, 'FVH 20', 2, '51744384-1_10', 157983, 0),
(36, 4, 3, 'FVH 21', 2, '51744384-1_10', 103529, 0),
(37, 4, 2, 'FVH 22', 2, '51744384-1_10', 125043, 0),
(38, 4, 1, 'FVH 23', 2, '51744384-1_10', 2218490, 0),
(39, 4, 1, 'FVH 24', 2, '51744384-1_10', 277311, 0),
(40, 4, 1, 'FVH 25', 2, '51744384-1_10', 125043, 0),
(41, 3, 1, 'FVH 26', 1, '51744384-1_10', 1274000, 0),
(42, 4, 2, 'FVH 27', 2, '51744384-1_10', 103529, 0),
(43, 4, 1, 'FVH 28', 2, '51744384-1_10', 5546220, 0),
(44, 5, 1, 'FVH 28', 1, '51744384-1_10', 3990000, 0),
(45, 4, 1, 'FVH 29', 2, '51744384-1_10', 384537, 0),
(46, 5, 1, 'FVH 29', 1, '51744384-1_10', 122500, 0),
(47, 4, 1, 'FVH 30', 2, '51744384-1_10', 103529, 0),
(48, 5, 1, 'FVH 31', 1, '51744384-1_10', 26000, 0),
(49, 4, 1, 'FVH 32', 2, '51744384-1_10', 192269, 0),
(50, 4, 2, 'FVH 33', 2, '51744384-1_10', 103529, 0),
(51, 3, 1, 'FVH 34', 1, '51744384-1_10', 20000, 0),
(52, 4, 1, 'FVH 34', 2, '51744384-1_10', 103529, 0),
(53, 5, 1, 'FVH 35', 1, '51744384-1_10', 75000, 0),
(54, 4, 1, 'FVH 36', 2, '51744384-1_10', 1000340, 0),
(55, 4, 1, 'FVH 37', 2, '51744384-1_10', 158656, 0),
(56, 5, 1, 'FVH 37', 1, '51744384-1_10', 55000, 0),
(57, 4, 1, 'FVH 38', 2, '51744384-1_10', 250420, 0),
(58, 4, 1, 'FVH 39', 2, '51744384-1_10', 240336, 0),
(59, 4, 1, 'FVH 40', 2, '51744384-1_10', 103529, 0),
(60, 5, 1, 'FVH 40', 1, '51744384-1_10', 71500, 0),
(61, 4, 1, 'FVH 41', 2, '51744384-1_10', 141177, 0),
(62, 5, 1, 'FVH 42', 1, '51744384-1_10', 17000, 0),
(63, 4, 1, 'FVH 43', 2, '51744384-1_10', 207059, 0),
(64, 4, 1, 'FVH 44', 2, '51744384-1_10', 113445, 0),
(65, 3, 1, 'FVH 45', 1, '51744384-1_10', 8000, 0),
(66, 4, 1, 'FVH 46', 2, '51744384-1_10', 103529, 0),
(67, 4, 1, 'FVH 47', 2, '51744384-1_10', 103529, 0),
(68, 4, 1, 'FVH 48', 2, '51744384-1_10', 103529, 0),
(69, 4, 1, 'FVH 49', 2, '51744384-1_10', 103529, 0),
(70, 4, 1, 'FVH 50', 2, '51744384-1_10', 103529, 0),
(71, 4, 1, 'FVH 51', 2, '51744384-1_10', 103529, 0),
(72, 4, 1, 'FVH 52', 2, '51744384-1_10', 103529, 0),
(73, 3, 1, 'FVH 53', 1, '51744384-1_10', 8500, 0),
(74, 3, 1, 'FVH 54', 1, '51744384-1_10', 8000, 0),
(75, 4, 1, 'FVH 55', 2, '51744384-1_10', 103529, 0),
(76, 4, 1, 'FVH 56', 2, '51744384-1_10', 103529, 0),
(77, 4, 1, 'FVH 57', 2, '51744384-1_10', 137142, 0),
(78, 3, 1, 'FVH 58', 1, '51744384-1_10', 102000, 0),
(79, 3, 1, 'FVH 59', 1, '51744384-1_10', 75500, 0),
(80, 5, 1, 'FVH 60', 1, '51744384-1_10', 41000, 0),
(81, 4, 2, 'FVH 61', 2, '51744384-1_10', 103529, 20706),
(82, 5, 1, 'FVH 62', 1, '51744384-1_10', 34000, 0),
(83, 5, 1, 'FVH 63', 1, '51744384-1_10', 24000, 0),
(84, 4, 3, 'FVH 64', 2, '51744384-1_10', 103529, 0),
(85, 4, 3, 'FVH 65', 2, '51744384-1_10', 103529, 0),
(86, 4, 1, 'FVH 66', 2, '51744384-1_10', 420168, 0),
(87, 5, 1, 'FVH 67', 1, '51744384-1_10', 509000, 0),
(88, 5, 1, 'FVH 68', 1, '51744384-1_10', 48000, 0),
(89, 3, 1, 'FVH 69', 1, '51744384-1_10', 16500, 0),
(90, 4, 1, 'FVH 70', 2, '51744384-1_10', 137143, 0),
(91, 4, 1, 'FVH 71', 2, '51744384-1_10', 112605, 0),
(92, 3, 1, 'FVH 72', 1, '51744384-1_10', 44500, 0),
(93, 5, 1, 'FVH 73', 1, '51744384-1_10', 26500, 0),
(94, 5, 1, 'FVH 73', 1, '51744384-1_10', 12500, 0),
(95, 5, 1, 'FVH 74', 1, '51744384-1_10', 30000, 0),
(96, 5, 1, 'FVH 74', 1, '51744384-1_10', 12000, 0),
(97, 4, 1, 'FVH 75', 2, '51744384-1_10', 103529, 0),
(98, 4, 1, 'FVH 76', 2, '51744384-1_10', 103529, 0),
(99, 5, 1, 'FVH 77', 1, '51744384-1_10', 83000, 0),
(100, 5, 1, 'FVH 78', 1, '51744384-1_10', 74500, 0),
(101, 4, 1, 'FVH 79', 2, '51744384-1_10', 93176, 0),
(102, 4, 1, 'FVH 80', 2, '51744384-1_10', 93176, 0),
(103, 4, 1, 'FVH 81', 2, '51744384-1_10', 93176, 0),
(104, 3, 1, 'FVH 82', 1, '51744384-1_10', 47000, 0),
(105, 3, 1, 'FVH 83', 1, '51744384-1_10', 16000, 0),
(106, 3, 1, 'FVH 84', 1, '51744384-1_10', 48000, 0),
(107, 4, 1, 'FVH 85', 2, '51744384-1_10', 125043, 0),
(108, 4, 6, 'FVH 86', 2, '51744384-1_10', 125043, 75024),
(109, 4, 6, 'FVH 87', 2, '51744384-1_10', 125043, 75030),
(110, 3, 1, 'FVH 88', 1, '51744384-1_10', 22000, 0),
(111, 3, 1, 'FVH 89', 1, '51744384-1_10', 8000, 0),
(112, 4, 5, 'FVH 90', 2, '51744384-1_10', 125043, 125045),
(113, 3, 1, 'FVH 90', 1, '51744384-1_10', 73000, 0),
(114, 1, 1, 'FVH 90', 2, '51744384-1_10', 21849, 0),
(115, 4, 1, 'FVH 91', 2, '51744384-1_10', 137142, 0),
(116, 4, 1, 'FVH 92', 2, '51744384-1_10', 137142, 0),
(117, 4, 3, 'FVH 93', 2, '51744384-1_10', 103529, 0),
(118, 5, 1, 'FVH 94', 1, '51744384-1_10', 58000, 0),
(119, 4, 3, 'FVH 95', 2, '51744384-1_10', 103529, 0),
(120, 4, 1, 'FVH 96', 2, '51744384-1_10', 125043, 0),
(121, 3, 1, 'FVH 97', 1, '51744384-1_10', 32500, 0),
(122, 4, 1, 'FVH 98', 2, '51744384-1_10', 125043, 0),
(123, 4, 1, 'FVH 99', 2, '51744384-1_10', 125043, 0),
(124, 4, 3, 'FVH 100', 2, '51744384-1_10', 103529, 31059),
(125, 4, 1, 'FVH 101', 2, '51744384-1_10', 103529, 0),
(126, 5, 1, 'FVH 102', 1, '51744384-1_10', 29000, 0),
(127, 4, 1, 'FVH 103', 2, '51744384-1_10', 94958, 9244),
(128, 4, 1, 'FVH 104', 2, '51744384-1_10', 94958, 9244),
(129, 4, 1, 'FVH 105', 2, '51744384-1_10', 103529, 10252),
(130, 4, 1, 'FVH 106', 2, '51744384-1_10', 103529, 10252),
(131, 4, 1, 'FVH 107', 2, '51744384-1_10', 103529, 10252),
(132, 4, 1, 'FVH 108', 2, '51744384-1_10', 103529, 10252),
(133, 4, 1, 'FVH 109', 2, '51744384-1_10', 94958, 9244),
(134, 4, 1, 'FVH 110', 2, '51744384-1_10', 103529, 0),
(135, 4, 1, 'FVH 111', 2, '51744384-1_10', 103529, 0),
(136, 4, 1, 'FVH 112', 2, '51744384-1_10', 103529, 0),
(137, 3, 1, 'FVH 113', 1, '51744384-1_10', 35000, 0),
(138, 4, 1, 'FVH 114', 2, '51744384-1_10', 103529, 0),
(139, 4, 1, 'FVH 115', 2, '51744384-1_10', 103529, 0),
(140, 4, 1, 'FVH 116', 2, '51744384-1_10', 103529, 0),
(141, 4, 1, 'FVH 117', 2, '51744384-1_10', 94958, 0),
(142, 5, 1, 'FVH 118', 1, '51744384-1_10', 17000, 0),
(143, 4, 1, 'FVH 119', 2, '51744384-1_10', 125043, 0),
(144, 4, 1, 'FVH 120', 2, '51744384-1_10', 103529, 0),
(145, 4, 1, 'FVH 120', 2, '51744384-1_10', 94958, 0),
(146, 4, 1, 'FVH 121', 2, '51744384-1_10', 103529, 0),
(147, 3, 1, 'FVH 122', 1, '51744384-1_10', 17000, 0),
(148, 4, 4, 'FVH 123', 2, '51744384-1_10', 103529, 0),
(149, 5, 1, 'FVH 124', 1, '51744384-1_10', 8000, 0),
(150, 4, 2, 'FVH 125', 2, '51744384-1_10', 125043, 0),
(151, 4, 1, 'FVH 126', 2, '51744384-1_10', 94958, 0),
(152, 4, 1, 'FVH 126', 2, '51744384-1_10', 103529, 0),
(153, 4, 1, 'FVH 127', 2, '51744384-1_10', 33613, 0),
(154, 5, 1, 'FVH 127', 1, '51744384-1_10', 24000, 0),
(155, 4, 1, 'FVH 128', 2, '51744384-1_10', 103529, 0),
(156, 5, 1, 'FVH 129', 1, '51744384-1_10', 130000, 0),
(157, 4, 1, 'FVH 130', 2, '51744384-1_10', 103529, 0),
(158, 5, 1, 'FVH 130', 1, '51744384-1_10', 27000, 0),
(159, 4, 5, 'FVH 131', 2, '51744384-1_10', 94958, 0),
(160, 5, 1, 'FVH 131', 1, '51744384-1_10', 6000, 0),
(161, 4, 1, 'FVH 132', 2, '51744384-1_10', 103529, 0),
(162, 4, 3, 'FVH 133', 2, '51744384-1_10', 103529, 0),
(163, 4, 2, 'FVH 134', 2, '51744384-1_10', 103529, 20705),
(164, 5, 1, 'FVH 135', 1, '51744384-1_10', 6800, 0),
(165, 5, 1, 'FVH 136', 1, '51744384-1_10', 9000, 0),
(166, 4, 1, 'FVH 137', 2, '51744384-1_10', 207059, 0),
(167, 5, 1, 'FVH 138', 1, '51744384-1_10', 83000, 0),
(168, 3, 1, 'FVH 139', 1, '51744384-1_10', 8000, 0),
(169, 4, 1, 'FVH 140', 2, '51744384-1_10', 103529, 0),
(170, 4, 3, 'FVH 141', 2, '51744384-1_10', 103529, 0),
(171, 4, 3, 'FVH 142', 2, '51744384-1_10', 103529, 0),
(172, 5, 1, 'FVH 143', 1, '51744384-1_10', 9800, 0),
(173, 3, 1, 'FVH 144', 1, '51744384-1_10', 195500, 0),
(174, 4, 2, 'FVH 145', 2, '51744384-1_10', 103529, 0),
(175, 4, 1, 'FVH 146', 2, '51744384-1_10', 103529, 0),
(176, 4, 1, 'FVH 147', 2, '51744384-1_10', 103529, 0),
(177, 4, 4, 'FVH 148', 2, '51744384-1_10', 103529, 0),
(178, 5, 1, 'FVH 148', 1, '51744384-1_10', 49000, 0),
(179, 4, 1, 'FVH 149', 2, '51744384-1_10', 103529, 0),
(180, 5, 1, 'FVH 150', 1, '51744384-1_10', 57000, 0),
(181, 4, 1, 'FVH 151', 2, '51744384-1_10', 170924, 0),
(182, 5, 1, 'FVH 151', 1, '51744384-1_10', 53000, 0),
(183, 4, 8, 'FVH 152', 2, '51744384-1_10', 103529, 0),
(184, 5, 1, 'FVH 152', 1, '51744384-1_10', 72000, 0),
(185, 5, 1, 'FVH 153', 1, '51744384-1_10', 56500, 0),
(186, 4, 1, 'FVH 154', 2, '51744384-1_10', 94958, 0),
(187, 4, 4, 'FVH 154', 2, '51744384-1_10', 137142, 0),
(188, 4, 1, 'FVH 154', 2, '51744384-1_10', 94958, 0),
(189, 4, 2, 'FVH 155', 2, '51744384-1_10', 103529, 0),
(190, 4, 2, 'FVH 156', 2, '51744384-1_10', 125043, 0),
(191, 4, 2, 'FVH 157', 2, '51744384-1_10', 103529, 0),
(192, 4, 2, 'FVH 158', 2, '51744384-1_10', 103529, 0),
(193, 4, 1, 'FVH 159', 2, '51744384-1_10', 137142, 0),
(194, 4, 2, 'FVH 160', 2, '51744384-1_10', 103529, 0),
(195, 5, 1, 'FVH 161', 1, '51744384-1_10', 16000, 0),
(196, 5, 1, 'FVH 162', 1, '51744384-1_10', 32000, 0),
(197, 4, 1, 'FVH 163', 2, '51744384-1_10', 103529, 0),
(198, 4, 1, 'FVH 163', 2, '51744384-1_10', 125043, 0),
(199, 4, 1, 'FVH 164', 2, '51744384-1_10', 125043, 0),
(200, 4, 1, 'FVH 165', 2, '51744384-1_10', 125043, 0),
(201, 4, 1, 'FVH 165', 2, '51744384-1_10', 125043, 0),
(202, 4, 1, 'FVH 166', 2, '51744384-1_10', 125043, 0),
(203, 4, 1, 'FVH 167', 2, '51744384-1_10', 125043, 0),
(204, 4, 1, 'FVH 168', 2, '51744384-1_10', 192269, 0),
(205, 4, 2, 'FVH 169', 2, '51744384-1_10', 125043, 0),
(206, 5, 1, 'FVH 170', 1, '51744384-1_10', 159000, 0),
(207, 4, 1, 'FVH 171', 2, '51744384-1_10', 723530, 0),
(208, 5, 1, 'FVH 171', 1, '51744384-1_10', 474500, 0),
(209, 4, 1, 'FVH 172', 2, '51744384-1_10', 1344540, 0),
(210, 5, 1, 'FVH 173', 1, '51744384-1_10', 72000, 0),
(211, 5, 1, 'FVH 174', 1, '51744384-1_10', 83500, 0),
(212, 5, 1, 'FVH 175', 1, '51744384-1_10', 8000, 0),
(213, 4, 1, 'FVH 176', 2, '51744384-1_10', 94958, 0),
(214, 4, 1, 'FVH 177', 2, '51744384-1_10', 125210, 0),
(215, 4, 8, 'FVH 178', 2, '51744384-1_10', 103529, 0),
(216, 4, 2, 'FVH 179', 2, '51744384-1_10', 103529, 0),
(217, 5, 1, 'FVH 180', 1, '51744384-1_10', 661500, 0),
(218, 4, 1, 'FVH 181', 2, '51744384-1_10', 103529, 0),
(219, 4, 1, 'FVH 182', 2, '51744384-1_10', 103529, 0),
(220, 4, 2, 'FVH 183', 2, '51744384-1_10', 103529, 0),
(221, 4, 1, 'FVH 184', 2, '51744384-1_10', 103529, 0),
(222, 4, 1, 'FVH 185', 2, '51744384-1_10', 103529, 0),
(223, 4, 1, 'FVH 186', 2, '51744384-1_10', 103529, 0),
(224, 4, 3, 'FVH 187', 2, '51744384-1_10', 103529, 0),
(225, 4, 1, 'FVH 188', 2, '51744384-1_10', 125043, 0),
(226, 5, 1, 'FVH 189', 1, '51744384-1_10', 47000, 0),
(227, 4, 1, 'FVH 190', 2, '51744384-1_10', 125043, 0),
(228, 4, 1, 'FVH 191', 2, '51744384-1_10', 103529, 0),
(229, 4, 1, 'FVH 192', 2, '51744384-1_10', 103529, 0),
(230, 4, 1, 'FVH 193', 2, '51744384-1_10', 103529, 0),
(231, 4, 1, 'FVH 194', 2, '51744384-1_10', 103529, 0),
(232, 4, 1, 'FVH 195', 2, '51744384-1_10', 125043, 0),
(233, 4, 1, 'FVH 196', 2, '51744384-1_10', 137142, 0),
(234, 5, 1, 'FVH 196', 1, '51744384-1_10', 30000, 0),
(235, 5, 1, 'FVH 197', 1, '51744384-1_10', 36500, 0),
(236, 4, 2, 'FVH 198', 2, '51744384-1_10', 103529, 0),
(237, 4, 1, 'FVH 199', 2, '51744384-1_10', 103529, 0),
(238, 5, 1, 'FVH 199', 1, '51744384-1_10', 11000, 0),
(239, 5, 1, 'FVH 200', 1, '51744384-1_10', 53000, 0),
(240, 4, 1, 'FVH 201', 2, '51744384-1_10', 413445, 0),
(241, 4, 1, 'FVH 202', 2, '51744384-1_10', 200420, 0),
(242, 3, 1, 'FVH 203', 1, '51744384-1_10', 49500, 0),
(243, 5, 1, 'FVH 204', 1, '51744384-1_10', 280000, 0),
(244, 4, 1, 'FVH 205', 2, '51744384-1_10', 103529, 0),
(245, 4, 1, 'FVH 206', 2, '51744384-1_10', 103529, 0),
(246, 4, 1, 'FVH 207', 2, '51744384-1_10', 103529, 0),
(247, 4, 1, 'FVH 208', 2, '51744384-1_10', 125043, 0),
(248, 4, 2, 'FVH 209', 2, '51744384-1_10', 158656, 0),
(249, 4, 3, 'FVH 210', 2, '51744384-1_10', 137142, 0),
(250, 4, 8, 'FVH 211', 2, '51744384-1_10', 192269, 0),
(251, 4, 1, 'FVH 212', 2, '51744384-1_10', 170755, 0),
(252, 5, 1, 'FVH 213', 1, '51744384-1_10', 83000, 0),
(253, 5, 1, 'FVH 214', 1, '51744384-1_10', 32500, 0),
(254, 4, 1, 'FVH 215', 2, '51744384-1_10', 103529, 20706),
(255, 4, 1, 'FVH 216', 2, '51744384-1_10', 103529, 0),
(256, 4, 1, 'FVH 217', 2, '51744384-1_10', 94958, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_factura_proveedor`
--

CREATE TABLE `detalle_factura_proveedor` (
  `id_dfp` int(15) NOT NULL,
  `id_factura_proveedor` int(15) NOT NULL,
  `valor` float NOT NULL,
  `id_categoria_pago` int(15) NOT NULL,
  `id_impuesto` int(2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `observaciones` varchar(200) DEFAULT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresas`
--

CREATE TABLE `empresas` (
  `id_empresa` varchar(15) NOT NULL,
  `nombre_razon` varchar(100) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `celular` varchar(15) DEFAULT NULL,
  `regimen_comun` tinyint(1) NOT NULL,
  `logo` longblob,
  `sitio_web` varchar(100) DEFAULT NULL,
  `id_ciudad` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL,
  `info` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empresas`
--

INSERT INTO `empresas` (`id_empresa`, `nombre_razon`, `direccion`, `telefono`, `correo`, `celular`, `regimen_comun`, `logo`, `sitio_web`, `id_ciudad`, `estado`, `transacion`, `info`) VALUES
('51744384-1', 'OLGA LUCIA CETINA HERNANDEZ Y/O HOTEL LAS VICTORIAS ', 'Carrera 32 contiguo a la Universidad Nacional vía Candelaria', '2624024', 'info@hotellasvictoriaspalmira.com', '3186921409', 1, NULL, 'www.hotellasvictoriaspalmira.com', 1087, 1, '', 'Consignación o transferencia en la cuenta corriente No. 012360010750 de Davivienda a nombre de Olga Lucia Cetina');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entidades_bancarias`
--

CREATE TABLE `entidades_bancarias` (
  `id_entidad_bancaria` int(11) NOT NULL,
  `entidad` varchar(200) NOT NULL,
  `codigo_banco` int(2) NOT NULL,
  `cuenta_nacional` tinyint(1) NOT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `entidades_bancarias`
--

INSERT INTO `entidades_bancarias` (`id_entidad_bancaria`, `entidad`, `codigo_banco`, `cuenta_nacional`, `transacion`) VALUES
(1, 'BANCO DE BOGOTÁ', 1, 1, ''),
(2, 'BANCO POPULAR', 2, 1, ''),
(3, 'BANCO CORPBANCA COLOMBIA S.A.', 6, 1, ''),
(4, '	\r\nBANCOLOMBIA S.A.', 7, 1, ''),
(5, '	\r\nCITIBANK COLOMBIA', 9, 1, ''),
(6, 'BANCO GNB COLOMBIA S.A.', 10, 1, ''),
(7, '	\r\nBANCO GNB SUDAMERIS COLOMBIA', 12, 1, ''),
(8, 'BBVA COLOMBIA', 13, 1, ''),
(9, '	\r\nHELM BANK', 14, 1, ''),
(10, 'RED MULTIBANCA COLPATRIA S.A.', 19, 1, ''),
(11, 'BANCO DE OCCIDENTE', 23, 1, ''),
(12, 'BANCO DE COMERCIO EXTERIOR DE COLOMBIA S.A. (BANCOLDEX)', 31, 0, ''),
(13, '	\r\nBANCO CAJA SOCIAL - BCSC S.A.', 32, 1, ''),
(14, 'BANCO AGRARIO DE COLOMBIA S.A.', 40, 1, ''),
(15, '	\r\nBANCO DAVIVIENDA S.A.', 51, 1, ''),
(16, 'BANCO AV VILLAS', 52, 1, ''),
(17, 'BANCO WWB S.A.', 53, 0, ''),
(18, 'BANCO PROCREDIT', 58, 1, ''),
(19, '	\r\nBANCAMIA', 59, 1, ''),
(20, 'BANCO PICHINCHA S.A.', 60, 1, ''),
(21, 'BANCOOMEVA', 61, 1, ''),
(22, 'BANCO FALABELLA S.A.', 62, 1, ''),
(23, 'BANCO FINANDINA S.A.', 63, 1, ''),
(24, 'BANCO SANTANDER DE NEGOCIOS COLOMBIA S.A. - BANCO SANTANDER', 65, 0, ''),
(25, '	\r\nBANCO COOPERATIVO COOPCENTRAL', 66, 1, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estaciones_empresas`
--

CREATE TABLE `estaciones_empresas` (
  `id` int(11) NOT NULL,
  `id_app_vendedor` int(11) NOT NULL,
  `identificador_estacion` varchar(50) NOT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados_facturas`
--

CREATE TABLE `estados_facturas` (
  `id_estado_factura` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estados_facturas`
--

INSERT INTO `estados_facturas` (`id_estado_factura`, `nombre`, `descripcion`, `transacion`) VALUES
(1, 'Aprobada', 'Las facturas cuando se generan permanecerán en estado "Aprobada", si no fueron cobradas, parcialmente cobradas, anuladas o generadas como borrador. ', ''),
(2, 'Cobrada', 'Las facturas en las cuales se les aplicó un recibo y quedaron totalmente cobradas, pasan de Estado: "Aprobada" a Estado: "Cobrada". También de Estado: "Parcialmente Cobrada" a Estado: "Cobrada".', ''),
(3, 'Parcialmente Cobrada', 'Las facturas en las cuales se les aplicó un recibo, quedando un saldo a cobrar, pasan de Estado: "Aprobada" a Estado: "Parcialmente Cobrada". ', ''),
(4, 'Vencida', 'Al momento de generar una factura, se tiene la opción de seleccionar el Estado "Borrador". Estos comprobantes borradores no generan movimientos ', ''),
(5, 'Anulada', 'Una factura puede pasar de Estado: "Aprobada" o "Cobrada" a "Anulada". Esto implica que se eliminan los efectos contables de la factura, es decir, y se eliminan los movimientos que había generado.', ''),
(6, 'Cotización', NULL, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados_usuarios`
--

CREATE TABLE `estados_usuarios` (
  `id_estado_usuario` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estados_usuarios`
--

INSERT INTO `estados_usuarios` (`id_estado_usuario`, `nombre`, `descripcion`, `transacion`) VALUES
(1, 'Activo', 'Los usuarios activos pueden iniciar sesión y obtener acceso completo a todas las funciones de su cuenta.', ''),
(2, 'Inactivo', 'Los usuarios inactivos han sido desactivados por un administrador de la cuenta y no pueden iniciar sesión.', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `id_factura` varchar(20) NOT NULL,
  `fecha` datetime NOT NULL,
  `id_resolucion` varchar(15) DEFAULT NULL,
  `id_cliente` int(11) NOT NULL,
  `subtotal` float NOT NULL,
  `iva` float DEFAULT NULL,
  `total` float NOT NULL,
  `id_estado_factura` int(11) NOT NULL,
  `id_empresa` varchar(15) NOT NULL,
  `id_usuario` varchar(15) NOT NULL,
  `id_termino_pago` int(11) NOT NULL,
  `vencimiento` date DEFAULT NULL,
  `nota` varchar(100) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `facturas`
--

INSERT INTO `facturas` (`id_factura`, `fecha`, `id_resolucion`, `id_cliente`, `subtotal`, `iva`, `total`, `id_estado_factura`, `id_empresa`, `id_usuario`, `id_termino_pago`, `vencimiento`, `nota`, `transacion`) VALUES
('FVH 1', '2017-03-25 17:13:55', '18762001981968', 2, 414117, 78682, 492750, 4, '51744384-1', '1113628111', 5, '2017-04-24', '4 HABITACIONES AMERICANAS SENCILLAS 111-112-206-207', '51744384-1_1'),
('FVH 10', '2017-03-30 22:09:07', '18762001981968', 10, 125042, 23758, 148800, 2, '51744384-1', '1113628111', 2, '2017-03-30', 'HABITACION 206 PAGA CONTRAJETA  VISA', '51744384-1_10'),
('FVH 100', '2017-05-09 11:13:29', '18762001981968', 25, 310587, 53110, 332600, 4, '51744384-1', '14251125', 5, '2017-06-08', 'ALOJAMIENTO SEÑOR  SERGIO ANDRES GOMEZ, CON INGRESO 24/04/17 AL 27/04/17 POR (3) NOCHES', '51744384-1_10'),
('FVH 101', '2017-05-09 11:39:41', '18762001981968', 53, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-05-09', '', '51744384-1_10'),
('FVH 102', '2017-05-09 11:44:22', '18762001981968', 53, 29000, 0, 29000, 2, '51744384-1', '14251125', 2, '2017-05-09', '', '51744384-1_10'),
('FVH 103', '2017-05-09 22:13:37', '18762001981968', 72, 94958, 16286, 102000, 2, '51744384-1', '1113628111', 2, '2017-05-09', 'HAB 110 ANOMBRE DE ESMERALDA  RODRIGUEZ', '51744384-1_10'),
('FVH 104', '2017-05-09 22:23:17', '18762001981968', 68, 94958, 16286, 102000, 5, '51744384-1', '1113628111', 2, '2017-05-09', 'HAB 110 A NOMBRE DE JESSICA LAURA HUEBSCHMANN', '51744384-1_10'),
('FVH 105', '2017-05-10 00:17:18', '18762001981968', 72, 103529, 17723, 111000, 2, '51744384-1', '1113628111', 2, '2017-05-10', 'HAB 120 JAIME ANDRES GOMEZ', '51744384-1_10'),
('FVH 106', '2017-05-10 00:24:14', '18762001981968', 72, 103529, 17723, 111000, 2, '51744384-1', '1113628111', 2, '2017-05-10', 'HAB 121 MANUEL BAYONA ENTRANDO EL 09/05/17', '51744384-1_10'),
('FVH 107', '2017-05-10 00:28:37', '18762001981968', 72, 103529, 17723, 111000, 2, '51744384-1', '1113628111', 2, '2017-05-10', 'HAB 118 JHON CASTAÑEDA ENTRANDO EL 09/05/17', '51744384-1_10'),
('FVH 108', '2017-05-10 00:35:16', '18762001981968', 72, 103529, 17723, 111000, 2, '51744384-1', '1113628111', 2, '2017-05-10', 'HAB118 ANJA HEUFT ENTRANDO EL 09/05/17', '51744384-1_10'),
('FVH 109', '2017-05-10 05:02:02', '18762001981968', 72, 94958, 16286, 102000, 2, '51744384-1', '1113628111', 2, '2017-05-10', 'HAB106 JESSICA LAURA HUEBSCHMANN ENTRANDO EL 09/05/17', '51744384-1_10'),
('FVH 11', '2017-03-31 12:34:34', '18762001981968', 11, 17000, 0, 17000, 2, '51744384-1', '14251125', 2, '2017-03-31', '', '51744384-1_10'),
('FVH 110', '2017-05-10 13:22:44', '18762001981968', 73, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-05-10', 'ALOJAMIENTO SEÑOR  ELKIN FUENTES DEL 9 AL 10 DE MAYO DEL 2017.', '51744384-1_10'),
('FVH 111', '2017-05-11 01:36:49', '18762001981968', 2, 103529, 19671, 123200, 4, '51744384-1', '1113628111', 5, '2017-06-10', 'HABITACION 205 A NOMBRE DE EDUERDO SANTANA ENTRANDO EL 10/05/17 CON SALIDA EL 11/05/17', '51744384-1_10'),
('FVH 112', '2017-05-11 01:43:19', '18762001981968', 74, 103529, 19671, 123200, 2, '51744384-1', '1113628111', 2, '2017-05-11', 'HAB 107 CON ENTRADA EL 10/05/17 Y SALIDA EL 11/05/17', '51744384-1_10'),
('FVH 113', '2017-05-11 06:56:16', '18762001981968', 75, 35000, 0, 35000, 2, '51744384-1', '1113628111', 2, '2017-05-11', 'INGRESO RECIBIDOS PARA TERCEROS RESTAURANTE', '51744384-1_10'),
('FVH 114', '2017-05-11 07:15:53', '18762001981968', 76, 103529, 19671, 123200, 4, '51744384-1', '14251125', 2, '2017-05-11', 'ALOJAMIENTO PARA 2 PERSONAS DEL DIA 08 MAYO DEL 2017', '51744384-1_10'),
('FVH 115', '2017-05-11 07:22:03', '18762001981968', 77, 103529, 19671, 123200, 4, '51744384-1', '14251125', 2, '2017-05-11', 'ALOJAMIENTO DIA 09 DE MAYO 2017', '51744384-1_10'),
('FVH 116', '2017-05-11 07:27:36', '18762001981968', 78, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-05-11', 'ALOJAMIENTO 10 DE MAYO DLE 2017', '51744384-1_10'),
('FVH 117', '2017-05-11 07:41:15', '18762001981968', 79, 94958, 18042, 113000, 2, '51744384-1', '14251125', 2, '2017-05-11', '', '51744384-1_10'),
('FVH 118', '2017-05-11 08:23:38', '18762001981968', 78, 17000, 0, 17000, 2, '51744384-1', '14251125', 2, '2017-05-11', '', '51744384-1_10'),
('FVH 119', '2017-05-11 15:46:15', '18762001981968', 80, 125043, 23758, 148800, 2, '51744384-1', '14251125', 2, '2017-05-11', '', '51744384-1_10'),
('FVH 12', '2017-03-31 13:57:43', '18762001981968', 8, 17000, 0, 17000, 2, '51744384-1', '14251125', 2, '2017-03-31', '', '51744384-1_10'),
('FVH 120', '2017-05-11 22:18:34', '18762001981968', 81, 198487, 37713, 236200, 2, '51744384-1', '1113628111', 2, '2017-05-11', 'HABITACION 108 DOBLE HAB 110 SENCILLA ', '51744384-1_10'),
('FVH 121', '2017-05-11 22:46:04', '18762001981968', 2, 103529, 19671, 123200, 4, '51744384-1', '1113628111', 5, '2017-06-10', 'HAB 112  EDUARDO SANTANA ENTRADA EL 11/05/17 SALIDA EL 12/05/17', '51744384-1_10'),
('FVH 122', '2017-05-12 06:13:37', '18762001981968', 75, 17000, 0, 17000, 2, '51744384-1', '1113628111', 2, '2017-05-12', 'INGRESOS RECIBIDOS PARA TERCEROS RESTAURANTE', '51744384-1_10'),
('FVH 123', '2017-05-12 10:17:28', '18762001981968', 53, 414116, 78682, 492750, 2, '51744384-1', '14251125', 2, '2017-05-12', 'ALOJAMIENTO POR  (4) NOCHES DEL 08 AL 12 DE MAYO DEL 2017 . SEÑORA MONICA ROBLEDO', '51744384-1_10'),
('FVH 124', '2017-05-12 13:15:55', '18762001981968', 8, 8000, 0, 8000, 2, '51744384-1', '14251125', 2, '2017-05-12', '', '51744384-1_10'),
('FVH 125', '2017-05-12 17:24:29', '18762001981968', 82, 250086, 47516, 297600, 4, '51744384-1', '14251125', 2, '2017-05-12', '', '51744384-1_10'),
('FVH 126', '2017-05-12 19:29:40', '18762001981968', 81, 198487, 37713, 236200, 2, '51744384-1', '1113628111', 2, '2017-05-12', 'HOSPEDAJE HABI 110 SENCILLA Y HAB 108 DOBLE', '51744384-1_10'),
('FVH 127', '2017-05-13 10:47:51', '18762001981968', 81, 57613, 6386, 63950, 2, '51744384-1', '14251125', 2, '2017-05-13', '', '51744384-1_10'),
('FVH 128', '2017-05-13 22:46:38', '18762001981968', 83, 103529, 19671, 123200, 2, '51744384-1', '1113628111', 2, '2017-05-13', '', '51744384-1_10'),
('FVH 129', '2017-05-14 15:07:55', '18762001981968', 84, 130000, 0, 130000, 2, '51744384-1', '14251125', 2, '2017-05-14', '', '51744384-1_10'),
('FVH 13', '2017-03-31 16:32:07', '18762001981968', 7, 560000, 0, 560000, 2, '51744384-1', '14251125', 2, '2017-03-31', 'ALMUERZOS 28', '51744384-1_10'),
('FVH 130', '2017-05-15 22:09:10', '18762001981968', 85, 130529, 19671, 150200, 2, '51744384-1', '14251125', 2, '2017-05-15', '', '51744384-1_10'),
('FVH 131', '2017-05-16 09:10:19', '18762001981968', 86, 480790, 90210, 571000, 2, '51744384-1', '1113628111', 2, '2017-05-16', 'HAB 102 POR 2 NOCHES HABI 110 POR DOS NOCHES  HAB 106 POR 1 NOCHE  VALOR POR NOCHE 113.000', '51744384-1_10'),
('FVH 132', '2017-05-16 18:28:20', '18762001981968', 53, 103529, 19671, 123200, 2, '51744384-1', '1113628111', 2, '2017-05-16', 'HOSPEDAJE POR 1 NOCHE', '51744384-1_10'),
('FVH 133', '2017-05-16 19:00:23', '18762001981968', 53, 310587, 59012, 369550, 2, '51744384-1', '1113628111', 2, '2017-05-16', 'HOSPEDAJE HAB 207 POR 3 NOCHES ENTRANDO EL 15/05/17 ', '51744384-1_10'),
('FVH 134', '2017-05-16 21:33:42', '18762001981968', 25, 207058, 35407, 221750, 4, '51744384-1', '14251125', 5, '2017-06-15', 'ALOJAMIENTO SEÑOR  JOSE MIGUEL CAPARRASCO DEL 16 AL 18 DE MAYO  HAB SENCILLA CON AIRE', '51744384-1_10'),
('FVH 135', '2017-05-17 07:53:27', '18762001981968', 85, 6800, 0, 6800, 2, '51744384-1', '1113628111', 2, '2017-05-17', 'INGRESOS RECIBIDOS PARA TERCEROS RESTAURANTE ', '51744384-1_10'),
('FVH 136', '2017-05-17 08:41:50', '18762001981968', 53, 9000, 0, 9000, 2, '51744384-1', '1113628111', 2, '2017-05-17', '', '51744384-1_10'),
('FVH 137', '2017-05-17 10:08:52', '18762001981968', 87, 207059, 39341, 246400, 5, '51744384-1', '1113628111', 2, '2017-05-17', 'PAGO DE NOCHES HAB 112 X 1 PERSONA  NOCHE DE L 17 Y 18 DE MARZO DEL 2017', '51744384-1_10'),
('FVH 138', '2017-05-17 13:50:01', '18762001981968', 23, 83000, 0, 83000, 2, '51744384-1', '1113628111', 2, '2017-05-17', '', '51744384-1_10'),
('FVH 139', '2017-05-17 16:23:11', '18762001981968', 53, 8000, 0, 8000, 2, '51744384-1', '1113628111', 2, '2017-05-17', '', '51744384-1_10'),
('FVH 14', '2017-04-01 09:40:23', '18762001981968', 13, 151529, 19671, 171200, 4, '51744384-1', '14251125', 5, '2017-05-01', 'ALOJAMIENTO DEL 30/03/17 A NOMBRE DE EDWIN BETANCOURT Y JOHNATTAN CASTELLANOS CON  CENA Y ALMUERZO', '51744384-1_10'),
('FVH 140', '2017-05-17 22:59:39', '18762001981968', 88, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-05-17', '', '51744384-1_10'),
('FVH 141', '2017-05-18 01:19:54', '18762001981968', 89, 310587, 59012, 369550, 2, '51744384-1', '14251125', 2, '2017-05-18', '', '51744384-1_10'),
('FVH 142', '2017-05-18 01:32:50', '18762001981968', 90, 310587, 59012, 369550, 2, '51744384-1', '14251125', 2, '2017-05-18', '', '51744384-1_10'),
('FVH 143', '2017-05-18 09:03:19', '18762001981968', 53, 9800, 0, 9800, 2, '51744384-1', '1113628111', 2, '2017-05-18', '', '51744384-1_10'),
('FVH 144', '2017-05-18 12:50:51', '18762001981968', 91, 195500, 0, 195500, 4, '51744384-1', '1113628111', 2, '2017-05-18', '', '51744384-1_10'),
('FVH 145', '2017-05-18 18:13:10', '18762001981968', 92, 207058, 39341, 246350, 2, '51744384-1', '1113628111', 2, '2017-05-18', 'HABI 111 POR 2 NOCHES ENTRANDO EL 18/05/17 Y SALIENDO EL ', '51744384-1_10'),
('FVH 146', '2017-05-18 20:10:34', '18762001981968', 93, 103529, 19671, 123200, 4, '51744384-1', '14251125', 5, '2017-06-17', 'HABITACION SEÑORA LEIDY JOHANNA BERMUDEZ  DEL 18 AL 19 DE MAYO-2017', '51744384-1_10'),
('FVH 147', '2017-05-19 00:03:26', '18762001981968', 94, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-05-19', 'ALOJAMIENTO SEÑOR CIRO CASTELLANOS DEL 18 AL 19 DE MAYO-2017', '51744384-1_10'),
('FVH 148', '2017-05-20 00:09:08', '18762001981968', 85, 463116, 78682, 541750, 2, '51744384-1', '14251125', 2, '2017-05-20', 'ALOJAMIENTO SEÑOR RODRIGO GOMEZ POR 3 NOCHES Y SEÑOR DIEGO CACUA POR 1 NOCHE . ', '51744384-1_10'),
('FVH 149', '2017-05-20 00:28:29', '18762001981968', 85, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-05-20', 'ALOJAMIENTO DEL SEÑOR  RODRIGO GOMEZ POR 1 NOCHE', '51744384-1_10'),
('FVH 15', '2017-04-01 10:20:51', '18762001981968', 13, 209142, 26057, 235150, 4, '51744384-1', '14251125', 5, '2017-05-01', '', '51744384-1_10'),
('FVH 150', '2017-05-20 13:57:01', '18762001981968', 89, 57000, 0, 57000, 2, '51744384-1', '1113628111', 2, '2017-05-20', '', '51744384-1_10'),
('FVH 151', '2017-05-20 14:22:04', '18762001981968', 95, 223924, 32476, 256400, 2, '51744384-1', '1113628111', 2, '2017-05-20', 'HOSPEDAJE POR 2 NOCHES 101300 POR NOCHE Y RESTAURANTE ', '51744384-1_10'),
('FVH 152', '2017-05-21 14:00:14', '18762001981968', 2, 900232, 157364, 1057550, 4, '51744384-1', '1113628111', 2, '2017-05-21', '4 HABITACIONES TIPO CHALET PARA 6 PERSONAS ', '51744384-1_10'),
('FVH 153', '2017-05-21 17:17:28', '18762001981968', 96, 56500, 0, 56500, 2, '51744384-1', '1113628111', 2, '2017-05-21', '', '51744384-1_10'),
('FVH 154', '2017-05-22 09:30:45', '18762001981968', 87, 738484, 140312, 878750, 2, '51744384-1', '1113667405', 2, '2017-05-22', 'habotacion 107 dia 20 de mayo ', '51744384-1_10'),
('FVH 155', '2017-05-23 22:53:03', '18762001981968', 53, 207058, 39341, 246350, 2, '51744384-1', '1113628111', 2, '2017-05-23', '', '51744384-1_10'),
('FVH 156', '2017-05-23 23:47:40', '18762001981968', 97, 250086, 47516, 297600, 2, '51744384-1', '1113628111', 2, '2017-05-23', '', '51744384-1_10'),
('FVH 157', '2017-05-24 20:26:24', '18762001981968', 99, 207058, 39341, 246350, 2, '51744384-1', '1113628111', 2, '2017-05-24', 'HOSPEDAJE POR 2 NOCHES ', '51744384-1_10'),
('FVH 158', '2017-05-24 21:27:09', '18762001981968', 100, 207058, 39341, 246350, 2, '51744384-1', '1113628111', 2, '2017-05-24', '', '51744384-1_10'),
('FVH 159', '2017-05-24 21:46:43', '18762001981968', 101, 137142, 26057, 163150, 2, '51744384-1', '1113628111', 2, '2017-05-24', '', '51744384-1_10'),
('FVH 16', '2017-04-04 13:32:05', '18762001981968', 14, 40000, 0, 40000, 2, '51744384-1', '1113628111', 2, '2017-04-04', '', '51744384-1_10'),
('FVH 160', '2017-05-24 22:02:17', '18762001981968', 85, 207058, 39341, 246350, 2, '51744384-1', '1113628111', 2, '2017-05-24', 'HABI 118-112 POR 1 NOCHE CADA UNA  ', '51744384-1_10'),
('FVH 161', '2017-05-25 17:54:24', '18762001981968', 102, 16000, 0, 16000, 2, '51744384-1', '14251125', 2, '2017-05-25', '', '51744384-1_10'),
('FVH 162', '2017-05-26 13:47:56', '18762001981968', 103, 32000, 0, 32000, 2, '51744384-1', '14251125', 2, '2017-05-26', '', '51744384-1_10'),
('FVH 163', '2017-05-26 18:03:55', '18762001981968', 104, 228572, 43429, 272000, 2, '51744384-1', '14251125', 2, '2017-05-26', '', '51744384-1_10'),
('FVH 164', '2017-05-27 00:02:25', '18762001981968', 105, 125043, 23758, 148800, 2, '51744384-1', '1113628111', 2, '2017-05-27', '', '51744384-1_10'),
('FVH 165', '2017-05-27 14:07:40', '18762001981968', 106, 250086, 47516, 297600, 2, '51744384-1', '14251125', 2, '2017-05-27', '', '51744384-1_10'),
('FVH 166', '2017-05-27 17:16:12', '18762001981968', 107, 125043, 23758, 148800, 2, '51744384-1', '14251125', 2, '2017-05-27', '', '51744384-1_10'),
('FVH 167', '2017-05-27 19:15:51', '18762001981968', 108, 125043, 23758, 148800, 2, '51744384-1', '1113628111', 2, '2017-05-27', '', '51744384-1_10'),
('FVH 168', '2017-05-28 11:14:55', '18762001981968', 109, 192269, 36531, 228800, 2, '51744384-1', '14251125', 2, '2017-05-28', '', '51744384-1_10'),
('FVH 169', '2017-05-28 13:32:03', '18762001981968', 110, 250086, 47516, 297600, 2, '51744384-1', '14251125', 2, '2017-05-28', '', '51744384-1_10'),
('FVH 17', '2017-04-05 02:11:44', '18762001981968', 13, 207058, 39341, 246350, 4, '51744384-1', '14251125', 5, '2017-05-05', 'ALOJAMIENTO SEÑOR FERNANDO OSPINA  POR 2  NOCHES DIAS 3 Y 4 ABRIL-17', '51744384-1_10'),
('FVH 170', '2017-05-28 16:34:27', '18762001981968', 107, 159000, 0, 159000, 2, '51744384-1', '14251125', 2, '2017-05-28', '', '51744384-1_10'),
('FVH 171', '2017-05-28 17:28:31', '18762001981968', 111, 1198030, 137471, 1335500, 2, '51744384-1', '14251125', 2, '2017-05-28', 'ALOJAMIENTO DEL 22 DE MAYO AL 29 DE MAYO 2017, Y SERVICIO DE RESTAURANTE', '51744384-1_10'),
('FVH 172', '2017-05-28 18:07:31', '18762001981968', 112, 1344540, 255462, 1599950, 2, '51744384-1', '14251125', 2, '2017-05-28', 'ALOJAMIENTO POR UN MES PARA 2 PERSONAS DEL 28 DEMAYO AL 27 DE JUNIO DEL 2017', '51744384-1_10'),
('FVH 173', '2017-05-28 18:45:19', '18762001981968', 113, 72000, 0, 72000, 2, '51744384-1', '14251125', 2, '2017-05-28', '', '51744384-1_10'),
('FVH 174', '2017-05-29 07:33:38', '18762001981968', 108, 83500, 0, 83500, 2, '51744384-1', '1113667405', 2, '2017-05-29', '', '51744384-1_10'),
('FVH 175', '2017-05-30 13:17:51', '18762001981968', 39, 8000, 0, 8000, 2, '51744384-1', '1113628111', 2, '2017-05-30', '', '51744384-1_10'),
('FVH 176', '2017-06-01 15:54:51', '18762001981968', 114, 94958, 18042, 113000, 2, '51744384-1', '1113628111', 2, '2017-06-01', '', '51744384-1_10'),
('FVH 177', '2017-06-02 16:19:13', '18762001981968', 115, 125210, 23790, 149000, 2, '51744384-1', '1113628111', 2, '2017-06-02', '', '51744384-1_10'),
('FVH 178', '2017-06-03 12:18:09', '18762001981968', 116, 828232, 157364, 985550, 2, '51744384-1', '1113628111', 2, '2017-06-03', '', '51744384-1_10'),
('FVH 179', '2017-06-03 17:46:27', '18762001981968', 117, 207058, 39341, 246350, 2, '51744384-1', '1113628111', 2, '2017-06-03', '', '51744384-1_10'),
('FVH 18', '2017-04-05 16:18:07', '18762001981968', 16, 125043, 23758, 148800, 2, '51744384-1', '1113628111', 2, '2017-04-05', '', '51744384-1_10'),
('FVH 180', '2017-06-06 12:46:38', '18762001981968', 116, 661500, 0, 661500, 2, '51744384-1', '14251125', 2, '2017-06-06', '', '51744384-1_10'),
('FVH 181', '2017-06-06 21:50:26', '18762001981968', 118, 103529, 19671, 123200, 2, '51744384-1', '1113628111', 2, '2017-06-06', '', '51744384-1_10'),
('FVH 182', '2017-06-07 18:45:19', '18762001981968', 119, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-06-07', '', '51744384-1_10'),
('FVH 183', '2017-06-07 18:54:43', '18762001981968', 119, 207058, 39341, 246350, 2, '51744384-1', '14251125', 2, '2017-06-07', 'ALOJAMIENTO POR 2 NOCHES', '51744384-1_10'),
('FVH 184', '2017-06-21 17:10:45', '18762001981968', 121, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-06-21', '', '51744384-1_10'),
('FVH 185', '2017-06-21 21:35:03', '18762001981968', 122, 103529, 19671, 123200, 2, '51744384-1', '1113628111', 2, '2017-06-21', '', '51744384-1_10'),
('FVH 186', '2017-06-21 22:50:51', '18762001981968', 123, 103529, 19671, 123200, 2, '51744384-1', '1113628111', 2, '2017-06-21', '', '51744384-1_10'),
('FVH 187', '2017-06-23 08:01:35', '18762001981968', 53, 310587, 59012, 369550, 2, '51744384-1', '14251125', 2, '2017-06-23', 'ALOJAMIENTO DEL 2O AL 23 DE JUNIO DEL 2017 ', '51744384-1_10'),
('FVH 188', '2017-06-23 15:38:11', '18762001981968', 124, 125043, 23758, 148800, 2, '51744384-1', '14251125', 2, '2017-06-23', '', '51744384-1_10'),
('FVH 189', '2017-06-23 16:09:26', '18762001981968', 125, 47000, 0, 47000, 2, '51744384-1', '14251125', 2, '2017-06-23', '', '51744384-1_10'),
('FVH 19', '2017-04-05 16:43:57', '18762001981968', 15, 310587, 59012, 369550, 2, '51744384-1', '1113628111', 2, '2017-04-05', 'PAGO DE HABITACION 207 POR 3 NOCHES', '51744384-1_10'),
('FVH 190', '2017-06-23 21:30:33', '18762001981968', 126, 125043, 23758, 148800, 2, '51744384-1', '1113628111', 2, '2017-06-23', '', '51744384-1_10'),
('FVH 191', '2017-07-11 19:47:48', '18762001981968', 127, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-07-11', '', '51744384-1_10'),
('FVH 192', '2017-07-13 01:22:55', '18762001981968', 127, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-07-13', '', '51744384-1_10'),
('FVH 193', '2017-07-13 12:57:48', '18762001981968', 128, 103529, 19671, 123200, 2, '51744384-1', '1113628111', 2, '2017-07-13', '', '51744384-1_10'),
('FVH 194', '2017-07-13 15:15:33', '18762001981968', 127, 103529, 19671, 123200, 2, '51744384-1', '1113628111', 2, '2017-07-13', '', '51744384-1_10'),
('FVH 195', '2017-07-13 23:13:25', '18762001981968', 129, 125043, 23758, 148800, 2, '51744384-1', '14251125', 2, '2017-07-13', '', '51744384-1_10'),
('FVH 196', '2017-07-22 08:17:32', '18762001981968', 130, 167142, 26057, 193150, 2, '51744384-1', '14251125', 2, '2017-07-22', '', '51744384-1_10'),
('FVH 197', '2017-07-22 08:34:14', '18762001981968', 131, 36500, 0, 36500, 2, '51744384-1', '14251125', 2, '2017-07-22', '', '51744384-1_10'),
('FVH 198', '2017-07-25 20:09:35', '18762001981968', 133, 207058, 39341, 246350, 2, '51744384-1', '14251125', 2, '2017-07-25', 'PAGO ALOJAMIENTO POR 2 HABITACIONES CON AIRE SENCILLAS', '51744384-1_10'),
('FVH 199', '2017-07-27 21:05:28', '18762001981968', 134, 114529, 19671, 134200, 2, '51744384-1', '14251125', 2, '2017-07-27', '', '51744384-1_10'),
('FVH 2', '2017-03-25 19:28:46', '18762001981968', 3, 328000, 0, 328000, 2, '51744384-1', '1113628111', 2, '2017-03-25', 'INGRESO RECIBIDO PARA TERCEROS (RESTAURANTE)', '51744384-1_2'),
('FVH 20', '2017-04-06 01:12:03', '18762001981968', 18, 157983, 30017, 188000, 2, '51744384-1', '14251125', 2, '2017-04-06', ' 3 PERSONAS', '51744384-1_10'),
('FVH 200', '2017-07-28 13:49:40', '18762001981968', 135, 53000, 0, 53000, 2, '51744384-1', '1113628111', 2, '2017-07-28', '', '51744384-1_10'),
('FVH 201', '2017-07-29 11:25:07', '18762001981968', 136, 413445, 78555, 492000, 2, '51744384-1', '1113628111', 2, '2017-07-29', 'PAGO DE HAB 110* 3 NOCHES ', '51744384-1_10'),
('FVH 202', '2017-07-30 10:18:27', '18762001981968', 137, 200420, 38080, 238500, 2, '51744384-1', '1113628111', 2, '2017-07-30', '', '51744384-1_10'),
('FVH 203', '2017-07-30 16:21:35', '18762001981968', 138, 49500, 0, 49500, 2, '51744384-1', '1113628111', 2, '2017-07-30', '', '51744384-1_10'),
('FVH 204', '2017-07-31 07:21:06', '18762001981968', 139, 280000, 0, 280000, 2, '51744384-1', '1113628111', 2, '2017-07-31', '', '51744384-1_10'),
('FVH 205', '2017-08-03 07:08:06', '18762001981968', 13, 103529, 19671, 123200, 4, '51744384-1', '14251125', 5, '2017-09-02', '', '51744384-1_10'),
('FVH 206', '2017-08-05 07:23:29', '18762001981968', 140, 103529, 19671, 123200, 5, '51744384-1', '14251125', 2, '2017-08-05', '', '51744384-1_10'),
('FVH 207', '2017-08-05 08:17:40', '18762001981968', 141, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-08-05', '', '51744384-1_10'),
('FVH 208', '2017-08-05 12:03:17', '18762001981968', 142, 125043, 23758, 148800, 2, '51744384-1', '14251125', 2, '2017-08-05', '', '51744384-1_10'),
('FVH 209', '2017-08-05 14:56:53', '18762001981968', 143, 317312, 60289, 377600, 2, '51744384-1', '14251125', 2, '2017-08-05', 'PAGO ALOJA. POR 2 NOCHES DEL 05 AL 07/08/17 HAB 111', '51744384-1_10'),
('FVH 21', '2017-04-06 01:43:14', '18762001981968', 19, 310587, 59012, 369550, 2, '51744384-1', '14251125', 2, '2017-04-06', 'ALOJAMIENTO PARA 1 PAX DEL 03 ABRIL AL 06 ABRIL-2017 POR 3 NOCHES. SEÑOR DIEGO BERNAL', '51744384-1_10'),
('FVH 210', '2017-08-05 15:12:06', '18762001981968', 144, 411426, 78171, 489550, 4, '51744384-1', '14251125', 2, '2017-08-05', 'PAGO HAB. POR 3 NOCHES CON RC No.4001', '51744384-1_10'),
('FVH 211', '2017-08-05 17:45:51', '18762001981968', 145, 1538150, 292249, 1830400, 2, '51744384-1', '14251125', 2, '2017-08-05', 'PAGO ALOJA. POR  8 NOCHES PARA 4 PAX', '51744384-1_10'),
('FVH 212', '2017-08-06 09:49:27', '18762001981968', 146, 170755, 32443, 203150, 2, '51744384-1', '14251125', 2, '2017-08-06', 'pago alj. hab 109 ', '51744384-1_10'),
('FVH 213', '2017-08-06 17:31:18', '18762001981968', 147, 83000, 0, 83000, 2, '51744384-1', '14251125', 2, '2017-08-06', '', '51744384-1_10'),
('FVH 214', '2017-08-16 16:22:29', '18762001981968', 149, 32500, 0, 32500, 2, '51744384-1', '14251125', 2, '2017-08-16', 'SERV. RESTAURANTE', '51744384-1_10'),
('FVH 215', '2017-08-17 17:05:52', '18762001981968', 150, 103529, 15736, 98550, 2, '51744384-1', '14251125', 2, '2017-08-17', 'ALOJAMIENTO POR 1 NOCHE DEL 17 AL 18 DE AGOST-17', '51744384-1_10'),
('FVH 216', '2017-08-23 10:20:39', '18762001981968', 2, 103529, 19671, 123200, 5, '51744384-1', '1113628111', 2, '2017-08-23', '', '51744384-1_10'),
('FVH 217', '2017-09-14 08:38:34', '18762001981968', 151, 94958, 18042, 113000, 2, '51744384-1', '14251125', 2, '2017-09-14', 'PAGO ALOJAMIENTO POR 1 NOCHE DEL 14 AL 15 DE SEPT-2017', '51744384-1_10'),
('FVH 22', '2017-04-07 10:42:04', '18762001981968', 20, 250086, 47516, 297600, 2, '51744384-1', '1113628111', 2, '2017-04-07', '', '51744384-1_10'),
('FVH 23', '2017-04-07 17:20:30', '18762001981968', 21, 2218490, 421513, 2640000, 2, '51744384-1', '1113628111', 2, '2017-04-07', '', '51744384-1_10'),
('FVH 24', '2017-04-07 17:43:06', '18762001981968', 21, 277311, 52689, 330000, 2, '51744384-1', '1113628111', 2, '2017-04-07', 'HOSPEDAJE DE 2 DEPORTISTAS', '51744384-1_10'),
('FVH 25', '2017-04-09 01:25:45', '18762001981968', 22, 125043, 23758, 148800, 2, '51744384-1', '14251125', 2, '2017-04-09', '', '51744384-1_10'),
('FVH 26', '2017-04-09 15:05:32', '18762001981968', 21, 1274000, 0, 1274000, 2, '51744384-1', '1113628111', 2, '2017-04-09', 'INGRESO RESIVIDOS PARA TERCEROS (RESTAURANTE)', '51744384-1_10'),
('FVH 27', '2017-04-12 06:24:08', '18762001981968', 25, 207058, 39341, 246350, 4, '51744384-1', '1113628111', 2, '2017-04-12', '', '51744384-1_10'),
('FVH 28', '2017-04-12 10:30:48', '18762001981968', 24, 9536220, 1053780, 10590000, 4, '51744384-1', '14251125', 2, '2017-04-12', 'HOSPEDAJE PANAMERICANO DE NATACION. 33 DEPORTISTAS DEL  6 AL 10 DE ABRIL-2017', '51744384-1_10'),
('FVH 29', '2017-04-12 13:29:24', '18762001981968', 26, 507037, 73062, 580050, 2, '51744384-1', '14251125', 2, '2017-04-12', '', '51744384-1_10'),
('FVH 3', '2017-03-25 19:36:36', '18762001981968', 4, 131000, 0, 131000, 2, '51744384-1', '1113628111', 2, '2017-03-25', '', '51744384-1_3'),
('FVH 30', '2017-04-12 14:16:21', '18762001981968', 27, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-04-12', '', '51744384-1_10'),
('FVH 31', '2017-04-12 14:20:53', '18762001981968', 27, 26000, 0, 26000, 2, '51744384-1', '14251125', 2, '2017-04-12', '', '51744384-1_10'),
('FVH 32', '2017-04-12 21:40:44', '18762001981968', 28, 192269, 36531, 228800, 2, '51744384-1', '1113628111', 2, '2017-04-12', '', '51744384-1_10'),
('FVH 33', '2017-04-13 08:06:57', '18762001981968', 29, 207058, 39341, 246350, 2, '51744384-1', '14251125', 2, '2017-04-13', '', '51744384-1_10'),
('FVH 34', '2017-04-13 23:04:35', '18762001981968', 30, 123529, 19671, 143200, 2, '51744384-1', '1113628111', 2, '2017-04-13', 'INGRESO RECIBIDOS PARA TERCEROS RESTAURANTE $20.000 Y A LOJAMIENTO DE HABITACION 106 POR 1 NOCHE', '51744384-1_10'),
('FVH 35', '2017-04-14 09:54:17', '18762001981968', 31, 75000, 0, 75000, 2, '51744384-1', '14251125', 2, '2017-04-14', '', '51744384-1_10'),
('FVH 36', '2017-04-14 14:39:44', '18762001981968', 32, 1000340, 190064, 1190400, 4, '51744384-1', '14251125', 5, '2017-05-14', 'ALOJAMIENTO SEÑOR  OSCAR BAUTISTA DEL 10 AL 14 DE ABRIL-2017 (4) NOCHES', '51744384-1_10'),
('FVH 37', '2017-04-14 16:29:30', '18762001981968', 33, 213656, 30145, 243800, 2, '51744384-1', '14251125', 2, '2017-04-14', '', '51744384-1_10'),
('FVH 38', '2017-04-14 22:26:56', '18762001981968', 34, 250420, 47580, 298000, 2, '51744384-1', '1113628111', 2, '2017-04-14', 'PAGO DE HABITACION 206-207 POR 1 NOCHE ', '51744384-1_10'),
('FVH 39', '2017-04-15 07:22:52', '18762001981968', 35, 240336, 45664, 286000, 2, '51744384-1', '14251125', 2, '2017-04-15', 'ALOJAMIENTO POR 1 NOCHE HAB 109-104', '51744384-1_10'),
('FVH 4', '2017-03-29 11:26:27', '18762001981968', 7, 6034160, 587564, 6256000, 2, '51744384-1', '14251125', 2, '2017-03-29', 'HOSPEDAJE VUELTA AL VALLE, DIAS 28 Y 31 DE MARZO-2017.  28 PAX', '51744384-1_4'),
('FVH 40', '2017-04-15 08:00:08', '18762001981968', 30, 175029, 19671, 194700, 2, '51744384-1', '14251125', 2, '2017-04-15', '', '51744384-1_10'),
('FVH 41', '2017-04-15 08:30:53', '18762001981968', 36, 141177, 26824, 168000, 2, '51744384-1', '14251125', 2, '2017-04-15', '', '51744384-1_10'),
('FVH 42', '2017-04-15 13:30:06', '18762001981968', 29, 17000, 0, 17000, 2, '51744384-1', '14251125', 2, '2017-04-15', '', '51744384-1_10'),
('FVH 43', '2017-04-15 19:38:06', '18762001981968', 37, 207059, 39341, 246400, 2, '51744384-1', '1113628111', 2, '2017-04-15', 'PAGO DE HABITACION 106-107 POR 1 NOCHE', '51744384-1_10'),
('FVH 44', '2017-04-16 15:31:17', '18762001981968', 38, 113445, 21555, 135000, 2, '51744384-1', '14251125', 2, '2017-04-16', '', '51744384-1_10'),
('FVH 45', '2017-04-18 13:39:50', '18762001981968', 39, 8000, 0, 8000, 2, '51744384-1', '1113628111', 2, '2017-04-18', '', '51744384-1_10'),
('FVH 46', '2017-04-19 08:19:11', '18762001981968', 40, 103529, 19671, 123200, 2, '51744384-1', '1113628111', 2, '2017-04-19', '', '51744384-1_10'),
('FVH 47', '2017-04-19 21:04:40', '18762001981968', 41, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-04-19', '', '51744384-1_10'),
('FVH 48', '2017-04-19 22:45:29', '18762001981968', 42, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-04-19', '', '51744384-1_10'),
('FVH 49', '2017-04-19 23:31:48', '18762001981968', 43, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-04-19', '', '51744384-1_10'),
('FVH 5', '2017-03-30 12:03:17', '18762001981968', 8, 17000, 0, 17000, 5, '51744384-1', '14251125', 2, '2017-03-30', 'ALMUERZOS (2)', '51744384-1_5'),
('FVH 50', '2017-04-20 20:48:59', '18762001981968', 44, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-04-20', '', '51744384-1_10'),
('FVH 51', '2017-04-20 21:49:49', '18762001981968', 45, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-04-20', '', '51744384-1_10'),
('FVH 52', '2017-04-20 22:18:49', '18762001981968', 43, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-04-20', '', '51744384-1_10'),
('FVH 53', '2017-04-21 11:32:25', '18762001981968', 11, 8500, 0, 8500, 2, '51744384-1', '1113628111', 2, '2017-04-21', 'INGRESO RECIBIDO PARA TERCEROS (RESTAIRANTE )', '51744384-1_10'),
('FVH 54', '2017-04-21 15:14:00', '18762001981968', 44, 8000, 0, 8000, 2, '51744384-1', '1113628111', 2, '2017-04-21', 'INGRESO RECIBIDO PARA TERCEROS ', '51744384-1_10'),
('FVH 55', '2017-04-22 15:45:12', '18762001981968', 46, 103529, 19671, 123200, 2, '51744384-1', '1113628111', 2, '2017-04-22', '', '51744384-1_10'),
('FVH 56', '2017-04-22 20:40:39', '18762001981968', 47, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-04-22', '', '51744384-1_10'),
('FVH 57', '2017-04-23 00:28:18', '18762001981968', 48, 137142, 26057, 163150, 2, '51744384-1', '14251125', 2, '2017-04-23', '', '51744384-1_10'),
('FVH 58', '2017-04-23 16:09:57', '18762001981968', 49, 102000, 0, 102000, 2, '51744384-1', '1113628111', 2, '2017-04-23', 'INGRESOS RECIBIDOS PARA TERCEROS ', '51744384-1_10'),
('FVH 59', '2017-04-23 17:04:17', '18762001981968', 50, 75500, 0, 75500, 4, '51744384-1', '1113628111', 2, '2017-04-23', '', '51744384-1_10'),
('FVH 6', '2017-03-30 14:12:38', '18762001981968', 8, 17000, 0, 17000, 2, '51744384-1', '14251125', 2, '2017-03-30', '', '51744384-1_6'),
('FVH 60', '2017-04-24 09:19:26', '18762001981968', 51, 41000, 0, 41000, 2, '51744384-1', '1113667405', 2, '2017-04-24', '', '51744384-1_10'),
('FVH 61', '2017-04-24 16:24:03', '18762001981968', 25, 207058, 35407, 221750, 4, '51744384-1', '1113667405', 5, '2017-05-24', '', '51744384-1_10'),
('FVH 62', '2017-04-25 09:45:16', '18762001981968', 39, 34000, 0, 34000, 2, '51744384-1', '14251125', 2, '2017-04-25', '', '51744384-1_10'),
('FVH 63', '2017-04-25 12:27:46', '18762001981968', 11, 24000, 0, 24000, 2, '51744384-1', '14251125', 2, '2017-04-25', 'PAGO 3 ALMUERZOS', '51744384-1_10'),
('FVH 64', '2017-04-25 18:04:14', '18762001981968', 52, 310587, 59012, 369550, 2, '51744384-1', '14251125', 2, '2017-04-25', 'ALOJAMIENTO  POR 3 NOCHES. HABITACION AMERICANA SENCILLA', '51744384-1_10'),
('FVH 65', '2017-04-26 10:33:31', '18762001981968', 53, 310587, 59012, 369550, 2, '51744384-1', '14251125', 2, '2017-04-26', 'ALOJAMIENTO DEL 25 AL 28 ABRIL-2017. HABITACION AMERICANA SENCILLA', '51744384-1_10'),
('FVH 66', '2017-04-27 08:01:50', '18762001981968', 54, 420168, 79832, 500000, 2, '51744384-1', '14251125', 2, '2017-04-27', '5 HABITACIONES TIPO CHALET CON DESCUENTO . CADA HABITACION CON UNVALOR DE $100.000 C/U', '51744384-1_10'),
('FVH 67', '2017-04-27 12:36:15', '18762001981968', 55, 509000, 0, 509000, 2, '51744384-1', '14251125', 2, '2017-04-27', '', '51744384-1_10'),
('FVH 68', '2017-04-27 14:04:50', '18762001981968', 39, 48000, 0, 48000, 2, '51744384-1', '14251125', 2, '2017-04-27', '', '51744384-1_10'),
('FVH 69', '2017-04-27 22:06:23', '18762001981968', 53, 16500, 0, 16500, 2, '51744384-1', '1113628111', 2, '2017-04-27', 'INGRESO RERCIBIDOS PARA TERCEROS PARA PAGAR DOMICILIO DE CLIENTE HB 204', '51744384-1_10'),
('FVH 7', '2017-03-30 14:30:14', '18762001981968', 8, 24000, 0, 24000, 5, '51744384-1', '14251125', 2, '2017-03-30', '', '51744384-1_7'),
('FVH 70', '2017-04-27 22:26:15', '18762001981968', 57, 137143, 26057, 163200, 2, '51744384-1', '1113628111', 2, '2017-04-27', 'PAGO DE HABITACION 102 Y UN ADICIONAL', '51744384-1_10'),
('FVH 71', '2017-04-27 22:46:09', '18762001981968', 58, 112605, 21395, 134000, 2, '51744384-1', '1113628111', 2, '2017-04-27', 'PAGO DE HAB 120 CON DESPUENTO DE 10%', '51744384-1_10'),
('FVH 72', '2017-04-28 06:56:48', '18762001981968', 23, 44500, 0, 44500, 2, '51744384-1', '1113628111', 2, '2017-04-28', '', '51744384-1_10'),
('FVH 73', '2017-04-29 13:32:55', '18762001981968', 59, 39000, 0, 39000, 2, '51744384-1', '14251125', 2, '2017-04-29', 'ALMUERZOS DIA 28 Y 29 ABRIL-2017', '51744384-1_10'),
('FVH 74', '2017-04-29 13:45:49', '18762001981968', 60, 42000, 0, 42000, 2, '51744384-1', '14251125', 2, '2017-04-29', '', '51744384-1_10'),
('FVH 75', '2017-04-29 21:58:07', '18762001981968', 61, 103529, 19671, 123200, 2, '51744384-1', '1113628111', 2, '2017-04-29', '', '51744384-1_10'),
('FVH 76', '2017-04-30 01:02:27', '18762001981968', 62, 103529, 19671, 123200, 2, '51744384-1', '1113628111', 2, '2017-04-30', 'PAGO DE HABITACION 106 POR 1 NOCHE ', '51744384-1_10'),
('FVH 77', '2017-05-01 16:21:32', '18762001981968', 63, 83000, 0, 83000, 2, '51744384-1', '1113667405', 2, '2017-05-01', '', '51744384-1_10'),
('FVH 78', '2017-05-01 16:41:59', '18762001981968', 64, 74500, 0, 74500, 2, '51744384-1', '1113667405', 2, '2017-05-01', 'SE CANCELARON $40.000 PESOS EN EFECTIVO', '51744384-1_10'),
('FVH 79', '2017-05-02 08:44:54', '18762001981968', 25, 93176, 17703, 110850, 5, '51744384-1', '1113628111', 5, '2017-06-01', 'HAB. 120 A NOMBRE DE FERNADO OSPINA CON INGRESO EL DIA 25/04/2017 Y SALIDAEL 26/04/2017', '51744384-1_10'),
('FVH 8', '2017-03-30 14:33:26', '18762001981968', 8, 16000, 0, 16000, 2, '51744384-1', '14251125', 2, '2017-03-30', '', '51744384-1_8'),
('FVH 80', '2017-05-02 08:54:44', '18762001981968', 25, 93176, 17703, 110850, 5, '51744384-1', '1113628111', 5, '2017-06-01', 'HAB. 112 SERGIO ANDRES GOMEZ CON INGRESO 24/04/2017Y SALIDA EL 27/04/2017', '51744384-1_10'),
('FVH 81', '2017-05-02 10:23:19', '18762001981968', 13, 93176, 17703, 110850, 4, '51744384-1', '1113628111', 5, '2017-06-01', 'HAB 120 A NOMBRE DE FERNADO OSPINA  CON INGRESO EL 25/04/2017 Y SALIDA EL 26/04/2017', '51744384-1_10'),
('FVH 82', '2017-05-02 13:29:03', '18762001981968', 60, 47000, 0, 47000, 2, '51744384-1', '1113628111', 2, '2017-05-02', '', '51744384-1_10'),
('FVH 83', '2017-05-02 13:47:10', '18762001981968', 23, 16000, 0, 16000, 2, '51744384-1', '1113628111', 2, '2017-05-02', '', '51744384-1_10'),
('FVH 84', '2017-05-02 14:05:01', '18762001981968', 59, 48000, 0, 48000, 2, '51744384-1', '1113628111', 2, '2017-05-02', '', '51744384-1_10'),
('FVH 85', '2017-05-03 07:13:46', '18762001981968', 65, 125043, 23758, 148800, 2, '51744384-1', '1113628111', 2, '2017-05-03', 'HAB 112 POR 1 NOCHE', '51744384-1_10'),
('FVH 86', '2017-05-03 07:48:55', '18762001981968', 25, 750258, 128294, 803500, 5, '51744384-1', '1113628111', 5, '2017-06-02', '', '51744384-1_10'),
('FVH 87', '2017-05-03 10:48:57', '18762001981968', 25, 750258, 128293, 803500, 4, '51744384-1', '1113628111', 5, '2017-06-02', 'INGRESO EL 26/04/17 Y SALEN EL 02/04/17 LOS SEÑORES JORGE  BOWIE Y JORGE BRITO', '51744384-1_10'),
('FVH 88', '2017-05-03 11:56:07', '18762001981968', 66, 22000, 0, 22000, 2, '51744384-1', '1113628111', 2, '2017-05-03', 'INGRESO RECIBIDOS PARA TERCEROS (RESTAURANTE)', '51744384-1_10'),
('FVH 89', '2017-05-03 13:29:45', '18762001981968', 8, 8000, 0, 8000, 2, '51744384-1', '1113628111', 2, '2017-05-03', 'INGRESORECIBIDOS PARA TERCEROS', '51744384-1_10'),
('FVH 9', '2017-03-30 17:57:45', '18762001981968', 9, 103529, 19671, 123200, 2, '51744384-1', '14251125', 2, '2017-03-30', '', '51744384-1_9'),
('FVH 90', '2017-05-03 16:36:07', '18762001981968', 58, 720064, 99184, 694200, 2, '51744384-1', '1113628111', 2, '2017-05-03', '', '51744384-1_10'),
('FVH 91', '2017-05-03 22:05:54', '18762001981968', 67, 137142, 26057, 163150, 2, '51744384-1', '14251125', 2, '2017-05-03', '', '51744384-1_10'),
('FVH 92', '2017-05-04 16:17:00', '18762001981968', 68, 137142, 26057, 163150, 2, '51744384-1', '1113628111', 2, '2017-05-04', '', '51744384-1_10'),
('FVH 93', '2017-05-05 00:33:39', '18762001981968', 53, 310587, 59012, 369550, 2, '51744384-1', '14251125', 2, '2017-05-05', '', '51744384-1_10'),
('FVH 94', '2017-05-05 00:39:58', '18762001981968', 53, 58000, 0, 58000, 2, '51744384-1', '14251125', 2, '2017-05-05', '', '51744384-1_10'),
('FVH 95', '2017-05-05 08:17:41', '18762001981968', 53, 310587, 59012, 369550, 2, '51744384-1', '1113628111', 2, '2017-05-05', 'HOSPEDAJE POR 3 NOCHES HABITACION 111', '51744384-1_10'),
('FVH 96', '2017-05-05 15:17:39', '18762001981968', 69, 125043, 23758, 148800, 2, '51744384-1', '1113628111', 2, '2017-05-05', '', '51744384-1_10'),
('FVH 97', '2017-05-06 15:21:33', '18762001981968', 69, 32500, 0, 32500, 2, '51744384-1', '1113628111', 2, '2017-05-06', 'INGRESO RECIBIDO PARA TERCEROS RESTAURANTE', '51744384-1_10'),
('FVH 98', '2017-05-08 17:48:53', '18762001981968', 70, 125043, 23758, 148800, 2, '51744384-1', '1113667405', 2, '2017-05-08', '', '51744384-1_10'),
('FVH 99', '2017-05-08 18:43:34', '18762001981968', 71, 125043, 23758, 148800, 2, '51744384-1', '1113667405', 2, '2017-05-08', '', '51744384-1_10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas_anuladas`
--

CREATE TABLE `facturas_anuladas` (
  `id_factura_anulada` int(11) NOT NULL,
  `id_factura` varchar(20) NOT NULL,
  `concepto` varchar(200) NOT NULL,
  `id_usuario` varchar(15) NOT NULL,
  `fecha` datetime NOT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `facturas_anuladas`
--

INSERT INTO `facturas_anuladas` (`id_factura_anulada`, `id_factura`, `concepto`, `id_usuario`, `fecha`, `transacion`) VALUES
(1, 'FVH 7', 'doble facturación', '31179383', '2017-03-30 15:31:02', '51744384-1_1'),
(2, 'FVH 5', 'POR CAMBIO DE RAZON SOCIAL', '31179383', '2017-03-31 16:20:18', '51744384-1_2'),
(3, 'FVH 79', 'error de nombre', '31179383', '2017-05-02 10:11:38', '51744384-1_3'),
(4, 'FVH 80', 'ANULAR', '31179383', '2017-05-16 16:33:42', '51744384-1_4'),
(5, 'FVH 86', 'ANULADA', '31179383', '2017-05-16 16:35:52', '51744384-1_5'),
(6, 'FVH 104', 'ANULADO', '31179383', '2017-05-16 16:41:57', '51744384-1_6'),
(7, 'FVH 137', 'El cliente exigio una factura por todas las noches de hospedaje, en vista de que estaban por noche se hizo una nueva. Factura de reemplazo  N. FVH 154', '31179383', '2017-05-22 16:29:46', '51744384-1_7'),
(8, 'FVH 137', 'SE  ANULA POR SOLICITUD DEL CLIENTE EN QUE SE GENERE UNA FACTURA GLOBAL DEL ALOJAMIENTO', '31179383', '2017-05-23 08:45:36', '51744384-1_8'),
(9, 'FVH 206', 'SE COLOCO MAL EL NIT', '31179383', '2017-08-05 11:20:49', '51744384-1_9'),
(10, 'FVH 216', 'EMPRESA ERRADA', '31179383', '2017-08-24 16:10:26', '51744384-1_10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas_proveedor`
--

CREATE TABLE `facturas_proveedor` (
  `id_factura_proveedor` int(15) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `observaciones` varchar(200) DEFAULT NULL,
  `numero_factura` varchar(60) NOT NULL,
  `fecha_factura` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `valor` int(15) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `franquicias`
--

CREATE TABLE `franquicias` (
  `id_franquicia` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `franquicias`
--

INSERT INTO `franquicias` (`id_franquicia`, `nombre`, `transacion`) VALUES
(1, 'American Express', NULL),
(2, 'Diners Club', NULL),
(3, 'Visa', NULL),
(4, 'MasterCard', NULL),
(5, 'Maestro', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitaciones`
--

CREATE TABLE `habitaciones` (
  `id_habitacion` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `cama_doble` int(2) NOT NULL,
  `cama_sencilla` int(2) NOT NULL,
  `total_camas` int(2) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL,
  `id_tipo_habitacion` int(11) NOT NULL,
  `disponible` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `habitaciones`
--

INSERT INTO `habitaciones` (`id_habitacion`, `nombre`, `cama_doble`, `cama_sencilla`, `total_camas`, `transacion`, `id_tipo_habitacion`, `disponible`) VALUES
(1, '101', 1, 2, 3, NULL, 1, 0),
(2, '102', 1, 1, 2, NULL, 1, 0),
(3, '103', 1, 1, 2, NULL, 1, 0),
(4, '104', 1, 2, 3, NULL, 1, 0),
(5, '105', 1, 1, 2, NULL, 1, 0),
(6, '106', 1, 1, 2, NULL, 1, 0),
(7, '107', 1, 1, 2, NULL, 1, 0),
(8, '108', 1, 1, 2, NULL, 1, 0),
(9, '109', 1, 2, 3, NULL, 1, 0),
(10, '110', 1, 1, 2, NULL, 1, 0),
(11, '111', 1, 1, 2, NULL, 2, 0),
(12, '112', 1, 1, 2, NULL, 2, 0),
(13, '117', 1, 5, 6, NULL, 2, 0),
(14, '118', 1, 1, 2, NULL, 2, 0),
(15, '119', 1, 2, 3, NULL, 2, 0),
(16, '120', 1, 1, 2, NULL, 2, 0),
(17, '121', 1, 1, 2, NULL, 2, 0),
(18, '122', 0, 4, 4, NULL, 2, 0),
(19, '203', 1, 5, 6, NULL, 2, 0),
(20, '204', 1, 1, 2, NULL, 2, 0),
(21, '205', 1, 1, 2, NULL, 2, 0),
(22, '206', 1, 1, 2, NULL, 2, 0),
(23, '207', 1, 1, 2, NULL, 2, 0),
(24, '208', 1, 4, 5, NULL, 2, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hospedajes`
--

CREATE TABLE `hospedajes` (
  `id_hospedaje` int(11) NOT NULL,
  `id_motivo` int(11) NOT NULL,
  `fecha_ingreso` datetime NOT NULL,
  `fecha_salida` datetime DEFAULT NULL,
  `id_habitacion` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `numero_adultos` int(11) NOT NULL,
  `numero_ninos` int(11) NOT NULL,
  `fecha_registro` datetime NOT NULL,
  `id_factura` varchar(15) DEFAULT NULL,
  `recepcionista` varchar(15) NOT NULL,
  `valor_noche` float NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `hospedajes`
--

INSERT INTO `hospedajes` (`id_hospedaje`, `id_motivo`, `fecha_ingreso`, `fecha_salida`, `id_habitacion`, `id_cliente`, `numero_adultos`, `numero_ninos`, `fecha_registro`, `id_factura`, `recepcionista`, `valor_noche`, `transacion`) VALUES
(1, 1, '2017-05-03 15:00:00', '2017-05-09 15:00:00', 22, 25, 2, 0, '2017-05-03 07:32:39', 'FVH 86', '1113628111', 125043, '51744384-1_1'),
(2, 1, '2017-05-04 15:00:00', '2017-05-05 15:00:00', 7, 68, 3, 0, '2017-05-04 16:13:53', 'FVH 92', '1113628111', 137142, '51744384-1_2'),
(3, 1, '2017-05-05 15:00:00', '2017-05-06 15:00:00', 23, 69, 2, 0, '2017-05-05 15:15:48', 'FVH 96', '1113628111', 125043, '51744384-1_3');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `impuestos`
--

CREATE TABLE `impuestos` (
  `id_impuesto` int(2) NOT NULL,
  `porcentaje_impuesto` float NOT NULL,
  `detalle` varchar(100) NOT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `impuestos`
--

INSERT INTO `impuestos` (`id_impuesto`, `porcentaje_impuesto`, `detalle`, `transacion`) VALUES
(1, 0, 'Ninguno - (0%)', ''),
(2, 19, 'IVA - (19%)', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ip_publicas`
--

CREATE TABLE `ip_publicas` (
  `id_ip` int(11) NOT NULL,
  `id_empresa` varchar(15) NOT NULL,
  `ip_publica` varchar(15) NOT NULL,
  `transacion` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `licencias`
--

CREATE TABLE `licencias` (
  `id_licencia` varchar(100) NOT NULL,
  `fecha` date NOT NULL,
  `transacion` varchar(30) NOT NULL,
  `id_suscripcion` int(11) NOT NULL,
  `estaciones` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `logs`
--

CREATE TABLE `logs` (
  `id_log` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `id_usuario` varchar(15) NOT NULL,
  `actividad` text NOT NULL,
  `id_empresa` varchar(15) NOT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modulos`
--

CREATE TABLE `modulos` (
  `id_modulo` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modulos_licencias`
--

CREATE TABLE `modulos_licencias` (
  `id` int(11) NOT NULL,
  `id_modulo` int(11) NOT NULL,
  `id_licencia` varchar(100) NOT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `motivos_estadia`
--

CREATE TABLE `motivos_estadia` (
  `id_motivo` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `motivos_estadia`
--

INSERT INTO `motivos_estadia` (`id_motivo`, `nombre`, `transacion`) VALUES
(1, 'NEGOCIOS', NULL),
(2, 'TURISMO', NULL),
(3, 'CONVENCIÓN', NULL),
(4, 'ESTUDIO', NULL),
(5, 'AMERCOS(VUELOS)', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nacionalidades`
--

CREATE TABLE `nacionalidades` (
  `id_nacionalidad` int(11) NOT NULL,
  `codigo` varchar(2) NOT NULL,
  `pais` varchar(100) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `nacionalidades`
--

INSERT INTO `nacionalidades` (`id_nacionalidad`, `codigo`, `pais`, `transacion`) VALUES
(1, 'AF', 'Afganistán', NULL),
(2, 'AX', 'Islas Gland', NULL),
(3, 'AL', 'Albania', NULL),
(4, 'DE', 'Alemania', NULL),
(5, 'AD', 'Andorra', NULL),
(6, 'AO', 'Angola', NULL),
(7, 'AI', 'Anguilla', NULL),
(8, 'AQ', 'Antártida', NULL),
(9, 'AG', 'Antigua y Barbuda', NULL),
(10, 'AN', 'Antillas Holandesas', NULL),
(11, 'SA', 'Arabia Saudí', NULL),
(12, 'DZ', 'Argelia', NULL),
(13, 'AR', 'Argentina', NULL),
(14, 'AM', 'Armenia', NULL),
(15, 'AW', 'Aruba', NULL),
(16, 'AU', 'Australia', NULL),
(17, 'AT', 'Austria', NULL),
(18, 'AZ', 'Azerbaiyán', NULL),
(19, 'BS', 'Bahamas', NULL),
(20, 'BH', 'Bahréin', NULL),
(21, 'BD', 'Bangladesh', NULL),
(22, 'BB', 'Barbados', NULL),
(23, 'BY', 'Bielorrusia', NULL),
(24, 'BE', 'Bélgica', NULL),
(25, 'BZ', 'Belice', NULL),
(26, 'BJ', 'Benin', NULL),
(27, 'BM', 'Bermudas', NULL),
(28, 'BT', 'Bhután', NULL),
(29, 'BO', 'Bolivia', NULL),
(30, 'BA', 'Bosnia y Herzegovina', NULL),
(31, 'BW', 'Botsuana', NULL),
(32, 'BV', 'Isla Bouvet', NULL),
(33, 'BR', 'Brasil', NULL),
(34, 'BN', 'Brunéi', NULL),
(35, 'BG', 'Bulgaria', NULL),
(36, 'BF', 'Burkina Faso', NULL),
(37, 'BI', 'Burundi', NULL),
(38, 'CV', 'Cabo Verde', NULL),
(39, 'KY', 'Islas Caimán', NULL),
(40, 'KH', 'Camboya', NULL),
(41, 'CM', 'Camerún', NULL),
(42, 'CA', 'Canadá', NULL),
(43, 'CF', 'República Centroafricana', NULL),
(44, 'TD', 'Chad', NULL),
(45, 'CZ', 'República Checa', NULL),
(46, 'CL', 'Chile', NULL),
(47, 'CN', 'China', NULL),
(48, 'CY', 'Chipre', NULL),
(49, 'CX', 'Isla de Navidad', NULL),
(50, 'VA', 'Ciudad del Vaticano', NULL),
(51, 'CC', 'Islas Cocos', NULL),
(52, 'CO', 'Colombia', NULL),
(53, 'KM', 'Comoras', NULL),
(54, 'CD', 'República Democrática del Congo', NULL),
(55, 'CG', 'Congo', NULL),
(56, 'CK', 'Islas Cook', NULL),
(57, 'KP', 'Corea del Norte', NULL),
(58, 'KR', 'Corea del Sur', NULL),
(59, 'CI', 'Costa de Marfil', NULL),
(60, 'CR', 'Costa Rica', NULL),
(61, 'HR', 'Croacia', NULL),
(62, 'CU', 'Cuba', NULL),
(63, 'DK', 'Dinamarca', NULL),
(64, 'DM', 'Dominica', NULL),
(65, 'DO', 'República Dominicana', NULL),
(66, 'EC', 'Ecuador', NULL),
(67, 'EG', 'Egipto', NULL),
(68, 'SV', 'El Salvador', NULL),
(69, 'AE', 'Emiratos Árabes Unidos', NULL),
(70, 'ER', 'Eritrea', NULL),
(71, 'SK', 'Eslovaquia', NULL),
(72, 'SI', 'Eslovenia', NULL),
(73, 'ES', 'España', NULL),
(74, 'UM', 'Islas ultramarinas de Estados Unidos', NULL),
(75, 'US', 'Estados Unidos', NULL),
(76, 'EE', 'Estonia', NULL),
(77, 'ET', 'Etiopía', NULL),
(78, 'FO', 'Islas Feroe', NULL),
(79, 'PH', 'Filipinas', NULL),
(80, 'FI', 'Finlandia', NULL),
(81, 'FJ', 'Fiyi', NULL),
(82, 'FR', 'Francia', NULL),
(83, 'GA', 'Gabón', NULL),
(84, 'GM', 'Gambia', NULL),
(85, 'GE', 'Georgia', NULL),
(86, 'GS', 'Islas Georgias del Sur y Sandwich del Sur', NULL),
(87, 'GH', 'Ghana', NULL),
(88, 'GI', 'Gibraltar', NULL),
(89, 'GD', 'Granada', NULL),
(90, 'GR', 'Grecia', NULL),
(91, 'GL', 'Groenlandia', NULL),
(92, 'GP', 'Guadalupe', NULL),
(93, 'GU', 'Guam', NULL),
(94, 'GT', 'Guatemala', NULL),
(95, 'GF', 'Guayana Francesa', NULL),
(96, 'GN', 'Guinea', NULL),
(97, 'GQ', 'Guinea Ecuatorial', NULL),
(98, 'GW', 'Guinea-Bissau', NULL),
(99, 'GY', 'Guyana', NULL),
(100, 'HT', 'Haití', NULL),
(101, 'HM', 'Islas Heard y McDonald', NULL),
(102, 'HN', 'Honduras', NULL),
(103, 'HK', 'Hong Kong', NULL),
(104, 'HU', 'Hungría', NULL),
(105, 'IN', 'India', NULL),
(106, 'ID', 'Indonesia', NULL),
(107, 'IR', 'Irán', NULL),
(108, 'IQ', 'Iraq', NULL),
(109, 'IE', 'Irlanda', NULL),
(110, 'IS', 'Islandia', NULL),
(111, 'IL', 'Israel', NULL),
(112, 'IT', 'Italia', NULL),
(113, 'JM', 'Jamaica', NULL),
(114, 'JP', 'Japón', NULL),
(115, 'JO', 'Jordania', NULL),
(116, 'KZ', 'Kazajstán', NULL),
(117, 'KE', 'Kenia', NULL),
(118, 'KG', 'Kirguistán', NULL),
(119, 'KI', 'Kiribati', NULL),
(120, 'KW', 'Kuwait', NULL),
(121, 'LA', 'Laos', NULL),
(122, 'LS', 'Lesotho', NULL),
(123, 'LV', 'Letonia', NULL),
(124, 'LB', 'Líbano', NULL),
(125, 'LR', 'Liberia', NULL),
(126, 'LY', 'Libia', NULL),
(127, 'LI', 'Liechtenstein', NULL),
(128, 'LT', 'Lituania', NULL),
(129, 'LU', 'Luxemburgo', NULL),
(130, 'MO', 'Macao', NULL),
(131, 'MK', 'ARY Macedonia', NULL),
(132, 'MG', 'Madagascar', NULL),
(133, 'MY', 'Malasia', NULL),
(134, 'MW', 'Malawi', NULL),
(135, 'MV', 'Maldivas', NULL),
(136, 'ML', 'Malí', NULL),
(137, 'MT', 'Malta', NULL),
(138, 'FK', 'Islas Malvinas', NULL),
(139, 'MP', 'Islas Marianas del Norte', NULL),
(140, 'MA', 'Marruecos', NULL),
(141, 'MH', 'Islas Marshall', NULL),
(142, 'MQ', 'Martinica', NULL),
(143, 'MU', 'Mauricio', NULL),
(144, 'MR', 'Mauritania', NULL),
(145, 'YT', 'Mayotte', NULL),
(146, 'MX', 'México', NULL),
(147, 'FM', 'Micronesia', NULL),
(148, 'MD', 'Moldavia', NULL),
(149, 'MC', 'Mónaco', NULL),
(150, 'MN', 'Mongolia', NULL),
(151, 'MS', 'Montserrat', NULL),
(152, 'MZ', 'Mozambique', NULL),
(153, 'MM', 'Myanmar', NULL),
(154, 'NA', 'Namibia', NULL),
(155, 'NR', 'Nauru', NULL),
(156, 'NP', 'Nepal', NULL),
(157, 'NI', 'Nicaragua', NULL),
(158, 'NE', 'Níger', NULL),
(159, 'NG', 'Nigeria', NULL),
(160, 'NU', 'Niue', NULL),
(161, 'NF', 'Isla Norfolk', NULL),
(162, 'NO', 'Noruega', NULL),
(163, 'NC', 'Nueva Caledonia', NULL),
(164, 'NZ', 'Nueva Zelanda', NULL),
(165, 'OM', 'Omán', NULL),
(166, 'NL', 'Países Bajos', NULL),
(167, 'PK', 'Pakistán', NULL),
(168, 'PW', 'Palau', NULL),
(169, 'PS', 'Palestina', NULL),
(170, 'PA', 'Panamá', NULL),
(171, 'PG', 'Papúa Nueva Guinea', NULL),
(172, 'PY', 'Paraguay', NULL),
(173, 'PE', 'Perú', NULL),
(174, 'PN', 'Islas Pitcairn', NULL),
(175, 'PF', 'Polinesia Francesa', NULL),
(176, 'PL', 'Polonia', NULL),
(177, 'PT', 'Portugal', NULL),
(178, 'PR', 'Puerto Rico', NULL),
(179, 'QA', 'Qatar', NULL),
(180, 'GB', 'Reino Unido', NULL),
(181, 'RE', 'Reunión', NULL),
(182, 'RW', 'Ruanda', NULL),
(183, 'RO', 'Rumania', NULL),
(184, 'RU', 'Rusia', NULL),
(185, 'EH', 'Sahara Occidental', NULL),
(186, 'SB', 'Islas Salomón', NULL),
(187, 'WS', 'Samoa', NULL),
(188, 'AS', 'Samoa Americana', NULL),
(189, 'KN', 'San Cristóbal y Nevis', NULL),
(190, 'SM', 'San Marino', NULL),
(191, 'PM', 'San Pedro y Miquelón', NULL),
(192, 'VC', 'San Vicente y las Granadinas', NULL),
(193, 'SH', 'Santa Helena', NULL),
(194, 'LC', 'Santa Lucía', NULL),
(195, 'ST', 'Santo Tomé y Príncipe', NULL),
(196, 'SN', 'Senegal', NULL),
(197, 'CS', 'Serbia y Montenegro', NULL),
(198, 'SC', 'Seychelles', NULL),
(199, 'SL', 'Sierra Leona', NULL),
(200, 'SG', 'Singapur', NULL),
(201, 'SY', 'Siria', NULL),
(202, 'SO', 'Somalia', NULL),
(203, 'LK', 'Sri Lanka', NULL),
(204, 'SZ', 'Suazilandia', NULL),
(205, 'ZA', 'Sudáfrica', NULL),
(206, 'SD', 'Sudán', NULL),
(207, 'SE', 'Suecia', NULL),
(208, 'CH', 'Suiza', NULL),
(209, 'SR', 'Surinam', NULL),
(210, 'SJ', 'Svalbard y Jan Mayen', NULL),
(211, 'TH', 'Tailandia', NULL),
(212, 'TW', 'Taiwán', NULL),
(213, 'TZ', 'Tanzania', NULL),
(214, 'TJ', 'Tayikistán', NULL),
(215, 'IO', 'Territorio Británico del Océano Índico', NULL),
(216, 'TF', 'Territorios Australes Franceses', NULL),
(217, 'TL', 'Timor Oriental', NULL),
(218, 'TG', 'Togo', NULL),
(219, 'TK', 'Tokelau', NULL),
(220, 'TO', 'Tonga', NULL),
(221, 'TT', 'Trinidad y Tobago', NULL),
(222, 'TN', 'Túnez', NULL),
(223, 'TC', 'Islas Turcas y Caicos', NULL),
(224, 'TM', 'Turkmenistán', NULL),
(225, 'TR', 'Turquía', NULL),
(226, 'TV', 'Tuvalu', NULL),
(227, 'UA', 'Ucrania', NULL),
(228, 'UG', 'Uganda', NULL),
(229, 'UY', 'Uruguay', NULL),
(230, 'UZ', 'Uzbekistán', NULL),
(231, 'VU', 'Vanuatu', NULL),
(232, 'VE', 'Venezuela', NULL),
(233, 'VN', 'Vietnam', NULL),
(234, 'VG', 'Islas Vírgenes Británicas', NULL),
(235, 'VI', 'Islas Vírgenes de los Estados Unidos', NULL),
(236, 'WF', 'Wallis y Futuna', NULL),
(237, 'YE', 'Yemen', NULL),
(238, 'DJ', 'Yibuti', NULL),
(239, 'ZM', 'Zambia', NULL),
(240, 'ZW', 'Zimbabue', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nv1categorias`
--

CREATE TABLE `nv1categorias` (
  `id_nv1_categoria` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `puc` varchar(30) DEFAULT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `nv1categorias`
--

INSERT INTO `nv1categorias` (`id_nv1_categoria`, `nombre`, `descripcion`, `puc`, `transacion`) VALUES
(1, 'Activos', 'Bajo esta categoría se encuentra los activos que tiene la empresa', '1', NULL),
(2, 'Egresos', 'Bajo esta categoría se encuentra todos los tipos de egresos', NULL, NULL),
(3, 'Ingresos', 'Bajo esta categoría se encuentra todos los tipos de ingresos', '4', NULL),
(4, 'Pasivos', 'Bajo esta categoría se encuentra los pasivos de la empresa', '2', NULL),
(5, 'Patrimonio', 'Bajo esta categoría se encuentra el patrimonio de la empresa', '3', NULL),
(6, 'Transferencias bancarias', 'Bajo esta categoría se ubican todas las transferencias que se hagan entre bancos de la empresa', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nv2categorias`
--

CREATE TABLE `nv2categorias` (
  `id_nv2_categoria` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `id_nv1_categoria` int(11) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL,
  `puc` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `nv2categorias`
--

INSERT INTO `nv2categorias` (`id_nv2_categoria`, `nombre`, `descripcion`, `id_nv1_categoria`, `transacion`, `puc`) VALUES
(1, 'Activo Corriente', '', 1, NULL, NULL),
(2, 'Activos fijos', 'Bajo esta categoría se ubican los activos principales de la empresa.', 1, NULL, NULL),
(3, 'Avances y anticipos entregados', '', 1, NULL, NULL),
(4, 'Cuentas por cobrar - devoluciones', '', 1, NULL, NULL),
(5, 'Impuestos a favor', NULL, 1, NULL, NULL),
(6, 'Inversiones', NULL, 1, NULL, NULL),
(7, 'Préstamos a terceros', NULL, 1, NULL, NULL),
(8, 'Retenciones a favor', NULL, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `id_pago` int(15) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_cuenta` int(5) NOT NULL,
  `fecha` date NOT NULL,
  `observaciones` varchar(200) DEFAULT NULL,
  `nota_egreso` varchar(200) DEFAULT NULL,
  `id_tipo_pago` int(11) NOT NULL,
  `id_comprobante_egreso` int(11) DEFAULT NULL,
  `id_recibo_caja` int(11) DEFAULT NULL,
  `valor` float NOT NULL,
  `id_factura_proveedor` int(15) DEFAULT NULL,
  `id_factura` varchar(20) DEFAULT NULL,
  `cuatro_digitos` varchar(4) DEFAULT NULL,
  `voucher` varchar(15) DEFAULT NULL,
  `id_franquicia` int(11) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL,
  `recepcionista` varchar(15) NOT NULL,
  `id_hospedaje` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pagos`
--

INSERT INTO `pagos` (`id_pago`, `id_cliente`, `id_cuenta`, `fecha`, `observaciones`, `nota_egreso`, `id_tipo_pago`, `id_comprobante_egreso`, `id_recibo_caja`, `valor`, `id_factura_proveedor`, `id_factura`, `cuatro_digitos`, `voucher`, `id_franquicia`, `transacion`, `recepcionista`, `id_hospedaje`) VALUES
(1, 4, 2, '2017-03-25', 'FACTURA FVH3', NULL, 1, NULL, 1, 131000, NULL, 'FVH 3', '', '', NULL, '51744384-1_1', '1113628111', 0),
(2, 3, 2, '2017-03-25', 'INGRESO RECIBIDOS PARA TERCEROS ', NULL, 1, NULL, 2, 328000, NULL, 'FVH 2', '', '', NULL, '51744384-1_2', '1113628111', 0),
(3, 7, 1, '2017-03-29', 'ABONO A LA FACTURA DE VENTA FVH 4 EVENTO EQUIPO CONTINENTAL DE CICLISMO. VUELTA AL VALLE', NULL, 3, NULL, 3, 5500000, NULL, 'FVH 4', '', '', NULL, '51744384-1_3', '14251125', 0),
(4, 8, 2, '2017-03-30', ' 2 ALMUERZOS , COMANDA 2741', NULL, 1, NULL, 4, 17000, NULL, 'FVH 5', '', '', NULL, '51744384-1_4', '14251125', 0),
(5, 8, 2, '2017-03-30', 'Ingreso para terceros - Restaurante.', NULL, 1, NULL, 5, 17000, NULL, 'FVH 6', '', '', NULL, '51744384-1_5', '14251125', 0),
(6, 8, 2, '2017-03-30', 'COMANDA No2749', NULL, 1, NULL, 6, 16000, NULL, 'FVH 8', '', '', NULL, '51744384-1_6', '14251125', 0),
(7, 9, 2, '2017-03-30', 'FVH9', NULL, 1, NULL, 7, 123200, NULL, 'FVH 9', '', '', NULL, '51744384-1_7', '14251125', 0),
(8, 10, 1, '2017-03-30', 'PAGA HOSPEDAJE HABITACION 206 PAGA CON TARJETA VISA', NULL, 5, NULL, 8, 148800, NULL, 'FVH 10', '7349', '000786', 3, '51744384-1_8', '1113628111', 0),
(9, 11, 2, '2017-03-31', ' 2 almuerzos', NULL, 1, NULL, 9, 17000, NULL, 'FVH 11', '', '', NULL, '51744384-1_9', '14251125', 0),
(10, 8, 2, '2017-03-31', 'almuerzos 2', NULL, 1, NULL, 10, 17000, NULL, 'FVH 12', '', '', NULL, '51744384-1_10', '14251125', 0),
(11, 7, 1, '2017-03-31', '- RETE FUNTE $19.600\nTOTAL PAGADO 540.000', NULL, 5, NULL, 11, 560000, NULL, 'FVH 13', '1009', '001060', 4, '51744384-1_10', '1113628111', 0),
(12, 7, 1, '2017-03-31', 'EL VALOR TOTAL PAGO FUE DE 557.605 DESPUES DE RETE FUENTES Y RETE IVA', NULL, 5, NULL, 12, 647765, NULL, 'FVH 4', '1009', '001059', 4, '51744384-1_10', '1113628111', 0),
(13, 14, 1, '2017-04-04', 'INGRESO RECIBIDOS PARA TERCEROS', NULL, 5, NULL, 13, 40000, NULL, 'FVH 16', '5018', '001066', 3, '51744384-1_10', '1113628111', 0),
(14, 16, 1, '2017-04-05', 'HABITACION 112 X 1 NOCHE ', NULL, 5, NULL, 14, 148800, NULL, 'FVH 18', '4521', '001068', 5, '51744384-1_10', '1113628111', 0),
(15, 15, 1, '2017-04-05', 'PAGO DE HOSPEDAJE POR 3 NOCHES', NULL, 6, NULL, 15, 369550, NULL, 'FVH 19', '0483', '001067', 3, '51744384-1_10', '1113628111', 0),
(16, 18, 1, '2017-04-06', 'PAGO ALOJAMIENTO HAB 206 X 1 NOCHE PARA 3 PAX', NULL, 5, NULL, 16, 188000, NULL, 'FVH 20', '3343', '000798', 4, '51744384-1_10', '14251125', 0),
(17, 19, 2, '2017-04-06', 'PAGO ALOJAMIENTO POR 3 NOCHES HAB 111', NULL, 1, NULL, 17, 369550, NULL, 'FVH 21', '', '', NULL, '51744384-1_10', '14251125', 0),
(18, 20, 1, '2017-04-07', 'pago de habitacion por 2 noches habitacion 112', NULL, 6, NULL, 18, 297600, NULL, 'FVH 22', '2414', '001074', 3, '51744384-1_10', '1113628111', 0),
(19, 21, 1, '2017-04-07', 'PAGO DE HOSPEDAJE DE 16 DEPORTISTAS', NULL, 2, NULL, 19, 2640000, NULL, 'FVH 23', '', '', NULL, '51744384-1_10', '1113628111', 0),
(20, 21, 1, '2017-04-07', 'HOSPEDAJE 2 DEPORTISTAS', NULL, 2, NULL, 20, 330000, NULL, 'FVH 24', '', '', NULL, '51744384-1_10', '1113628111', 0),
(21, 22, 1, '2017-04-09', 'PAGO ALOJAMIENTO HAB 112 POR UNA NOCHE', NULL, 5, NULL, 21, 148800, NULL, 'FVH 25', '7310', '000801', 3, '51744384-1_10', '14251125', 0),
(22, 21, 2, '2017-04-09', 'INGRESO RECIBIDO PARA TECEROS (RESTAURANTE)', NULL, 1, NULL, 22, 1274000, NULL, 'FVH 26', '', '', NULL, '51744384-1_10', '1113628111', 0),
(23, 24, 1, '2017-04-12', 'RETEFUENTE 3.5% $377193', NULL, 3, NULL, 23, 7212810, NULL, 'FVH 28', '', '', NULL, '51744384-1_10', '14251125', 0),
(24, 26, 1, '2017-04-12', 'PAGO ALOJAMIENTO HAB 111   POR 2 NOCHES Y SERV DE RESTAURANTE', NULL, 5, NULL, 24, 580050, NULL, 'FVH 29', '7151', '000804', 4, '51744384-1_10', '14251125', 0),
(25, 27, 1, '2017-04-12', 'restaurante', NULL, 6, NULL, 25, 26000, NULL, 'FVH 31', '6053', '000805', 5, '51744384-1_10', '14251125', 0),
(26, 27, 1, '2017-04-12', 'cancelación hospedaje habitacion 107 - factura FVH 30', NULL, 6, NULL, 26, 123200, NULL, 'FVH 30', '6053', '00085', 5, '51744384-1_10', '14251125', 0),
(27, 28, 1, '2017-04-12', 'PAGO DE HABITACION 111', NULL, 6, NULL, 27, 228800, NULL, 'FVH 32', '4748', '001082', 4, '51744384-1_10', '1113628111', 0),
(28, 29, 1, '2017-04-13', 'ABONO ALOJAMINEOT HAB 107 POR 2 NOCHES FVH 33', NULL, 3, NULL, 28, 140000, NULL, 'FVH 33', '', '', NULL, '51744384-1_10', '14251125', 0),
(29, 29, 1, '2017-04-13', 'ABONO ALOJAMIENOT HAB 107 FVH 33', NULL, 6, NULL, 29, 106350, NULL, 'FVH 33', '4033', '000807', 5, '51744384-1_10', '14251125', 0),
(30, 30, 1, '2017-04-13', 'INGRESO RECIBIDOS PARA TERCEROS 20.000 Y PAGO DE HABIRTACION 106  $123.200', NULL, 5, NULL, 30, 143200, NULL, 'FVH 34', '4940', '001085', 4, '51744384-1_10', '1113628111', 0),
(31, 31, 1, '2017-04-14', 'serv de restaurante', NULL, 5, NULL, 31, 75000, NULL, 'FVH 35', '4441', '000808', 4, '51744384-1_10', '14251125', 0),
(32, 33, 1, '2017-04-14', 'pago aloja hab 120 y serv restaurante', NULL, 5, NULL, 32, 243800, NULL, 'FVH 37', '2137', '000810', 4, '51744384-1_10', '14251125', 0),
(33, 34, 1, '2017-04-14', 'PAGO DE HABICION ', NULL, 5, NULL, 33, 298000, NULL, 'FVH 38', '0899', '00812', 5, '51744384-1_10', '1113628111', 0),
(34, 35, 1, '2017-04-15', 'PAGO ALOJ HAB 104-109', NULL, 5, NULL, 34, 148800, NULL, 'FVH 39', '2269', '000811', 4, '51744384-1_10', '14251125', 0),
(35, 35, 2, '2017-04-15', 'PAGO ALOJ HAB 104-109', NULL, 1, NULL, 35, 137200, NULL, 'FVH 39', '', '', NULL, '51744384-1_10', '14251125', 0),
(36, 30, 1, '2017-04-15', 'PAGO ALOJAMIENTO  HAB 106 Y SERV RESTAURANTE', NULL, 6, NULL, 36, 194700, NULL, 'FVH 40', '4940', '00813', 5, '51744384-1_10', '14251125', 0),
(37, 36, 1, '2017-04-15', 'PAGO ALOJAMIENTO HAB 102  NOCHE DEL 14-04-17', NULL, 6, NULL, 37, 168000, NULL, 'FVH 41', '0928', '000815', 3, '51744384-1_10', '14251125', 0),
(38, 29, 1, '2017-04-15', 'SERV RESTAURANTE HAB  107', NULL, 6, NULL, 38, 17000, NULL, 'FVH 42', '4033', '000816', 5, '51744384-1_10', '14251125', 0),
(39, 37, 1, '2017-04-15', 'PAGA CON TARJETA V.LECETRON 2 HABITACION 106/ 107', NULL, 6, NULL, 39, 246400, NULL, 'FVH 43', '2167', '000817', 3, '51744384-1_10', '1113628111', 0),
(40, 38, 1, '2017-04-16', 'pago aloja hab 108', NULL, 6, NULL, 40, 135000, NULL, 'FVH 44', '7055', '000818', 3, '51744384-1_10', '14251125', 0),
(41, 39, 2, '2017-04-18', 'INGRESO RECIBIODO PARA TERCEROS ', NULL, 1, NULL, 41, 8000, NULL, 'FVH 45', '', '', NULL, '51744384-1_10', '1113628111', 0),
(42, 40, 2, '2017-04-19', 'PAGO DE UNA NOCHE', NULL, 1, NULL, 42, 123200, NULL, 'FVH 46', '', '', NULL, '51744384-1_10', '1113628111', 0),
(43, 41, 1, '2017-04-19', 'PAGO ALOJAMIENTO HAB 111', NULL, 5, NULL, 43, 123200, NULL, 'FVH 47', '2584', '000825', 4, '51744384-1_10', '14251125', 0),
(44, 42, 2, '2017-04-19', 'PAGO ALOJAMIENTO HAB 112', NULL, 1, NULL, 44, 123200, NULL, 'FVH 48', '', '', NULL, '51744384-1_10', '14251125', 0),
(45, 43, 1, '2017-04-19', 'pago laoja hab 206', NULL, 5, NULL, 45, 123200, NULL, 'FVH 49', '2107', '000824', 3, '51744384-1_10', '14251125', 0),
(46, 44, 1, '2017-04-20', 'PAGO ALOJAMIENTO HAB 106', NULL, 5, NULL, 46, 123200, NULL, 'FVH 50', '7518', '000827', 3, '51744384-1_10', '14251125', 0),
(47, 45, 1, '2017-04-20', 'pago aloja hab 108', NULL, 6, NULL, 47, 123200, NULL, 'FVH 51', '0843', '000829', 3, '51744384-1_10', '14251125', 0),
(48, 43, 1, '2017-04-20', 'pago aloja hab 206', NULL, 5, NULL, 48, 123200, NULL, 'FVH 52', '2107', '000828', 3, '51744384-1_10', '14251125', 0),
(49, 11, 2, '2017-04-21', 'INGRESO PARA TERCEROS', NULL, 1, NULL, 49, 8500, NULL, 'FVH 53', '', '', NULL, '51744384-1_10', '1113628111', 0),
(50, 44, 2, '2017-04-21', 'INGRESO RECIBIDO PARA TERCEROS', NULL, 1, NULL, 50, 8000, NULL, 'FVH 54', '', '', NULL, '51744384-1_10', '1113628111', 0),
(51, 46, 1, '2017-04-22', 'PAGO', NULL, 6, NULL, 51, 123200, NULL, 'FVH 55', '3979', '000834', 5, '51744384-1_10', '1113628111', 0),
(52, 47, 1, '2017-04-22', 'pago aloja hab 102', NULL, 6, NULL, 52, 123200, NULL, 'FVH 56', '6700', '000837', 3, '51744384-1_10', '14251125', 0),
(53, 48, 1, '2017-04-23', 'PAGO ALOJ. HAB 104', NULL, 6, NULL, 53, 163150, NULL, 'FVH 57', '3882', '000836', 3, '51744384-1_10', '14251125', 0),
(54, 49, 1, '2017-04-23', 'PAGO INFESO TERCEROS', NULL, 5, NULL, 54, 102000, NULL, 'FVH 58', '7006', '00841', 4, '51744384-1_10', '1113628111', 0),
(55, 50, 1, '2017-04-23', 'INGRESO PARA TERCEROS', NULL, 5, NULL, 55, 75000, NULL, 'FVH 59', '8448', '000840', 5, '51744384-1_10', '1113628111', 0),
(56, 51, 1, '2017-04-24', 'ingresos recibidos para terceros (restaurante)', NULL, 5, NULL, 56, 41000, NULL, 'FVH 60', '0853', '000844', 3, '51744384-1_10', '1113667405', 0),
(57, 39, 2, '2017-04-25', 'pago serv. restaurante', NULL, 1, NULL, 57, 34000, NULL, 'FVH 62', '', '', NULL, '51744384-1_10', '14251125', 0),
(58, 11, 2, '2017-04-25', 'PAGO 3 ALMUERZOS', NULL, 1, NULL, 58, 24000, NULL, 'FVH 63', '', '', NULL, '51744384-1_10', '14251125', 0),
(59, 52, 1, '2017-04-25', 'PAGO ALOJAMIENTO HAB 205 POR 3 NOCHES', NULL, 6, NULL, 59, 369550, NULL, 'FVH 64', '5465', '000846', 3, '51744384-1_10', '14251125', 0),
(60, 53, 1, '2017-04-26', 'PAGO ALOJAMIENTO HAB 204 POR 3 NOCHES', NULL, 6, NULL, 60, 369550, NULL, 'FVH 65', '5039', '000847', 3, '51744384-1_10', '14251125', 0),
(61, 54, 2, '2017-04-27', 'PAGO 5 HABITACIONES CHALET', NULL, 1, NULL, 61, 500000, NULL, 'FVH 66', '', '', NULL, '51744384-1_10', '14251125', 0),
(62, 55, 1, '2017-04-27', 'pago serv. resturante', NULL, 5, NULL, 62, 509000, NULL, 'FVH 67', '1364', '000848', 3, '51744384-1_10', '14251125', 0),
(63, 39, 2, '2017-04-27', 'CANCELACION RESTAURANTE', NULL, 1, NULL, 63, 48000, NULL, 'FVH 68', '', '', NULL, '51744384-1_10', '14251125', 0),
(64, 53, 1, '2017-04-27', 'PARA PAGAR DOMICILIO DE CLINTE HB 205', NULL, 6, NULL, 64, 16500, NULL, 'FVH 69', '5039', '000850', 3, '51744384-1_10', '1113628111', 0),
(65, 57, 1, '2017-04-27', 'PAGO DE HAB 102', NULL, 5, NULL, 65, 163200, NULL, 'FVH 70', '7689', '000849', 2, '51744384-1_10', '1113628111', 0),
(66, 58, 2, '2017-04-27', 'HB 120', NULL, 1, NULL, 66, 134000, NULL, 'FVH 71', '', '', NULL, '51744384-1_10', '1113628111', 0),
(67, 23, 1, '2017-04-28', 'PAGO SERV RESTAURANTE', NULL, 6, NULL, 67, 44500, NULL, 'FVH 72', '5465', '000851', 3, '51744384-1_10', '1113628111', 0),
(68, 59, 2, '2017-04-29', 'PAGO SERV RESTAURANTE', NULL, 1, NULL, 68, 39000, NULL, 'FVH 73', '', '', NULL, '51744384-1_10', '14251125', 0),
(69, 60, 2, '2017-04-29', 'pago ser. restaurante', NULL, 1, NULL, 69, 42000, NULL, 'FVH 74', '', '', NULL, '51744384-1_10', '14251125', 0),
(70, 61, 1, '2017-04-29', 'HB 102', NULL, 6, NULL, 70, 123200, NULL, 'FVH 75', '5240', '000852', 5, '51744384-1_10', '1113628111', 0),
(71, 62, 1, '2017-04-30', 'HAB  106', NULL, 6, NULL, 71, 123200, NULL, 'FVH 76', '9363', '000853', 3, '51744384-1_10', '1113628111', 0),
(72, 63, 1, '2017-05-01', 'CANCELADO CON TARJETA', NULL, 5, NULL, 72, 83000, NULL, 'FVH 77', '8583', '000854', 4, '51744384-1_10', '1113667405', 0),
(73, 64, 1, '2017-05-01', 'SE CANCELARON $40.000 PESOS EN EFECTIVO ', NULL, 5, NULL, 73, 74500, NULL, 'FVH 78', '9976', '000855', 4, '51744384-1_10', '1113667405', 0),
(74, 60, 2, '2017-05-02', 'RESTAURANTE', NULL, 1, NULL, 74, 47000, NULL, 'FVH 82', '', '', NULL, '51744384-1_10', '1113628111', 0),
(75, 23, 2, '2017-05-02', 'RESTAURANTE', NULL, 1, NULL, 75, 16000, NULL, 'FVH 83', '', '', NULL, '51744384-1_10', '1113628111', 0),
(76, 59, 2, '2017-05-02', 'RESTAURANT', NULL, 1, NULL, 76, 48000, NULL, 'FVH 84', '', '', NULL, '51744384-1_10', '1113628111', 0),
(77, 65, 1, '2017-05-03', 'PAGO DE HB 112', NULL, 6, NULL, 77, 148800, NULL, 'FVH 85', '1051', '000856', 3, '51744384-1_10', '1113628111', 0),
(78, 66, 1, '2017-05-03', 'INGRESO PARA TERCEROS', NULL, 5, NULL, 78, 22000, NULL, 'FVH 88', '2194', '000857', 4, '51744384-1_10', '1113628111', 0),
(79, 8, 2, '2017-05-03', 'INGRESO RECIBIDOS PARA TERCEROS ', NULL, 1, NULL, 79, 8000, NULL, 'FVH 89', '', '', NULL, '51744384-1_10', '1113628111', 0),
(80, 58, 2, '2017-05-03', 'HABITACION RESTAURANTE, LAVANDERIA', NULL, 1, NULL, 80, 694200, NULL, 'FVH 90', '', '', NULL, '51744384-1_10', '1113628111', 0),
(81, 67, 1, '2017-05-03', 'pago aloja hab 102', NULL, 6, NULL, 81, 163150, NULL, 'FVH 91', '3140', '000858', 5, '51744384-1_10', '14251125', 0),
(82, 68, 1, '2017-05-04', 'PAGO DE 1 NOCHE', NULL, 6, NULL, 82, 163150, NULL, 'FVH 92', '3831', '000859', 5, '51744384-1_10', '1113628111', 0),
(83, 53, 1, '2017-05-05', 'INGRESO PARA TERCEROS', NULL, 6, NULL, 83, 58000, NULL, 'FVH 94', '4848', '000860', 3, '51744384-1_10', '1113628111', 0),
(84, 53, 1, '2017-05-05', 'HOSPEDAJE 3 NOCHES', NULL, 6, NULL, 84, 369550, NULL, 'FVH 93', '4848', '000860', 3, '51744384-1_10', '1113628111', 0),
(85, 69, 1, '2017-05-05', 'POGO HAB 207', NULL, 5, NULL, 85, 148800, NULL, 'FVH 96', '5931', '000861', 3, '51744384-1_10', '1113628111', 0),
(86, 53, 2, '2017-05-05', 'PAGO HABI ', NULL, 1, NULL, 86, 369550, NULL, 'FVH 95', '', '', NULL, '51744384-1_10', '1113628111', 0),
(87, 69, 1, '2017-05-06', 'RESTAURANTE', NULL, 5, NULL, 87, 32500, NULL, 'FVH 97', '5931', '000862', 3, '51744384-1_10', '1113628111', 0),
(88, 70, 1, '2017-05-08', 'Depronto se queda mas noches ', NULL, 5, NULL, 88, 148800, NULL, 'FVH 98', '0017', '000864', 3, '51744384-1_10', '1113667405', 0),
(89, 71, 1, '2017-05-08', 'NO HAY OBSERVACIONES ', NULL, 6, NULL, 89, 148800, NULL, 'FVH 99', '8869', '000865', 5, '51744384-1_10', '1113667405', 0),
(90, 53, 1, '2017-05-09', 'PAGO ALOJ HAB 207 POR 1 NOCHE', NULL, 6, NULL, 90, 123200, NULL, 'FVH 101', '4848', '000866', 3, '51744384-1_10', '14251125', 0),
(91, 53, 1, '2017-05-09', 'PAGO SERV RESTAURANTE', NULL, 6, NULL, 91, 29000, NULL, 'FVH 102', '4848', '000866', 3, '51744384-1_10', '14251125', 0),
(92, 72, 1, '2017-05-09', 'V.ELECTRON', NULL, 6, NULL, 92, 102000, NULL, 'FVH 103', '1570', '000869', 3, '51744384-1_10', '1113628111', 0),
(93, 68, 1, '2017-05-09', 'HB107', NULL, 6, NULL, 93, 102000, NULL, 'FVH 104', '3013', '000868', 4, '51744384-1_10', '1113628111', 0),
(94, 72, 2, '2017-05-10', 'hab', NULL, 1, NULL, 94, 111000, NULL, 'FVH 107', '', '', NULL, '51744384-1_10', '1113628111', 0),
(95, 72, 2, '2017-05-10', 'hab', NULL, 1, NULL, 95, 111000, NULL, 'FVH 106', '', '', NULL, '51744384-1_10', '1113628111', 0),
(96, 72, 2, '2017-05-10', 'hab', NULL, 1, NULL, 96, 111000, NULL, 'FVH 105', '', '', NULL, '51744384-1_10', '1113628111', 0),
(97, 72, 2, '2017-05-10', 'HAB', NULL, 1, NULL, 97, 111000, NULL, 'FVH 108', '', '', NULL, '51744384-1_10', '1113628111', 0),
(98, 72, 1, '2017-05-10', 'PAGO ALOJA HAB 107', NULL, 5, NULL, 98, 102000, NULL, 'FVH 109', '3013', '000868', 4, '51744384-1_10', '14251125', 0),
(99, 73, 1, '2017-05-10', 'PAGO ALOJAMIENTO HAB 112', NULL, 3, NULL, 99, 123200, NULL, 'FVH 110', '', '', NULL, '51744384-1_10', '14251125', 0),
(100, 74, 1, '2017-05-11', 'HB', NULL, 5, NULL, 100, 123200, NULL, 'FVH 112', '4098', '000870', 4, '51744384-1_10', '1113628111', 0),
(101, 75, 2, '2017-05-11', 'RESTAURANTE', NULL, 1, NULL, 101, 35000, NULL, 'FVH 113', '', '', NULL, '51744384-1_10', '1113628111', 0),
(102, 78, 2, '2017-05-11', 'PAGO ALOJAMIENTO HAB 106 POR  1 NOCHE', NULL, 1, NULL, 102, 123200, NULL, 'FVH 116', '', '', NULL, '51744384-1_10', '14251125', 0),
(103, 79, 1, '2017-05-11', 'PAGO ALOJAMIENTO HAB 110', NULL, 5, NULL, 103, 113000, NULL, 'FVH 117', '5200', '000871', 3, '51744384-1_10', '14251125', 0),
(104, 78, 1, '2017-05-11', 'PAGO SERV RESTAURANTE', NULL, 5, NULL, 104, 17000, NULL, 'FVH 118', '5200', '000871', 3, '51744384-1_10', '14251125', 0),
(105, 80, 1, '2017-05-11', 'pago alojamiento hab 111', NULL, 5, NULL, 105, 148800, NULL, 'FVH 119', '1989', '000872', 3, '51744384-1_10', '14251125', 0),
(106, 81, 1, '2017-05-11', 'HB', NULL, 6, NULL, 106, 236200, NULL, 'FVH 120', '1713', '000873', 3, '51744384-1_10', '1113628111', 0),
(107, 75, 2, '2017-05-12', 'RESTAURANTE', NULL, 1, NULL, 107, 17000, NULL, 'FVH 122', '', '', NULL, '51744384-1_10', '1113628111', 0),
(108, 8, 2, '2017-05-12', 'PAGO SERV RESTAURANTE', NULL, 1, NULL, 108, 8000, NULL, 'FVH 124', '', '', NULL, '51744384-1_10', '14251125', 0),
(109, 53, 2, '2017-05-12', 'PAGO ALOJAMIENTO HAB 207', NULL, 1, NULL, 109, 492750, NULL, 'FVH 123', '', '', NULL, '51744384-1_10', '14251125', 0),
(110, 82, 1, '2017-05-12', 'pago alojamienothab 205 por 2 noches retefuente 3.5% $ 8.755', NULL, 3, NULL, 110, 288847, NULL, 'FVH 125', '', '', NULL, '51744384-1_10', '14251125', 0),
(111, 81, 1, '2017-05-13', 'PAGO ADICIONAL Y SERV. RESTAURANTE', NULL, 6, NULL, 111, 63950, NULL, 'FVH 127', '1713', '000876', 3, '51744384-1_10', '14251125', 0),
(112, 81, 1, '2017-05-13', 'PAGO ALOJAMIENTO HAB 108 Y110 POR 1 NOCHE', NULL, 6, NULL, 112, 236200, NULL, 'FVH 126', '1713', '000876', 3, '51744384-1_10', '14251125', 0),
(113, 83, 1, '2017-05-13', 'HBA', NULL, 6, NULL, 113, 123200, NULL, 'FVH 128', '7161', '000879', 5, '51744384-1_10', '1113628111', 0),
(114, 84, 1, '2017-05-14', 'PAGO SER. RESTAURANTE', NULL, 5, NULL, 114, 130000, NULL, 'FVH 129', '1342', '000880', 3, '51744384-1_10', '14251125', 0),
(115, 85, 2, '2017-05-15', 'pago alojamiento hab 112', NULL, 1, NULL, 115, 123200, NULL, 'FVH 130', '', '', NULL, '51744384-1_10', '14251125', 0),
(116, 85, 2, '2017-05-15', 'pago servicio de restaurante', NULL, 1, NULL, 116, 27000, NULL, 'FVH 130', '', '', NULL, '51744384-1_10', '14251125', 0),
(117, 86, 1, '2017-05-16', 'hb', NULL, 6, NULL, 117, 571000, NULL, 'FVH 131', '7456', '000886', 3, '51744384-1_10', '1113628111', 0),
(118, 53, 1, '2017-05-16', 'pago aloja hab 207 por 3 noches', NULL, 5, NULL, 118, 369550, NULL, 'FVH 133', '7910', '000888', 3, '51744384-1_10', '14251125', 0),
(119, 53, 1, '2017-05-16', 'pago alojamiento  hab 206', NULL, 5, NULL, 119, 123200, NULL, 'FVH 132', '7910', '000887', 3, '51744384-1_10', '14251125', 0),
(120, 85, 2, '2017-05-17', 'DESAYUNO', NULL, 1, NULL, 120, 6800, NULL, 'FVH 135', '', '', NULL, '51744384-1_10', '1113628111', 0),
(121, 87, 1, '2017-05-17', 'HAB', NULL, 5, NULL, 121, 246400, NULL, 'FVH 137', '9822', '000889', 4, '51744384-1_10', '1113628111', 0),
(122, 53, 2, '2017-05-17', 'restaurante', NULL, 1, NULL, 122, 9000, NULL, 'FVH 136', '', '', NULL, '51744384-1_10', '1113628111', 0),
(123, 23, 2, '2017-05-17', 'RESTAURANTE', NULL, 1, NULL, 123, 83000, NULL, 'FVH 138', '', '', NULL, '51744384-1_10', '1113628111', 0),
(124, 53, 2, '2017-05-17', 'RESTAURANTE', NULL, 1, NULL, 124, 8000, NULL, 'FVH 139', '', '', NULL, '51744384-1_10', '1113628111', 0),
(125, 88, 1, '2017-05-17', 'pago laojamienot hab 111', NULL, 5, NULL, 125, 123200, NULL, 'FVH 140', '2404', '000890', 3, '51744384-1_10', '14251125', 0),
(126, 89, 1, '2017-05-18', 'pago aloja hab 204 por 3 noches', NULL, 6, NULL, 126, 369550, NULL, 'FVH 141', '6587', '000892', 4, '51744384-1_10', '14251125', 0),
(127, 90, 1, '2017-05-18', 'pago aloja hab 206', NULL, 5, NULL, 127, 369550, NULL, 'FVH 142', '6587', '000892', 4, '51744384-1_10', '14251125', 0),
(128, 53, 2, '2017-05-18', 'RESTAURANTE', NULL, 1, NULL, 128, 9800, NULL, 'FVH 143', '', '', NULL, '51744384-1_10', '1113628111', 0),
(129, 91, 1, '2017-05-18', 'RESTAURANTE', NULL, 6, NULL, 129, 195000, NULL, 'FVH 144', '7435', '000893', 5, '51744384-1_10', '1113628111', 0),
(130, 92, 1, '2017-05-18', 'HAB', NULL, 5, NULL, 130, 246350, NULL, 'FVH 145', '8866', '000894', 3, '51744384-1_10', '1113628111', 0),
(131, 94, 1, '2017-05-19', 'PAGO ALOJA HAB 207', NULL, 3, NULL, 131, 123200, NULL, 'FVH 147', '', '', NULL, '51744384-1_10', '14251125', 0),
(132, 85, 1, '2017-05-20', 'PAGO ALOJ HAB 118-119', NULL, 6, NULL, 132, 541750, NULL, 'FVH 148', '5792', '000895', 5, '51744384-1_10', '14251125', 0),
(133, 85, 2, '2017-05-20', 'HAB', NULL, 1, NULL, 133, 123200, NULL, 'FVH 149', '', '', NULL, '51744384-1_10', '1113628111', 0),
(134, 89, 2, '2017-05-20', 'RESTAURANTE', NULL, 1, NULL, 134, 57000, NULL, 'FVH 150', '', '', NULL, '51744384-1_10', '1113628111', 0),
(135, 95, 2, '2017-05-20', 'HOSPEDAJE Y RESRTAURANTE', NULL, 1, NULL, 135, 256400, NULL, 'FVH 151', '', '', NULL, '51744384-1_10', '1113628111', 0),
(136, 96, 1, '2017-05-21', 'restaurante', NULL, 6, NULL, 136, 56500, NULL, 'FVH 153', '2100', '000898', 4, '51744384-1_10', '1113628111', 0),
(137, 87, 2, '2017-05-22', 'hospedaje del dia 17 al 21 de mayo 2017\n1 noche hab 107 del dia 20 de mayo 2017', NULL, 1, NULL, 137, 878750, NULL, 'FVH 154', '', '', NULL, '51744384-1_10', '1113667405', 0),
(138, 2, 1, '2017-05-22', 'RETEFUENTE:  $14.492\nRETEIVA:            $11.802\nCOMISION:         $49.236', NULL, 3, NULL, 138, 417220, NULL, 'FVH 1', '', '', NULL, '51744384-1_10', '1113667405', 0),
(139, 53, 1, '2017-05-23', 'HAB', NULL, 5, NULL, 139, 246350, NULL, 'FVH 155', '5050', '000900', 3, '51744384-1_10', '1113628111', 0),
(140, 97, 1, '2017-05-23', 'HAB', NULL, 5, NULL, 140, 297600, NULL, 'FVH 156', '4953', '000899', 4, '51744384-1_10', '1113628111', 0),
(141, 99, 2, '2017-05-24', 'HB', NULL, 1, NULL, 141, 246350, NULL, 'FVH 157', '', '', NULL, '51744384-1_10', '1113628111', 0),
(142, 100, 1, '2017-05-24', 'HBA', NULL, 5, NULL, 142, 246350, NULL, 'FVH 158', '9247', '000901', 4, '51744384-1_10', '1113628111', 0),
(143, 101, 1, '2017-05-24', 'HB', NULL, 5, NULL, 143, 163150, NULL, 'FVH 159', '2606', '000904', 3, '51744384-1_10', '1113628111', 0),
(144, 85, 1, '2017-05-25', 'PAGO ALOJA HAB 112-118', NULL, 6, NULL, 144, 246350, NULL, 'FVH 160', '1360', '000905', 3, '51744384-1_10', '14251125', 0),
(145, 102, 1, '2017-05-25', 'pago serv. de restaurante', NULL, 6, NULL, 145, 16000, NULL, 'FVH 161', '6365', '000907', 5, '51744384-1_10', '14251125', 0),
(146, 103, 1, '2017-05-26', 'serv. restau', NULL, 6, NULL, 146, 32000, NULL, 'FVH 162', '6981', '000908', 5, '51744384-1_10', '14251125', 0),
(147, 105, 1, '2017-05-27', 'HB', NULL, 5, NULL, 147, 148800, NULL, 'FVH 164', '7541', '000909', 3, '51744384-1_10', '1113628111', 0),
(148, 104, 1, '2017-05-27', 'PAGO ALOJA HAB 119-204', NULL, 3, NULL, 148, 272000, NULL, 'FVH 163', '', '', NULL, '51744384-1_10', '14251125', 0),
(149, 106, 1, '2017-05-27', 'pago aloja hab 119 por 2 noches', NULL, 6, NULL, 149, 297600, NULL, 'FVH 165', '7449', '000910', 5, '51744384-1_10', '14251125', 0),
(150, 107, 1, '2017-05-27', 'pago alojamiento hab 111', NULL, 5, NULL, 150, 148800, NULL, 'FVH 166', '4435', '000911', 3, '51744384-1_10', '14251125', 0),
(151, 108, 1, '2017-05-27', 'HB', NULL, 6, NULL, 151, 148800, NULL, 'FVH 167', '1894', '000912', 3, '51744384-1_10', '1113628111', 0),
(152, 109, 1, '2017-05-28', 'pago aloj hab 117 por 1 noche', NULL, 6, NULL, 152, 228800, NULL, 'FVH 168', '8354', '000913', 5, '51744384-1_10', '14251125', 0),
(153, 110, 1, '2017-05-28', 'HAB 204 POR 2 NOCHES', NULL, 5, NULL, 153, 297600, NULL, 'FVH 169', '4953', '000914', 4, '51744384-1_10', '14251125', 0),
(154, 111, 1, '2017-05-28', 'PAGO HAB 206 ALOJAMIENTO Y HABITACION', NULL, 5, NULL, 154, 1335500, NULL, 'FVH 171', '0545', '000919', 3, '51744384-1_10', '14251125', 0),
(155, 112, 1, '2017-05-28', 'PAGO ALOJAMIENTO POR UN MES 2 PAX', NULL, 6, NULL, 155, 1599950, NULL, 'FVH 172', '7231', '000918', 3, '51744384-1_10', '14251125', 0),
(156, 107, 1, '2017-05-28', 'PAGO RESTAURANTE', NULL, 5, NULL, 156, 159000, NULL, 'FVH 170', '4435', '000917', 3, '51744384-1_10', '14251125', 0),
(157, 113, 1, '2017-05-28', 'RESTAUANTE', NULL, 6, NULL, 157, 72000, NULL, 'FVH 173', '5830', '000920', 5, '51744384-1_10', '14251125', 0),
(158, 108, 1, '2017-05-29', 'Restaurante', NULL, 5, NULL, 158, 83500, NULL, 'FVH 174', '9037', '000922', 3, '51744384-1_10', '1113667405', 0),
(159, 39, 2, '2017-05-30', 'RESTAURANTE', NULL, 1, NULL, 159, 8000, NULL, 'FVH 175', '', '', NULL, '51744384-1_10', '1113628111', 0),
(160, 114, 1, '2017-06-01', 'HAB', NULL, 6, NULL, 160, 113000, NULL, 'FVH 176', '6044', '000929', 3, '51744384-1_10', '1113628111', 0),
(161, 115, 2, '2017-06-02', 'HAB', NULL, 1, NULL, 161, 149000, NULL, 'FVH 177', '', '', NULL, '51744384-1_10', '1113628111', 0),
(162, 116, 1, '2017-06-03', 'HB', NULL, 5, NULL, 162, 985550, NULL, 'FVH 178', '1619', '000930', 3, '51744384-1_10', '1113628111', 0),
(163, 117, 1, '2017-06-03', 'hb', NULL, 5, NULL, 163, 246350, NULL, 'FVH 179', '7163', '000931', 4, '51744384-1_10', '1113628111', 0),
(164, 116, 1, '2017-06-06', 'SERV RESTAURANTE', NULL, 5, NULL, 164, 661500, NULL, 'FVH 180', '1619', '000934', 3, '51744384-1_10', '14251125', 0),
(165, 118, 1, '2017-06-06', 'HAB', NULL, 5, NULL, 165, 123200, NULL, 'FVH 181', '5139', '000935', 3, '51744384-1_10', '1113628111', 0),
(166, 119, 1, '2017-06-07', 'pago aloja hab 204', NULL, 5, NULL, 166, 123200, NULL, 'FVH 182', '5388', '000936', 3, '51744384-1_10', '14251125', 0),
(167, 119, 1, '2017-06-07', 'PAGO ALOJA HAB 112', NULL, 6, NULL, 167, 246350, NULL, 'FVH 183', '2079', '000938', 5, '51744384-1_10', '14251125', 0),
(168, 121, 1, '2017-06-21', 'pago aloj hab 121', NULL, 6, NULL, 168, 123200, NULL, 'FVH 184', '3862', '000957', 5, '51744384-1_10', '14251125', 0),
(169, 122, 2, '2017-06-21', 'HB', NULL, 1, NULL, 169, 123200, NULL, 'FVH 185', '', '', NULL, '51744384-1_10', '1113628111', 0),
(170, 123, 1, '2017-06-21', 'HA', NULL, 5, NULL, 170, 123200, NULL, 'FVH 186', '4322', '000958', 3, '51744384-1_10', '1113628111', 0),
(171, 53, 1, '2017-06-23', 'PAGO HAB 111 POR 3 NOCHES AVON', NULL, 6, NULL, 171, 369550, NULL, 'FVH 187', '5050', '000961', 3, '51744384-1_10', '14251125', 0),
(172, 124, 1, '2017-06-23', 'PAGO ALOJA HAB 112', NULL, 5, NULL, 172, 148800, NULL, 'FVH 188', '5019', '000962', 3, '51744384-1_10', '14251125', 0),
(173, 125, 1, '2017-06-23', 'PAGO SERV RESTAURANTRE', NULL, 5, NULL, 173, 47000, NULL, 'FVH 189', '4609', '000963', 3, '51744384-1_10', '14251125', 0),
(174, 126, 1, '2017-06-24', 'PAGO ALOJ HAB 207', NULL, 6, NULL, 174, 148800, NULL, 'FVH 190', '9884', '000966', 5, '51744384-1_10', '14251125', 0),
(175, 127, 1, '2017-07-11', 'pago aloja hab 102', NULL, 5, NULL, 175, 123200, NULL, 'FVH 191', '1107', '000982', 3, '51744384-1_10', '14251125', 0),
(176, 128, 1, '2017-07-13', 'HB', NULL, 6, NULL, 176, 123200, NULL, 'FVH 193', '5280', '000984', 5, '51744384-1_10', '1113628111', 0),
(177, 127, 1, '2017-07-13', 'hb', NULL, 5, NULL, 177, 123200, NULL, 'FVH 194', '1107', '000985', 3, '51744384-1_10', '1113628111', 0),
(178, 127, 1, '2017-07-13', 'hb', NULL, 5, NULL, 178, 123200, NULL, 'FVH 192', '1107', '0000985', 3, '51744384-1_10', '1113628111', 0),
(179, 129, 1, '2017-07-13', 'pago alojamientohab 204', NULL, 6, NULL, 179, 148800, NULL, 'FVH 195', '6871', '000990', 5, '51744384-1_10', '14251125', 0),
(180, 130, 1, '2017-07-22', 'PAGO ALOJAMIENTO Y SERV RESTAURANTE HAB 107', NULL, 6, NULL, 180, 193150, NULL, 'FVH 196', '0943', '0001009', 3, '51744384-1_10', '14251125', 0),
(181, 131, 1, '2017-07-22', 'PAGO SERV. RESTAURANTE', NULL, 5, NULL, 181, 36500, NULL, 'FVH 197', '8686', '0001010', 4, '51744384-1_10', '14251125', 0),
(182, 133, 1, '2017-07-25', 'PAGO LAOJAMIENTO HAB 118-121', NULL, 3, NULL, 182, 246350, NULL, 'FVH 198', '', '', NULL, '51744384-1_10', '14251125', 0),
(183, 134, 1, '2017-07-27', 'PAGO ALOJAMIENTO HAB 112', NULL, 5, NULL, 183, 123200, NULL, 'FVH 199', '2446', '0001015', 3, '51744384-1_10', '14251125', 0),
(184, 134, 2, '2017-07-28', 'PAGO SERV RESTAURANTE', NULL, 1, NULL, 184, 11000, NULL, 'FVH 199', '', '', NULL, '51744384-1_10', '14251125', 0),
(185, 135, 1, '2017-07-28', 'RESTA', NULL, 6, NULL, 185, 53000, NULL, 'FVH 200', '9061', '001016', 5, '51744384-1_10', '1113628111', 0),
(186, 136, 1, '2017-07-29', 'HB', NULL, 6, NULL, 186, 492000, NULL, 'FVH 201', '8306', '001018', 5, '51744384-1_10', '1113628111', 0),
(187, 137, 1, '2017-07-30', 'hb', NULL, 6, NULL, 187, 238500, NULL, 'FVH 202', '1264', '001021', 4, '51744384-1_10', '1113628111', 0),
(188, 138, 1, '2017-07-30', 'REST', NULL, 5, NULL, 188, 49500, NULL, 'FVH 203', '9290', '001022', 4, '51744384-1_10', '1113628111', 0),
(189, 139, 1, '2017-07-31', '0885- 001023 RESTAURANTE DOS BAUCHER', NULL, 6, NULL, 189, 28000, NULL, 'FVH 204', '8306', '001024', 4, '51744384-1_10', '1113628111', 0),
(190, 139, 1, '2017-07-31', 'RESTAURANTE', NULL, 6, NULL, 190, 252000, NULL, 'FVH 204', '0885', '001023', 4, '51744384-1_10', '1113628111', 0),
(191, 141, 2, '2017-08-05', 'PAGO ALOJAMEINTO HAB 206', NULL, 1, NULL, 191, 123200, NULL, 'FVH 207', '', '', NULL, '51744384-1_10', '14251125', 0),
(192, 142, 1, '2017-08-05', 'PAGO ALOJAMIENTO HAB 120', NULL, 5, NULL, 192, 148800, NULL, 'FVH 208', '5677', '01034', 3, '51744384-1_10', '14251125', 0),
(193, 143, 1, '2017-08-05', 'PAGO ALOJAMIENTO PARA 3 PAX POR 2 NOCHES', NULL, 5, NULL, 193, 377600, NULL, 'FVH 209', '7692', '001035', 3, '51744384-1_10', '14251125', 0),
(194, 145, 1, '2017-08-05', 'PAGO ALJ. HAB 208 X 8 NOCHES', NULL, 5, NULL, 194, 1830400, NULL, 'FVH 211', '4067', '001036', 3, '51744384-1_10', '14251125', 0),
(195, 146, 1, '2017-08-06', 'PAGO ALOJ. HAB 109 POR 1 NOCHE ', NULL, 6, NULL, 195, 203150, NULL, 'FVH 212', '9805', '001037', 5, '51744384-1_10', '14251125', 0),
(196, 147, 1, '2017-08-06', 'RESTAURANTE', NULL, 5, NULL, 196, 83000, NULL, 'FVH 213', '3570', '001038', 1, '51744384-1_10', '14251125', 0),
(197, 149, 1, '2017-08-16', 'RESTAURANTE', NULL, 6, NULL, 197, 32500, NULL, 'FVH 214', '1124', '001049', 5, '51744384-1_10', '14251125', 0),
(198, 150, 2, '2017-08-18', 'PAGO LAOJAMIENTO', NULL, 1, NULL, 198, 98550, NULL, 'FVH 215', '', '', NULL, '51744384-1_10', '14251125', 0),
(199, 151, 1, '2017-09-14', 'PAGO ALOJAMIENTO HAB 107 POR 1 NOCHE', NULL, 6, NULL, 199, 113000, NULL, 'FVH 217', '2901', '001066', 3, '51744384-1_10', '14251125', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_servicios`
--

CREATE TABLE `productos_servicios` (
  `id_producto_servicio` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `referencia` varchar(100) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `costo_unidad` float DEFAULT NULL,
  `precio_venta` float DEFAULT NULL,
  `imagen` longblob,
  `garantia` varchar(200) DEFAULT NULL,
  `id_unidad_medida` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `producto` tinyint(1) NOT NULL,
  `codigo_barra` varchar(100) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL,
  `id_empresa` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `productos_servicios`
--

INSERT INTO `productos_servicios` (`id_producto_servicio`, `nombre`, `referencia`, `descripcion`, `costo_unidad`, `precio_venta`, `imagen`, `garantia`, `id_unidad_medida`, `stock`, `producto`, `codigo_barra`, `transacion`, `id_empresa`) VALUES
(1, 'Servicio de lavandería ', 'LAVANDERIA', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL, '', '51744384-1'),
(3, 'Restaurante – Bar', 'RESTBAR', NULL, NULL, NULL, NULL, NULL, 1, 0, 0, NULL, '', '51744384-1'),
(4, 'Hospedaje', 'HOSPEDAJE', NULL, NULL, NULL, NULL, NULL, 10, 0, 0, NULL, '', '51744384-1'),
(5, 'Ingresos para Terceros (Restaurante)', '0', 'Ingresos para el restaurante', 0, 0, NULL, 'No tiene', 1, 1, 0, NULL, '51744384-1_1', '51744384-1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recibos_caja`
--

CREATE TABLE `recibos_caja` (
  `id_recibo_caja` int(11) NOT NULL,
  `id_factura` varchar(20) DEFAULT NULL,
  `consecutivo` varchar(11) NOT NULL,
  `fecha` date NOT NULL,
  `id_empresa` varchar(15) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `valor` float NOT NULL,
  `concepto` varchar(200) NOT NULL,
  `cheque` varchar(50) DEFAULT NULL,
  `id_tipo_pago` int(11) NOT NULL,
  `transacion` varchar(30) NOT NULL,
  `id_hospedaje` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `recibos_caja`
--

INSERT INTO `recibos_caja` (`id_recibo_caja`, `id_factura`, `consecutivo`, `fecha`, `id_empresa`, `id_cliente`, `valor`, `concepto`, `cheque`, `id_tipo_pago`, `transacion`, `id_hospedaje`) VALUES
(1, 'FVH 3', '1', '2017-03-25', '51744384-1', 4, 131000, 'Pago total a la factura No. FVH 3', '', 1, '51744384-1_1', 0),
(2, 'FVH 2', '2', '2017-03-25', '51744384-1', 3, 328000, 'Pago total a la factura No. FVH 2', '', 1, '51744384-1_2', 0),
(3, 'FVH 4', '3', '2017-03-29', '51744384-1', 7, 5500000, 'Pago parcial a la factura No. FVH 4', '', 3, '51744384-1_3', 0),
(4, 'FVH 5', '4', '2017-03-30', '51744384-1', 8, 17000, 'Pago total a la factura No. FVH 5', '', 1, '51744384-1_4', 0),
(5, 'FVH 6', '5', '2017-03-30', '51744384-1', 8, 17000, 'Pago total a la factura No. FVH 6', '', 1, '51744384-1_5', 0),
(6, 'FVH 8', '6', '2017-03-30', '51744384-1', 8, 16000, 'Pago total a la factura No. FVH 8', '', 1, '51744384-1_6', 0),
(7, 'FVH 9', '7', '2017-03-30', '51744384-1', 9, 123200, 'Pago total a la factura No. FVH 9', '', 1, '51744384-1_7', 0),
(8, 'FVH 10', '8', '2017-03-30', '51744384-1', 10, 148800, 'Pago total a la factura No. FVH 10', '', 5, '51744384-1_8', 0),
(9, 'FVH 11', '9', '2017-03-31', '51744384-1', 11, 17000, 'Pago total a la factura No. FVH 11', '', 1, '51744384-1_9', 0),
(10, 'FVH 12', '10', '2017-03-31', '51744384-1', 8, 17000, 'Pago total a la factura No. FVH 12', '', 1, '51744384-1_10', 0),
(11, 'FVH 13', '11', '2017-03-31', '51744384-1', 7, 560000, 'Pago total a la factura No. FVH 13', '', 5, '51744384-1_10', 0),
(12, 'FVH 4', '12', '2017-03-31', '51744384-1', 7, 647765, 'Pago total a la factura No. FVH 4', '', 5, '51744384-1_10', 0),
(13, 'FVH 16', '13', '2017-04-04', '51744384-1', 14, 40000, 'Pago total a la factura No. FVH 16', '', 5, '51744384-1_10', 0),
(14, 'FVH 18', '14', '2017-04-05', '51744384-1', 16, 148800, 'Pago total a la factura No. FVH 18', '', 5, '51744384-1_10', 0),
(15, 'FVH 19', '15', '2017-04-05', '51744384-1', 15, 369550, 'Pago total a la factura No. FVH 19', '', 6, '51744384-1_10', 0),
(16, 'FVH 20', '16', '2017-04-06', '51744384-1', 18, 188000, 'Pago total a la factura No. FVH 20', '', 5, '51744384-1_10', 0),
(17, 'FVH 21', '17', '2017-04-06', '51744384-1', 19, 369550, 'Pago total a la factura No. FVH 21', '', 1, '51744384-1_10', 0),
(18, 'FVH 22', '18', '2017-04-07', '51744384-1', 20, 297600, 'Pago total a la factura No. FVH 22', '', 6, '51744384-1_10', 0),
(19, 'FVH 23', '19', '2017-04-07', '51744384-1', 21, 2640000, 'Pago total a la factura No. FVH 23', '', 2, '51744384-1_10', 0),
(20, 'FVH 24', '20', '2017-04-07', '51744384-1', 21, 330000, 'Pago total a la factura No. FVH 24', '', 2, '51744384-1_10', 0),
(21, 'FVH 25', '21', '2017-04-09', '51744384-1', 22, 148800, 'Pago total a la factura No. FVH 25', '', 5, '51744384-1_10', 0),
(22, 'FVH 26', '22', '2017-04-09', '51744384-1', 21, 1274000, 'Pago total a la factura No. FVH 26', '', 1, '51744384-1_10', 0),
(23, 'FVH 28', '23', '2017-04-12', '51744384-1', 24, 7212810, 'Pago parcial a la factura No. FVH 28', '', 3, '51744384-1_10', 0),
(24, 'FVH 29', '24', '2017-04-12', '51744384-1', 26, 580050, 'Pago total a la factura No. FVH 29', '', 5, '51744384-1_10', 0),
(25, 'FVH 31', '25', '2017-04-12', '51744384-1', 27, 26000, 'Pago total a la factura No. FVH 31', '', 6, '51744384-1_10', 0),
(26, 'FVH 30', '26', '2017-04-12', '51744384-1', 27, 123200, 'Pago total a la factura No. FVH 30', '', 6, '51744384-1_10', 0),
(27, 'FVH 32', '27', '2017-04-12', '51744384-1', 28, 228800, 'Pago total a la factura No. FVH 32', '', 6, '51744384-1_10', 0),
(28, 'FVH 33', '28', '2017-04-13', '51744384-1', 29, 140000, 'Pago parcial a la factura No. FVH 33', '', 3, '51744384-1_10', 0),
(29, 'FVH 33', '29', '2017-04-13', '51744384-1', 29, 106350, 'Pago total a la factura No. FVH 33', '', 6, '51744384-1_10', 0),
(30, 'FVH 34', '30', '2017-04-13', '51744384-1', 30, 143200, 'Pago total a la factura No. FVH 34', '', 5, '51744384-1_10', 0),
(31, 'FVH 35', '31', '2017-04-14', '51744384-1', 31, 75000, 'Pago total a la factura No. FVH 35', '', 5, '51744384-1_10', 0),
(32, 'FVH 37', '32', '2017-04-14', '51744384-1', 33, 243800, 'Pago total a la factura No. FVH 37', '', 5, '51744384-1_10', 0),
(33, 'FVH 38', '33', '2017-04-14', '51744384-1', 34, 298000, 'Pago total a la factura No. FVH 38', '', 5, '51744384-1_10', 0),
(34, 'FVH 39', '34', '2017-04-15', '51744384-1', 35, 148800, 'Pago parcial a la factura No. FVH 39', '', 5, '51744384-1_10', 0),
(35, 'FVH 39', '35', '2017-04-15', '51744384-1', 35, 137200, 'Pago total a la factura No. FVH 39', '', 1, '51744384-1_10', 0),
(36, 'FVH 40', '36', '2017-04-15', '51744384-1', 30, 194700, 'Pago total a la factura No. FVH 40', '', 6, '51744384-1_10', 0),
(37, 'FVH 41', '37', '2017-04-15', '51744384-1', 36, 168000, 'Pago total a la factura No. FVH 41', '', 6, '51744384-1_10', 0),
(38, 'FVH 42', '38', '2017-04-15', '51744384-1', 29, 17000, 'Pago total a la factura No. FVH 42', '', 6, '51744384-1_10', 0),
(39, 'FVH 43', '39', '2017-04-15', '51744384-1', 37, 246400, 'Pago total a la factura No. FVH 43', '', 6, '51744384-1_10', 0),
(40, 'FVH 44', '40', '2017-04-16', '51744384-1', 38, 135000, 'Pago total a la factura No. FVH 44', '', 6, '51744384-1_10', 0),
(41, 'FVH 45', '41', '2017-04-18', '51744384-1', 39, 8000, 'Pago total a la factura No. FVH 45', '', 1, '51744384-1_10', 0),
(42, 'FVH 46', '42', '2017-04-19', '51744384-1', 40, 123200, 'Pago total a la factura No. FVH 46', '', 1, '51744384-1_10', 0),
(43, 'FVH 47', '43', '2017-04-19', '51744384-1', 41, 123200, 'Pago total a la factura No. FVH 47', '', 5, '51744384-1_10', 0),
(44, 'FVH 48', '44', '2017-04-19', '51744384-1', 42, 123200, 'Pago total a la factura No. FVH 48', '', 1, '51744384-1_10', 0),
(45, 'FVH 49', '45', '2017-04-19', '51744384-1', 43, 123200, 'Pago total a la factura No. FVH 49', '', 5, '51744384-1_10', 0),
(46, 'FVH 50', '46', '2017-04-20', '51744384-1', 44, 123200, 'Pago total a la factura No. FVH 50', '', 5, '51744384-1_10', 0),
(47, 'FVH 51', '47', '2017-04-20', '51744384-1', 45, 123200, 'Pago total a la factura No. FVH 51', '', 6, '51744384-1_10', 0),
(48, 'FVH 52', '48', '2017-04-20', '51744384-1', 43, 123200, 'Pago total a la factura No. FVH 52', '', 5, '51744384-1_10', 0),
(49, 'FVH 53', '49', '2017-04-21', '51744384-1', 11, 8500, 'Pago total a la factura No. FVH 53', '', 1, '51744384-1_10', 0),
(50, 'FVH 54', '50', '2017-04-21', '51744384-1', 44, 8000, 'Pago total a la factura No. FVH 54', '', 1, '51744384-1_10', 0),
(51, 'FVH 55', '51', '2017-04-22', '51744384-1', 46, 123200, 'Pago total a la factura No. FVH 55', '', 6, '51744384-1_10', 0),
(52, 'FVH 56', '52', '2017-04-22', '51744384-1', 47, 123200, 'Pago total a la factura No. FVH 56', '', 6, '51744384-1_10', 0),
(53, 'FVH 57', '53', '2017-04-23', '51744384-1', 48, 163150, 'Pago total a la factura No. FVH 57', '', 6, '51744384-1_10', 0),
(54, 'FVH 58', '54', '2017-04-23', '51744384-1', 49, 102000, 'Pago total a la factura No. FVH 58', '', 5, '51744384-1_10', 0),
(55, 'FVH 59', '55', '2017-04-23', '51744384-1', 50, 75000, 'Pago parcial a la factura No. FVH 59', '', 5, '51744384-1_10', 0),
(56, 'FVH 60', '56', '2017-04-24', '51744384-1', 51, 41000, 'Pago total a la factura No. FVH 60', '', 5, '51744384-1_10', 0),
(57, 'FVH 62', '57', '2017-04-25', '51744384-1', 39, 34000, 'Pago total a la factura No. FVH 62', '', 1, '51744384-1_10', 0),
(58, 'FVH 63', '58', '2017-04-25', '51744384-1', 11, 24000, 'Pago total a la factura No. FVH 63', '', 1, '51744384-1_10', 0),
(59, 'FVH 64', '59', '2017-04-25', '51744384-1', 52, 369550, 'Pago total a la factura No. FVH 64', '', 6, '51744384-1_10', 0),
(60, 'FVH 65', '60', '2017-04-26', '51744384-1', 53, 369550, 'Pago total a la factura No. FVH 65', '', 6, '51744384-1_10', 0),
(61, 'FVH 66', '61', '2017-04-27', '51744384-1', 54, 500000, 'Pago total a la factura No. FVH 66', '', 1, '51744384-1_10', 0),
(62, 'FVH 67', '62', '2017-04-27', '51744384-1', 55, 509000, 'Pago total a la factura No. FVH 67', '', 5, '51744384-1_10', 0),
(63, 'FVH 68', '63', '2017-04-27', '51744384-1', 39, 48000, 'Pago total a la factura No. FVH 68', '', 1, '51744384-1_10', 0),
(64, 'FVH 69', '64', '2017-04-27', '51744384-1', 53, 16500, 'Pago total a la factura No. FVH 69', '', 6, '51744384-1_10', 0),
(65, 'FVH 70', '65', '2017-04-27', '51744384-1', 57, 163200, 'Pago total a la factura No. FVH 70', '', 5, '51744384-1_10', 0),
(66, 'FVH 71', '66', '2017-04-27', '51744384-1', 58, 134000, 'Pago total a la factura No. FVH 71', '', 1, '51744384-1_10', 0),
(67, 'FVH 72', '67', '2017-04-28', '51744384-1', 23, 44500, 'Pago total a la factura No. FVH 72', '', 6, '51744384-1_10', 0),
(68, 'FVH 73', '68', '2017-04-29', '51744384-1', 59, 39000, 'Pago total a la factura No. FVH 73', '', 1, '51744384-1_10', 0),
(69, 'FVH 74', '69', '2017-04-29', '51744384-1', 60, 42000, 'Pago total a la factura No. FVH 74', '', 1, '51744384-1_10', 0),
(70, 'FVH 75', '70', '2017-04-29', '51744384-1', 61, 123200, 'Pago total a la factura No. FVH 75', '', 6, '51744384-1_10', 0),
(71, 'FVH 76', '71', '2017-04-30', '51744384-1', 62, 123200, 'Pago total a la factura No. FVH 76', '', 6, '51744384-1_10', 0),
(72, 'FVH 77', '72', '2017-05-01', '51744384-1', 63, 83000, 'Pago total a la factura No. FVH 77', '', 5, '51744384-1_10', 0),
(73, 'FVH 78', '73', '2017-05-01', '51744384-1', 64, 74500, 'Pago total a la factura No. FVH 78', '', 5, '51744384-1_10', 0),
(74, 'FVH 82', '74', '2017-05-02', '51744384-1', 60, 47000, 'Pago total a la factura No. FVH 82', '', 1, '51744384-1_10', 0),
(75, 'FVH 83', '75', '2017-05-02', '51744384-1', 23, 16000, 'Pago total a la factura No. FVH 83', '', 1, '51744384-1_10', 0),
(76, 'FVH 84', '76', '2017-05-02', '51744384-1', 59, 48000, 'Pago total a la factura No. FVH 84', '', 1, '51744384-1_10', 0),
(77, 'FVH 85', '77', '2017-05-03', '51744384-1', 65, 148800, 'Pago total a la factura No. FVH 85', '', 6, '51744384-1_10', 0),
(78, 'FVH 88', '78', '2017-05-03', '51744384-1', 66, 22000, 'Pago total a la factura No. FVH 88', '', 5, '51744384-1_10', 0),
(79, 'FVH 89', '79', '2017-05-03', '51744384-1', 8, 8000, 'Pago total a la factura No. FVH 89', '', 1, '51744384-1_10', 0),
(80, 'FVH 90', '80', '2017-05-03', '51744384-1', 58, 694200, 'Pago total a la factura No. FVH 90', '', 1, '51744384-1_10', 0),
(81, 'FVH 91', '81', '2017-05-03', '51744384-1', 67, 163150, 'Pago total a la factura No. FVH 91', '', 6, '51744384-1_10', 0),
(82, 'FVH 92', '82', '2017-05-04', '51744384-1', 68, 163150, 'Pago total a la factura No. FVH 92', '', 6, '51744384-1_10', 0),
(83, 'FVH 94', '83', '2017-05-05', '51744384-1', 53, 58000, 'Pago total a la factura No. FVH 94', '', 6, '51744384-1_10', 0),
(84, 'FVH 93', '84', '2017-05-05', '51744384-1', 53, 369550, 'Pago total a la factura No. FVH 93', '', 6, '51744384-1_10', 0),
(85, 'FVH 96', '85', '2017-05-05', '51744384-1', 69, 148800, 'Pago total a la factura No. FVH 96', '', 5, '51744384-1_10', 0),
(86, 'FVH 95', '86', '2017-05-05', '51744384-1', 53, 369550, 'Pago total a la factura No. FVH 95', '', 1, '51744384-1_10', 0),
(87, 'FVH 97', '87', '2017-05-06', '51744384-1', 69, 32500, 'Pago total a la factura No. FVH 97', '', 5, '51744384-1_10', 0),
(88, 'FVH 98', '88', '2017-05-08', '51744384-1', 70, 148800, 'Pago total a la factura No. FVH 98', '', 5, '51744384-1_10', 0),
(89, 'FVH 99', '89', '2017-05-08', '51744384-1', 71, 148800, 'Pago total a la factura No. FVH 99', '', 6, '51744384-1_10', 0),
(90, 'FVH 101', '90', '2017-05-09', '51744384-1', 53, 123200, 'Pago total a la factura No. FVH 101', '', 6, '51744384-1_10', 0),
(91, 'FVH 102', '91', '2017-05-09', '51744384-1', 53, 29000, 'Pago total a la factura No. FVH 102', '', 6, '51744384-1_10', 0),
(92, 'FVH 103', '92', '2017-05-09', '51744384-1', 72, 102000, 'Pago total a la factura No. FVH 103', '', 6, '51744384-1_10', 0),
(93, 'FVH 104', '93', '2017-05-09', '51744384-1', 68, 102000, 'Pago total a la factura No. FVH 104', '', 6, '51744384-1_10', 0),
(94, 'FVH 107', '94', '2017-05-10', '51744384-1', 72, 111000, 'Pago total a la factura No. FVH 107', '', 1, '51744384-1_10', 0),
(95, 'FVH 106', '95', '2017-05-10', '51744384-1', 72, 111000, 'Pago total a la factura No. FVH 106', '', 1, '51744384-1_10', 0),
(96, 'FVH 105', '96', '2017-05-10', '51744384-1', 72, 111000, 'Pago total a la factura No. FVH 105', '', 1, '51744384-1_10', 0),
(97, 'FVH 108', '97', '2017-05-10', '51744384-1', 72, 111000, 'Pago total a la factura No. FVH 108', '', 1, '51744384-1_10', 0),
(98, 'FVH 109', '98', '2017-05-10', '51744384-1', 72, 102000, 'Pago total a la factura No. FVH 109', '', 5, '51744384-1_10', 0),
(99, 'FVH 110', '99', '2017-05-10', '51744384-1', 73, 123200, 'Pago total a la factura No. FVH 110', '', 3, '51744384-1_10', 0),
(100, 'FVH 112', '100', '2017-05-11', '51744384-1', 74, 123200, 'Pago total a la factura No. FVH 112', '', 5, '51744384-1_10', 0),
(101, 'FVH 113', '101', '2017-05-11', '51744384-1', 75, 35000, 'Pago total a la factura No. FVH 113', '', 1, '51744384-1_10', 0),
(102, 'FVH 116', '102', '2017-05-11', '51744384-1', 78, 123200, 'Pago total a la factura No. FVH 116', '', 1, '51744384-1_10', 0),
(103, 'FVH 117', '103', '2017-05-11', '51744384-1', 79, 113000, 'Pago total a la factura No. FVH 117', '', 5, '51744384-1_10', 0),
(104, 'FVH 118', '104', '2017-05-11', '51744384-1', 78, 17000, 'Pago total a la factura No. FVH 118', '', 5, '51744384-1_10', 0),
(105, 'FVH 119', '105', '2017-05-11', '51744384-1', 80, 148800, 'Pago total a la factura No. FVH 119', '', 5, '51744384-1_10', 0),
(106, 'FVH 120', '106', '2017-05-11', '51744384-1', 81, 236200, 'Pago total a la factura No. FVH 120', '', 6, '51744384-1_10', 0),
(107, 'FVH 122', '107', '2017-05-12', '51744384-1', 75, 17000, 'Pago total a la factura No. FVH 122', '', 1, '51744384-1_10', 0),
(108, 'FVH 124', '108', '2017-05-12', '51744384-1', 8, 8000, 'Pago total a la factura No. FVH 124', '', 1, '51744384-1_10', 0),
(109, 'FVH 123', '109', '2017-05-12', '51744384-1', 53, 492750, 'Pago total a la factura No. FVH 123', '', 1, '51744384-1_10', 0),
(110, 'FVH 125', '110', '2017-05-12', '51744384-1', 82, 288847, 'Pago parcial a la factura No. FVH 125', '', 3, '51744384-1_10', 0),
(111, 'FVH 127', '111', '2017-05-13', '51744384-1', 81, 63950, 'Pago total a la factura No. FVH 127', '', 6, '51744384-1_10', 0),
(112, 'FVH 126', '112', '2017-05-13', '51744384-1', 81, 236200, 'Pago total a la factura No. FVH 126', '', 6, '51744384-1_10', 0),
(113, 'FVH 128', '113', '2017-05-13', '51744384-1', 83, 123200, 'Pago total a la factura No. FVH 128', '', 6, '51744384-1_10', 0),
(114, 'FVH 129', '114', '2017-05-14', '51744384-1', 84, 130000, 'Pago total a la factura No. FVH 129', '', 5, '51744384-1_10', 0),
(115, 'FVH 130', '115', '2017-05-15', '51744384-1', 85, 123200, 'Pago parcial a la factura No. FVH 130', '', 1, '51744384-1_10', 0),
(116, 'FVH 130', '116', '2017-05-15', '51744384-1', 85, 27000, 'Pago total a la factura No. FVH 130', '', 1, '51744384-1_10', 0),
(117, 'FVH 131', '117', '2017-05-16', '51744384-1', 86, 571000, 'Pago total a la factura No. FVH 131', '', 6, '51744384-1_10', 0),
(118, 'FVH 133', '118', '2017-05-16', '51744384-1', 53, 369550, 'Pago total a la factura No. FVH 133', '', 5, '51744384-1_10', 0),
(119, 'FVH 132', '119', '2017-05-16', '51744384-1', 53, 123200, 'Pago total a la factura No. FVH 132', '', 5, '51744384-1_10', 0),
(120, 'FVH 135', '120', '2017-05-17', '51744384-1', 85, 6800, 'Pago total a la factura No. FVH 135', '', 1, '51744384-1_10', 0),
(121, 'FVH 137', '121', '2017-05-17', '51744384-1', 87, 246400, 'Pago total a la factura No. FVH 137', '', 5, '51744384-1_10', 0),
(122, 'FVH 136', '122', '2017-05-17', '51744384-1', 53, 9000, 'Pago total a la factura No. FVH 136', '', 1, '51744384-1_10', 0),
(123, 'FVH 138', '123', '2017-05-17', '51744384-1', 23, 83000, 'Pago total a la factura No. FVH 138', '', 1, '51744384-1_10', 0),
(124, 'FVH 139', '124', '2017-05-17', '51744384-1', 53, 8000, 'Pago total a la factura No. FVH 139', '', 1, '51744384-1_10', 0),
(125, 'FVH 140', '125', '2017-05-17', '51744384-1', 88, 123200, 'Pago total a la factura No. FVH 140', '', 5, '51744384-1_10', 0),
(126, 'FVH 141', '126', '2017-05-18', '51744384-1', 89, 369550, 'Pago total a la factura No. FVH 141', '', 6, '51744384-1_10', 0),
(127, 'FVH 142', '127', '2017-05-18', '51744384-1', 90, 369550, 'Pago total a la factura No. FVH 142', '', 5, '51744384-1_10', 0),
(128, 'FVH 143', '128', '2017-05-18', '51744384-1', 53, 9800, 'Pago total a la factura No. FVH 143', '', 1, '51744384-1_10', 0),
(129, 'FVH 144', '129', '2017-05-18', '51744384-1', 91, 195000, 'Pago parcial a la factura No. FVH 144', '', 6, '51744384-1_10', 0),
(130, 'FVH 145', '130', '2017-05-18', '51744384-1', 92, 246350, 'Pago total a la factura No. FVH 145', '', 5, '51744384-1_10', 0),
(131, 'FVH 147', '131', '2017-05-19', '51744384-1', 94, 123200, 'Pago total a la factura No. FVH 147', '', 3, '51744384-1_10', 0),
(132, 'FVH 148', '132', '2017-05-20', '51744384-1', 85, 541750, 'Pago total a la factura No. FVH 148', '', 6, '51744384-1_10', 0),
(133, 'FVH 149', '133', '2017-05-20', '51744384-1', 85, 123200, 'Pago total a la factura No. FVH 149', '', 1, '51744384-1_10', 0),
(134, 'FVH 150', '134', '2017-05-20', '51744384-1', 89, 57000, 'Pago total a la factura No. FVH 150', '', 1, '51744384-1_10', 0),
(135, 'FVH 151', '135', '2017-05-20', '51744384-1', 95, 256400, 'Pago total a la factura No. FVH 151', '', 1, '51744384-1_10', 0),
(136, 'FVH 153', '136', '2017-05-21', '51744384-1', 96, 56500, 'Pago total a la factura No. FVH 153', '', 6, '51744384-1_10', 0),
(137, 'FVH 154', '137', '2017-05-22', '51744384-1', 87, 878750, 'Pago total a la factura No. FVH 154', '', 1, '51744384-1_10', 0),
(138, 'FVH 1', '138', '2017-05-22', '51744384-1', 2, 417220, 'Pago parcial a la factura No. FVH 1', '', 3, '51744384-1_10', 0),
(139, 'FVH 155', '139', '2017-05-23', '51744384-1', 53, 246350, 'Pago total a la factura No. FVH 155', '', 5, '51744384-1_10', 0),
(140, 'FVH 156', '140', '2017-05-23', '51744384-1', 97, 297600, 'Pago total a la factura No. FVH 156', '', 5, '51744384-1_10', 0),
(141, 'FVH 157', '141', '2017-05-24', '51744384-1', 99, 246350, 'Pago total a la factura No. FVH 157', '', 1, '51744384-1_10', 0),
(142, 'FVH 158', '142', '2017-05-24', '51744384-1', 100, 246350, 'Pago total a la factura No. FVH 158', '', 5, '51744384-1_10', 0),
(143, 'FVH 159', '143', '2017-05-24', '51744384-1', 101, 163150, 'Pago total a la factura No. FVH 159', '', 5, '51744384-1_10', 0),
(144, 'FVH 160', '144', '2017-05-25', '51744384-1', 85, 246350, 'Pago total a la factura No. FVH 160', '', 6, '51744384-1_10', 0),
(145, 'FVH 161', '145', '2017-05-25', '51744384-1', 102, 16000, 'Pago total a la factura No. FVH 161', '', 6, '51744384-1_10', 0),
(146, 'FVH 162', '146', '2017-05-26', '51744384-1', 103, 32000, 'Pago total a la factura No. FVH 162', '', 6, '51744384-1_10', 0),
(147, 'FVH 164', '147', '2017-05-27', '51744384-1', 105, 148800, 'Pago total a la factura No. FVH 164', '', 5, '51744384-1_10', 0),
(148, 'FVH 163', '148', '2017-05-27', '51744384-1', 104, 272000, 'Pago total a la factura No. FVH 163', '', 3, '51744384-1_10', 0),
(149, 'FVH 165', '149', '2017-05-27', '51744384-1', 106, 297600, 'Pago total a la factura No. FVH 165', '', 6, '51744384-1_10', 0),
(150, 'FVH 166', '150', '2017-05-27', '51744384-1', 107, 148800, 'Pago total a la factura No. FVH 166', '', 5, '51744384-1_10', 0),
(151, 'FVH 167', '151', '2017-05-27', '51744384-1', 108, 148800, 'Pago total a la factura No. FVH 167', '', 6, '51744384-1_10', 0),
(152, 'FVH 168', '152', '2017-05-28', '51744384-1', 109, 228800, 'Pago total a la factura No. FVH 168', '', 6, '51744384-1_10', 0),
(153, 'FVH 169', '153', '2017-05-28', '51744384-1', 110, 297600, 'Pago total a la factura No. FVH 169', '', 5, '51744384-1_10', 0),
(154, 'FVH 171', '154', '2017-05-28', '51744384-1', 111, 1335500, 'Pago total a la factura No. FVH 171', '', 5, '51744384-1_10', 0),
(155, 'FVH 172', '155', '2017-05-28', '51744384-1', 112, 1599950, 'Pago total a la factura No. FVH 172', '', 6, '51744384-1_10', 0),
(156, 'FVH 170', '156', '2017-05-28', '51744384-1', 107, 159000, 'Pago total a la factura No. FVH 170', '', 5, '51744384-1_10', 0),
(157, 'FVH 173', '157', '2017-05-28', '51744384-1', 113, 72000, 'Pago total a la factura No. FVH 173', '', 6, '51744384-1_10', 0),
(158, 'FVH 174', '158', '2017-05-29', '51744384-1', 108, 83500, 'Pago total a la factura No. FVH 174', '', 5, '51744384-1_10', 0),
(159, 'FVH 175', '159', '2017-05-30', '51744384-1', 39, 8000, 'Pago total a la factura No. FVH 175', '', 1, '51744384-1_10', 0),
(160, 'FVH 176', '160', '2017-06-01', '51744384-1', 114, 113000, 'Pago total a la factura No. FVH 176', '', 6, '51744384-1_10', 0),
(161, 'FVH 177', '161', '2017-06-02', '51744384-1', 115, 149000, 'Pago total a la factura No. FVH 177', '', 1, '51744384-1_10', 0),
(162, 'FVH 178', '162', '2017-06-03', '51744384-1', 116, 985550, 'Pago total a la factura No. FVH 178', '', 5, '51744384-1_10', 0),
(163, 'FVH 179', '163', '2017-06-03', '51744384-1', 117, 246350, 'Pago total a la factura No. FVH 179', '', 5, '51744384-1_10', 0),
(164, 'FVH 180', '164', '2017-06-06', '51744384-1', 116, 661500, 'Pago total a la factura No. FVH 180', '', 5, '51744384-1_10', 0),
(165, 'FVH 181', '165', '2017-06-06', '51744384-1', 118, 123200, 'Pago total a la factura No. FVH 181', '', 5, '51744384-1_10', 0),
(166, 'FVH 182', '166', '2017-06-07', '51744384-1', 119, 123200, 'Pago total a la factura No. FVH 182', '', 5, '51744384-1_10', 0),
(167, 'FVH 183', '167', '2017-06-07', '51744384-1', 119, 246350, 'Pago total a la factura No. FVH 183', '', 6, '51744384-1_10', 0),
(168, 'FVH 184', '168', '2017-06-21', '51744384-1', 121, 123200, 'Pago total a la factura No. FVH 184', '', 6, '51744384-1_10', 0),
(169, 'FVH 185', '169', '2017-06-21', '51744384-1', 122, 123200, 'Pago total a la factura No. FVH 185', '', 1, '51744384-1_10', 0),
(170, 'FVH 186', '170', '2017-06-21', '51744384-1', 123, 123200, 'Pago total a la factura No. FVH 186', '', 5, '51744384-1_10', 0),
(171, 'FVH 187', '171', '2017-06-23', '51744384-1', 53, 369550, 'Pago total a la factura No. FVH 187', '', 6, '51744384-1_10', 0),
(172, 'FVH 188', '172', '2017-06-23', '51744384-1', 124, 148800, 'Pago total a la factura No. FVH 188', '', 5, '51744384-1_10', 0),
(173, 'FVH 189', '173', '2017-06-23', '51744384-1', 125, 47000, 'Pago total a la factura No. FVH 189', '', 5, '51744384-1_10', 0),
(174, 'FVH 190', '174', '2017-06-24', '51744384-1', 126, 148800, 'Pago total a la factura No. FVH 190', '', 6, '51744384-1_10', 0),
(175, 'FVH 191', '175', '2017-07-11', '51744384-1', 127, 123200, 'Pago total a la factura No. FVH 191', '', 5, '51744384-1_10', 0),
(176, 'FVH 193', '176', '2017-07-13', '51744384-1', 128, 123200, 'Pago total a la factura No. FVH 193', '', 6, '51744384-1_10', 0),
(177, 'FVH 194', '177', '2017-07-13', '51744384-1', 127, 123200, 'Pago total a la factura No. FVH 194', '', 5, '51744384-1_10', 0),
(178, 'FVH 192', '178', '2017-07-13', '51744384-1', 127, 123200, 'Pago total a la factura No. FVH 192', '', 5, '51744384-1_10', 0),
(179, 'FVH 195', '179', '2017-07-13', '51744384-1', 129, 148800, 'Pago total a la factura No. FVH 195', '', 6, '51744384-1_10', 0),
(180, 'FVH 196', '180', '2017-07-22', '51744384-1', 130, 193150, 'Pago total a la factura No. FVH 196', '', 6, '51744384-1_10', 0),
(181, 'FVH 197', '181', '2017-07-22', '51744384-1', 131, 36500, 'Pago total a la factura No. FVH 197', '', 5, '51744384-1_10', 0),
(182, 'FVH 198', '182', '2017-07-25', '51744384-1', 133, 246350, 'Pago total a la factura No. FVH 198', '', 3, '51744384-1_10', 0),
(183, 'FVH 199', '183', '2017-07-27', '51744384-1', 134, 123200, 'Pago parcial a la factura No. FVH 199', '', 5, '51744384-1_10', 0),
(184, 'FVH 199', '184', '2017-07-28', '51744384-1', 134, 11000, 'Pago total a la factura No. FVH 199', '', 1, '51744384-1_10', 0),
(185, 'FVH 200', '185', '2017-07-28', '51744384-1', 135, 53000, 'Pago total a la factura No. FVH 200', '', 6, '51744384-1_10', 0),
(186, 'FVH 201', '186', '2017-07-29', '51744384-1', 136, 492000, 'Pago total a la factura No. FVH 201', '', 6, '51744384-1_10', 0),
(187, 'FVH 202', '187', '2017-07-30', '51744384-1', 137, 238500, 'Pago total a la factura No. FVH 202', '', 6, '51744384-1_10', 0),
(188, 'FVH 203', '188', '2017-07-30', '51744384-1', 138, 49500, 'Pago total a la factura No. FVH 203', '', 5, '51744384-1_10', 0),
(189, 'FVH 204', '189', '2017-07-31', '51744384-1', 139, 28000, 'Pago parcial a la factura No. FVH 204', '', 6, '51744384-1_10', 0),
(190, 'FVH 204', '190', '2017-07-31', '51744384-1', 139, 252000, 'Pago total a la factura No. FVH 204', '', 6, '51744384-1_10', 0),
(191, 'FVH 207', '191', '2017-08-05', '51744384-1', 141, 123200, 'Pago total a la factura No. FVH 207', '', 1, '51744384-1_10', 0),
(192, 'FVH 208', '192', '2017-08-05', '51744384-1', 142, 148800, 'Pago total a la factura No. FVH 208', '', 5, '51744384-1_10', 0),
(193, 'FVH 209', '193', '2017-08-05', '51744384-1', 143, 377600, 'Pago total a la factura No. FVH 209', '', 5, '51744384-1_10', 0),
(194, 'FVH 211', '194', '2017-08-05', '51744384-1', 145, 1830400, 'Pago total a la factura No. FVH 211', '', 5, '51744384-1_10', 0),
(195, 'FVH 212', '195', '2017-08-06', '51744384-1', 146, 203150, 'Pago total a la factura No. FVH 212', '', 6, '51744384-1_10', 0),
(196, 'FVH 213', '196', '2017-08-06', '51744384-1', 147, 83000, 'Pago total a la factura No. FVH 213', '', 5, '51744384-1_10', 0),
(197, 'FVH 214', '197', '2017-08-16', '51744384-1', 149, 32500, 'Pago total a la factura No. FVH 214', '', 6, '51744384-1_10', 0),
(198, 'FVH 215', '198', '2017-08-18', '51744384-1', 150, 98550, 'Pago total a la factura No. FVH 215', '', 1, '51744384-1_10', 0),
(199, 'FVH 217', '199', '2017-09-14', '51744384-1', 151, 113000, 'Pago total a la factura No. FVH 217', '', 6, '51744384-1_10', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resoluciones_facturas`
--

CREATE TABLE `resoluciones_facturas` (
  `id_resolucion` varchar(15) NOT NULL,
  `fecha` date NOT NULL,
  `prefijo` varchar(15) NOT NULL,
  `consecutivo_inicial` int(11) NOT NULL,
  `consecutivo_final` int(11) NOT NULL,
  `id_tipo_resolucion` int(11) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `id_empresa` varchar(15) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `resoluciones_facturas`
--

INSERT INTO `resoluciones_facturas` (`id_resolucion`, `fecha`, `prefijo`, `consecutivo_inicial`, `consecutivo_final`, `id_tipo_resolucion`, `activo`, `id_empresa`, `transacion`) VALUES
('18762001981968', '2017-01-27', 'FVH', 1, 5000, 1, 1, '51744384-1', '51744384-1_1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `retenciones`
--

CREATE TABLE `retenciones` (
  `id_retencion` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `tarifa` float NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `retenciones`
--

INSERT INTO `retenciones` (`id_retencion`, `nombre`, `tarifa`, `transacion`) VALUES
(1, 'Arrendamiento de bienes muebles (4%)', 4, NULL),
(2, 'Arrendamiento de bienes raíces (3.5%)', 3.5, NULL),
(3, 'Compras (2.5%)', 2.5, NULL),
(4, 'Compras (3.5%)', 3.5, NULL),
(5, 'Honorarios y comisiones (10%)', 10, NULL),
(6, 'Honorarios y comisiones (11%)', 11, NULL),
(7, 'Servicios de aseo y vigilancia (2%)', 2, NULL),
(8, 'Servicios de hoteles y restaurantes (3.5%)', 3.5, NULL),
(9, 'Servicios en general (4%)', 4, NULL),
(10, 'Servicios en general (6%)', 4, NULL),
(11, 'ReteICA (0%)', 0, NULL),
(12, 'ReteIVA (15%)', 15, NULL),
(13, 'ReteCREE (0.4%)', 0.4, NULL),
(14, 'ReteCREE (0.8%)', 0.8, NULL),
(15, 'ReteCREE (1.6%)', 1.6, NULL),
(16, 'Transporte de carga (1%)', 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `retenciones_empresas`
--

CREATE TABLE `retenciones_empresas` (
  `id_retencion_empresa` int(11) NOT NULL,
  `id_retencion` int(11) NOT NULL,
  `id_empresa` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `retenciones_empresas`
--

INSERT INTO `retenciones_empresas` (`id_retencion_empresa`, `id_retencion`, `id_empresa`) VALUES
(1, 9, '51744384-1'),
(2, 8, '51744384-1'),
(3, 2, '51744384-1'),
(4, 12, '51744384-1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `retenciones_pagos`
--

CREATE TABLE `retenciones_pagos` (
  `id_retencion_pagos` int(11) NOT NULL,
  `id_factura` varchar(15) NOT NULL,
  `id_retencion` int(11) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL,
  `valor` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `retenciones_pagos`
--

INSERT INTO `retenciones_pagos` (`id_retencion_pagos`, `id_factura`, `id_retencion`, `transacion`, `valor`) VALUES
(1, 'FVH 4', 8, NULL, 108235);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id_rol` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id_rol`, `nombre`, `descripcion`, `transacion`) VALUES
(1, 'Administrador', 'Designado a personal de código innova ', ''),
(2, 'Vendedor Supervisor', 'Usuario que modifica tarifas, autoriza modificaciones y gestiona facturas', ''),
(3, 'Vendedor Operario', 'Operario de la aplicación. ', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripciones`
--

CREATE TABLE `suscripciones` (
  `id_suscripcion` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `suscripciones`
--

INSERT INTO `suscripciones` (`id_suscripcion`, `nombre`, `descripcion`, `transacion`) VALUES
(1, 'Plan Full', 'Sin fecha de caducidad, sin actulizaciones', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `terminos_de_pago`
--

CREATE TABLE `terminos_de_pago` (
  `id_termino_pago` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `terminos_de_pago`
--

INSERT INTO `terminos_de_pago` (`id_termino_pago`, `nombre`, `transacion`) VALUES
(1, 'Vencimiento Manual', NULL),
(2, 'De contado', NULL),
(3, '8 días', NULL),
(4, '15 días', NULL),
(5, '30 días', NULL),
(6, '60 días', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_de_habitacion`
--

CREATE TABLE `tipos_de_habitacion` (
  `id_tipo_habitacion` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `costo_persona` float NOT NULL,
  `costo_pareja` float NOT NULL,
  `costo_persona_adicional` float NOT NULL,
  `id_empresa` varchar(15) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipos_de_habitacion`
--

INSERT INTO `tipos_de_habitacion` (`id_tipo_habitacion`, `nombre`, `costo_persona`, `costo_pareja`, `costo_persona_adicional`, `id_empresa`, `transacion`) VALUES
(1, 'Chalet', 94958, 103529, 33613, '51744384-1', ''),
(2, 'Americana', 103529, 125043, 33613, '51744384-1', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_de_identificacion`
--

CREATE TABLE `tipos_de_identificacion` (
  `id_tipo_identificacion` int(1) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipos_de_identificacion`
--

INSERT INTO `tipos_de_identificacion` (`id_tipo_identificacion`, `nombre`, `descripcion`, `transacion`) VALUES
(1, 'Cédula de Ciudadanía', 'C.C', ''),
(2, 'Cédula de Extranjería', 'C.E', ''),
(3, 'NIT', 'NIT', ''),
(4, 'Pasaporte', 'P.P', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_de_pagos`
--

CREATE TABLE `tipos_de_pagos` (
  `id_tipo_pago` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipos_de_pagos`
--

INSERT INTO `tipos_de_pagos` (`id_tipo_pago`, `nombre`, `descripcion`, `transacion`) VALUES
(1, 'Efectivo', NULL, ''),
(2, 'Consignación', NULL, ''),
(3, 'Transferencia', NULL, ''),
(4, 'Cheque', NULL, ''),
(5, 'Tarjeta crédito', NULL, ''),
(6, 'Tarjeta débito', NULL, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_imprimibles`
--

CREATE TABLE `tipos_imprimibles` (
  `id_tipo_imprimible` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipos_imprimibles`
--

INSERT INTO `tipos_imprimibles` (`id_tipo_imprimible`, `nombre`, `descripcion`, `transacion`) VALUES
(1, 'Facturas', NULL, ''),
(2, 'Cotización', NULL, ''),
(3, 'Recibo de Caja', NULL, ''),
(4, 'Comprobante de Egrego', NULL, ''),
(6, 'Nota Dédito', NULL, ''),
(7, 'Nota crédito', NULL, ''),
(8, 'Cuenta De Cobro', NULL, ''),
(9, 'Prefactura', NULL, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_resoluciones`
--

CREATE TABLE `tipos_resoluciones` (
  `id_tipo_resolucion` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `transacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipos_resoluciones`
--

INSERT INTO `tipos_resoluciones` (`id_tipo_resolucion`, `nombre`, `descripcion`, `transacion`) VALUES
(1, 'Autorizar', NULL, ''),
(2, 'Habilitar', NULL, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `unidades_medida`
--

CREATE TABLE `unidades_medida` (
  `id_unidad_medida` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `unidades_medida`
--

INSERT INTO `unidades_medida` (`id_unidad_medida`, `nombre`, `descripcion`, `transacion`) VALUES
(1, 'No Aplica', '--', NULL),
(2, 'UNIDAD', 'Und', NULL),
(4, 'CENTÍMETRO', 'Cm', NULL),
(5, 'METRO', 'M', NULL),
(6, 'MILILITRO', 'mL', NULL),
(7, 'LITRO', 'L', NULL),
(8, 'GALÓN', 'G', NULL),
(9, 'HORA', 'Hora(s)', NULL),
(10, 'NOCHE', 'Noche(s)', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_app`
--

CREATE TABLE `usuarios_app` (
  `id_usuario` varchar(15) NOT NULL,
  `id_tipo_identificacion` int(11) NOT NULL,
  `id_empresa` varchar(15) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `id_estado_usuario` int(11) NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios_app`
--

INSERT INTO `usuarios_app` (`id_usuario`, `id_tipo_identificacion`, `id_empresa`, `id_rol`, `password`, `nombres`, `apellidos`, `id_estado_usuario`, `transacion`) VALUES
('1113628111', 1, '51744384-1', 3, '[0]', 'Carlos Andrés', 'Reyes', 1, '51744384-1_4'),
('1113667405', 1, '51744384-1', 3, '[0]', 'Harold', 'Smith Velazco', 1, '51744384-1_1'),
('14251125', 1, '51744384-1', 3, '[0]', 'Mauricio ', 'Torres', 1, '51744384-1_3'),
('31179383', 1, '51744384-1', 2, '[0]', 'María Elsyd', 'Llanos', 1, '51744384-1_2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `versiones_app`
--

CREATE TABLE `versiones_app` (
  `id_version` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `fecha_compilacion` date NOT NULL,
  `transacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `versiones_app`
--

INSERT INTO `versiones_app` (`id_version`, `nombre`, `descripcion`, `fecha_compilacion`, `transacion`) VALUES
(1, '0.6', 'Versión Inicial ', '2017-03-07', '');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividades_economicas`
--
ALTER TABLE `actividades_economicas`
  ADD PRIMARY KEY (`id_actividad_economica`);

--
-- Indices de la tabla `actividades_empresas`
--
ALTER TABLE `actividades_empresas`
  ADD PRIMARY KEY (`id_actividad_empresa`);

--
-- Indices de la tabla `app_vendedores`
--
ALTER TABLE `app_vendedores`
  ADD PRIMARY KEY (`id_app_vendedor`),
  ADD KEY `id_version` (`id_version`),
  ADD KEY `id_licencia` (`id_licencia`),
  ADD KEY `id_vendedor` (`id_empresa`);

--
-- Indices de la tabla `bancos`
--
ALTER TABLE `bancos`
  ADD PRIMARY KEY (`id_banco`);

--
-- Indices de la tabla `categorias_pagos`
--
ALTER TABLE `categorias_pagos`
  ADD PRIMARY KEY (`id_categoria_pago`);

--
-- Indices de la tabla `ciudades`
--
ALTER TABLE `ciudades`
  ADD PRIMARY KEY (`id_ciudad`,`id_departamento`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `comprobantes_egreso`
--
ALTER TABLE `comprobantes_egreso`
  ADD PRIMARY KEY (`id_comprobante_egreso`);

--
-- Indices de la tabla `consecutivos_imprimibles`
--
ALTER TABLE `consecutivos_imprimibles`
  ADD PRIMARY KEY (`id_consecutivo`);

--
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`id_cuenta`);

--
-- Indices de la tabla `departamentos`
--
ALTER TABLE `departamentos`
  ADD PRIMARY KEY (`id_departamento`);

--
-- Indices de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  ADD PRIMARY KEY (`id_descuento`);

--
-- Indices de la tabla `descuentos_clientes`
--
ALTER TABLE `descuentos_clientes`
  ADD PRIMARY KEY (`id_descuentos_clientes`);

--
-- Indices de la tabla `detalle_factura_cotizacion`
--
ALTER TABLE `detalle_factura_cotizacion`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `id_producto_servicio` (`id_producto_servicio`),
  ADD KEY `id_factura` (`id_factura`);

--
-- Indices de la tabla `detalle_factura_proveedor`
--
ALTER TABLE `detalle_factura_proveedor`
  ADD PRIMARY KEY (`id_dfp`);

--
-- Indices de la tabla `empresas`
--
ALTER TABLE `empresas`
  ADD PRIMARY KEY (`id_empresa`);

--
-- Indices de la tabla `entidades_bancarias`
--
ALTER TABLE `entidades_bancarias`
  ADD PRIMARY KEY (`id_entidad_bancaria`);

--
-- Indices de la tabla `estaciones_empresas`
--
ALTER TABLE `estaciones_empresas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_app_vendedor` (`id_app_vendedor`);

--
-- Indices de la tabla `estados_facturas`
--
ALTER TABLE `estados_facturas`
  ADD PRIMARY KEY (`id_estado_factura`);

--
-- Indices de la tabla `estados_usuarios`
--
ALTER TABLE `estados_usuarios`
  ADD PRIMARY KEY (`id_estado_usuario`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`id_factura`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_resolucion` (`id_resolucion`),
  ADD KEY `id_estado_factura` (`id_estado_factura`),
  ADD KEY `id_empresa` (`id_empresa`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `facturas_anuladas`
--
ALTER TABLE `facturas_anuladas`
  ADD PRIMARY KEY (`id_factura_anulada`);

--
-- Indices de la tabla `facturas_proveedor`
--
ALTER TABLE `facturas_proveedor`
  ADD PRIMARY KEY (`id_factura_proveedor`);

--
-- Indices de la tabla `franquicias`
--
ALTER TABLE `franquicias`
  ADD PRIMARY KEY (`id_franquicia`);

--
-- Indices de la tabla `habitaciones`
--
ALTER TABLE `habitaciones`
  ADD PRIMARY KEY (`id_habitacion`);

--
-- Indices de la tabla `hospedajes`
--
ALTER TABLE `hospedajes`
  ADD PRIMARY KEY (`id_hospedaje`);

--
-- Indices de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  ADD PRIMARY KEY (`id_impuesto`);

--
-- Indices de la tabla `ip_publicas`
--
ALTER TABLE `ip_publicas`
  ADD PRIMARY KEY (`id_ip`);

--
-- Indices de la tabla `licencias`
--
ALTER TABLE `licencias`
  ADD PRIMARY KEY (`id_licencia`);

--
-- Indices de la tabla `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`id_log`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_vendedor` (`id_empresa`);

--
-- Indices de la tabla `modulos`
--
ALTER TABLE `modulos`
  ADD PRIMARY KEY (`id_modulo`);

--
-- Indices de la tabla `modulos_licencias`
--
ALTER TABLE `modulos_licencias`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_modulo` (`id_modulo`),
  ADD KEY `id_licencia` (`id_licencia`);

--
-- Indices de la tabla `motivos_estadia`
--
ALTER TABLE `motivos_estadia`
  ADD PRIMARY KEY (`id_motivo`);

--
-- Indices de la tabla `nacionalidades`
--
ALTER TABLE `nacionalidades`
  ADD PRIMARY KEY (`id_nacionalidad`);

--
-- Indices de la tabla `nv1categorias`
--
ALTER TABLE `nv1categorias`
  ADD PRIMARY KEY (`id_nv1_categoria`);

--
-- Indices de la tabla `nv2categorias`
--
ALTER TABLE `nv2categorias`
  ADD PRIMARY KEY (`id_nv2_categoria`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`id_pago`);

--
-- Indices de la tabla `productos_servicios`
--
ALTER TABLE `productos_servicios`
  ADD PRIMARY KEY (`id_producto_servicio`),
  ADD KEY `id_unidad_medida` (`id_unidad_medida`);

--
-- Indices de la tabla `recibos_caja`
--
ALTER TABLE `recibos_caja`
  ADD PRIMARY KEY (`id_recibo_caja`);

--
-- Indices de la tabla `resoluciones_facturas`
--
ALTER TABLE `resoluciones_facturas`
  ADD PRIMARY KEY (`id_resolucion`),
  ADD KEY `id_tipo_resolucion` (`id_tipo_resolucion`),
  ADD KEY `id_vendedor` (`id_empresa`);

--
-- Indices de la tabla `retenciones`
--
ALTER TABLE `retenciones`
  ADD PRIMARY KEY (`id_retencion`);

--
-- Indices de la tabla `retenciones_empresas`
--
ALTER TABLE `retenciones_empresas`
  ADD PRIMARY KEY (`id_retencion_empresa`);

--
-- Indices de la tabla `retenciones_pagos`
--
ALTER TABLE `retenciones_pagos`
  ADD PRIMARY KEY (`id_retencion_pagos`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `suscripciones`
--
ALTER TABLE `suscripciones`
  ADD PRIMARY KEY (`id_suscripcion`);

--
-- Indices de la tabla `terminos_de_pago`
--
ALTER TABLE `terminos_de_pago`
  ADD PRIMARY KEY (`id_termino_pago`);

--
-- Indices de la tabla `tipos_de_habitacion`
--
ALTER TABLE `tipos_de_habitacion`
  ADD PRIMARY KEY (`id_tipo_habitacion`);

--
-- Indices de la tabla `tipos_de_identificacion`
--
ALTER TABLE `tipos_de_identificacion`
  ADD PRIMARY KEY (`id_tipo_identificacion`);

--
-- Indices de la tabla `tipos_de_pagos`
--
ALTER TABLE `tipos_de_pagos`
  ADD PRIMARY KEY (`id_tipo_pago`);

--
-- Indices de la tabla `tipos_imprimibles`
--
ALTER TABLE `tipos_imprimibles`
  ADD PRIMARY KEY (`id_tipo_imprimible`);

--
-- Indices de la tabla `tipos_resoluciones`
--
ALTER TABLE `tipos_resoluciones`
  ADD PRIMARY KEY (`id_tipo_resolucion`);

--
-- Indices de la tabla `unidades_medida`
--
ALTER TABLE `unidades_medida`
  ADD PRIMARY KEY (`id_unidad_medida`);

--
-- Indices de la tabla `usuarios_app`
--
ALTER TABLE `usuarios_app`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `id_tipo_identificacion` (`id_tipo_identificacion`),
  ADD KEY `id_vendedor` (`id_empresa`),
  ADD KEY `id_rol` (`id_rol`),
  ADD KEY `id_estado_usuario` (`id_estado_usuario`);

--
-- Indices de la tabla `versiones_app`
--
ALTER TABLE `versiones_app`
  ADD PRIMARY KEY (`id_version`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actividades_economicas`
--
ALTER TABLE `actividades_economicas`
  MODIFY `id_actividad_economica` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `actividades_empresas`
--
ALTER TABLE `actividades_empresas`
  MODIFY `id_actividad_empresa` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `app_vendedores`
--
ALTER TABLE `app_vendedores`
  MODIFY `id_app_vendedor` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `bancos`
--
ALTER TABLE `bancos`
  MODIFY `id_banco` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `categorias_pagos`
--
ALTER TABLE `categorias_pagos`
  MODIFY `id_categoria_pago` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT de la tabla `ciudades`
--
ALTER TABLE `ciudades`
  MODIFY `id_ciudad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1117;
--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=152;
--
-- AUTO_INCREMENT de la tabla `comprobantes_egreso`
--
ALTER TABLE `comprobantes_egreso`
  MODIFY `id_comprobante_egreso` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `consecutivos_imprimibles`
--
ALTER TABLE `consecutivos_imprimibles`
  MODIFY `id_consecutivo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `departamentos`
--
ALTER TABLE `departamentos`
  MODIFY `id_departamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- AUTO_INCREMENT de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  MODIFY `id_descuento` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `descuentos_clientes`
--
ALTER TABLE `descuentos_clientes`
  MODIFY `id_descuentos_clientes` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `detalle_factura_cotizacion`
--
ALTER TABLE `detalle_factura_cotizacion`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=257;
--
-- AUTO_INCREMENT de la tabla `detalle_factura_proveedor`
--
ALTER TABLE `detalle_factura_proveedor`
  MODIFY `id_dfp` int(15) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `entidades_bancarias`
--
ALTER TABLE `entidades_bancarias`
  MODIFY `id_entidad_bancaria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT de la tabla `estaciones_empresas`
--
ALTER TABLE `estaciones_empresas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `estados_facturas`
--
ALTER TABLE `estados_facturas`
  MODIFY `id_estado_factura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `estados_usuarios`
--
ALTER TABLE `estados_usuarios`
  MODIFY `id_estado_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `facturas_anuladas`
--
ALTER TABLE `facturas_anuladas`
  MODIFY `id_factura_anulada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `facturas_proveedor`
--
ALTER TABLE `facturas_proveedor`
  MODIFY `id_factura_proveedor` int(15) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `franquicias`
--
ALTER TABLE `franquicias`
  MODIFY `id_franquicia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `habitaciones`
--
ALTER TABLE `habitaciones`
  MODIFY `id_habitacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT de la tabla `hospedajes`
--
ALTER TABLE `hospedajes`
  MODIFY `id_hospedaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  MODIFY `id_impuesto` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `ip_publicas`
--
ALTER TABLE `ip_publicas`
  MODIFY `id_ip` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `logs`
--
ALTER TABLE `logs`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `modulos`
--
ALTER TABLE `modulos`
  MODIFY `id_modulo` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `modulos_licencias`
--
ALTER TABLE `modulos_licencias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `motivos_estadia`
--
ALTER TABLE `motivos_estadia`
  MODIFY `id_motivo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `nacionalidades`
--
ALTER TABLE `nacionalidades`
  MODIFY `id_nacionalidad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=241;
--
-- AUTO_INCREMENT de la tabla `nv1categorias`
--
ALTER TABLE `nv1categorias`
  MODIFY `id_nv1_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `nv2categorias`
--
ALTER TABLE `nv2categorias`
  MODIFY `id_nv2_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `id_pago` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=200;
--
-- AUTO_INCREMENT de la tabla `productos_servicios`
--
ALTER TABLE `productos_servicios`
  MODIFY `id_producto_servicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `recibos_caja`
--
ALTER TABLE `recibos_caja`
  MODIFY `id_recibo_caja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=200;
--
-- AUTO_INCREMENT de la tabla `retenciones`
--
ALTER TABLE `retenciones`
  MODIFY `id_retencion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT de la tabla `retenciones_empresas`
--
ALTER TABLE `retenciones_empresas`
  MODIFY `id_retencion_empresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `retenciones_pagos`
--
ALTER TABLE `retenciones_pagos`
  MODIFY `id_retencion_pagos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `suscripciones`
--
ALTER TABLE `suscripciones`
  MODIFY `id_suscripcion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `terminos_de_pago`
--
ALTER TABLE `terminos_de_pago`
  MODIFY `id_termino_pago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `tipos_de_habitacion`
--
ALTER TABLE `tipos_de_habitacion`
  MODIFY `id_tipo_habitacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `tipos_de_identificacion`
--
ALTER TABLE `tipos_de_identificacion`
  MODIFY `id_tipo_identificacion` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `tipos_de_pagos`
--
ALTER TABLE `tipos_de_pagos`
  MODIFY `id_tipo_pago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `tipos_imprimibles`
--
ALTER TABLE `tipos_imprimibles`
  MODIFY `id_tipo_imprimible` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT de la tabla `tipos_resoluciones`
--
ALTER TABLE `tipos_resoluciones`
  MODIFY `id_tipo_resolucion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `unidades_medida`
--
ALTER TABLE `unidades_medida`
  MODIFY `id_unidad_medida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `versiones_app`
--
ALTER TABLE `versiones_app`
  MODIFY `id_version` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
