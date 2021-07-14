package com.inditex.pricing.convert;

import com.fasterxml.jackson.databind.JavaType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import java.util.Iterator;
import javax.money.MonetaryAmount;

/**
 * Clase de conversión de propiedades de JavaMoney para corregir el error en OpenApi. OpenApi
 * interpreta amount como un long, en lugar de un number.
 * 
 * @author Antonio Toryano
 *
 */
public class MonetaryAmountConverter implements ModelConverter {

  /**
   * Método que sobreescribe los tipos de las propiedades de MonetaryAmount.
   */
  @SuppressWarnings("rawtypes")
  @Override
  public Schema resolve(AnnotatedType annotatedType, ModelConverterContext context,
      Iterator<ModelConverter> chain) {

    if (annotatedType.isSchemaProperty()) {
      JavaType type = Json.mapper().constructType(annotatedType.getType());

      if (type != null) {
        Class<?> cls = type.getRawClass();
        if (MonetaryAmount.class.isAssignableFrom(cls)) {

          return new ObjectSchema().addProperties("amount", new NumberSchema())
              .addProperties("currency", new StringSchema());
        }
      }
    }

    return (chain.hasNext()) ? chain.next().resolve(annotatedType, context, chain) : null;
  }

}
