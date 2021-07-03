package com.inditex.pricepoc.service;

import java.sql.Timestamp;
import java.util.Optional;

import com.inditex.pricepoc.entity.Price;

/**
 * Price Service
 * @author Debora RT
 *
 */
public interface PriceService {
	
	/**
	 * Price 
	 * @param productId 
	 * @param brandId
	 * @param fecha
	 * @return
	 */
	Optional<Price> findByProductIdAndBrandIdAndFecha(int productId, int brandId, Timestamp fecha);

}
