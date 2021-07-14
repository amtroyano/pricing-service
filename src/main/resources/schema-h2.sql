-- Tabla BRAND
CREATE TABLE `BRAND` (
  `ID` INT NOT NULL,
  `DESCRIPTION` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`));


-- Tabla PRICE
CREATE TABLE `PRICE` (
  `ID` int NOT NULL,
  `BRAND_ID` int NOT NULL,
  `START_DATE` datetime NOT NULL,
  `END_DATE` datetime NOT NULL,
  `PRICE_LIST` int NOT NULL,
  `PRODUCT_ID` int NOT NULL,
  `PRIORITY` tinyint NOT NULL,
  `PRICE` decimal(4,2) NOT NULL,
  `CURR` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (BRAND_ID) REFERENCES BRAND(ID),
  INDEX (`BRAND_ID`, `START_DATE`, `END_DATE`, `PRODUCT_ID`)
);

CREATE SEQUENCE seq_PRICE_ID START WITH 1 INCREMENT BY 1 MAXVALUE 9999 CYCLE;
