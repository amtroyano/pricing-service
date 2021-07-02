package com.inditex.pricepoc.service.impl;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.inditex.pricepoc.entity.Price;
import com.inditex.pricepoc.repository.PriceRepository;
import com.inditex.pricepoc.service.PriceService;

/**
 * Price service implementation
 * 
 * @author Debora RT
 *
 */
@Service
public class PriceServiceImpl implements PriceService {

	private final PriceRepository priceRepository;

	public PriceServiceImpl(final PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Override
	public Price findByProductIdAndBrandIdAndFecha(Long productId, int brandId, Timestamp fecha) {

		var list = priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
				productId, brandId, fecha, fecha);
		return list.get(0);
	}

}
