package com.inditex.pricing.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Inditex Brand entity
 *
 * @author Debora RT
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "BRAND")
public class Brand implements Serializable {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 8531841145109144969L;

	@Id
	@Column(nullable = false, length = 10)
	private Integer id;

	@Size(min = 1, max = 256)
	@Column(nullable = false, length = 256)
	private String description;

}