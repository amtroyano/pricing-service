package com.inditex.pricing.config;

import com.fasterxml.jackson.databind.Module;
import com.inditex.pricing.convert.MonetaryAmountConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.jackson.datatype.money.MoneyModule;

/**
 * Clase de configuracion para el precio. Sigue con la recomendación the JSR-354.
 * 
 * @author Antonio Troyano
 *
 */
@Configuration
public class MoneyConfiguration {

  /**
   * Bean la serialización / Deserialización de la clase Money en amount y currency.
   * 
   * @return {@link Module} El módulo a usar para la conversión.
   */
  @Bean
  public Module moneyModule() {
    return new MoneyModule();
  }

  /**
   * Bean para la conversión de la propiedad amount de JavaMoney correctamente en OpenApi
   * 
   * @return {@link MonetaryAmountConverter}
   */
  @Bean
  public MonetaryAmountConverter amountPropertyConverter() {
    return new MonetaryAmountConverter();
  }

}
