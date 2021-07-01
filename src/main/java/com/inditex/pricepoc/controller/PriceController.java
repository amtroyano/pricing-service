package com.inditex.pricepoc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.pricepoc.dto.PriceDto;
import com.inditex.pricepoc.entity.Price;
import com.inditex.pricepoc.service.PriceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


/**
 * Controlador que expone las operaciones API Rest
 * @author Debora RT
 *
 */

@RestController
public class PriceController {
	
	private final PriceService priceService;
	
	public PriceController(final PriceService priceService) {
		this.priceService = priceService;
	}
	
	/**
	   * Método que recupera un listado de Prices.
	   *
	   * @return Listado de objetos de tipo {@link Price}
	   */
	  @Operation(summary = "Recovery a price values list", description = "Recovery a price values list")
	  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Values recovered."),
			  @ApiResponse(responseCode = "404", description = "No values recovered."),
			  @ApiResponse(responseCode = "500", description = "Internal Server.")})
	  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<List<PriceDto>> getValues() {

	    List<Price> values = priceService.getAll();

	    return new ResponseEntity<>(
	        new ObjectMapper().convertValue(values, new TypeReference<List<PriceDto>>() {}),
	        values.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	  }

}
