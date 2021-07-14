package com.inditex.pricing.service.impl;

import com.inditex.pricing.dto.request.PriceFilterDto;
import com.inditex.pricing.entity.Brand;
import com.inditex.pricing.entity.Price;
import com.inditex.pricing.repository.PricingRepository;
import com.inditex.pricing.service.PricingService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

/**
 * Servicio de la lógica de negocio para manejar los precios.
 * 
 * @author Antonio Troyano
 *
 */
@Slf4j
@Service
public class PricingServiceImpl implements PricingService {

	private final PricingRepository priceRepository;

	/**
	 * Constructor que recibe como parámetro el repositorio de base de datos de los
	 * precios.
	 * 
	 * @param priceRepository El repositorio de gestión de precios.
	 */
	public PricingServiceImpl(final PricingRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Price findByProductIdAndBrandIdAndDate(PriceFilterDto priceFilterDto) {
		log.debug("Recovering price ...");

		Brand brand = Brand.builder().id(priceFilterDto.getBrandId()).build();
		List<Price> prices = priceRepository
				.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(brand,
						priceFilterDto.getProductId(), priceFilterDto.getApplicationDate(),
						priceFilterDto.getApplicationDate());

		if (prices.isEmpty()) {
			throw new NoSuchElementException("Price not found");
		}

		return prices.get(0);

	}

}
