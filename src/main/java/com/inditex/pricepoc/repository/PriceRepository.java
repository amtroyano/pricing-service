package com.inditex.pricepoc.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inditex.pricepoc.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

	public List<Price> findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(int productId,
			int brandId, Timestamp fechaBefore, Timestamp fechaAfter);

}
