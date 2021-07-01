package com.inditex.pricepoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inditex.pricepoc.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

}
