package com.inditex.pricepoc.service;

import java.sql.Timestamp;
import java.util.List;

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
	  Price findByProductIdAndBrandIdAndFecha(Long productId, int brandId, Timestamp fecha);

}
