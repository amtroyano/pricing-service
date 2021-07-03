package com.inditex.pricepoc.controller;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.pricepoc.dto.PriceDto;
import com.inditex.pricepoc.entity.Price;
import com.inditex.pricepoc.service.PriceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * API Rest Price Controller
 * 
 * @author Debora RT
 *
 */

@Tag(name = "API Price Controller")
@RestController
@RequestMapping(path = "price")
@Validated
@ApiResponses(value = {
		@ApiResponse(responseCode = "404", description = "Not Found.", content = {
				@Content(schema = @Schema(hidden = true)) }),
		@ApiResponse(responseCode = "422", description = "Unprocessable parameter.", content = {
				@Content(schema = @Schema(hidden = true)) }),
		@ApiResponse(responseCode = "500", description = "Internal Server Error.", content = {
				@Content(schema = @Schema(hidden = true)) }) })
public class PriceController {

	private final PriceService priceService;

	public PriceController(final PriceService priceService) {
		this.priceService = priceService;
	}

	/**
	 * Recovery a price values list by filter
	 * 
	 * @param applicationDate Application price date
	 * @param productId Product identifier
	 * @param brandId Brand identifier
	 * @return List of {@link PriceDto}
	 */
	@Operation(summary = "Recovery a price values list by filter", description = "Recovery a price values list")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Values recovered.") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PriceDto> getValues(
			@Parameter(description = "Application price date") @RequestParam(required = true) Timestamp applicationDate,
			@Parameter(description = "Product identifier") @RequestParam(required = true) int productId,
			@Parameter(description = "Brand identifier") @RequestParam(required = true) int brandId) {

		Optional<Price> value = priceService.findByProductIdAndBrandIdAndDate(productId, brandId, applicationDate);

		return value.isPresent() ? new ResponseEntity<>(new ObjectMapper().convertValue(value.get(), new TypeReference<PriceDto>() {
		}), HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

}
