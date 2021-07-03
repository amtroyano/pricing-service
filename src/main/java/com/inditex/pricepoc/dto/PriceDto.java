package com.inditex.pricepoc.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Price response object
 *
 * @author Debora RT
 */
@Schema(name = "PriceDto", description = "Price reponse object ")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties (ignoreUnknown = true)
public class PriceDto {
	
	
	@Schema(title = "Brand identifier", example = "1")
	private Long brandId;

	@Schema(title = "Product identifier", example = "12345")
	private Long productId;
	
	@Schema(title = "Price application start date", example = "2000-01-01 23:59:000")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="Europe/Madrid")
	private Timestamp startDate;
	
	@Schema(title = "Price application end date", example = "2000-01-01 00:00:000")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="Europe/Madrid")
	private Timestamp endDate;

	@Schema(title = "Final sale price", example = "30.50")
	private BigDecimal price;
		
	@Schema(title = "Price rate", example = "1")
	private int priceList;
	

}
