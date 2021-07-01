package com.inditex.pricepoc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inditex.pricepoc.entity.Price;
import com.inditex.pricepoc.repository.PriceRepository;
import com.inditex.pricepoc.service.PriceService;


/**
 * Price service implementation
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
	public List<Price> getAll() {
		return priceRepository.findAll();
	}

}
