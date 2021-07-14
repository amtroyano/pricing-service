package com.inditex.pricing.controller;

import com.inditex.pricing.dto.request.PriceFilterDto;
import com.inditex.pricing.dto.response.PriceDto;
import com.inditex.pricing.service.PricingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API Controlador de precios.
 * 
 * @author Antonio Troyano
 *
 */

@Tag(name = "API controlador de precios")
@RestController
@RequestMapping(path = "/price")
@Validated
public class PricingController {

  private final PricingService pricingService;
  private final ModelMapper modelMapper;

  /**
   * Construcotr de la clase. Recibe como parámetro el servicio que gestiona los precios.
   * 
   * @param pricingService El servicio que contiene la lógica de negocio sobre precios.
   */
  public PricingController(final PricingService pricingService) {
    this.pricingService = pricingService;
    this.modelMapper = new ModelMapper();
  }

  /**
   * Endpoint que recupera un precio a partir de unos valores de entrada.
   * 
   * @param priceFilterDto Filtro para recuperar el precio {@link PriceFilterDto}
   * 
   * @return {@link PriceDto} El precio.
   */
  @Operation(summary = "Recupera un precio mediante un filtro.",
      description = "Recupera un precio a partir de una fecha, del identificador del producto y del identificador de la marca")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Listado de precios")})
  @GetMapping
  public ResponseEntity<PriceDto> getPrices(@Valid PriceFilterDto priceFilterDto) {

    return new ResponseEntity<>(modelMapper
        .map(pricingService.findByProductIdAndBrandIdAndDate(priceFilterDto), PriceDto.class),
        HttpStatus.OK);
  }

}
