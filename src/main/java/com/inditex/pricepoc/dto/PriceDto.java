package com.inditex.pricepoc.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

import org.hibernate.type.TimeZoneType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Objeto de salida tipo Price.
 *
 * @author Debora RT
 */
@Schema(name = "PriceDto", description = "Price output object ")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties (ignoreUnknown = true)
public class PriceDto {
	
	
	@Schema(title = "Identificador de cadena", example = "1")
	private Long brandId;

	@Schema(title = "Identificador de producto", example = "12345")
	private Long productId;
	
	@Schema(title = "Fecha inicio de aplicacion", example = "2000-01-01 23:59:000")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="Europe/Madrid")
	private Timestamp startDate;
	
	@Schema(title = "Fecha fin de aplicacion", example = "2000-01-01 00:00:000")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="Europe/Madrid")
	private Timestamp endDate;

	@Schema(title = "Precio final de venta", example = "30.50")
	private BigDecimal price;
		
	@Schema(title = "Tarifa a aplicar", example = "1")
	private int priceList;
	

}
