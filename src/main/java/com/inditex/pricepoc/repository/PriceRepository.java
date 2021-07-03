package com.inditex.pricepoc.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inditex.pricepoc.entity.Price;

/**
 * Price Repository
 * @author Debora RT
 *
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

	/**
	 * BBDD consult by filter and order by priority
	 * @param productId Product identifier
	 * @param brandId Brand identifier
	 * @param starDate Star Date
	 * @param endDate End date
	 * @return List of {@link Price}}
	 */
	public List<Price> findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(int productId,
			int brandId, Timestamp starDate, Timestamp endDate);

}
