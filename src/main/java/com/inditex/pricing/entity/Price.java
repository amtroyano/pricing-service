package com.inditex.pricing.entity;

import com.inditex.pricing.enums.PriorityEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.javamoney.moneta.Money;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entidad Price. Representa el precio final pvp.
 *
 * @author Antonio Troyano
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "PRICE")
public class Price implements Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -3806793342223009345L;

	@Id
	private Integer id;

	@NotNull
	@Digits(integer = 2, fraction = 0)
	@Column(nullable = false)
	private Integer productId;

	@NotNull
	@Digits(integer = 2, fraction = 0)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRAND_ID", referencedColumnName = "ID", nullable = false)
	private Brand brandId;

	@NotNull
	@Digits(integer = 2, fraction = 0)
	@Column(nullable = false)
	private Integer priceList;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false, length = 1)
	private PriorityEnum priority;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(nullable = false)
	private LocalDateTime startDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(nullable = false)
	private LocalDateTime endDate;

	@Type(type = "com.inditex.pricing.convert.MoneyType")
	@Columns(columns = { @Column(name = "price"), @Column(name = "curr") })
	private Money money;

}