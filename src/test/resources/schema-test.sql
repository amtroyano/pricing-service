CREATE TABLE `BRAND` (
  `BRAND_ID` INT NOT NULL COMMENT 'Identificador',
  `BRAND_DESCRIPTION` VARCHAR(45) NOT NULL COMMENT 'Cadena del grupo',
  PRIMARY KEY (`BRAND_ID`));

 
  
CREATE TABLE `PRICE` (
  `ID` int(4)  NOT NULL COMMENT 'Identificador',
  `BRAND_ID` int NOT NULL COMMENT 'foreign key de la cadena del grupo',
  `PRODUCT_ID` int(10)  NOT NULL COMMENT 'Identificador c�digo de producto',
  `PRIORITY` tinyint NOT NULL COMMENT 'Desambiguador de aplicaci�n de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numerico).',
  `START_DATE` datetime NOT NULL COMMENT 'Rango de fechas en el que aplica el precio tarifa indicado.',
  `END_DATE` datetime NOT NULL COMMENT 'Rango de fechas en el que aplica el precio tarifa indicado.',
  `PRICE` decimal(4,2) NOT NULL COMMENT 'Precio final de venta',
  `CURR` varchar(10)  NOT NULL DEFAULT 'EUR' COMMENT 'ISO de la moneda.',
  `PRICE_LIST` int NOT NULL COMMENT 'Identificador de la tarifa de precios aplicable',
  PRIMARY KEY (`ID`),
  FOREIGN KEY (BRAND_ID) REFERENCES BRAND(BRAND_ID)
);


CREATE SEQUENCE seq_PRICE_ID START WITH 1 INCREMENT BY 1 MAXVALUE 9999 CYCLE;