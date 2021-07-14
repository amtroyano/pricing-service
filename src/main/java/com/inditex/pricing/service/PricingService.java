package com.inditex.pricing.service;

import com.inditex.pricing.dto.request.PriceFilterDto;
import com.inditex.pricing.entity.Price;

/**
 * Interfaz que expone los métodos para realizar la lógica de negocio sobre los
 * precios.
 * 
 * @author Antonio Troyano
 *
 */
public interface PricingService {

	/**
	 * Método que recupera un precio a partir de una serie de parámetros de entrada.
	 * 
	 * @param priceFilterDto Objeto quw representa el filtro por el cual recuperar
	 *                       precios {@link PriceFilterDto}
	 * 
	 * @return {@link Price} El precio recuperado.
	 */
	Price findByProductIdAndBrandIdAndDate(PriceFilterDto priceFilterDto);

}
