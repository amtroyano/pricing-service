package com.inditex.pricepoc.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
	
	@Schema(title = "Fecha inicio de aplicacion")
	private Timestamp startDate;
	
	@Schema(title = "Fecha fin de aplicacion")
	private Timestamp endDate;

	@Schema(title = "Precio final de venta", example = "30.50")
	private BigDecimal price;
		
	@Schema(title = "Tarifa a aplicar", example = "1")
	private int priceList;
	

}
