package com.inditex.pricepoc.service;

import java.sql.Timestamp;
import java.util.Optional;

import com.inditex.pricepoc.entity.Price;

/**
 * Price Service
 * 
 * @author Debora RT
 *
 */
public interface PriceService {
	
	/**
	 * Find price by product, brand and date
	 * @param productId Product identifier
	 * @param brandId Brand identifier
	 * @param applicationDate Application date
	 * @return {@link Price}
	 */
	Optional<Price> findByProductIdAndBrandIdAndDate(int productId, int brandId, Timestamp applicationDate);

}
