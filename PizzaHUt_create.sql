Create database bdPizzaHut;

use bdPizzaHut;

-- Table: CLIENTE
CREATE TABLE CLIENTE (
    IDCLI int identity(1,1) NOT NULL,
    NOMCLI varchar(90)  NOT NULL,
	APECLI varchar(90)  NOT NULL,
    DNICLI char(8)  NOT NULL,
    EMACLI varchar(80)  NOT NULL,
    CELCLI char(9)  NOT NULL,
    DOMCLI varchar(80)  NOT NULL,
    CODUBI char(6)  NOT NULL,
    ESTCLI char(1)  NOT NULL,
    CONSTRAINT CLIENTE_pk PRIMARY KEY (IDCLI)
);

-- Table: PIZZA
CREATE TABLE PIZZA (
    IDPIZ int identity(1,1) NOT NULL,
    NOMPIZ varchar(60)  NOT NULL,
    TAMPIZ varchar(30)  NOT NULL,
    PREPIZ decimal(5,2)  NOT NULL,
	ESTPIZ char(1) NOt NULL,
    CONSTRAINT PIZZA_pk PRIMARY KEY (IDPIZ)
);

-- Table: UBIGEO
CREATE TABLE UBIGEO (
    CODUBI char(6)   NOT NULL,
    DEPUBI varchar(40)  NOT NULL,
    PROUBI varchar(40)  NOT NULL,
    DISUBI varchar(40)  NOT NULL,
    CONSTRAINT UBIGEO_pk PRIMARY KEY (CODUBI)
);

-- Table: USUARIO
CREATE TABLE USUARIO (
    IDUSU int identity(1,1) NOT NULL,
    NOMUSU varchar(90)  NOT NULL,
	APEUSU varchar(90)  NOT NULL,
    CELUSU char(9)  NOT NULL,
    DNIUSU char(8)  NOT NULL,
    EMAUSU varchar(80)  NOT NULL,
    DOMUSU varchar(80)  NOT NULL,
    CODUBI char(6)  NOT NULL,
    ESTUSU char(1)  NOT NULL,
    CONSTRAINT USUARIO_pk PRIMARY KEY (IDUSU)
);

-- Table: VENTA
CREATE TABLE VENTA (
    IDVEN int identity(1,1) NOT NULL,
    FECVEN date  NOT NULL,
    IDUSU int  NOT NULL,
    IDCLI int  NOT NULL,
    TOTVEN decimal(5,2)  NOT NULL,
    CONSTRAINT VENTA_pk PRIMARY KEY (IDVEN)
);

-- Table: VENTA_DETALLE
CREATE TABLE VENTA_DETALLE (
    IDVENDET int identity(1,1) NOT NULL,
    CANVENDET int  NOT NULL,
    SUBVENDET decimal(5,2)  NOT NULL,
    IDPIZ int  NOT NULL,
    IDVEN int  NOT NULL,
    CONSTRAINT VENTA_DETALLE_pk PRIMARY KEY (IDVENDET)
);

-- foreign keys
-- Reference: CLIENTE_UBIGEO (table: CLIENTE)
ALTER TABLE CLIENTE ADD CONSTRAINT CLIENTE_UBIGEO
    FOREIGN KEY (CODUBI)
    REFERENCES UBIGEO (CODUBI)  

;

-- Reference: VENDEDOR_UBIGEO (table: USUARIO)
ALTER TABLE USUARIO ADD CONSTRAINT VENDEDOR_UBIGEO
    FOREIGN KEY (CODUBI)
    REFERENCES UBIGEO (CODUBI)  

;

-- Reference: VENTA_CLIENTE (table: VENTA)
ALTER TABLE VENTA ADD CONSTRAINT VENTA_CLIENTE
    FOREIGN KEY (IDCLI)
    REFERENCES CLIENTE (IDCLI)  

;

-- Reference: VENTA_DETALLE_PIZZA (table: VENTA_DETALLE)
ALTER TABLE VENTA_DETALLE ADD CONSTRAINT VENTA_DETALLE_PIZZA
    FOREIGN KEY (IDPIZ)
    REFERENCES PIZZA (IDPIZ)  

;

-- Reference: VENTA_DETALLE_VENTA (table: VENTA_DETALLE)
ALTER TABLE VENTA_DETALLE ADD CONSTRAINT VENTA_DETALLE_VENTA
    FOREIGN KEY (IDVEN)
    REFERENCES VENTA (IDVEN)  

;

-- Reference: VENTA_VENDEDOR (table: VENTA)
ALTER TABLE VENTA ADD CONSTRAINT VENTA_VENDEDOR
    FOREIGN KEY (IDUSU)
    REFERENCES USUARIO (IDUSU)  

;
--Insert Datos UBIGEO
CREATE PROCEDURE spInertUbigeo
(
  @CODUBI char(6),
  @DEPUBI nvarchar(60),
  @PROUBI nvarchar(60),
  @DISUBI nvarchar(60)
  )
 AS 
 BEGIN 
SET NOCOUNT ON 
INSERT INTO  UBIGEO 
(CODUBI, DEPUBI,PROUBI,DISUBI)
VALUES 
(@CODUBI, @DEPUBI, @PROUBI, @DISUBI)
SELECT * FROM UBIGEO
WHERE CODUBI = @CODUBI
END 
GO 

EXEC dbo.spInertUbigeo 
						'150501','LIMA','CAÑETE','SAN VICENTE DE CAÑETE',
						'150502','LIMA','CAÑETE','ASIA',
						'150503','LIMA','CAÑETE','CALANGO',
						'150504','LIMA','CAÑETE','CERRO AZUL',
						'150505','LIMA','CAÑETE','CHILCA',
						'150506','LIMA','CAÑETE','COAYLLO',
						'150507','LIMA','CAÑETE','IMPERIAL',
						'150508','LIMA','CAÑETE','LUNAHUANA',
						'150509','LIMA','CAÑETE','MALA',
						'150510','LIMA','CAÑETE','NUEVO IMPERIAL',
						'150511','LIMA','CAÑETE','PACARAN',
						'150512','LIMA','CAÑETE','QUILMANA',
						'150513','LIMA','CAÑETE','SAN ANTONIO',
						'150514','LIMA','CAÑETE','SAN LUIS',
						'150515','LIMA','CAñETE','SANTA CRUZ DE FLORES',
						'150516','LIMA','CAÑETE','ZUÑIGA';



CREATE PROCEDURE spInertCliente
(
		@IDCLI int ,
        @NOMCLI varchar(50),
		@APECLI varchar(50),
		@DNICLI char(8),
        @EMACLI varchar(50),      
        @CELCLI char(9),
        @DOMCLI varchar(50),       
        @CODUBI char(6),
		@ESTCLI char(1)
  )
 AS 
 BEGIN 
SET NOCOUNT ON 
INSERT INTO  CLIENTE 
(IDCLI, NOMCLI, APECLI, DNICLI, EMACLI,  CELCLI, DOMCLI, CODUBI, ESTCLI)
VALUES 
(@IDCLI ,@NOMCLI ,@APECLI ,@DNICLI, @EMACLI ,@CELCLI, @DOMCLI, @CODUBI, @ESTCLI)
SELECT * FROM CLIENTE
WHERE IDCLI = @IDCLI
END 
GO 

EXEC dbo.spInertCliente 'Juan','Fajardo','74140385','juan@gmail.com','974862456','2 de mayo','150504','A',
						'Angie Daniela','Alcala Peralta', '75412634','angie@gmail.com', '912364828', 'La Mar', '150506', 'A',
						'Eduardo Dani','De la Cruz Ruiz', '15266739', 'eduardo@gmail.com', '924856021', 'Malvinas', '150506', 'A',
						'Juan Cesar','Sanches Flores', '15738410', 'juan@hotmail.com', '912485039', '28 de Julio', '150506', 'A',
						'Luis Hipólito','Del Pozo Fernandez', '71284509', 'luis@gmail.com', '923567489', 'Sucre', '150506', 'A',
						'Pedro Ezequiel','Ordoñez Huaman', '16473980', 'pedro@hotmail.com', '915473976', '13 de Noviembre', '150506', 'A',
						'Esmeralda Cristina','Canales Rojas', '71849029', 'esmeralda@hotmail.com', '926489187', 'Santa Rosa', '150501', 'A',
						'Cecilio Alfredo','Sanchez Lara', '72839024', 'cecilio@gmail.com', '934827156', 'Las Palmas', '150501', 'A',
						'Mario Rodrigo','Sanchez Alcala', '14780924', 'mario@gmail.com', '932984563', '2 de Mayo', '150506', 'A',
						'Cristhian Jeremías','Suarez Frances', '15238976', 'cristhian@hotmail.com', '965374821', 'Caltopilla', '150507', 'A',
						'Gustavo Sebastian','Ormeño Perales', '71256309', 'gustavo@gmail.com', '952839812', 'Ramos Larrea', '150506', 'A',
						'Jose Ignacio','Rivas Escrivá', '71839273', 'jose.ignacio@hotmail.com', '923519649', '13 de Noviembre', '150506', 'A',
						'Valerio Farré','Santiago Garcia', '72735095', 'valerio.santiago@hotmail.com', '913354032', 'Santa Rosa', '150501', 'A',
						'Teodoro Joan','Velázquez Caballero', '72585674', 'teodoro.velazquez@gmail.com', '914739273', 'Las Palmas', '150501', 'A',
						'Porfirio Colomer','León Rivero', '15728342', 'porfirio.leon@gmail.com', '942761039', '2 de Mayo', '150506', 'A',
						'Jordana Tania','Blasco Sotelo', '71382462', 'jordana.blasco@hotmail.com', '921056093', 'Caltopilla', '150507', 'A',
						'Soledad Celia','Casanova Gaya', '72639451', 'soledad.casanova@gmail.com', '972961350', 'Ramos Larrea', '150506', 'A';


CREATE PROCEDURE spInertUsuario
(
		@IDUSU int ,
        @NOMUSU varchar(50),
		@APEUSU varchar(50),
		@DNIUSU char(8),
        @EMAUSU varchar(50),      
        @CELUSU char(9),
        @DOMUSU varchar(50),       
        @CODUBI char(6),
		@ESTUSU char(1)
  )
 AS 
 BEGIN 
SET NOCOUNT ON 
INSERT INTO  USUARIO
(IDUSU, NOMUSU, APEUSU, DNIUSU, EMAUSU,  CELUSU, DOMUSU, CODUBI, ESTUSU)
VALUES 
(@IDUSU, @NOMUSU, @APEUSU, @DNIUSU, @EMAUSU,  @CELUSU, @DOMUSU, @CODUBI, @ESTUSU)
SELECT * FROM USUARIO
WHERE IDUSU = @IDUSU
END 
GO 

EXEC dbo.spInertUsuario 'Jhianpol','Ramos','945643535','73423424','jhianpol.ramos@vallegrande.edu.pe','Av Los Laureles','150516','A',
						'Sofia Inés', 'Bolaños Velázquez', '910007822','07750457', 'sofia.bolaños@gmail.com', 'Jiron O Higgins', '150501', 'A',
						'Estefany Elisabeth', 'Romero Villa', '915647761', '25735046', 'estefany.romero@gmail.com', 'Santa Maria Alta', '150509', 'A',
						'Juana Edelmira', 'Galván Pascual', '914575236', '21261108', 'juana.galván@gmail.com', 'Av 28 de Julio', '150506', 'A',
						'Luciano Dafne', 'Rivera Campo', '915908420', '46967914', 'luciano.rivera@gmail.com', 'Av 2 de Mayo', '150506', 'A',
						'Angela Estrella', 'Rodriguez León', '921367745', '07756532', 'angela.rodriguez@gmail.com', 'Cerro Libre', '150509', 'A',
						'Rocio Jessica', 'Sotelo Lara', '916156023', '45055499', 'rocio.sotelo@gmail.com', 'Av Ayacucho', '150506', 'A',
						'Felix Renato', 'Cruz Santiago', '913881305', '06224656', 'felix.cruz@gmail.com', 'Cementerio', '150506', 'A',
						'Paul Donato', 'Ramos Roda', '925829547', '15448909', 'paul.ramos@gmail.com', 'Caltopilla', '150507', 'A',
						'Emma Rosario', 'Redondo Abascal', '923547549', '16458936', 'emma.redondo@gmail.com', 'Urb. Mariscal Ramon Castilla', '150513', 'A',
						'Bruno Julio', 'Robles Saavedra', '917420895', '12569827', 'bruno.robles@gmail.com', 'Jirón Junín', '150511', 'A',
						'Reina Manuela', 'Cáceres Tejada', '927672301', '12673920', 'reina.caceres@gmail.com', 'Carmen Alto', '150509', 'A',
						'Valerio Isaías', 'Ojeda Cárdenas', '910588801', '22378967', 'valerio.ojeda@gmail.com', 'Nuevo imperial', '150509', 'A',
						'Jenaro Bautista', 'Acosta Menendez', '923883907', '45678017', 'jenaro.acosta@gmail.com', 'C. Miraflores', '150501', 'A',
						'Cristhian Paco', 'Gámez Rodriguez', '922156180', '76890278', 'cristhian.gamez@gmail.com', 'Los Leones', '150503', 'A',
						'Agustín Manu', 'Carrillo Navarro', '921451567', '12799398', 'agustin.carrillo@gmail.com', 'Av. Arequipa', '150511', 'A',
						'Jimena Ale', 'Delgado Montoya', '910244145', '13578997', 'jimena.delgado@gmail.com', 'Asunción 8', '150506', 'I',
						'Maxi Victor', 'Torres Quintanilla', '910411462', '79628176', 'maxi.torres@gmail.com', 'Calles los claveles', '150503', 'A',
						'Pedro Max', 'Quispe Avalos', '921381283', '76223469', 'pedro.quispe@gmail.com', 'Calle las Violetas', '150503', 'A',
						'Xiomara Valentina', 'Brañes Torres', '912383824', '15467831', 'xiomara.brañes@vallegrande.edu.pe', 'Santa Maria Alta', '150509', 'A';








						--Procedure Pizza


CREATE PROCEDURE spInertPizza
(
		@IDPIZ int ,
        @NOMPIZ varchar(60),
		@TAMPIZ varchar(30),
		@PREPIZ decimal(5,2)     
  )
 AS 
 BEGIN 
SET NOCOUNT ON 
INSERT INTO  PIZZA
(NOMPIZ,TAMPIZ,PREPIZ)
VALUES 
(@NOMPIZ,@TAMPIZ,@PREPIZ)
SELECT * FROM PIZZA
WHERE IDPIZ = @IDPIZ
END 
GO 

EXEC dbo.spInertPizza
'Americana','Familiar','60','A',
'Americana','Mediana','40','A',
'Americana','Personal','22','A',
'Huawaiana','Familiar','60','A',
'Huawaiana','Mediana','40','A',
'Huawaiana','Personal','22','A',
'Cabanosi','Familiar','60','A',
'Cabanosi','Mediana','40','A',
'Cabanosi','Personal','22','A',
'Vegetariana','Familiar','60','A',
'Vegetariana','Mediana','40','A',
'Vegetariana','Personal','22','A',
'Salami','Familiar','60','A',
'Salami','Mediana','40','A',
'Salami','Personal','22','A';

--Procedure VENTA

CREATE PROCEDURE spInertVenta
(
  @IDVEN int,
  @FECVEN date,
  @IDUSU int,
  @IDCLI int,
  @TOTVEN decimal(5,2)
  )
 AS 
 BEGIN 
SET NOCOUNT ON 
INSERT INTO  VENTA
(FECVEN,IDUSU, IDCLI,TOTVEN)
VALUES 
(@FECVEN,@IDUSU, @IDCLI,@TOTVEN)
SELECT * FROM VENTA
WHERE IDVEN = @IDVEN
END 
GO 

EXEC dbo.spInertVenta
'2022-06-12','1','6','100.00',
'2022-06-12','1','4','100.00',
'2022-06-12','2','2','100.00',
'2022-06-12','2','5','100.00',
'2022-06-12','2','4','100.00',
'2022-06-12','1','3','100.00',
'2022-06-12','1','9','100.00',
'2022-06-12','2','1','100.00',
'2022-06-12','1','10','100.00',
'2022-06-12','2','11','100.00';



CREATE PROCEDURE spInertVentaDetalle
(
  @IDVENDET int,
  @CANVENDET int,
  @SUBVENDET decimal(5,2),
  @IDPIZ int,
  @IDVEN int
  )
 AS 
 BEGIN 
SET NOCOUNT ON 
INSERT INTO  VENTA_DETALLE
(CANVENDET,SUBVENDET,IDPIZ,IDVEN)
VALUES 
(@CANVENDET,@SUBVENDET,@IDPIZ,@IDVEN)
SELECT * FROM VENTA_DETALLE
WHERE IDVENDET = @IDVENDET
END 
GO 

EXEC dbo.spInertVentaDetalle
'5','110.00','3','1',
'3','66.00','3','1',
'1','22.00','3','1',
'1','22.00','6','2',
'2','44.00','6','2',
'3','66.00','6','1',
'1','40.00','2','2',
'1','40.00','2','1',
'2','120.00','1','2',
'1','60.00','1','1';




--vista para vendedor
CREATE VIEW vVendedor
AS
SELECT
     V.IDUSU AS IDUSU, 
     V.NOMUSU AS NOMUSU,
     V.APEUSU AS APEUSU,
	V.CELUSU AS CELUSU,
	 V.DNIUSU AS DNIUSU,
	 V.EMAUSU AS EMAUSU, 
     V.DOMUSU AS DOMUSU,
	 V.CODUBI AS CODUBI,
     U.DISUBI AS DISUBI,
	 U.PROUBI AS PROUBI,
	 U.DEPUBI AS DEPUBI,
	 V.ESTUSU AS ESTUSU,
	 ROW_NUMBER() OVER (ORDER BY V.IDUSU DESC) AS ORDEN
FROM 
     USUARIO V
 INNER JOIN UBIGEO U ON
	 V.CODUBI = U.CODUBI
	 GO


--vista para Cliente
CREATE VIEW vCliente
AS
SELECT
     C.IDCLI AS IDCLI, 
     C.NOMCLI AS NOMCLI,
     C.APECLI AS APECLI,
	 C.DNICLI AS DNICLI,
	 C.EMACLI AS EMACLI, 
     C.CELCLI AS CELCLI,
	 C.DOMCLI AS DOMCLI,
     U.CODUBI AS CODUBI,
	 U.DISUBI AS DISUBI, 
	 U.PROUBI AS PROUBI,
	 U.DEPUBI AS DEPUBI,
	 C.ESTCLI AS ESTCLI,
	 ROW_NUMBER() OVER (ORDER BY C.IDCLI DESC) AS ORDEN
FROM 
     CLIENTE C
 INNER JOIN UBIGEO U ON
	 C.CODUBI = U.CODUBI
GO


-- Reporte
CREATE VIEW vClient
AS
SELECT
	C.IDCLI AS IDCLI,
	UPPER(CONCAT(C.APECLI,' ',C.NOMCLI)) AS NOMCLI,
    C.DNICLI AS DNICLI,
	C.EMACLI AS EMACLI,
	C.DOMCLI AS DOMCLI,
    U.CODUBI AS CODUBI,
    U.DEPUBI AS DEPUBI,
	U.DISUBI AS DISUBI,
	U.PROUBI AS PROUBI
FROM CLIENTE AS C
    INNER JOIN UBIGEO AS U
    ON C.CODUBI = C.CODUBI
GO

CREATE VIEW vVenta
AS
SELECT
    V.IDVEN AS IDVEN,
    C.DNICLI AS DNICLI,
-- Vista usada en Venta
	C.IDCLI AS IDCLI,
	UPPER(CONCAT(C.APECLI,' ',C.NOMCLI)) AS NOMCLI,
    V.FECVEN AS FECVEN,
    V.TOTVEN AS TOTVEN
FROM VENTA AS V
    INNER JOIN CLIENTE AS C
	ON V.IDCLI = C.IDCLI
GO


--vista para prenda
CREATE VIEW vPizza
AS
SELECT 
	P.IDPIZ AS IDPIZ,
	P.NOMPIZ AS NOMPIZ,
	P.TAMPIZ AS TAMPIZ,
	P.PREPIZ AS PREPIZ,
	P.ESTPIZ AS ESTPIZ,
	ROW_NUMBER() OVER (ORDER BY P.IDPIZ DESC) AS ORDEN
FROM PIZZA AS P 
INNER JOIN PIZZA AS C
	ON P.IDPIZ = C.IDPIZ
GO 
 -- Vista reporte de venta
CREATE VIEW vReporte
AS
SELECT 
	V.IDVEN,
	V.FECVEN,
	(UPPER(C.APECLI) + ', ' + C.NOMCLI) AS CLIENTE,
	C.DNICLI AS DNI_CLIENTE,
	(U.DISUBI + ' | ' + U.PROUBI + ' | ' + U.DEPUBI) AS UBICACION,
	VD.CANVENDET,
	P.NOMPIZ AS PIZZA,
	P.PREPIZ AS PRECIO,
	(P.PREPIZ * VD.CANVENDET) AS SUBTOTAL
FROM VENTA AS V INNER JOIN CLIENTE AS C
	ON V.IDCLI = C.IDCLI
	INNER JOIN UBIGEO AS U
	ON C.CODUBI = U.CODUBI
	INNER JOIN VENTA_DETALLE AS VD
	ON V.IDVEN = VD.IDVEN
	INNER JOIN PIZZA AS P
	ON VD.IDPIZ = P.IDPIZ
GO






