package com.inditex.pricepoc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Inditex Price entity
 *
 * @author Debora RT
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "PRICE")
public class Price implements Serializable {

	/**
	* Default serial version
	*/
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column (name = "ID", nullable = false, precision = 4)
	private Long id;
	
	@Column (name = "BRAND_ID", nullable = false, precision = 1)
	private int brandId;
	
	@Column (name = "PRODUCT_ID", nullable = false, precision = 10)
	private int productId;
	
	@Column (name = "PRIORITY", nullable = false)
	private int priority;
	
	@Column (name = "START_DATE", nullable = false)
	private Timestamp startDate;
	
	@Column (name = "END_DATE", nullable = false)
	private Timestamp endDate;
	
	@Column (name = "PRICE", nullable = false, precision = 6, scale = 2)
	private BigDecimal price;
	
	@Column (name = "CURR", nullable = false, length = 10)
	private String curr;
	
	@Column (name = "PRICE_LIST", nullable = false)
	private int priceList;
	
}