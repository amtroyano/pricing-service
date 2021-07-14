package com.inditex.pricing.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Objeto que representa un filtro de datos de entrada para recuperar precios.
 * 
 * @author Antonio Troyano
 *
 */
@Schema(name = "PriceFilterDto", description = "Filtro de entrada para recuperar precios",
    example = "{\n\t\"applicationDate\": \"2020-06-14T16:00:00.000\",\n\t\"productId\": 35455,\r\t\"brandId\": 1\r}")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceFilterDto {

  @Parameter(description = "Fecha de aplicación para recuperar el precio.", required = true,
      example = "2021-07-10T15:32:08.758")
  @NotNull
  @DateTimeFormat(iso = ISO.DATE_TIME)
  private LocalDateTime applicationDate;

  @Parameter(description = "Identificador del producto para recuperar el precio", required = true,
      example = "35455")
  @Min(value = 30000)
  @Max(value = 40000)
  @NotNull
  private Integer productId;

  @Parameter(description = "Identificador de la marca a recuerar el precio", required = true,
      example = "1")
  @Min(value = 1)
  @Max(value = 4)
  @NotNull
  private Integer brandId;
}
