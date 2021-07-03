package com.inditex.pricepoc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Inditex Brand entity
 *
 * @author Debora RT
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BRAND")
public class Brand implements Serializable {

	/**
	* Default serial version
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BRAND_ID", nullable = false, length = 10)
	private Long brandId;

	@Column(name = "BRAND_DESCRIPTION", nullable = true, length = 2000)
	private String brandDescription;

}