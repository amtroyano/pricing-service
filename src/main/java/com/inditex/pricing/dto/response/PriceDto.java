package com.inditex.pricing.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

import org.javamoney.moneta.Money;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Objeto que representa un precio.
 *
 * @author Antonio Troyano
 */
@Schema(name = "PriceDto", description = "Objeto precio.")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDto {

	@Parameter(description = "Identificador de la marca", example = "1")
	private Integer brandId;

	@Parameter(description = "Identificador del producto", example = "12345")
	private Integer productId;

	@Parameter(description = "Fecha a partir de la cual el precio es válido.", example = "2000-01-01 23:59:000")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime startDate;

	@Parameter(description = "Fecha a partir de la cual el precio no es aplicable.", example = "2000-01-01 00:00:000")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime endDate;

	@Parameter(description = "Precio", example = "\"price\":{\n\t\"amount\": 34.45,\t\"currency\":\"EUR\"\n}")
	@JsonProperty(value = "price")
	private Money money;

	@Parameter(description = "Lista de precios a la cual pertenece el precio final", example = "1")
	private int priceList;

}
