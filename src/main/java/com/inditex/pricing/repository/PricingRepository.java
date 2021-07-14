package com.inditex.pricing.repository;

import com.inditex.pricing.entity.Brand;
import com.inditex.pricing.entity.Price;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que expone los métodos para recuperar precios.
 * 
 * @author Antonio Troyano
 *
 */
@Repository
public interface PricingRepository extends JpaRepository<Price, Integer> {

	/**
	 * Consulta para recuperar los precios a partir de un filtro de datos como
	 * entrada. Los precios son recuperados a partir del identificador del producto,
	 * del identificador de la marca y de una fecha de aplicación. Los precios son
	 * ordenados por prioridad.
	 * 
	 * @param brand     Marca {@link Brand}
	 * @param productId Identificador del producto
	 * @param starDate  Fecha de comienzo de la aplicación
	 * @param endDate   Fecha final de la apliación
	 * 
	 * @return El listado de precios filtrados {@link Price}.
	 */
	public List<Price> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(Brand brand,
			Integer productId, LocalDateTime starDate, LocalDateTime endDate);

}
