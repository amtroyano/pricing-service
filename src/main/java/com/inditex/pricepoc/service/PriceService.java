package com.inditex.pricepoc.service;

import java.util.List;

import com.inditex.pricepoc.entity.Price;

/**
 * Price Service
 * @author Debora RT
 *
 */
public interface PriceService {
	
	/**
	 * List of all price
	 * @return List of Price
	 */
	  List<Price> getAll();

}
